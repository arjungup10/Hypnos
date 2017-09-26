package logic.screens;

import java.awt.*;

public class AchievementBox extends Box{
	
	private boolean unlocked;
	private int level;
	
	public AchievementBox(int x, int y, int level, boolean unlocked) {
		super(x, y);
		this.level = level;
		this.unlocked = unlocked;
	}
	
	public void render(Graphics g) {
		//Determines color based off of Unlocked
		if (unlocked) {
			g.setColor(Color.black);
			g.setFont(new Font ("Courier New", Font.BOLD, 40));
			g.drawString(((Integer)level).toString(), x + 35, y + 55);
			g.setColor(new Color(147, 234, 170, 150));
		}
		else {
			g.setColor(Color.black);
			g.setFont(new Font ("Courier New", Font.BOLD, 40));
			g.drawString("?", x + 35, y + 55);
			
			g.setColor(new Color(192, 192, 192, 150));
		}
		   
		g.fillRect(x, y, width, height);
		g.drawRect(x, y, width, height);		
		
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean isUnlocked() {
		return unlocked;
	}

	public boolean checkBound(int achv, int mouseX, int mouseY) {
		if ((mouseX > Achievement.achvList[achv].leftBound()) && (mouseX < Achievement.achvList[achv].rightBound()) &&
			(mouseY > Achievement.achvList[achv].upperBound()) && (mouseY < Achievement.achvList[achv].lowerBound())) { 
				return true;
		}
			
		return false;
	}
}
