// Created by Jonathan Chianglin
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.main.Game;
import logic.screens.CreateApp;

public class TestCreateApp {
	private Game game = Game.getInstance(); 
	
	@Test	
	public void testGUIUser() {
		CreateApp createapp = new CreateApp(game); 
		String user = null;
		
		assertEquals(user, createapp.getUser());
	}
	
	@Test
	public void testGUIPass() {
		CreateApp createapp = new CreateApp(game); 
		String pass = null;
		
		assertEquals(pass, createapp.getPassword());
		
	}

}
