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

public class CreateApp extends LoginScreenApp{

	// Swing Objects for User Input
	private JFrame mainFrame;
	private JPanel controlPanel = new JPanel();

	private String user;
	private String pass;
	private JButton createButton;
	private Game game; 
	
	public CreateApp(Game game) {
		super();
		this.game = game; 
		
		prepareGUI(0); 
		mainFrame = getFrame();
		
		controlPanel.setLayout(new FlowLayout());
		
		mainFrame.add(controlPanel); 
		mainFrame.setVisible(true);
	}

	public void show() {
		JLabel namelabel = new JLabel("User ID: ", JLabel.RIGHT);
		JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
		final JLabel statusLabel = new JLabel("", JLabel.CENTER);
		final JTextField userText = new JTextField(6);
		final JPasswordField passwordText = new JPasswordField(6);

		createButton = new JButton("Create Account");
		createButton.addActionListener(e->{
			user = userText.getText();
			pass = new String(passwordText.getPassword());

			if (pass == null || pass.isEmpty()) {
				statusLabel.setText("Password is empty");
			}
			else {
				game.getClient().checkLogin(user, pass);
				if (game.getClient().getState() == "CREATE USER FAIL") 
					statusLabel.setText("Username is taken");
				else {
					statusLabel.setText("Account created!");
					mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
					LoginApp loginapp = new LoginApp(game);
					loginapp.show();
				}
				
			}
		});
		

		controlPanel.add(namelabel);
		controlPanel.add(userText);
		controlPanel.add(passwordLabel);
		controlPanel.add(passwordText);
		controlPanel.add(createButton);
		controlPanel.add(statusLabel);

	}
	
	public String getUser(){ return user; }
	
	public String getPassword() { return pass; }
	
	public JButton getCreateButton() { return createButton; }
}
