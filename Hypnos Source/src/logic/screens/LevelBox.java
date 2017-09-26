package logic.screens;

import java.awt.Color;
import java.awt.Graphics;

public class LevelBox extends Box{


	
	private boolean unlocked;
	private int level;
	
	public LevelBox(int x, int y, int level, boolean unlocked) {
		super(x,y);
		this.level = level;
		this.unlocked = unlocked;
	}
	
	public void render(Graphics g) {
		//Determines color based off of Unlocked
		if (unlocked) {
		   g.setColor(new Color(147, 234, 170));
		}
		else {
			g.setColor(Color.pink);
		}
		   
		g.fillRect(x, y, width, height);
		g.drawRect(x, y, width, height);
		
		//Level Indicator
		g.setColor(Color.black);
		g.drawString(((Integer)level).toString(), x + 30, y + 65);
		
	}

	public int getLevel() {
		return level;
	}
	
	public boolean isUnlocked() {
		return unlocked;
	}

	public boolean checkBound(int level, int mouseX, int mouseY) {
		if ((mouseX > LevelSelect.getLevels()[level].leftBound()) && (mouseX < LevelSelect.getLevels()[level].rightBound()) &&
			(mouseY > LevelSelect.getLevels()[level].upperBound()) && (mouseY < LevelSelect.getLevels()[level].lowerBound())) { 
			return true;
		}
			return false;
	}

}
