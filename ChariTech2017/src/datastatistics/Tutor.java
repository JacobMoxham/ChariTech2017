package datastatistics;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.DateFormatter;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import dataHandling.*;

public class Tutor {
	
	private Integer centre_id;
	private LocalDate date = LocalDate.now();
	private Set<Learner> learnerSet;

	Tutor (Integer id) throws FileNotFoundException
	{
		centre_id = id;
		Date date = new Date();
		learnerSet = DataInput.getCentreMap().get(centre_id).getLearnerSet();
		//System.out.println("size = " + learnerSet.size());
		//System.out.println(learnerSet);
		
	}	
	
	//last month
	Double getBusinessData (Statistic name) throws FileNotFoundException
	{
		if(name.equals(Statistic.INCOME))
		{
			
			Double totalincome = 0.0;
			for(Transaction tran: DataInput.getTransactionList())
			{
				//System.out.println(tran.getLearnerId());
				if(!learnerSet.contains(tran.getLearner())) continue;
				LocalDate trandate = LocalDate.parse( tran.getTimeStamp().substring(0, 10), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(trandate, date);
				if(days < 30) totalincome += tran.getAmount();	
			}
			return totalincome;
		}
		if(name == Statistic.NEW_CUSTOMERS)
		{
			Double total = 0.0;
			for(Learner lea: learnerSet)
			{
				LocalDate joindate = LocalDate.parse( lea.getDateJoined().substring(0, 10), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(joindate, date);
				if(days < 30) total += 1;
			}
			return total;
		}
		if(name == Statistic.ATTRITION)
		{
			Double total = 0.0;
			for(Learner lea: learnerSet)
			{
				boolean thism = false, lastm = false;
				for(Transaction tran: lea.getTransactionSet())
				{
					if(tran.getAmount() != -1) continue;
					LocalDate trandate = LocalDate.parse( tran.getTimeStamp().substring(0, 10), DateTimeFormatter.ISO_DATE);
					long days = ChronoUnit.DAYS.between(trandate, date);
					if(days > 30 && days < 60) lastm = true;
					if(days < 30) thism = true;
				}
				if(thism == false && lastm == true) total += 1;
			}
			return total;
		}
		if(name == Statistic.CONVERSION_RATE)
		{
			Double total = 0.0;
			for(Learner lea: learnerSet)
			{
				boolean buying = false;
				for(Transaction tran:lea.getTransactionSet())
				{
					if(tran.getAmount() <= 1) continue;
					buying = true;
				}
				if(buying) total += 1;
			}
			return total/learnerSet.size();
		}
		return null;
	}
	
	//last months - index 0 - the most recent month
	List<Double> getAllBusinessData (Statistic name) throws FileNotFoundException
	{
		
		if(name == Statistic.INCOME)
		{
			ArrayList <Double> total = new ArrayList <Double> ();
			for(Transaction tran: DataInput.getTransactionList())
			{
				if(!learnerSet.contains(tran.getLearner())) continue;
				if(tran.getAmount() <= 1) continue;
				LocalDate trandate = LocalDate.parse( tran.getTimeStamp().substring(0, 10), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(trandate, date);
				int num = (int) (days/30);
				while(total.size() <= num) total.add(0.0);
				total.set(num, total.get(num) + tran.getAmount());
			}
			return total;
		}
		if(name == Statistic.NEW_CUSTOMERS)
		{
			ArrayList <Double> total = new ArrayList <Double> ();
			for(Learner lea: learnerSet)
			{
				LocalDate joindate = LocalDate.parse( lea.getDateJoined().substring(0, 10), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(joindate, date);
				int num = (int) (days/30);
				while(total.size() <= num) total.add(0.0);
				total.set(num, total.get(num)+1);
			}
			return total;
		}
		return null;
	}
	
	Double getAdministrativeData (Statistic name) throws FileNotFoundException
	{
		Double return_val;
		
		switch (name) {
			case SESSION_LENGTH:
				// mapping number of days since current date to list of (minimum start time, maximum end time)
				Map<Long, List<LocalTime>> dayStartTimes = new HashMap<>();
				
				for (Experience experience : DataInput.getExperienceList()) {
					// check if experience is at the correct centre by cross-referencing the learner with the centre set
					if (learnerSet.contains(experience.getLearner())) {
						// check if experience is within last month
						LocalDateTime startDateTime = LocalDateTime.parse(experience.getStartTime());
						
						long days = ChronoUnit.DAYS.between(startDateTime, date);
						
						if (days < 30) {
							LocalTime startTime = startDateTime.toLocalTime();
							LocalTime endTime = startTime.plusMinutes(experience.getLatency());
							
							// initialise list
							dayStartTimes.putIfAbsent(days, new ArrayList<LocalTime>());
							
							if (dayStartTimes.get(days).isEmpty()) {
								dayStartTimes.get(days).add(startTime);
								dayStartTimes.get(days).add(endTime);
							} else {
								// ASSERT: both the start time and end time in the list are valid local times
								// if minimum start time for that day, update
								if (startTime.compareTo(dayStartTimes.get(days).get(0)) == -1) {
									dayStartTimes.get(days).set(0, startTime);
								}
								
								// if maximum (start time + latency) for that day, update
								if (endTime.compareTo(dayStartTimes.get(days).get(1)) == 1) {
									dayStartTimes.get(days).set(1, endTime);
								}
							}
						}
					}
				}
				
				// go through all data and calculate actual session length for each day, then average
				int totalSessionTime = 0;
				
				for (List<LocalTime> startEndTimes : dayStartTimes.values()) {
					// calculate session length
					totalSessionTime += ChronoUnit.MINUTES.between(startEndTimes.get(0), startEndTimes.get(1));
				}
				
				// return average session length given in minutes
				return_val = (double) totalSessionTime / dayStartTimes.size();
				
			default:
				return_val = null;
		}
		
		return return_val;
	}
	
	List<Double> getAllAdministrativeData (Statistic name)
	{
		return null;
		
	}
	Double getLearningData (Statistic name)
	{
		return null;
		
	}
	List<Double> getAllLearningData (Statistic name)
	{
		return null;
		
	}
	
	public static void main(String args[]) throws FileNotFoundException
	{
		LocalDate firstDate = LocalDate.now();
		LocalDate trandate = LocalDate.parse( "2016-03-22", DateTimeFormatter.ISO_DATE);
		final long days = ChronoUnit.DAYS.between(firstDate, trandate);
		System.out.println(days);
		
		Tutor koko = new Tutor(1);
		System.out.println(koko.getAllBusinessData(Statistic.INCOME));
	}
}
