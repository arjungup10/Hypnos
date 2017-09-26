package test;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.junit.Test;

import logic.levels.Level1;
import logic.main.Game;

/*Created by Andrew 3/12/17*/

public class TestKeyPlayer {

	@Test
	public void testSetPlayerLeft() throws IOException {
		Level1 level1 = new Level1(Game.getInstance());
		level1.keyPressed(KeyEvent.VK_LEFT);
		
		boolean expected = true;
		boolean result = level1.getPlayer().getLeft();
		
		assertEquals(expected, result);
	}

}
