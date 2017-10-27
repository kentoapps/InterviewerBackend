package ca.ciccc.madp202.InterviewerBackend.entities;

import java.util.Date;

public class History {
    private InterviewType topic;
    private Date date;
    private String score;

    public History() {}
 
    public History(InterviewType topic, Date date, String score) {
		this.topic = topic;
		this.date = date;
		this.score = score;
	}

	public InterviewType getTopic() {
        return topic;
    }

    public void setTopic(InterviewType topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
