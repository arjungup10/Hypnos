package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Test;

import logic.main.Game;
import logic.screens.LoginApp;

// Created by Jonathan Chianglin
public class TestLoginToCreate {

	@Test
	public void testCreateLaunch() {
		Game game = Game.getInstance();
		game.setCurState(Game.STATE.LOGIN);
		
		LoginApp loginapp = new LoginApp(game); 
		loginapp.show();
		JButton createButton = loginapp.getCreateButton(); 
		createButton.getActionListeners()[0].actionPerformed(new ActionEvent(createButton, 
																			ActionEvent.ACTION_PERFORMED, 
																			createButton.getActionCommand()));
		
		assertEquals(Game.STATE.LOGIN, game.getCurState());
	}

}
