package ca.ciccc.madp202.InterviewerBackend.entities;

import java.util.ArrayList;
import java.util.List;

public class AnswerCollection {

    private int interviewId;
    private int userId;
    private List<Answer> answers = new ArrayList<>();

    public AnswerCollection() {}

    public void putAnswer(int questionId, String answer) {
        answers.add(new Answer(questionId, answer));
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
