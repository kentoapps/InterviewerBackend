package ca.ciccc.madp202.InterviewerBackend.entities;

public class Error {
	public static final String EMAIL_INVALID = "Couldn't find user";
	public static final String PASSWORD_INVALID = "Wrong password";
	
	private int id;
	private String message;
	
	public Error() {
	}

	public Error(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
