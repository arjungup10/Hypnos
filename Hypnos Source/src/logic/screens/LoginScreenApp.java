package logic.screens;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class LoginScreenApp {
	private JFrame frame;
	
	public void prepareGUI(int flag) {
		if (flag == 0) {
			frame = new JFrame("Create Account");
		}
		else {
			frame = new JFrame("Login"); 
		}
		frame.setSize(450, 250);
		frame.setLayout(new GridLayout(3, 1));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
