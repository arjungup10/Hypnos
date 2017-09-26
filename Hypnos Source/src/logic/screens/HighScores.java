package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class HighScores extends Screen{
	//Content box width, height, and calculations
	private int widthBuffer = Game.GAMEWIDTH / 6;
	private int menuBuffer = widthBuffer + 20;
	private int heightBuffer = Game.GAMEHEIGHT / 6;
	
	private Rectangle contentBox = new Rectangle(widthBuffer, heightBuffer, Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	
	private int titleBuffer = (Game.GAMEWIDTH / 2) - 200;
	private int contTextBufferX = contentBox.x + Styles.bodyfont.getSize();
	private int contTextBufferY = contentBox.y + Styles.bodyfont.getSize();
	
	private int[] score = {10,20,30,40};
	private String[] user = {"one", "two", "three", "four"};
	private static boolean scoresRead = false;
	private int index = 4;	

	public HighScores(Game game) throws IOException {
		super(game);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if (scoresRead) {
			getScores();
		}
		else {
			g.drawImage(bg, 0, 0, null);

	    	g.setColor(new Color(255, 255, 255, 150));
			g.fillRect(contentBox.x, contentBox.y, contentBox.width, contentBox.height);
			g2d.draw(contentBox);
			
			g.setFont(Styles.titlefont);
			g.setColor(Color.BLACK);	
			g.drawString("HIGH SCORES", titleBuffer, 50);

			g.setFont(Styles.bodyfont);
			for (int i = 0; i < index; i++) {
				g.drawString(user[i], contTextBufferX, (i * 25) + contTextBufferY);
				g.drawString("" + Integer.toString(score[i]) + "", contTextBufferX + 425, (i * 25) + contTextBufferY);
			}
			
			g.drawString("main menu", menuBuffer, Game.GAMEHEIGHT - Game.GAMEHEIGHT/5);
		}
	}
	
	public void getScores() {
		// TO-DO
		
	}

	
}
