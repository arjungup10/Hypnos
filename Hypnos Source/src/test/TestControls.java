package test;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import logic.main.Game;
import logic.screens.Controls;

/*
 * Created by Arjun Gupta
 */

public class TestControls {
	
	Map<String, Integer> controlsMap;
	
	@Before
	public void init() throws IOException {
		Controls controlsToTest = new Controls(Game.getInstance());
		controlsMap = controlsToTest.getMap();
	}
	
	
	@Test
	public void testInitControlsUp() {
		int expectedUp = KeyEvent.VK_W;
		int actualUp = (int)controlsMap.get("up");
		assertEquals(expectedUp, actualUp);
	}
	
	@Test
	public void testInitControlsLeft() {
		int expectedLeft = KeyEvent.VK_A;
		int actualLeft = (int)controlsMap.get("left");
		assertEquals(expectedLeft, actualLeft);
	}
	
	@Test
	public void testInitControlsDown() {
		int expectedDown = KeyEvent.VK_S;
		int actualDown = (int)controlsMap.get("down");
		assertEquals(expectedDown, actualDown);
	}
	
	@Test
	public void testInitControlsRight() {
		int expectedRight = KeyEvent.VK_D;
		int actualRight = (int)controlsMap.get("right");
		assertEquals(expectedRight, actualRight);
	}
	
	@Test
	public void testInitControlsCompanion() {
		int expectedCompanion = KeyEvent.VK_SPACE;
		int actualCompanion = (int)controlsMap.get("companion");
		assertEquals(expectedCompanion, actualCompanion);
	}
}
