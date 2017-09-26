package test;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import logic.main.Game;
import logic.entity.Player;
import logic.levels.TileMap;

/* Created by Audrey Chan */

public class TestPlayerLoop {
	Player testPlayer;
	boolean result;
	Collection<BufferedImage[]> sprites;
	
	@Before
	public void init() throws IOException {
		testPlayer = new Player(new TileMap(75), Game.getInstance());
	}
	
	@Test
	public void testPlayerLoopGeneral() {
		sprites = testPlayer.getSprites();
		assertEquals(false, sprites.isEmpty());
	}
}
