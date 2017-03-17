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
	private Centre learnerCentre;
	
	
	public int getId() {
		return id;
	}
	
	public void addMemory(Memory m) {
		memorySet.add(m);
	}
	
	public Set<Memory> getMemories() {
		return memorySet;
	}
	
	public int getCentreId() {
		return centre_id;
	}
	
	public void setCentre(Centre c) {
		learnerCentre = c;
	}
	
	
	
}
