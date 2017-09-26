package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.screens.Achievement;
import logic.screens.AchievementBox;

/*
 * Created by Arjun Gupta
 */

public class TestAchievementBox {
	
	@Test
	public void testCheckBoundGeneral() {
		AchievementBox testCheckBound = new AchievementBox(Achievement.getAchievements()[1].leftBound(), Achievement.getAchievements()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 380, 150);
		
		assertEquals(true, output);
	}
	
	@Test
	public void testCheckBoundLeft() {
		AchievementBox testCheckBound = new AchievementBox(Achievement.getAchievements()[1].leftBound(), Achievement.getAchievements()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 300, 150);
		
		assertEquals(false, output);
	}
	
	@Test
	public void testCheckBoundRight() {
		AchievementBox testCheckBound = new AchievementBox(Achievement.getAchievements()[1].leftBound(), Achievement.getAchievements()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 500, 150);
		
		assertEquals(false, output);
	}
	
	@Test
	public void testCheckBoundUpper() {
		AchievementBox testCheckBound = new AchievementBox(Achievement.getAchievements()[1].leftBound(), Achievement.getAchievements()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 380, 130);
		
		assertEquals(false, output);
	}
	
	@Test
	public void testCheckBoundLower() {
		AchievementBox testCheckBound = new AchievementBox(Achievement.getAchievements()[1].leftBound(), Achievement.getAchievements()[1].upperBound(), 1, true);
		boolean output = testCheckBound.checkBound(1, 380, 250);
		
		assertEquals(false, output);
	}
}
