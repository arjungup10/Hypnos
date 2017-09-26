package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import logic.entity.enemies.Spider;
import logic.levels.TileMap;

/*Created by Andrew Ly*/

public class TestSpider {
	
	@Test
	public void testSpiderHitWallFacingRight() throws IOException {
		TileMap tileMap = new TileMap(75);
		Spider s = new Spider(tileMap);
		

		tileMap.loadTiles("/level1_tileset.png");
		tileMap.loadMap("/level1.map");
		tileMap.setPosition(0, 0);
		
		s.setRight(true);
		s.setDx(0);
		
		s.checkHitWall();
		
		assertEquals(false, s.isRight());
	}
	
	@Test
	public void testSpiderHitWallFacingLeft() throws IOException {
		TileMap tileMap = new TileMap(75);
		Spider s = new Spider(tileMap);
		

		tileMap.loadTiles("/level1_tileset.png");
		tileMap.loadMap("/level1.map");
		tileMap.setPosition(0, 0);
		
		s.setRight(false);
		s.setLeft(true);
		s.setDx(0);
		
		s.checkHitWall();
		
		assertEquals(true, s.isRight());
	}
}