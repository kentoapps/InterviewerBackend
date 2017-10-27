package ca.ciccc.madp202.InterviewerBackend.entities;

public class Result {
    private int interviewId;
    private int questions;
    private int correctAnswer;
    private int wrongAnswer;
    private int skippedAnswer;
    private InterviewType topic;
    private int duration;
    private String score;

    public Result() {
    		correctAnswer = 0;
    		wrongAnswer = 0;
    		skippedAnswer = 0;
    }
    
    public void finalize() {
    		score = 10 * correctAnswer + "/" + (questions*10);
    }
    
    public void incrementCorrect() {
		correctAnswer++;
	}
    
    public void incrementWrong() {
		wrongAnswer++;
	}
    
    public void incrementSkipped() {
		skippedAnswer++;
	}

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public int getSkippedAnswer() {
        return skippedAnswer;
    }

    public void setSkippedAnswer(int skippedAnswer) {
        this.skippedAnswer = skippedAnswer;
    }

    public InterviewType getTopic() {
        return topic;
    }

    public void setTopic(InterviewType topic) {
        this.topic = topic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
