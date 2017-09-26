package logic;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.DBConnection;

public class HypnosThread extends Thread {
	private Connection conn;
	protected Socket socket;
	
	private Logger logger = Logger.getLogger(HypnosThread.class.getName());
	private Scanner sc;
	private PrintStream p;
	private String user;
	private String result;
	private Queries callQuery;

	// New Thread Constructor
	public HypnosThread(Socket clientSocket) throws IOException {
		this.socket = clientSocket;
		this.conn = DBConnection.getConnection();
		this.callQuery = new Queries(this);
	}

	/**
	 * @param sc
	 *            is used for reading information from the client
	 * @param p
	 *            is used to send information back to the client
	 */
	@Override
	public void run() {
		try {
			sc = new Scanner(socket.getInputStream());
			p = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			logger.log(Level.FINE, e.getMessage(), e);
			return;
		}

		try {
			// constantly reads information from the client

			while (sc.hasNextLine()) {
				String query = sc.nextLine();
					
				if ("[CHECK LOGIN]".equalsIgnoreCase(query)) {
					checkLogin();
				}
				else if ("[CHECK LEVELS]".equalsIgnoreCase(query)) {
					checkLevels();
				}
				else if ("[CHECK HIGHSCORES]".equalsIgnoreCase(query)) {
					checkHighScores();
				}
				else if ("[UPDATE LEVELS]".equalsIgnoreCase(query)) {
					updateLevels();
				}
				else if ("[UPDATE USER]".equalsIgnoreCase(query)) {
					updateUser();
				}
				else if ("[UPDATE HIGHSCORES]".equalsIgnoreCase(query)) {
					updateHighScores();
				}
				else if ("[DONE]".equalsIgnoreCase(query)) {
					break;
				}
				else
					p.print("Invalid option.");
			}

			sc.close();
			return;
		} catch (SQLException e) {
			logger.log(Level.FINE, e.getMessage(), e);
		}
	}

	public void checkLogin() throws SQLException {
		String password;

		user = sc.nextLine();
		password = sc.nextLine();
		result = callQuery.checkForUser(user, password);
		p.println(result);
	}

	public void checkLevels() throws SQLException {
		user = sc.nextLine();
		result = callQuery.checkForLevels(user);
		p.println(result);
	}
	
	public void checkHighScores() throws SQLException {
		result = callQuery.checkForHighScores();
		p.println(result);
	}

	public void updateLevels() throws SQLException {
		String level;

		user = sc.nextLine();
		level = sc.nextLine();
		result = callQuery.updateLevels(user, level);
		p.println(result);
	}

	public void updateUser() throws SQLException {
		String password;

		user = sc.nextLine();
		password = sc.nextLine();
		result = callQuery.updateUser(user, password);
		p.println(result);
	}

	public void updateHighScores() throws SQLException {
		String score;

		user = sc.nextLine();
		score = sc.nextLine();
		result = callQuery.updateHighScores(user, score);
		p.println(result);
	}	
	
	public Connection getConnection() {
		return this.conn;
	}
}
