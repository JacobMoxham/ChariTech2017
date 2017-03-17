package datastatistics;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.text.DateFormatter;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import dataHandling.*;

public class Tutor {
	
	private Integer centre_id;
	private DataInput data;
	private LocalDate date = LocalDate.now();
	private Set<Learner> learnerSet;

	Tutor (Integer id)
	{
		centre_id = id;
		data = new DataInput();
		Date date = new Date();
		learnerSet = new Centre(centre_id).getLearnerSet();
		
		
	}	
	
	//last month
	Double getBusinessData (Statistic name) throws FileNotFoundException
	{
		if(name == Statistic.INCOME)
		{
			Double totalincome = 0.0;
			ArrayList <Transaction> trans = new ArrayList <Transaction> (data.getTransactionList());
			for(Transaction tran: trans)
			{
				LocalDate trandate = LocalDate.parse( tran.getTimeStamp(), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(trandate, date);
				if(days < 30) totalincome += tran.getAmount();	
			}
			return totalincome;
		}
		if(name == Statistic.NEW_CUSTOMERS)
		{
			Double total = 0.0;
			ArrayList <Learner> learners = new ArrayList <Learner> (data.getLearnerList());
			for(Learner lea: learners)
			{
				LocalDate joindate = LocalDate.parse( lea.getDateJoined(), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(joindate, date);
				if(days < 30) total += 1;
			}
			return total;
		}
		if(name == Statistic.ATTRITION)
		{
			Double total = 0.0;
			ArrayList <Learner> learners = new ArrayList <Learner> (data.getLearnerList());
			for(Learner lea: learners)
			{
				Set <Transaction> trans = lea.getMemorySet();
				for(Transaction tran: trans)
				{
					LocalDate trandate = LocalDate.parse( tran.getTimeStamp(), DateTimeFormatter.ISO_DATE);
					long days = ChronoUnit.DAYS.between(trandate, date);
					if(days < 30) totalincome += tran.getAmount();	
				}
				LocalDate joindate = LocalDate.parse( lea.getDateJoined(), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(joindate, date);
				if(days < 30) total += 1;
			}
			return total;
		}
		return null;
	}
	
	//last months - index 0 - the most recent month
	List<Double> getAllBusinessData (Statistic name) throws FileNotFoundException
	{
		
		if(name == Statistic.INCOME)
		{
			ArrayList <Double> total = new ArrayList <Double> ();
			ArrayList <Transaction> trans = new ArrayList <Transaction> (data.getTransactionList());
			for(Transaction tran: trans)
			{
				LocalDate trandate = LocalDate.parse( tran.getTimeStamp(), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(trandate, date);
				int num = (int) (days/30);
				while(total.size() < num) total.add(0.0);
				total.set(num, total.get( num) + tran.getAmount());
			}
			return total;
		}
		if(name == Statistic.NEW_CUSTOMERS)
		{
			ArrayList <Double> total = new ArrayList <Double> ();
			ArrayList <Learner> learners = new ArrayList <Learner> (data.getLearnerList());
			for(Learner lea: learners)
			{
				LocalDate joindate = LocalDate.parse( lea.getDateJoined(), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(joindate, date);
				int num = (int) (days/30);
				while(total.size() < num) total.add(0.0);
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
				for (Experience experience : data.getExperienceList()) {
					if (learnerSet.contains(experience.getLearner()) {
						
					}
				}
				
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
	
	public static void main(String args[])
	{
		LocalDate firstDate = LocalDate.now();
		LocalDate trandate = LocalDate.parse( "2016-03-22", DateTimeFormatter.ISO_DATE);
		final long days = ChronoUnit.DAYS.between(firstDate, trandate);
		System.out.println(days);
	}
}
