package test;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;

import org.junit.Test;

import logic.entity.Animation;

/*Created by Audrey Chan*/

public class TestAnimation {
	@Test
	public void testGetFrameInit(){
		Animation testAnimation = new Animation();
		BufferedImage[] frames = new BufferedImage[2];
		testAnimation.setFrames(frames);
		
		assertEquals((long)0, (long)testAnimation.getFrame());
	}
	
	@Test
	public void testHasPlayedOnceInit(){
		Animation testAnimation = new Animation();
		assertEquals(false, testAnimation.hasPlayedOnce());
	}
	
	@Test
	public void testSetFrameGeneral(){
		Animation testAnimation = new Animation();
		testAnimation.setFrame(1);
		
		assertEquals(1, testAnimation.getFrame());
	}
	
	@Test
	public void testUpdateCurrentFrame() {
		Animation testAnimation = new Animation();
		BufferedImage[] frames = new BufferedImage[2];
		testAnimation.setFrames(frames);
		testAnimation.setFrame(2);
		testAnimation.update();
		
		assertEquals(0, testAnimation.getFrame(), 0);
	}
	
	

}
