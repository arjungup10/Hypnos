package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class LevelSelect extends Screen{

	//Width and Height calculations with Font style
	private int widthBuffer = Game.GAMEWIDTH/6;
	private int widthBuffer2 = widthBuffer + 20;
	private int heightBuffer = Game.GAMEHEIGHT/6;
	
	//Background of the Screen
	private Rectangle levelSelectBox = new Rectangle(widthBuffer, heightBuffer, Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	
	//List containing the Levels
	private static final LevelBox[] levelList = {new LevelBox(Game.GAMEWIDTH / 5 + 30, Game.GAMEHEIGHT / 4, 1, true),
			                                    new LevelBox(Game.GAMEWIDTH / 2 - 50, Game.GAMEHEIGHT / 4, 2, false),
			                                    new LevelBox((Game.GAMEWIDTH * 3) / 5 + 30, Game.GAMEHEIGHT / 4, 3, false),
			                                    new LevelBox(Game.GAMEWIDTH / 5 + 30, Game.GAMEHEIGHT * 2 / 4, 4, false),
			                                    new LevelBox(Game.GAMEWIDTH / 2 - 50, Game.GAMEHEIGHT * 2 / 4, 5, false),
			                                    new LevelBox((Game.GAMEWIDTH * 3) / 5 + 30, Game.GAMEHEIGHT * 2 / 4, 6, false)};
	  
	public LevelSelect(Game game) throws IOException {
		super(game);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
    	g.drawImage(bg, 0, 0, null);
    	g.setFont(Styles.titlefont);
    	g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(levelSelectBox.x, levelSelectBox.y, levelSelectBox.width, levelSelectBox.height);
		g2d.draw(levelSelectBox);
		g.setColor(Color.black);
		g.drawString("LEVEL SELECT", Game.GAMEWIDTH/2 - 210, 60);
		
		drawLevels(g);
		
		g.setFont(Styles.bodyfont);
		g.drawString("main menu", widthBuffer2, Game.GAMEHEIGHT - Game.GAMEHEIGHT/5);

	}
	
	public void drawLevels(Graphics g) {
		for (int i = 0; i < levelList.length; i++) {
			levelList[i].render(g);
		}
	}
	
	public static LevelBox[] getLevels() {
		return levelList;
	}

}
