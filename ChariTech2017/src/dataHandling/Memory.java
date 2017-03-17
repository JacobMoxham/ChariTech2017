package dataHandling;

public class Memory {
	private int learner_id;
	private int vkc_id;
	private String last_seen;
	private int number_seen;
	private double knowledge_probability;
	
	private Learner learner;
	
	public int getLearnerID() {
		return learner_id;
	}
	
	public void setLearner(Learner l) {
		learner = l;
	}
	
}
