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
	
	public int getQuestionId() {
		return question_id;
	}
	
	public int getLearnerId() {
		return learner_id;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getAnswerValue() {
		return answer_value;
	}
	
	public String getStartTime() {
		return start_time;
	}
	
	public String getRecordingTime() {
		return recording_time;
	}
	
	public int getLatency() {
		return latency;
	}
	
	public Learner getLearner() {
		return learner;
	}
	
	public void setLearner(Learner l) {
		learner = l;
	}

	
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content c) {
		content = c;
	}
}
