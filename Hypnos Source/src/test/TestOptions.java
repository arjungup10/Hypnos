package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.screens.Options;

/*Created by Audrey Chan*/

public class TestOptions {
	@Test
	public void testSFXVolumeBounds() {
		int solution = Options.SFX_VOLUME_B_BOUND - 15;
		int output = Options.SFX_VOLUME_T_BOUND;
		
		assertEquals(solution, output, 0);
	}
}
