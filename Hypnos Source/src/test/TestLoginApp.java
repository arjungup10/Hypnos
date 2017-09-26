// Created by Jonathan Chianglin
package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.main.Game;
import logic.screens.LoginApp;

public class TestLoginApp {
	private Game game = Game.getInstance(); 
	
	@Test	
	public void testGUIUser() {
		LoginApp loginapp = new LoginApp(game); 
		String user = null;
		
		assertEquals(user, loginapp.getUser());
	}
	
	@Test
	public void testGUIPass() {
		LoginApp loginapp = new LoginApp(game); 
		String pass = null;
		
		assertEquals(pass, loginapp.getPassword());
	}
	
}
