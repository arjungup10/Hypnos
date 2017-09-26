package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class Help extends Screen{
	
	//Content box width, height, and calculations
	private int widthBuffer = Game.GAMEWIDTH / 6;
	private int menuBuffer = widthBuffer + 20;
	private int heightBuffer = Game.GAMEHEIGHT / 6;
	
	private Rectangle contentBox = new Rectangle(widthBuffer, heightBuffer, Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	
	// temporary text
	private String temp = "Lorem ipsum dolor sit amet, consectetur ...";
	
	public Help(Game game) throws IOException {
		super(game);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(bg, 0, 0, null);
		
    	g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(contentBox.x, contentBox.y, contentBox.width, contentBox.height);
		g2d.draw(contentBox);
		
		g.setFont(Styles.titlefont);
		g.setColor(Color.BLACK);	
		g.drawString("HELP", (Game.GAMEWIDTH/2) - 75, 50);

		g.setFont(Styles.bodyfont);
		g.drawString(temp, contentBox.x + Styles.bodyfont.getSize(), contentBox.y + Styles.bodyfont.getSize());

		g.drawString("main menu", menuBuffer, Game.GAMEHEIGHT - Game.GAMEHEIGHT/5);
	}

	
}
