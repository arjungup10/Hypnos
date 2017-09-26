package test;

import static org.junit.Assert.assertEquals;

import java.awt.event.MouseEvent;

import org.junit.Test;

import logic.main.Game;
import logic.main.MouseInput;

/*Created by Audrey Chan*/

public class TestOptionsMouseInput {
	@Test
	public void testGoToControls() {
		Game.getInstance().setCurState(Game.STATE.OPTIONS);
		
		MouseEvent testMouse = new MouseEvent(Game.getInstance(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, 550, 460, 1, false, MouseEvent.BUTTON1);
		MouseInput testInput = new MouseInput(Game.getInstance());
		testInput.mousePressed(testMouse);
		
		assertEquals(Game.STATE.CONTROLS, Game.getInstance().getCurState());
	}
	
	@Test
	public void testGoToMenu() {
		Game.getInstance().setCurState(Game.STATE.OPTIONS);
		
		MouseEvent testMouse = new MouseEvent(Game.getInstance(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, 200, 460, 1, false, MouseEvent.BUTTON1);
		MouseInput testInput = new MouseInput(Game.getInstance());
		testInput.mousePressed(testMouse);
		
		assertEquals(Game.STATE.MENU, Game.getInstance().getCurState());
	}
}
