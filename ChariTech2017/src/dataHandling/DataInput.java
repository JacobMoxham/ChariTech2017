package dataHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class DataInput {
	
	private static List<Learner> learnerList = null;
	private static List<Content> contentList = null;
	private static List<Transaction> transactionList = null;
	private static List<Memory> memoryList = null;
	private static List<Experience> experienceList = null;
	
	
	
	public static List<Learner> getLearnerList() throws FileNotFoundException {
		
		if (learnerList == null) {
			Gson gson = new Gson();
			
			BufferedReader br = new BufferedReader(new FileReader(Paths.get("json/learners.json").toFile()));
			JsonReader reader = new JsonReader(br);
			//reader.setLenient(true);
			
			Type type = new TypeToken<List<Learner>>(){}.getType();
			
	        learnerList = gson.fromJson(reader, type);
	        
		}
		
		return learnerList;
	
	}
	
	public static List<Content> getContentList() throws FileNotFoundException {
		if (contentList == null) {
			Gson gson = new Gson();
			
			BufferedReader br = new BufferedReader(new FileReader(Paths.get("json/content.json").toFile()));
			JsonReader reader = new JsonReader(br);
			//reader.setLenient(true);
			
			Type type = new TypeToken<List<Content>>(){}.getType();
			
	        contentList = gson.fromJson(reader, type);
	        
		}
		
		return contentList;
	}
	
	public static List<Transaction> getTransactionList() throws FileNotFoundException {
		if (transactionList == null) {
			Gson gson = new Gson();
			
			BufferedReader br = new BufferedReader(new FileReader(Paths.get("json/transactions.json").toFile()));
			JsonReader reader = new JsonReader(br);
			//reader.setLenient(true);
			
			Type type = new TypeToken<List<Transaction>>(){}.getType();
			
	        transactionList = gson.fromJson(reader, type);
	        
		}
		
		return transactionList;
	}
	
	public static List<Memory> getMemoryList() throws FileNotFoundException {
		if (memoryList == null) {
			Gson gson = new Gson();
			
			BufferedReader br = new BufferedReader(new FileReader(Paths.get("json/memories.json").toFile()));
			JsonReader reader = new JsonReader(br);
			//reader.setLenient(true);
			
			Type type = new TypeToken<List<Memory>>(){}.getType();
			
	        memoryList = gson.fromJson(reader, type);
	        
		}
		
		return memoryList;
	}
	
	public static List<Experience> getExperienceList() throws FileNotFoundException {
		if (experienceList == null) {
			Gson gson = new Gson();
			
			BufferedReader br = new BufferedReader(new FileReader(Paths.get("json/memories.json").toFile()));
			JsonReader reader = new JsonReader(br);
			//reader.setLenient(true);
			
			Type type = new TypeToken<List<Experience>>(){}.getType();
			
	        experienceList = gson.fromJson(reader, type);
	        
		}
		
		return experienceList;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(getContentList());
		System.out.println(getExperienceList());
		System.out.println(getMemoryList());
		System.out.println(getTransactionList());
		System.out.println(getLearnerList());
		
	}
	
	
	
	
	
		
		
}
	
