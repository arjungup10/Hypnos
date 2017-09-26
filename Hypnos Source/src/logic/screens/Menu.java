package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class Menu extends Screen{

	private int widthBuffer = Game.GAMEWIDTH/6;
	private int heightBuffer = Game.GAMEHEIGHT/6;
	private int heightDiff = 50;
	private int titleHeight = 60;

	private Rectangle menuBox = new Rectangle(widthBuffer, heightBuffer, Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	private Rectangle exitBox = new Rectangle(Game.GAMEWIDTH - 150, Game.GAMEHEIGHT - 30, 150, 40);
	
	public Menu(Game game) throws IOException {
		super(game);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
    	g.drawImage(bg, 0, 0, null); 
    	
    	g.setFont(Styles.titlefont);
    	g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(menuBox.x, menuBox.y, menuBox.width, menuBox.height);
		g2d.draw(menuBox);
		g.setColor(Color.black);
		g.drawString("HYPNOS", Game.GAMEWIDTH/2 - (2 * heightDiff), titleHeight);
				
		g.setFont(Styles.bodyfont);
		g.drawString("start", Game.GAMEWIDTH/2 -30, Game.GAMEHEIGHT/5);
		g.drawString("help", Game.GAMEWIDTH/2 - 25, Game.GAMEHEIGHT/5 + heightDiff);
		g.drawString("options", Game.GAMEWIDTH/2 - 40, Game.GAMEHEIGHT/5 + 2 * heightDiff);
		g.drawString("endless mode", Game.GAMEWIDTH/2 - 60, Game.GAMEHEIGHT/5 + 3 * heightDiff);
		g.drawString("high scores", Game.GAMEWIDTH/2 - 55, Game.GAMEHEIGHT/5 + 4 * heightDiff);
		g.drawString("achievements", Game.GAMEWIDTH/2 - 60, Game.GAMEHEIGHT/5 + 5 * heightDiff);
		g.drawString("credits", Game.GAMEWIDTH/2 - 40, Game.GAMEHEIGHT/5 + 6 * heightDiff);
		g.drawString("log out", Game.GAMEWIDTH/2 - 40, Game.GAMEHEIGHT/5 + 7 * heightDiff);
		
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(exitBox.x, exitBox.y, exitBox.width, exitBox.height);
		g.setColor(Color.black);
		g.drawString("exit game.", Game.GAMEWIDTH - 130, Game.GAMEHEIGHT - 10);

	}
	
	public void exitSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - 150 && mouseX <= Game.GAMEWIDTH && mouseY >= Game.GAMEHEIGHT - 30 && mouseY <= Game.GAMEHEIGHT) {
			game.setClose(true);
		}
	}
	
	public void levelSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 40 && mouseX <= Game.GAMEWIDTH/2 + 40 && 
				 mouseY >= Game.GAMEHEIGHT - 490 && mouseY <= Game.GAMEHEIGHT - 460) {
			game.setCurState(Game.STATE.LEVEL_SELECT);
		}
	}
	
	public void helpSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 35 && mouseX <= Game.GAMEWIDTH/2 + 35 && 
				 mouseY >= Game.GAMEHEIGHT - 440 && mouseY <= Game.GAMEHEIGHT - 410) {
			game.setCurState(Game.STATE.HELP);
		}
	}
	
	public void optionsSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 50 && mouseX <= Game.GAMEWIDTH/2 + 50 && 
				 mouseY >= Game.GAMEHEIGHT - 390 && mouseY <= Game.GAMEHEIGHT - 360) {
			game.setCurState(Game.STATE.OPTIONS);
		}
	}
	
	public  void endlessSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 70 && mouseX <= Game.GAMEWIDTH/2 + 80 && 
				 mouseY >= Game.GAMEHEIGHT - 340 && mouseY <= Game.GAMEHEIGHT - 310) {
			//game.curState = Game.STATE.ENDLESS (proposed)
		}
	}
	
	public void highScoresSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 65 && mouseX <= Game.GAMEWIDTH/2 + 65 && 
				 mouseY >= Game.GAMEHEIGHT - 290 && mouseY <= Game.GAMEHEIGHT - 260) {
			game.setCurState(Game.STATE.HIGH_SCORES);
		}
	}
	
	public void achievementSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 70 && mouseX <= Game.GAMEWIDTH/2 + 80 && 
				 mouseY >= Game.GAMEHEIGHT - 240 && mouseY <= Game.GAMEHEIGHT - 210) {
			game.setCurState(Game.STATE.ACHIEVEMENT);
		}
	}
	
	public void creditsSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 50 && mouseX <= Game.GAMEWIDTH/2 + 50 && 
				 mouseY >= Game.GAMEHEIGHT - 190 && mouseY <= Game.GAMEHEIGHT - 160) {
			game.setCurState(Game.STATE.CREDITS);
		}
	}
	
	public void logoutSelect(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH/2 - 50 && mouseX <= Game.GAMEWIDTH/2 + 50 && 
				 mouseY >= Game.GAMEHEIGHT - 140 && mouseY <= Game.GAMEHEIGHT - 110) {
			game.setCurState(Game.STATE.LOGIN);
			LoginApp la = new LoginApp(game); 
			la.show(); 
		}
	}
	
	public void menuSelect(int mouseX, int mouseY) {
		exitSelect(mouseX, mouseY);
		levelSelect(mouseX, mouseY);
		helpSelect(mouseX, mouseY);
		optionsSelect(mouseX, mouseY);
		endlessSelect(mouseX, mouseY);
		highScoresSelect(mouseX, mouseY);
		achievementSelect(mouseX, mouseY);
		creditsSelect(mouseX, mouseY);
		logoutSelect(mouseX, mouseY);	
	}
}