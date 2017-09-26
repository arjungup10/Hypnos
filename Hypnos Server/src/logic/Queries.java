package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Queries {
	
	private static final String WHERECLAUSE = "WHERE userName = '";

	private HypnosThread thread;
	
	public Queries (HypnosThread thread) {
		this.thread = thread;
	}
	
	public String checkForUser(String userName, String password) throws SQLException{
		String query;
		Statement statement;
		ResultSet results;
		boolean found = false;
		boolean added = false;
		boolean exists;
		
		query = "SELECT password " +
		        " FROM UserData "  +
				WHERECLAUSE +
		        userName + "';";
		
		exists = checkQueryReturns(query);
		
		if (exists) {
			query = "SELECT * " +
		            " FROM UserData" +
					" WHERE userName = '" + userName + "'" +
		            "      AND password = '" + password + "';";
			exists = checkQueryReturns(query);
			
			if (exists) {
				found = true;
			}
			else {
				return "[LOGIN - PASS FAIL]";
			}
		}
		else {
			query = "SELECT userName" + 
					" FROM UserData " + 
					WHERECLAUSE + 
					userName + "';";

			statement = thread.getConnection().createStatement();

			results = statement.executeQuery(query);
				
			if (!results.isBeforeFirst()) {
				added = addNewUser(userName, password);
				results.close();
			}
			else {
				results.close();
				return "[LOGIN - CREATE USER - USERNAME TAKEN]";
			}
			if (results.next()) {
				found = true;
			}
				
			closeQuery(results);
		}
		
		if (found)
			return "[LOGIN - PASS]";
		else if (added)
			return "[LOGIN - CREATE USER]";
		else
			return "[LOGIN - FAIL]";
	}
	
	public boolean checkQueryReturns(String query) throws SQLException{
		Statement statement;
		ResultSet results;
		
		statement = thread.getConnection().createStatement();

		results = statement.executeQuery(query);

		if (!results.isBeforeFirst()) {
			results.close();
			return false;
		}
			
		results.close();
		
		return true;
	}
	
	public boolean addNewUser(String userName, String password) throws SQLException{
		boolean added; 
		
		setupUser(userName, password);
		added = true;
		
		return added;
	}

	public void setupUser(String newUser, String password) throws SQLException{
		String insert;
		Statement statement;

		insert = "INSERT INTO UserData (userName, password)" + 
				 " VALUES ('" + newUser + "', '" + password + "');";

		statement = thread.getConnection().createStatement();
		statement.executeUpdate(insert);
		statement.close();

	}	
	
	public String checkForLevels(String userNameInput) throws SQLException{
		String query;
		StringBuilder ret = new StringBuilder();
		PreparedStatement prepStmt;
		ResultSet results;
		int numLevels = 0;
		
		query = "SELECT numLevels, L.level as level " +
				"FROM " + 
				"	(SELECT U.userId, COUNT(*) as numLevels " + 
				"	FROM UserData U JOIN LevelInfo L ON U.userID = L.userID " + 
				"	WHERE userName = ? " + 
				"   AND isLocked = false) N " + 
				"	JOIN UserData U ON U.userID = N.userID " + 
				"	JOIN LevelInfo L ON L.userID = U.userID " +
				"	WHERE U.userName = ? " + 
				"   AND isLocked = false;";
		
		prepStmt = (PreparedStatement) thread.getConnection().prepareStatement(query);
		prepStmt.setString(1, userNameInput);
		prepStmt.setString(2, userNameInput);
		
		results = prepStmt.executeQuery();
			
		if (results.next())
			numLevels = results.getInt("numLevels");

		if (numLevels == 1)
			ret.append("[NO LEVELS]\n" + numLevels);
		else if (numLevels > 1)
			ret.append("[HAS LEVELS]\n" + numLevels);
			
		results.beforeFirst();
			
		while (results.next()) {
			ret.append(" " + results.getString("level"));
		}
			
		closeQuery(results);
		return ret.toString();
	
	}

	public String checkForHighScores() throws SQLException {
		String query;
		StringBuilder ret = new StringBuilder();
		Statement statement;
		ResultSet results;
		
		query = "SELECT userName, score " +
				"FROM " + 
				"	UserData U JOIN HighScores H on H.userId = U.userId  " +
				"ORDER BY " + 
					"rank ASC;";

		statement = thread.getConnection().createStatement();
		results = statement.executeQuery(query);

		if (!results.isBeforeFirst()) {
			ret.append("[NO SCORES]\n");
		}
		else {
			ret.append("[HAS SCORES]\n");
			while (results.next()) {
				ret.append(results.getString(1) + " ");
				ret.append(results.getInt(2) + "\n");
			}
		}
		
		closeQuery(results);
		return ret.toString();
	}	
	
	public String updateLevels(String userName, String level) throws SQLException{
		String insert;
		Statement statement;
		
		insert = "INSERT INTO LevelInfo (userId, level, isLocked)" +
				" VALUES (" + 
				"  (SELECT userId " + 
				"   FROM UserData " +
				"   WHERE userName = '" + 
					userName + "'), " + 
				level + ", false);";

		statement = thread.getConnection().createStatement();
		statement.executeUpdate(insert);
		statement.close();
		
		return "[LEVEL UNLOCKED]";
	}	

	public String updateUser(String userName, String password) throws SQLException{
		String update;
		Statement statement;
		
		update = "UPDATE UserData " +
				 "SET password = '" + password + "' " + 
				 WHERECLAUSE + userName + "';";

		statement = thread.getConnection().createStatement();
		statement.executeUpdate(update);
		statement.close();
		
		return "[PASSWORD SET]";
	}		

	public String updateHighScores(String userName, String score) throws SQLException{
		String insert;
		Statement statement;
		
		insert = "INSERT INTO HighScores (userId, score)" +
				" VALUES (" + 
				"  (SELECT userId " + 
				"   FROM UserData " +
				WHERECLAUSE + 
				userName + "'), " + 
				score + ");";

		statement = thread.getConnection().createStatement();
		statement.executeUpdate(insert);
		statement.close();
		
		return "[SCORE ADDED]";
	}		
	
	public void closeQuery(ResultSet results) throws SQLException{
		if (results != null) 
			results.close();

	}

}
