package logic.screens;

import java.awt.image.BufferedImage;
import java.io.IOException;

import logic.main.BufferedImageLoader;
import logic.main.Game;

public abstract class Screen {

	protected Game game;
	protected BufferedImage bg;
	
	public Screen(Game game) throws IOException {
		this.game = game;
		
		BufferedImageLoader loader = new BufferedImageLoader();
    	
    	bg = loader.loadImage("/hypnosbackground.jpg");

		
	}
}
