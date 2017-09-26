package logic.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/* Note: This class is utilized to communicate with the server to store/load user information
 * Possible Options:
 *    [CHECK LOGIN] - Expects pass/fail/create
 *    [CHECK LEVELS] - Expects Number of Unlocked Levels followed by the available levels
 *    [CHECK CONTROLS] - Expects on each line  <control_string, key_string>
 *    [CHECK HIGHSCORES] - Expect number of high scores followed by <user, time> on each line
 *    [UPDATE LEVELS] - Just updates levels
 *    [UPDATE CONTROLS] - just updates controls
 *    [UPDATE HIGHSCORES] - just updates high scores Max 500
 *    [CREATE LOGIN] - Check if the user name exists if it does send a response saying user exists
 *                   - If it works then it works.
 */

public class HypnosClient {
	
	private Socket connector;
	private Scanner  serverResponse;
	private PrintStream p;
	private String response;
	private int[] unlockedLevels;
	private String state;
	
	public HypnosClient() throws IOException {
		connector = new Socket("unix3.csc.calpoly.edu", 1236);
		serverResponse = new Scanner(connector.getInputStream());
		p = new PrintStream(connector.getOutputStream());
		unlockedLevels = new int[6];
		state = "";
	}
	
	//Passes the user and the password to check if login is correct
	//Note: No hashing is done for passwords
	public void checkLogin(String user, String pass) {
		p.println("[CHECK LOGIN]");
		p.println(user);
		p.println(pass);
		loginResponse();
	}
	
	private void loginResponse() {
		if (serverResponse.hasNextLine()) {
			response = serverResponse.nextLine();
			checkLoginPass();
		}
	}
	
	private void checkLoginPass() {
		if ("[LOGIN - PASS]".equals(response) || ("[LOGIN - FAIL]".equals(response))) {
			this.state = "MENU";
		}
		else if ("[LOGIN - CREATE USER]".equals(response)){
			this.state = "CREATE USER";
		}
		else if ("[LOGIN - CREATE USER - USERNAME TAKEN]".equals(response)) {
			this.state ="CREATE USER FAIL";
		}
		else if ("[LOGIN - PASS FAIL]".equals(response)) {
			this.state = "FAIL";
		}
	}
	//Obtains available levels for the user.
	public void checkLevels(String user) {
		p.println("[CHECK LEVELS]");
		p.println(user);
		levelResponse();
	}
	
	public void levelResponse() {
		if (serverResponse.hasNextLine()) {
			response = serverResponse.nextLine();
			checkLevelResponse();
		}
	}
	
	public void checkLevelResponse() {
		int numUnlocked;
		
		if ("[NO LEVELS]".equals(response)) {
			this.state = "TUTORIAL";
		}
		else if ("[HAS LEVELS]".equals(response) && serverResponse.hasNextInt()){
			numUnlocked = serverResponse.nextInt();
			while (numUnlocked-- > 0 && serverResponse.hasNextInt()) {
				unlockedLevels[numUnlocked] = serverResponse.nextInt();
			}
		}
		serverResponse.nextLine();
	}

	//Obtains each user's high score.
	public void checkHighScores() {
		p.println("[CHECK HIGHSCORES]");
		highScoresResponse();
	}
	
	public void highScoresResponse() {
		if (serverResponse.hasNextLine()) {
			response = serverResponse.nextLine();
			if ("[NO SCORES]".equals(response) || "[HAS SCORES]".equals(response))
			checkHighScoresResponse();
		}
	}

	public void checkHighScoresResponse() {		
		if ("[NO SCORES]".equals(response)) {
			state = "NO SCORES";
		}
		else if ("[HAS SCORES]".equals(response)){
			while (serverResponse.hasNextLine()) {
				response = serverResponse.nextLine();
			}
		}
		serverResponse.nextLine();
	}	

	//Updates available levels for the user.
	public void updateLevels(String user, String level) {
		p.println("[UPDATE LEVELS]");
		p.println(user);
		p.println(level);
		updateLevelsResponse();
	}
	
	private void updateLevelsResponse() {
		if (serverResponse.hasNextLine()) {
			response = serverResponse.nextLine();
			checkUpdateLevelsResponse();
		}
	}
	
	public void checkUpdateLevelsResponse() {		
		if (("[LEVEL UNLOCKED]").equals(response)) {
			state = "LEVEL";
		}
	}

	//Updates user password. 
	public void updateUser(String user, String password) {
		p.println("[UPDATE USER]");
		p.println(user);
		p.println(password);
		updateUserResponse();
	}
	
	private void updateUserResponse() {
		if (serverResponse.hasNextLine()) {
			response = serverResponse.nextLine();
			checkUpdateUserResponse();
		}
	}
	
	public void checkUpdateUserResponse() {		
		if (("[PASSWORD SET]").equals(response)) {
			state = "PASSWORD SET";
		}
	}	
	
	//Add's a user's new high score.
	public void updateScores(String user, String score) {
		p.println("[UPDATE HIGHSCORES]");
		p.println(user);
		p.println(score);
		updateScoresResponse();
	}
	
	private void updateScoresResponse() {
		if (serverResponse.hasNextLine()) {
			response = serverResponse.nextLine();
			checkUpdateScoresResponse();
		}
	}
	
	public void checkUpdateScoresResponse() {		
		if (("[SCORE ADDED]").equals(response)) {
			state = "SCORE ADDED";
		}
	}
	
	public void inputComplete() {
		p.println("[DONE]");
	}	
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}
