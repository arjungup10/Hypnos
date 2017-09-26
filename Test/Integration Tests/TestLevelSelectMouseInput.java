package test;

import static org.junit.Assert.assertEquals;

import java.awt.event.MouseEvent;

import org.junit.Test;

import logic.main.Game;
import logic.main.MouseInput;

/* Created by Arjun Gupta*/

public class TestLevelSelectMouseInput {
	Game testGame = Game.getInstance();
	
	@Test
	public void testGoToLevel1() {
		testGame.setCurState(Game.STATE.LEVEL_SELECT);
		
		MouseEvent testMouse = new MouseEvent(testGame, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, 200, 150, 1, false, MouseEvent.BUTTON1);
		MouseInput testInput = new MouseInput(testGame);
		testInput.mousePressed(testMouse);
		
		assertEquals(Game.STATE.LEVEL_1, testGame.getCurState());
	}
	
	@Test
	public void testGoToMenu() {
		testGame.setCurState(Game.STATE.LEVEL_SELECT);
		
		MouseEvent testMouse = new MouseEvent(testGame, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, 200, 460, 1, false, MouseEvent.BUTTON1);
		MouseInput testInput = new MouseInput(testGame);
		testInput.mousePressed(testMouse);
		
		assertEquals(Game.STATE.MENU, testGame.getCurState());
	}
}
