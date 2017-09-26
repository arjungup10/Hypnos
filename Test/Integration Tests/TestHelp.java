package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import logic.main.Game;
import logic.main.MouseInput;
import logic.screens.Help;

/*
 * Created by Natasha Cortez
 */

public class TestHelp {

	@Test
	public void testHighScoresMaintainState() throws IOException {
		Game testGame = Game.getInstance();
		Help helpScreen = new Help(testGame);
		testGame.setHelp(helpScreen);
		testGame.setCurState(Game.STATE.HELP);
		Game.STATE initialState = Game.getInstance().getCurState();
		
		MouseInput input = new MouseInput(testGame);
		
		input.checkState(100,100); 
		
		assertEquals(initialState, Game.getInstance().getCurState());
	}

	@Test
	public void testHighScoresChangeState() throws IOException {
		Game testGame = Game.getInstance();
		Help helpScreen = new Help(testGame);
		testGame.setHelp(helpScreen);
		testGame.setCurState(Game.STATE.HELP);
		
		MouseInput input = new MouseInput(testGame);
		
		input.checkState(170,460); 
		
		assertEquals(Game.STATE.MENU, Game.getInstance().getCurState());
	}	
	
}
