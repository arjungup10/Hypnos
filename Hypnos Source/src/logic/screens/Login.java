package logic.screens;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class Login extends Screen{
	// Width and Height calculations with Font style
	private int widthBuffer = Game.GAMEWIDTH / 6;
	private int heightBuffer = Game.GAMEHEIGHT / 6;

	// Background of the Screen
	private BufferedImage bg = null;
	private Rectangle achieveBox = new Rectangle(widthBuffer, heightBuffer,
			Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	private Rectangle exitBox = new Rectangle(Game.GAMEWIDTH - 150,
			Game.GAMEHEIGHT - 30, 150, 40);
	private Rectangle gameBox = new Rectangle(0, 0, Game.GAMEWIDTH, Game.GAMEHEIGHT);

	public Login(Game game) throws IOException {
		super(game);
	}


	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(bg, 0, 0, null);

		g.setColor(new Color(150, 150, 150, 150));
		g.fillRect(gameBox.x, gameBox.y, gameBox.width, gameBox.height);

		g.setFont(Styles.titlefont);
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(achieveBox.x, achieveBox.y, achieveBox.width,
				achieveBox.height);
		g2d.draw(achieveBox);
		g.setColor(Color.black);
		g.drawString("LOGIN", Game.GAMEWIDTH / 2 - 80, 60);

		g.setFont(Styles.bodyfont);
		
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(exitBox.x, exitBox.y, exitBox.width, exitBox.height);
		g.setColor(Color.black);
		g.drawString("exit game.", Game.GAMEWIDTH - 130, Game.GAMEHEIGHT - 10);

	}
}

