package dataHandling;

import java.util.HashSet;
import java.util.Set;

public class Learner {

	private int id;
	private String date_joined;
	private String language;
	private String gender;
	private String date_of_birth;
	private int grade_at_registration;
	public int family_size;
	private String father_occupation;
	private String mother_occupation;
	private int centre_id;
	
	private HashSet<Memory> memorySet = new HashSet<>();
	private HashSet<Transaction> transactionSet = new HashSet<>();
	private HashSet<Experience> experienceSet = new HashSet<>();
	private Centre learnerCentre;
	
	
	public int getId() {
		return id;
	}
	
	public String getDateJoined() {
		return date_joined;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getDOB() {
		return date_of_birth;
	}
	
	public int getGradeAtRegistration() {
		return grade_at_registration;
	}
	
	public String getFatherOccupation() {
		return father_occupation;
	}
	
	public String getMotherOcccupation() {
		return mother_occupation;
	}
	
	public void addMemory(Memory m) {
		memorySet.add(m);
	}
	
	public void addTransaction(Transaction t) {
		transactionSet.add(t);
	}
	
	public void addExperience(Experience e) {
		experienceSet.add(e);
	}
	
	public int getCentreId() {
		return centre_id;
	}
	
	public void setCentre(Centre c) {
		learnerCentre = c;
	}
	
	public Centre getCentre() {
		return learnerCentre;
	}
	
	
	
	
	
}
