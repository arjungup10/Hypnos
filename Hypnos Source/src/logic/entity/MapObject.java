package logic.entity;

import java.awt.Rectangle;

import logic.levels.Tile;
import logic.levels.TileMap;
import logic.main.Game;

public abstract class MapObject {

	//Tile
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	//position and vector
	protected double x;
	protected double y;
	private double dx;
	protected double dy;
	
	//dimensions
	protected int width;
	protected int height;
	
	//collisions box
	protected int cwidth;
	protected int cheight;
	
	//collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	//animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	//movement
	protected boolean left;
	private boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	//movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	protected boolean dead;
	
	//constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	public boolean intersects(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - cwidth / 2, (int)y - cheight / 2, cwidth, cheight);
	}
	
	public void calculateCorners(double x, double y) {
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
	}
	
	private void checkMovingDown() {
		if (dy < 0) {
			if (topLeft || topRight) {
				dy = 0;
				ytemp = (double)currRow * tileSize + cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
	}
	
	private void checkMovingUp() {
		if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				ytemp = (double)(currRow + 1) * tileSize - cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
	}
	
	private void checkMovingLeft() {
		if (getDx() < 0) {
			facingRight = false;
			if (topLeft || bottomLeft) {
				setDx(0);
				xtemp = (double)currCol * tileSize + cwidth / 2;
			}
			else {
				xtemp += getDx();
			}
		}
	}
	
	private void checkMovingRight() {
		if (getDx() > 0) {
			facingRight = true;
			if (topRight || bottomRight) {
				setDx(0);
				xtemp = (double)(currCol + 1) * tileSize - cwidth / 2;
			}
			else {
				xtemp += getDx();
			}
		}
	}
	
	public void checkTileMapCollision() {
		
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		
		xdest = x + getDx();
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
		
		checkMovingDown();
		checkMovingUp();
		
		calculateCorners(xdest, y);
		
		checkMovingRight();
		checkMovingLeft();
		
		if (!falling) {
			calculateCorners(x, ydest + 1);
			if (!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
	}
	
	public int getx() {return (int)x; }
	public int gety() {return (int)y; }
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getCWidth() {return cwidth;}
	public int getCHeight() {return cheight;}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy) {
		this.setDx(dx);
		this.dy = dy;
	}
	
	public void setMapPosition() {
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	
	public void setLeft(boolean b) { 
		left = b;
	}
	public void setRight(boolean b) { 
		right = b;
	}
	public void setUp(boolean b) { 
		up = b;
	}
	public void setDown(boolean b) { 
		down = b;
	}
	public void setJumping(boolean b) {
		jumping = b;
	}
	
	public boolean onScreen() {
		return (x + xmap + width > 0 &&
			   x + xmap - width < Game.GAMEWIDTH) &&
			   (y + ymap + height > 0 &&
			   y + ymap - height < Game.GAMEHEIGHT);
	}
	
	public void render(java.awt.Graphics2D g) {
		if (facingRight) {
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), width, height, null);
		}
		else {
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
		}
	}
	
	public void setMovement() {
		if (left) {
			setDx(getDx() - moveSpeed);
			compToMaxSpeed();
		}
		else if (isRight()) {
			setDx(getDx() + moveSpeed);
			compToMaxSpeed();
		}	
		else {
			if (getDx() > 0) {
				setDx(getDx() - stopSpeed);
				if (getDx() < 0) {
					setDx(0);
				}
			}
			else if (getDx() < 0) {
				setDx(getDx() + stopSpeed);
				if (getDx() > 0) {
					setDx(0);
				}
			}
		}
	}
	
	public void compToMaxSpeed(){
		if (getDx() < -maxSpeed) {
			setDx(-maxSpeed);
		}
		
		if (getDx() > maxSpeed) {
			setDx(maxSpeed);
		}
	}
	
	public void checkFalling() {
		if (falling) {
			dy += fallSpeed;
			
			if (dy > 0) {
				jumping = false;
			}
			if (dy < 0 && !jumping) {
				dy += stopJumpSpeed;
			}
			if (dy > maxFallSpeed) {
				dy = maxFallSpeed;
			}
		}
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public boolean isRight() {
		return right;
	}

}
