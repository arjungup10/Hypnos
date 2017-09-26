package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class Credits extends Screen{
	private int widthBuffer = Game.GAMEWIDTH/6; //130
	private int heightBuffer = Game.GAMEHEIGHT/6; 
	
	private Rectangle creditsBox = new Rectangle(widthBuffer, heightBuffer, Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	
	public static final int MENU_L_BOUND = 150;
	public static final int MENU_R_BOUND = 250;
	public static final int MENU_T_BOUND = 457;
	public static final int MENU_B_BOUND = 470;
	
	public static final int CONTROL_L_BOUND = 445;
	public static final int CONTROL_R_BOUND = 635;
	public static final int CONTROL_T_BOUND = MENU_T_BOUND;
	public static final int CONTROL_B_BOUND = MENU_B_BOUND;
	
	public static final int TITLE_L_BOUND = 170;
	public static final int TITLE_B_BOUND = 135;
	public static final int TITLE_T_BOUND = TITLE_B_BOUND - 15;
	
	private int vSpace = 30;
	
	public Credits(Game game) throws IOException {
		super(game);
	}
	
	public void render(Graphics g) {
    	g.drawImage(bg, 0, 0, null);
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(Styles.titlefont);
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(creditsBox.x, creditsBox.y, creditsBox.width, creditsBox.height);
		g2d.draw(creditsBox);
		g.setColor(Color.black);
		g.drawString("CREDITS", Game.GAMEWIDTH/2 - 150, 60);
		
		g.setFont(Styles.bodyfont);
		g.drawString("created by:", TITLE_L_BOUND - 15, TITLE_B_BOUND);
		g.drawString("audrey chan", TITLE_L_BOUND, TITLE_B_BOUND + vSpace);
		g.drawString("jonathan chianglin", TITLE_L_BOUND, TITLE_B_BOUND + (vSpace * 2));
		g.drawString("natasha cortez", TITLE_L_BOUND, TITLE_B_BOUND + (vSpace * 3));
		g.drawString("arjun gupta", TITLE_L_BOUND, TITLE_B_BOUND + (vSpace * 4));
		g.drawString("andrew ly", TITLE_L_BOUND, TITLE_B_BOUND + (vSpace * 5));
		g.drawString("elyse munemura", TITLE_L_BOUND, TITLE_B_BOUND + (vSpace * 6));
		
		g.drawString("sound & music credits:", TITLE_L_BOUND - 15, TITLE_B_BOUND + (vSpace * 8));
		g.drawString("bensound", TITLE_L_BOUND, TITLE_B_BOUND + (vSpace * 9));
		g.drawString("main menu", MENU_L_BOUND, MENU_B_BOUND);
	}
}