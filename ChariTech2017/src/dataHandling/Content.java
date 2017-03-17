package dataHandling;

import java.util.HashSet;
import java.util.Set;

public class Content {
	private int question_id;
	private int template_id;
	private int vocabularyknowledgecomponent_id;
	private int challenge_id;
	private int unit_id;
	
	private Set<Experience> experienceSet = new HashSet<>();
	
	
	public int getQuestionId() {
		return question_id;
	}
	
	public void addExperience(Experience e) {
		experienceSet.add(e);
	}
}
