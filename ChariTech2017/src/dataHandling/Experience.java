package dataHandling;

public class Experience {
	private int question_id;
	private int learner_id;
	private int score;
	private String answer_value;
	private String start_time;
	private String recording_time;
	private int latency;
	
	private Learner learner;
	private Content content;
	
	public int getLearnerId() {
		return learner_id;
	}
	
	public Learner getLearner() {
		return learner;
	}
	
	public void setLearner(Learner l) {
		learner = l;
	}
	
	public int getQuestionId() {
		return question_id;
	}
	
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content c) {
		content = c;
	}
}
