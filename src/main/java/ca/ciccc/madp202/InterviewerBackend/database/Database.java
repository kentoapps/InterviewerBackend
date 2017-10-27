package ca.ciccc.madp202.InterviewerBackend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;
import com.mysql.jdbc.Statement;
import com.sun.crypto.provider.RSACipher;

import ca.ciccc.madp202.InterviewerBackend.entities.Answer;
import ca.ciccc.madp202.InterviewerBackend.entities.History;
import ca.ciccc.madp202.InterviewerBackend.entities.Interview;
import ca.ciccc.madp202.InterviewerBackend.entities.InterviewType;
import ca.ciccc.madp202.InterviewerBackend.entities.Profile;
import ca.ciccc.madp202.InterviewerBackend.entities.Question;
import ca.ciccc.madp202.InterviewerBackend.entities.Result;
import ca.ciccc.madp202.InterviewerBackend.entities.User;
import ca.ciccc.madp202.InterviewerBackend.entities.UserRequest;
import ca.ciccc.madp202.InterviewerBackend.utils.Key;

public class Database {
	public static int insertUser(String query) {
		Connection con = null;
		int id = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewer", "root", "root");
            Statement statement = (Statement) con.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            
            while (result.next()) {
				id = result.getInt(1);
			}
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                		e.printStackTrace();
                }
            }
		}
        return id;
	}

	public static User selectUser(String query) {
		User user = new User();
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewer", "root", "root");
            
	        Statement stmt = (Statement) con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	    user.setUserId(rs.getInt(Key.id));
	        	    user.setFirstName(rs.getString(Key.firstname));
	        	    user.setLastName(rs.getString(Key.lastname));
	        	    user.setUsername(rs.getString(Key.username));
	        	    user.setPassword(rs.getString(Key.password));
	        	    user.setCountry(rs.getString(Key.country));
	        	    user.setJoined(rs.getTimestamp(Key.joined));
	        }
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                		e.printStackTrace();
                }
            }
		}
		return user;
	}

	public static Interview selectInterview(String query) {
		Interview interview = new Interview();
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewer", "root", "root");
            
	        Statement stmt = (Statement) con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        List<Question> questions = new ArrayList<>();
	        while (rs.next()) {
	        		interview.setInterviewId(rs.getInt(Key.interviewId));
	        		int type = rs.getInt(Key.type);
	        		interview.setType(InterviewType.values()[type-1]);
	        		interview.setDuration(rs.getInt(Key.duration));
	        		interview.setDescription(rs.getString(Key.description));
	        		questions.add(new Question(rs.getInt(Key.questionId), rs.getInt(Key.level), rs.getString(Key.description), rs.getString(Key.option1), rs.getString(Key.option2), rs.getString(Key.option3), rs.getString(Key.option4)));
	        }
	        interview.setQuestions(questions);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                		e.printStackTrace();
                }
            }
		}
		return interview;
	}

	public static String selectAnswer(String query) {
		String answer = "";
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewer", "root", "root");
            
	        Statement stmt = (Statement) con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        		answer = rs.getString(Key.answer);
	        }
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                		e.printStackTrace();
                }
            }
		}
		return answer;
	}
    public static void insertHistory(String query) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewer", "root", "root");
            con.createStatement().executeUpdate(query);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }
        }
    }

	public static List<History> selectHistory(String query) {
		List<History> list = new ArrayList<>();
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewer", "root", "root");
            
	        Statement stmt = (Statement) con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
        			int type = rs.getInt(Key.type);
	        		list.add(new History(InterviewType.values()[type-1], rs.getTimestamp(Key.date), rs.getString(Key.score)));
	        }
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                		e.printStackTrace();
                }
            }
		}
		return list;
	}
}
