package logic.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import logic.entity.Enemy;
import logic.entity.Player;
import logic.entity.enemies.EnemyFactory;
import logic.main.BufferedImageLoader;
import logic.main.Game;

public class Level1 {

	private Game game;
	
	private TileMap tileMap;
	private BufferedImage bg;
	
	private Player player;
	
	private double mapX;
	private double mapDx;
	
	private ArrayList<Enemy> enemies;
	
	public Level1(Game game) throws IOException{
		this.game = game;
		init();
	}
	
	public void init() throws IOException{
		tileMap = new TileMap(75);
		tileMap.loadTiles("/level1_tileset.png");
		tileMap.loadMap("/level1.map");
		tileMap.setPosition(0, 0);
		
		BufferedImageLoader loader = new BufferedImageLoader();
    	
    	bg = loader.loadImage("/hypnosbackground.jpg");

    	
    	player = new Player(tileMap, game);
    	player.setPosition(200, 300);
    	
    	mapX = 0;
    	mapDx = 2;
    	
    	enemies = new ArrayList<>();
    	EnemyFactory enemyFactory = new EnemyFactory();
    	Enemy s;
    	s = enemyFactory.getEnemy("spider", tileMap);
    	s.setPosition(100, 100);
    	enemies.add(s);
    	s = enemyFactory.getEnemy("spider", tileMap);
    	s.setPosition(200, 200);
    	enemies.add(s);
	}
	
	public void update() {
		//update player
		player.update();
		mapX += mapDx;
		tileMap.setPosition(Game.GAMEWIDTH /2 - mapX, Game.GAMEHEIGHT / 2 - player.gety());
		
		if (player.getx() < (mapX - player.getWidth() - (Game.GAMEWIDTH / 2)) || player.getHealth() == 0) {
			game.setCurState(Game.STATE.GAME_OVER);
			game.setPrevState(Game.STATE.LEVEL_1);
			game.setLevel1(null);
		}
		
		player.checkHit(enemies);

		//update enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		//clear screen
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		
		//draw tilemap
		g.drawImage(bg, 0, 0, null);
		tileMap.render(g2d);
		
		player.render(g2d);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g2d);
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if (k == KeyEvent.VK_UP) {
			player.setUp(true);
		}
		if (k == KeyEvent.VK_DOWN) {
			player.setDown(true);
		}
		if (k == KeyEvent.VK_W) {
			player.setJumping(true);
		}
		
	}
	
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		if (k == KeyEvent.VK_UP) {
			player.setUp(false);
		}
		if (k == KeyEvent.VK_DOWN) {
			player.setDown(false);
		}
		if (k == KeyEvent.VK_W) {
			player.setJumping(false);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
}
