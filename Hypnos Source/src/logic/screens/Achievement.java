package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class Achievement extends Screen {
	
	//Width and Height calculations with Font style
	private int widthBuffer = Game.GAMEWIDTH/6;
	private int widthBuffer2 = widthBuffer + 20;
	private int heightBuffer = Game.GAMEHEIGHT/6;
	
	private Rectangle achieveBox = new Rectangle(widthBuffer, heightBuffer, Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	private Rectangle exitBox = new Rectangle(Game.GAMEWIDTH - 150, Game.GAMEHEIGHT - 30, 150, 40);
	
	protected static final AchievementBox[] achvList = {new AchievementBox(Game.GAMEWIDTH / 5 + 30, Game.GAMEHEIGHT / 4, 1, false),
            new AchievementBox(Game.GAMEWIDTH / 2 - 50, Game.GAMEHEIGHT / 4, 2, false),
            new AchievementBox((Game.GAMEWIDTH * 3) / 5 + 30, Game.GAMEHEIGHT / 4, 3, false),
            new AchievementBox(Game.GAMEWIDTH / 5 + 30, Game.GAMEHEIGHT * 2 / 4, 4, false),
            new AchievementBox(Game.GAMEWIDTH / 2 - 50, Game.GAMEHEIGHT * 2 / 4, 5, false),
            new AchievementBox((Game.GAMEWIDTH * 3) / 5 + 30, Game.GAMEHEIGHT * 2 / 4, 6, false)};
	
	public Achievement(Game game) throws IOException {
		super(game);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
    	g.drawImage(bg, 0, 0, null);
    	    	
    	g.setFont(Styles.titlefont);
    	g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(achieveBox.x, achieveBox.y, achieveBox.width, achieveBox.height);
		
		g2d.draw(achieveBox);
		
		g.setColor(Color.black);
		g.drawString("ACHIEVEMENTS", Game.GAMEWIDTH/2 - 210, 60);
		
		g.setFont(Styles.bodyfont);
		g.drawString("main menu", widthBuffer2 + 200, Game.GAMEHEIGHT - Game.GAMEHEIGHT/5);
		
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(exitBox.x, exitBox.y, exitBox.width, exitBox.height);
		g.setColor(Color.black);
		g.drawString("exit game.", Game.GAMEWIDTH - 130, Game.GAMEHEIGHT - 10);
		
		drawAchievements(g);

	}
	
	public void drawAchievements(Graphics g) {
		for (int i = 0; i < achvList.length; i++) {
			achvList[i].render(g);
		}
	}
	
	public void achieveSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - 150 && mouseX <= Game.GAMEWIDTH && mouseY >= Game.GAMEHEIGHT - 30 && mouseY <= Game.GAMEHEIGHT) {
			game.setClose(true);
		}
		else if (mouseX >= Game.GAMEWIDTH/6 + 220 && mouseX <= Game.GAMEWIDTH/6 + 320 &&
				 mouseY >= Game.GAMEHEIGHT - Game.GAMEHEIGHT/5 - 10 && mouseY <= Game.GAMEHEIGHT - Game.GAMEHEIGHT/5)
			game.setCurState(Game.STATE.MENU);
	}
	
	public static AchievementBox[] getAchievements() {
		return achvList;
	}
}