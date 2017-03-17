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
	private LocalDate date = LocalDate.now();
	private Set<Learner> learnerSet;

	Tutor (Integer id) throws FileNotFoundException
	{
		centre_id = id;
		Date date = new Date();
		learnerSet = DataInput.getCentreMap().get(centre_id).getLearnerSet();
		
	}	
	
	//last month
	Double getBusinessData (Statistic name) throws FileNotFoundException
	{
		if(name == Statistic.INCOME)
		{
			Double totalincome = 0.0;
			for(Transaction tran: DataInput.getTransactionList())
			{
				if(!learnerSet.contains(tran.getLearnerId())) continue;
				LocalDate trandate = LocalDate.parse( tran.getTimeStamp(), DateTimeFormatter.ISO_DATE);
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
				LocalDate joindate = LocalDate.parse( lea.getDateJoined(), DateTimeFormatter.ISO_DATE);
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
					LocalDate trandate = LocalDate.parse( tran.getTimeStamp(), DateTimeFormatter.ISO_DATE);
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
				if(!learnerSet.contains(tran.getLearnerId())) continue;
				if(tran.getAmount() <= 1) continue;
				LocalDate trandate = LocalDate.parse( tran.getTimeStamp(), DateTimeFormatter.ISO_DATE);
				long days = ChronoUnit.DAYS.between(trandate, date);
				int num = (int) (days/30);
				while(total.size() < num) total.add(0.0);
				total.set(num, total.get(num) + tran.getAmount());
			}
			return total;
		}
		if(name == Statistic.NEW_CUSTOMERS)
		{
			ArrayList <Double> total = new ArrayList <Double> ();
			for(Learner lea: learnerSet)
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
	
	Double getAdministrativeData (Statistic name)
	{
		return null;
		
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
		
		Tutor koko = new Tutpr(
	}
}
