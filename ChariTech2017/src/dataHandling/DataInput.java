package dataHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class DataInput {
	
	private static List<Learner> learnerList = null;
	private static List<Content> contentList = null;
	private static List<Transaction> transactionList = null;
	private static List<Memory> memoryList = null;
	private static List<Experience> experienceList = null;
	private static List<Centre> centreList = null;
	private static List<KnowledgeComponent> knowledgeComponentList = null;
	
	private static Map<Integer, Centre> CentreMap = null;
	
	
	
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
		System.out.println(getCentreList().size());
	}
	
	public static void loadData() throws FileNotFoundException {
		getContentList();
		getExperienceList();
		getMemoryList();
		getTransactionList();
		getLearnerList();
	}
	
	public static List<Centre> getCentreList() throws FileNotFoundException {
		if (centreList == null) {
			loadData();
			normaliseData();
		}
		
		return centreList;
	}
	
	public static List<KnowledgeComponent> getKnowledgeComponentList() throws FileNotFoundException {
		if (knowledgeComponentList == null) {
			loadData();
			normaliseData();
		}
		
		return knowledgeComponentList;
	}
	
	public static Map<Integer,Centre> getCentreMap() throws FileNotFoundException {
		if (CentreMap == null) {
			loadData();
			normaliseData();
		}
		
		return CentreMap;
	}
	
	public static void normaliseData() {
		HashMap<Integer,Learner> learnerMap = new HashMap<>();
		HashMap<Integer,Centre> centreMap = new HashMap<>();
		HashMap<Integer, Content> contentMap = new HashMap<>();
		HashMap<Integer, KnowledgeComponent> kCMap = new HashMap<>();
		
		centreList = new ArrayList<>();
		knowledgeComponentList = new ArrayList<>();
		
		
		//iterate over learners
		for (Learner l : learnerList) {
			learnerMap.put(l.getId(), l);
			
			//set centre information
			if (!centreMap.containsKey(l.getCentreId())) {
				Centre newCentre = new Centre(l.getCentreId());
				centreMap.put(l.getCentreId(), newCentre);
				centreList.add(newCentre);
			}
			
			Centre c = centreMap.get(l.getCentreId());
			c.addLearner(l);
			l.setCentre(c);
			
		}
		
		CentreMap = centreMap;
		
		//iterate over content
		for (Content c : contentList) {
			contentMap.put(c.getQuestionId(), c);
			
			//set knowledge component information
			if (!kCMap.containsKey(c.getVKCId())) {
				KnowledgeComponent kc = new KnowledgeComponent(c.getVKCId());
				kCMap.put(c.getVKCId(), kc);
				knowledgeComponentList.add(kc);
			}
			
			KnowledgeComponent kc = kCMap.get(c.getVKCId());
			kc.addQuestion(c);
			c.setKnowledgeComponent(kc);
		}
		
		//set memory information
		for (Memory m : memoryList) {
			int learnerId = m.getLearnerID();
			learnerMap.get(learnerId).addMemory(m);
			m.setLearner(learnerMap.get(learnerId));
			
			KnowledgeComponent kc = kCMap.get(m.getVKCId());
			kc.addMemory(m);
			m.setKnowledgeComponent(kc);
		}
		
		//set transaction information
		for (Transaction t : transactionList) {
			int learnerId = t.getLearnerId();
			learnerMap.get(learnerId).addTransaction(t);
			t.setLearner(learnerMap.get(learnerId));
		}
		
		// set experience information
		for (Experience e : experienceList) {
			int learnerId = e.getLearnerId();
			learnerMap.get(learnerId).addExperience(e);
			e.setLearner(learnerMap.get(learnerId));
			
			int questionId = e.getQuestionId();
			contentMap.get(questionId).addExperience(e);
			e.setContent(contentMap.get(questionId));
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
		
		
}
	
