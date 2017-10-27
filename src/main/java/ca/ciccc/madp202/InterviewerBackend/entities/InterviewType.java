package ca.ciccc.madp202.InterviewerBackend.entities;

public enum InterviewType {
	JAVA(1), SQL(2);
	
	public int id;

	private InterviewType(int id) {
		this.id = id;
	}
}
