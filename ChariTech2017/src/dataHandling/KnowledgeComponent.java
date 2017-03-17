package dataHandling;

import java.util.HashSet;
import java.util.Set;

public class KnowledgeComponent {
	private int id;
	
	Set<Content> questionSet = new HashSet<>();
	Set<Memory> memorySet = new HashSet<>();
	
	public KnowledgeComponent(int vkc) {
		id = vkc;
	}
	
	public void addQuestion(Content c) {
		questionSet.add(c);
	}
	
	public void addMemory(Memory m) {
		memorySet.add(m);
	}
	
	public int getId() {
		return id;
	}
	
	public Set<Content> getContentSet() {
		return questionSet;
	}
	
	public Set<Memory> getMemorySet() {
		return memorySet;
	}
}
