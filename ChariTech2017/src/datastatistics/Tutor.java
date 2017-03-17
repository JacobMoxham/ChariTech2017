package datastatistics;

import java.util.List;

import dataHandling.*;

public class Tutor {
	
	private Integer centre_id;
	
	Tutor (Integer id)
	{
		centre_id = id;
	}	
	
	//last month
	Double getBusinessData (Statistic name)
	{
		Double return_val = null;
		
		switch (name) {
			case SESSION_LENGTH:
				
				
			default:
				break;
		}
			
		return return_val;	
	}
	
	//last months - index 0 - the most recent month
	List<Double> getAllBusinessData (Statistic name)
	{
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
	

}
