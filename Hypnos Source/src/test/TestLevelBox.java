package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.screens.LevelBox;
import logic.screens.LevelSelect;

/*
 * Created by Elyse Munemura
 */

public class TestLevelBox {
	
	@Test
	public void testCheckBoundGeneral() {
		LevelBox testCheckBound = new LevelBox(LevelSelect.getLevels()[1].leftBound(), LevelSelect.getLevels()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 380, 150);
		
		assertEquals(true, output);
	}
	
	@Test
	public void testCheckBoundLeft() {
		LevelBox testCheckBound = new LevelBox(LevelSelect.getLevels()[1].leftBound(), LevelSelect.getLevels()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 300, 150);
		
		assertEquals(false, output);
	}
	
	@Test
	public void testCheckBoundRight() {
		LevelBox testCheckBound = new LevelBox(LevelSelect.getLevels()[1].leftBound(), LevelSelect.getLevels()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 500, 150);
		
		assertEquals(false, output);
	}
	
	@Test
	public void testCheckBoundUpper() {
		LevelBox testCheckBound = new LevelBox(LevelSelect.getLevels()[1].leftBound(), LevelSelect.getLevels()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 380, 130);
		
		assertEquals(false, output);
	}
	
	@Test
	public void testCheckBoundLower() {
		LevelBox testCheckBound = new LevelBox(LevelSelect.getLevels()[1].leftBound(), LevelSelect.getLevels()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 380, 250);
		
		assertEquals(false, output);
	}

}
