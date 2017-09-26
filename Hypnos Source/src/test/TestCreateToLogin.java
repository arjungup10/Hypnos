package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Test;

import logic.main.Game;
import logic.screens.CreateApp;

//Created by Jonathan Chianglin
public class TestCreateToLogin {

	@Test
	public void testLoginLaunch() {
		Game game = Game.getInstance();
		game.setCurState(Game.STATE.LOGIN);
		
		CreateApp createapp = new CreateApp(game); 
		createapp.show();
		JButton createButton = createapp.getCreateButton(); 
		createButton.getActionListeners()[0].actionPerformed(new ActionEvent(createButton, 
																			ActionEvent.ACTION_PERFORMED, 
																			createButton.getActionCommand()));
		
		assertEquals(Game.STATE.LOGIN, game.getCurState());
	}

}
