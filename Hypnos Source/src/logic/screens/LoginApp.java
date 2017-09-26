package logic.screens;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logic.main.Game;

public class LoginApp extends LoginScreenApp{
	// Swing Objects for User Input
	private JFrame mainFrame;
	private JPanel controlPanel = new JPanel();

	private String user;
	private String pass; 
	private Game game; 
	private JButton loginButton; 
	private JButton createButton;
	
	public LoginApp(Game game) {
		super();
		this.game = game; 
		
		prepareGUI(1); 
		mainFrame = getFrame(); 
	
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	public void show() {
		// show stuff
		JTextField userText;
		JPasswordField passwordText;
		JLabel namelabel = new JLabel("User ID: ", JLabel.RIGHT);
		JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
		JLabel statusLabel = new JLabel("", JLabel.CENTER);
		userText = new JTextField(6);
		passwordText = new JPasswordField(6);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(e->{
			user = userText.getText();
			pass = new String(passwordText.getPassword());
			
			if (user != null && pass != null) {
				statusLabel.setText("SUCCESS");
				mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
				game.setCurState(Game.STATE.MENU); 
			}
			else {
				// print Error message
				statusLabel.setText("Incorrect username or password");
			}}) ;

		createButton = new JButton("Create Account");
		createButton.addActionListener(e->{
			CreateApp createapp = new CreateApp(game);
			createapp.show();
			mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
		});
		
		controlPanel.add(namelabel);
		controlPanel.add(userText);
		controlPanel.add(passwordLabel);
		controlPanel.add(passwordText);
		controlPanel.add(loginButton);
		controlPanel.add(createButton);
		controlPanel.add(statusLabel);
		mainFrame.setVisible(true);

	}

	public String getUser(){ return user; }

	public String getPassword() { return pass; }
	
	public JButton getLoginButton() { return loginButton; }
	
	public JButton getCreateButton() { return createButton; }
	
}
