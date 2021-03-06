package logic.entity;

import logic.levels.TileMap;

public abstract class Enemy extends MapObject{

	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;
	
	public Enemy(TileMap tm) {
		super(tm);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void update() {
		if (health == 0) {
			dead = true;
		}
	}
	
	public void hit(int damage) {
		if (dead) {
			return;
		}
		
		health -= damage;
		
		if (health < 0) {
			health = 0;
		}
		if (health == 0) {
			dead = true;
		}
		
	}
}
