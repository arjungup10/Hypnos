package logic.screens;

public abstract class Box {
	
	protected static int width = 90;
	protected static int height = 90;
	
	protected int x;
	protected int y;
	
	public Box(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int leftBound() {
		return x;
	}
	
	public int rightBound() {
		return x + width;
	}
	
	public int upperBound() {
		return y;
	}
	
	public int lowerBound() {
		return y + height;
	}
	
}
