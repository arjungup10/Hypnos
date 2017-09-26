package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.entity.Enemy;
import logic.entity.Player;
import logic.entity.enemies.Spider;
import logic.levels.TileMap;
import logic.main.Game;

/* Created by Andrew Ly 3/12/2017*/

public class TestPlayerEnemy {

	private TileMap tileMap;
	private Player player;
	
	@Before
	public void init() throws IOException {
		tileMap = new TileMap(75);
		player = new Player(tileMap, Game.getInstance());
		
		tileMap.loadTiles("/level1_tileset.png");
		tileMap.loadMap("/level1.map");
		tileMap.setPosition(0, 0);
	}
	
	@Test
	public void testSpiderHitPlayer() throws IOException {
		ArrayList<Enemy> enemies = new ArrayList<>();
		Enemy hitSpider = new Spider(tileMap);
		
		player.setPosition(100, 100);
		hitSpider.setPosition(100, 100);
		enemies.add(hitSpider);
		
		player.checkHit(enemies);
		
		int expected = 4;
		int result = player.getHealth();
		
		assertEquals(expected, result);
		
	}

}
