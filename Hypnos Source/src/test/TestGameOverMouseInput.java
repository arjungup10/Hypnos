package test;

import static org.junit.Assert.assertEquals;

import java.awt.event.MouseEvent;

import org.junit.Test;

import logic.main.Game;
import logic.main.MouseInput;

/*Created by Audrey Chan*/

public class TestGameOverMouseInput {
	@Test
	public void testGoToMenu() {
		Game.getInstance().setCurState(Game.STATE.GAME_OVER);
		
		MouseEvent testMouse = new MouseEvent(Game.getInstance(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), MouseEvent.BUTTON1_MASK, 390, 370, 1, false, MouseEvent.BUTTON1);
		MouseInput testInput = new MouseInput(Game.getInstance());
		testInput.mousePressed(testMouse);
		
		assertEquals(Game.STATE.MENU, Game.getInstance().getCurState());
	}
}
