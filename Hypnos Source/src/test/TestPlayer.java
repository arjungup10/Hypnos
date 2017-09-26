package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.entity.Enemy;
import logic.entity.Player;
import logic.entity.enemies.EnemyFactory;
import logic.levels.TileMap;
import logic.main.Game;

/*Created by Andrew Ly and Jonathan Chianglin*/

public class TestPlayer {
	
	private Player player;
	private boolean onScreen;

	private ArrayList<Enemy> enemies = new ArrayList<>(); // empty ArrayList
	private EnemyFactory enemyFactory = new EnemyFactory(); 
	private TileMap tileMap; 
	private String enemyType = "spider";
	private Enemy e;
	
	@Before
	public void init() throws IOException {
		tileMap = new TileMap(75);
		player = new Player(tileMap, Game.getInstance());
		
		tileMap.loadTiles("/level1_tileset.png");
		tileMap.loadMap("/level1.map");
		tileMap.setPosition(0, 0);
	}
	
	@Test
	public void testPlayerPositionRightScreen() {
		
		player.setPosition(1000, 100);
		onScreen = player.onScreen();
		
		assertEquals(false, onScreen);
	}
	
	@Test
	public void testPlayerPositionLeftScreen() {
		player.setPosition(-100, 100);
		onScreen = player.onScreen();
		
		assertEquals(false, onScreen);
	}
	
	@Test
	public void testPlayerPositionAboveScreen() {
		player.setPosition(100,-100);
		onScreen = player.onScreen();
		
		assertEquals(false, onScreen);
	}
	
	@Test
	public void testPlayerPositionBelowScreen() {
		player.setPosition(100, 1000);
		onScreen = player.onScreen();
		
		assertEquals(false, onScreen);
	}
	
	@Test
	public void testPlayerPositionOnScreen() {
		player.setPosition(100, 100);
		onScreen = player.onScreen();
		
		assertEquals(true, onScreen);
	}
	
	// Loop Testing
	@Test
	public void testHitZero() {
		player.setPosition(100, 100);
		player.checkHit(enemies);
		
		int expected = 5;
		assertEquals(expected, player.getHealth()); 
		
	}
	
	@Test
	public void testHitOne() {
		player.setPosition(100, 100);

		e = enemyFactory.getEnemy(enemyType, tileMap);
		e.setPosition(100, 100);
		enemies.add(e); 
		
		player.checkHit(enemies);

		int expected = 4;
		assertEquals(expected, player.getHealth()); 
	}
	
	@Test
	public void testHitMany() {
		player.setPosition(100, 100);

		e = enemyFactory.getEnemy(enemyType, tileMap);
		e.setPosition(100, 100);
		enemies.add(e); 
		Enemy e2 = enemyFactory.getEnemy(enemyType, tileMap);
		e2.setPosition(200, 200);
		enemies.add(e2); 
		player.checkHit(enemies);
		
		player.setPosition(200, 200);
		player.checkHit(enemies);
	
		int expected = 4;
		assertEquals(expected, player.getHealth());
	}
}