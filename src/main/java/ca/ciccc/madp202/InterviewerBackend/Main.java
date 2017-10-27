package ca.ciccc.madp202.InterviewerBackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
        try (Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/interviewer", "root", "")){
            // JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // MySQLに接続
            System.out.println("MySQLに接続できました。");
            
            con.createStatement().execute(String.format("insert into user (first_name) values (\"%s\");", "aaa"));
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("JDBCドライバのロードに失敗しました。");
        } catch (SQLException e) {
        	e.printStackTrace();
            System.out.println("MySQLに接続できませんでした。");
        }
	}
}
