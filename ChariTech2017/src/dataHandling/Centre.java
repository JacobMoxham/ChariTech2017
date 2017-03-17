package dataHandling;

import java.util.HashSet;
import java.util.Set;

public class Centre {

	private int id;
	private Set<Learner> learnerSet = new HashSet<>();
	
	
	public Centre(int centreId) {
		id = centreId;
	}
	
	public void addLearner(Learner l ) {
		learnerSet.add(l);
	}
	
	public Set<Learner> getLearnerSet() {
		return learnerSet;
	}
	
	public int getCentreID() {
		return id;
	}
	
}
