package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import logic.main.Game;
import logic.main.Styles;

public class Controls extends Screen{
	private static final int WIDTHBUFFER = Game.GAMEWIDTH/6; //130
	private static final int HEIGHTBUFFER = Game.GAMEHEIGHT/6; 
	
	private Rectangle controlsBox = new Rectangle(WIDTHBUFFER, HEIGHTBUFFER, Game.GAMEWIDTH - (WIDTHBUFFER * 2), Game.GAMEHEIGHT - (HEIGHTBUFFER * 2));
	
	public static final int MENU_L_BOUND = 150;
	public static final int MENU_R_BOUND = 250;
	public static final int MENU_T_BOUND = 457;
	public static final int MENU_B_BOUND = 470;
	
	public static final int CONTROL_L_BOUND = 445;
	public static final int CONTROL_R_BOUND = 635;
	public static final int CONTROL_T_BOUND = MENU_T_BOUND;
	public static final int CONTROL_B_BOUND = MENU_B_BOUND;
	
	
	private Map<String, Integer> controlsMap = new HashMap<>();
	
	public Controls(Game game) throws IOException {
		super(game);
		initControlsMap();
	}
	
	public void render(Graphics g) {
    	g.drawImage(bg, 0, 0, null);
    	
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(Styles.titlefont);
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(controlsBox.x, controlsBox.y, controlsBox.width, controlsBox.height);
		g2d.draw(controlsBox);
		g.setColor(Color.black);
		g.drawString("CONTROLS", Game.GAMEWIDTH/2 - 150, 60);
		
		g.setFont(Styles.bodyfont);
		g.drawString("up", Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4);
		g.drawString("W", Game.GAMEWIDTH - Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4);
		g.drawString("left", Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4 + Game.GAMEHEIGHT/15);
		g.drawString("A", Game.GAMEWIDTH - Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4 + Game.GAMEHEIGHT/15);
		g.drawString("down", Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4 + 2*Game.GAMEHEIGHT/15);
		g.drawString("S", Game.GAMEWIDTH - Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4 + 2*Game.GAMEHEIGHT/15);
		g.drawString("right", Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4 + 3*Game.GAMEHEIGHT/15);
		g.drawString("D", Game.GAMEWIDTH - Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4 + 3*Game.GAMEHEIGHT/15);
		g.drawString("use companion", Game.GAMEWIDTH/3 - 30, Game.GAMEHEIGHT/4 + 4*Game.GAMEHEIGHT/15);
		g.drawString("space", Game.GAMEWIDTH - Game.GAMEWIDTH/3, Game.GAMEHEIGHT/4 + 4*Game.GAMEHEIGHT/15);
		g.drawString("return to options", CONTROL_L_BOUND, CONTROL_B_BOUND);
		g.drawString("main menu", MENU_L_BOUND, MENU_B_BOUND);
	}
	
	public void initControlsMap() {
		controlsMap.put("up", KeyEvent.VK_W);
		controlsMap.put("left", KeyEvent.VK_A);
		controlsMap.put("down", KeyEvent.VK_S);
		controlsMap.put("right", KeyEvent.VK_D);
		controlsMap.put("companion", KeyEvent.VK_SPACE);
	}
	
	public Map<String, Integer> getMap() {
		return controlsMap;
	}
}
