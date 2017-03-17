package dataHandling;

public class Memory {
	private int learner_id;
	private int vkc_id;
	private String last_seen;
	private int number_seen;
	private double knowledge_probability;
	
	private Learner learner;
	
	private KnowledgeComponent KC;
	
	public int getLearnerID() {
		return learner_id;
	}
	
	public void setLearner(Learner l) {
		learner = l;
	}
	
	public int getVKCId() {
		return vkc_id;
	}
	
	public String getLastSeen() {
		return last_seen;
	}
	
	public int getNumberSeen() {
		return number_seen;
	}
	
	public double getKnowledgeProbability() {
		return knowledge_probability;
	}
	
	public void setKnowledgeComponent(KnowledgeComponent kc) {
		KC = kc;
	}
	
}
