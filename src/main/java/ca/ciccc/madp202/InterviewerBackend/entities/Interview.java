package ca.ciccc.madp202.InterviewerBackend.entities;

import java.util.List;

public class Interview {
    private int interviewId;
    private InterviewType type;
    private int duration;
    private List<Question> questions;
    private String description;

    public Interview() {}
    
    public Interview(int interviewId, InterviewType type, int duration, List<Question> questions, String description) {
		this.interviewId = interviewId;
		this.type = type;
		this.duration = duration;
		this.questions = questions;
		this.description = description;
	}

	public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public InterviewType getType() {
        return type;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
