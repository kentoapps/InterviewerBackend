package ca.ciccc.madp202.InterviewerBackend.services;

import java.util.Iterator;
import java.util.List;

import ca.ciccc.madp202.InterviewerBackend.database.Database;
import ca.ciccc.madp202.InterviewerBackend.entities.Answer;
import ca.ciccc.madp202.InterviewerBackend.entities.AnswerCollection;
import ca.ciccc.madp202.InterviewerBackend.entities.CredentialRequest;
import ca.ciccc.madp202.InterviewerBackend.entities.History;
import ca.ciccc.madp202.InterviewerBackend.entities.Interview;import ca.ciccc.madp202.InterviewerBackend.entities.InterviewType;
import ca.ciccc.madp202.InterviewerBackend.entities.Profile;
import ca.ciccc.madp202.InterviewerBackend.entities.Result;
import ca.ciccc.madp202.InterviewerBackend.entities.User;
import ca.ciccc.madp202.InterviewerBackend.entities.UserRequest;

public class InterviewService {

	public static int createUser(UserRequest ur) {
		return Database.insertUser(String.format(
            		"INSERT INTO user " +
            		"(first_name, last_name, username, password, country) values " +
            		"(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",
            		ur.getFirstName(), ur.getLastName(), ur.getUsername(), ur.getPassword(), ur.getCountry()));
	}

	public static User getUser(int id) {
		return Database.selectUser("SELECT * FROM user WHERE id = " + id);
	}

	public static User getUserByCredential(CredentialRequest cr) {
		return Database.selectUser("SELECT * FROM user WHERE username=\"" + cr.getUsername() + "\"");
	}

	public static Interview getInterview(int topicId) {
		return Database.selectInterview(
				"SELECT * FROM interview " + 
				"INNER JOIN question ON interview.id = question.interview_id " + 
				"WHERE type = " + topicId + ";");
	}

	public static Result submitAnswer(int interviewId, AnswerCollection ac) {
		Interview interview = Database.selectInterview(
				"SELECT * FROM interview "+
				"INNER JOIN question ON interview.id = question.interview_id " +
				"WHERE interview.id = " + interviewId + ";");
		
		Result result = new Result();
		result.setInterviewId(interviewId);
		result.setQuestions(interview.getQuestions().size());
		result.setTopic(interview.getType());
		
		for(Answer a : ac.getAnswers()) {
			if (a.getAnswer() == null || a.getAnswer().isEmpty()) {
				result.incrementSkipped();
				continue;
			}
			
			String answer = Database.selectAnswer("SELECT * FROM answer WHERE question_id = " + a.getQuestionId() + ";");
			if (a.getAnswer().equals(answer))
				result.incrementCorrect();
			else
				result.incrementWrong();
		}
		result.finalize();
		Database.insertHistory(String.format(
				"INSERT INTO history " +
				"(user_id, score, type) values " +
				"(\"%d\", \"%s\", \"%s\");",
				ac.getUserId(), result.getScore(), result.getTopic().id));
		return result;
	}

	public static List<History> getHistories(int userId) {
		return Database.selectHistory("SELECT * FROM history WHERE user_id = " + userId);
	}
}
