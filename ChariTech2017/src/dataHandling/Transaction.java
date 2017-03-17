package dataHandling;

public class Transaction {
	private int learner_id;
	private String timestamp;
	private int credits;
	private int amount;
	
	private Learner learner;
	
	public int getLearnerId() {
		return learner_id;
	}
	
	public void setLearner(Learner l) {
		learner = l;
	}
	
	public String getTimeStamp() {
		return timestamp;
	}
	
	public int getCredits() {
		return credits;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Learner getLearner() {
		return learner;
	}
}
