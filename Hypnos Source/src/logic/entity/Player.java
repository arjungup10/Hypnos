package logic.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import logic.levels.*;
import logic.main.BufferedImageLoader;
import logic.main.Game;
import logic.main.Styles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends MapObject {
	
	//information about the player
	private Game game;
	
	private int maxHealth;
	private int health;
	private boolean hit;
	private long hitTimer;
	
	//Animations
	private ArrayList<BufferedImage[]> sprites;
	private static final int[] numFrames = {1, 8, 1, 1, 1};
	private static final int SPRITE_H = 50;
	private static final int SPRITE_W = 50;	
	
	//Animation Actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int ATTACKING = 4;
	
	public Player(TileMap tm, Game game) throws IOException {
		super(tm);
		this.game = game;
		
		width = 75;
		height = 75;
		cwidth = 45;
		cheight = 75;
		
		moveSpeed = 0.6;
		maxSpeed = 3.2;
		stopSpeed = 0.8;
		fallSpeed = 0.30;
		maxFallSpeed = 8.0;
		jumpStart = -10;
		stopJumpSpeed = 0.6;
		
		facingRight = true;
		
		health = maxHealth = 5;
		
		BufferedImageLoader loader = new BufferedImageLoader();
    	
		BufferedImage spritesheet = loader.loadImage("/playerframes.png");
		sprites = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			BufferedImage[] bi = new BufferedImage[numFrames[i]];
			for (int j = 0; j < numFrames[i]; j++) {
				bi[j] = spritesheet.getSubimage(j * SPRITE_W, i * SPRITE_H, SPRITE_W, SPRITE_H);
			}
			sprites.add(bi);
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		hit = false;
	}
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public Collection<BufferedImage[]> getSprites() { return sprites; }
	
	public void checkHit(Collection<Enemy> enemies) {
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			checkHitIntersection(iterator.next());
		}
	}

	public void checkHitIntersection(Enemy e) {
		if (this.intersects(e)) {
			this.hit(e.getDamage());
		}
	}
	
	public void hit(int damage) {
		if(hit) {
			return;
		}
		
		health -= damage;
		
		if (health < 0) {
			health = 0;
		}
		
		if (health == 0) {
			dead = true;
		}
		
		hit = true;
		hitTimer = System.nanoTime();
	}
	
	private void getNextPosition() {
		
		if (tileMap.outOfBounds(currRow) == 1) {
			setPosition(200, 200);
			health = 0;
			game.setPrevState(Game.STATE.LEVEL_1);
			game.setCurState(Game.STATE.GAME_OVER);
			
		}

		if (gety() <= 0) {
			jumping = false;
			falling = true;
		}
		
		//movement
		setMovement();
		
		// cannot attack while moving except in air
		if ((currentAction == ATTACKING) && !(jumping || falling)) {
			setDx(0);
		}
		
		// jumping
		if (jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}
		
		// falling
		checkFalling();
	}
	
	private boolean notIdle() {
		return left || isRight();
	}
	
	private void setAnimations() {
		if (notIdle() && !jumping && !falling) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(100);
			}
		}
		else if (jumping) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
			}
		}
		else if (falling) {
			if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(-1);
			}
		} 
		else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(-1);
			}
		}
	}
	
	public void update() {
		//update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		//set animations
		setAnimations();
		
		animation.update();
		
		if (hit) {
			long elapsed = (System.nanoTime() - hitTimer) / 1000000;
			if (elapsed > 1000) {
				hit = false;
			}
		}
		
	}
	
	public void render (Graphics2D g) {
    	
		//Set the map position based off of player
		setMapPosition();
		
		//draw player
		if(hit) {
			long elapsed = (System.nanoTime() - hitTimer) / 1000000;
			
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		
		super.render(g);
		
		//draw health
		g.setColor(Color.BLACK);
		g.setFont(Styles.bodyfont);
		g.drawString("Health: " + health, 100, 50);
		
		
	}
	
	public boolean getLeft() {
		return left;
	}
	
	
}
