package logic.entity.enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import logic.entity.Enemy;
import logic.levels.TileMap;
public class Spider extends Enemy {

	private Rectangle rect;
	
	public Spider(TileMap tm) {
		super(tm);
		
		moveSpeed = 0.6;
		maxSpeed = 0.6;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		
		width = 20;
		height = 20;
		cwidth = 15;
		cheight = 15;
		
		health = maxHealth = 2;
		damage = 1;
		
		rect = new Rectangle((int)x, (int)y, width, height);

		facingRight = true;
		setRight(true);
	}
	
	private void getNextPosition() {
		setMovement();
		checkFalling();
		
	}
	
	public void update() {
		//update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		checkHitWall();
	}
	
	public void render(Graphics2D g) {
		setMapPosition();
		
		g.setColor(Color.RED);
	
		rect.setLocation((int)(x + xmap - width / 2), (int)(y + ymap - height / 2));

		g.draw(rect);
		
	}
	
	public void checkHitWall() {
		if (isRight() && getDx() == 0) {
			setRight(false);
			left = true;
		}
		else if (left && getDx() == 0) {
			setRight(true);
			left = false;

		}
	}
	
	
}
