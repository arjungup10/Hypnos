package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class GameOver extends Screen{

	public GameOver(Game game) throws IOException {
		super(game);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		String[] gameOverText = {"GAME OVER", "play again?", "main menu"};
		
		g2d.clearRect(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);
		
		//Drawing the background
		g2d.setPaint(new Color (0, 0, 0));
    	g2d.fillRect(0, 0, Game.GAMEWIDTH / 2, Game.GAMEHEIGHT);
    	
    	g2d.setPaint(new Color (126, 126, 126));
    	g2d.fillRect(Game.GAMEWIDTH / 2, 0, Game.GAMEWIDTH / 2, Game.GAMEHEIGHT);
    	
    	//Drawing Text
    	g2d.setFont(Styles.titlefont);
    	g2d.setColor(Color.WHITE);
    	g2d.drawString(gameOverText[0], Game.GAMEWIDTH / 2 - 160 , Game.GAMEHEIGHT / 2);
    	
    	g2d.setFont(Styles.bodyfont);
    	g2d.setColor(Color.WHITE);
    	g2d.drawString(gameOverText[1], Game.GAMEWIDTH / 2 - 60, Game.GAMEHEIGHT / 2 + 40);
    	g2d.drawString(gameOverText[2], Game.GAMEWIDTH / 2 - 60, Game.GAMEHEIGHT / 2 + 2 * 40);
	}
	
}
