package test;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import logic.main.Game;
import logic.screens.Options;

/*
 * Created by Arjun Gupta
 */

public class TestSetVolumeRectanglesLoop {
	
	Options optionsToTest;
	private Rectangle[] sfxVolBoxes;
	private Rectangle[] mVolBoxes;
	public static final int VOLUME_L_BOUND = 170;
	public static final int SFX_VOLUME_T_BOUND = 220;
	public static final int M_VOLUME_T_BOUND = 335;	
	
	@Before
	public void init() throws IOException {
		optionsToTest = new Options(Game.getInstance());
	}

	
	@Test
	public void testSFXFirst() {
		optionsToTest.setVolumeRectangles();
		sfxVolBoxes = optionsToTest.getsfxVolBoxes();
		Rectangle expectedRec = new Rectangle((VOLUME_L_BOUND + 190) + (27 * 0), SFX_VOLUME_T_BOUND, 20, 20);
		assertEquals(expectedRec, sfxVolBoxes[0]);
	}
	
	@Test
	public void testMFirst() {
		optionsToTest.setVolumeRectangles();
		mVolBoxes = optionsToTest.getmVolBoxes();
		Rectangle expectedRec = new Rectangle((VOLUME_L_BOUND + 190) + (27 * 0), M_VOLUME_T_BOUND, 20, 20);
		assertEquals(expectedRec, mVolBoxes[0]);
	}
	
	@Test
	public void testSFXSecond() {
		optionsToTest.setVolumeRectangles();
		sfxVolBoxes = optionsToTest.getsfxVolBoxes();
		Rectangle expectedRec = new Rectangle((VOLUME_L_BOUND + 190) + (27 * 1), SFX_VOLUME_T_BOUND, 20, 20);
		assertEquals(expectedRec, sfxVolBoxes[1]);
	}
	
	@Test
	public void testMSecond() {
		optionsToTest.setVolumeRectangles();
		mVolBoxes = optionsToTest.getmVolBoxes();
		Rectangle expectedRec = new Rectangle((VOLUME_L_BOUND + 190) + (27 * 1), M_VOLUME_T_BOUND, 20, 20);
		assertEquals(expectedRec, mVolBoxes[1]);
	}
	
	@Test
	public void testSFXLast() {
		optionsToTest.setVolumeRectangles();
		sfxVolBoxes = optionsToTest.getsfxVolBoxes();
		Rectangle expectedRec = new Rectangle((VOLUME_L_BOUND + 190) + (27 * (sfxVolBoxes.length - 1)), SFX_VOLUME_T_BOUND, 20, 20);
		assertEquals(expectedRec, sfxVolBoxes[sfxVolBoxes.length - 1]);
	}
	
	@Test
	public void testMLast() {
		optionsToTest.setVolumeRectangles();
		mVolBoxes = optionsToTest.getmVolBoxes();
		Rectangle expectedRec = new Rectangle((VOLUME_L_BOUND + 190) + (27 * (mVolBoxes.length - 1)), M_VOLUME_T_BOUND, 20, 20);
		assertEquals(expectedRec, mVolBoxes[mVolBoxes.length - 1]);
	}
}
