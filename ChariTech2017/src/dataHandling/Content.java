package dataHandling;

import java.util.HashSet;
import java.util.Set;

public class Content {
	private int question_id;
	private int template_id;
	private int vocabularyknowledgecomponent_id;
	private int challenge_id;
	private int unit_id;
	
	private KnowledgeComponent KC;
	
	private Set<Experience> experienceSet = new HashSet<>();
	
	public int getQuestionId() {
		return question_id;
	}
	
	public int getTemplateID() {
		return template_id;
	}
	
	
	public int getVKCId() {
		return vocabularyknowledgecomponent_id;
	}
	
	public int getChallengeId() {
		return challenge_id;
	}
	
	public int getUnitId() {
		return unit_id;
	}
	
	public void addExperience(Experience e) {
		experienceSet.add(e);
	}
	
	public void setKnowledgeComponent(KnowledgeComponent kc) {
		KC = kc;
	}
	
	public KnowledgeComponent getKnowledgeComponent() {
		return KC;
	}
	
	public Set<Experience> getExperienceSet() {
		return experienceSet;
	}
	
	
}
