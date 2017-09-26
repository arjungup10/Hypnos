package logic.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import logic.main.Game;
import logic.main.Styles;

public class Options extends Screen{
	
	private int widthBuffer = Game.GAMEWIDTH/6;
	private int heightBuffer = Game.GAMEHEIGHT/6;
	
	public static final  int MENU_L_BOUND = 150;
	public static final int MENU_R_BOUND = 250;
	public static final int MENU_T_BOUND = 457;
	public static final int MENU_B_BOUND = 470;
	
	public static final int CONTROL_L_BOUND = 520;
	public static final int CONTROL_R_BOUND = 610;
	public static final int CONTROL_T_BOUND = MENU_T_BOUND;
	public static final int CONTROL_B_BOUND = MENU_B_BOUND;
	
	public static final int VOLUME_L_BOUND = 170;
	public static final int SFX_VOLUME_B_BOUND = 235;
	public static final int SFX_VOLUME_T_BOUND = SFX_VOLUME_B_BOUND - 15;
	public static final int M_VOLUME_B_BOUND = 350;
	public static final int M_VOLUME_T_BOUND = M_VOLUME_B_BOUND - 15;
	
	private static int sfxVol = 4;
	private static int mVol = 3;
	public static final int MAXVOL = 8;
	
	private Rectangle optionsBox = new Rectangle(widthBuffer, heightBuffer, Game.GAMEWIDTH - (widthBuffer * 2), Game.GAMEHEIGHT - (heightBuffer * 2));
	private Rectangle[] sfxVolBoxes = new Rectangle[MAXVOL];
	private Rectangle[] mVolBoxes = new Rectangle[MAXVOL];
	
	public Options(Game game) throws IOException {
		super(game);
	}
	
	public Rectangle[] getsfxVolBoxes() {
		return sfxVolBoxes;
	}
	
	public Rectangle[] getmVolBoxes() {
		return mVolBoxes;
	}
	
	public static int getSFXVol() {
		return sfxVol;
	}
	
	public static int getMVol() {
		return mVol;
	}
	
	public static void reduceSFX() {
		sfxVol--;
	}
	
	public static void increaseSFX() {
		sfxVol++;
	}
	
	public static void reduceM() {
		mVol--;
	}
	
	public static void increaseM() {
		mVol++;
	}
	
	public void render(Graphics g) {
    	//set volume rectangles
		setVolumeRectangles();
    	
    	g.drawImage(bg, 0, 0, null);
    	
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(Styles.titlefont);
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(optionsBox.x, optionsBox.y, optionsBox.width, optionsBox.height);
		g2d.draw(optionsBox);
		
		int tempSFXVol = sfxVol;
		int tempMVOL = mVol;
		for (int j = 0; j < MAXVOL; j++) {
			g.setColor(Color.darkGray);
			g2d.draw(sfxVolBoxes[j]);
			g2d.draw(mVolBoxes[j]);
			if (tempSFXVol != 0) { //fill volume
				g.setColor(Color.darkGray);
				g.fillRect(sfxVolBoxes[j].x, sfxVolBoxes[j].y, sfxVolBoxes[j].width, sfxVolBoxes[j].height);
				tempSFXVol--;
			}
			else {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(sfxVolBoxes[j].x, sfxVolBoxes[j].y, sfxVolBoxes[j].width, sfxVolBoxes[j].height);
			}
			if (tempMVOL != 0) { //fill volume
				g.setColor(Color.darkGray);
				g.fillRect(mVolBoxes[j].x, mVolBoxes[j].y, mVolBoxes[j].width, mVolBoxes[j].height);
				tempMVOL--;
			}
			else {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(mVolBoxes[j].x, mVolBoxes[j].y, mVolBoxes[j].width, mVolBoxes[j].height);
			}
		}
		
		g.setColor(Color.black);
		
		g.drawString("OPTIONS", Game.GAMEWIDTH/2 - 120, 60);
		
		g.setFont(Styles.bodyfont);
		g.drawString("main menu", MENU_L_BOUND, MENU_B_BOUND);
		g.drawString("controls", CONTROL_L_BOUND, CONTROL_B_BOUND);
		g.drawString("SFX volume", VOLUME_L_BOUND, SFX_VOLUME_B_BOUND);
		g.drawString("music volume", VOLUME_L_BOUND, M_VOLUME_B_BOUND);
		g.drawString("-", VOLUME_L_BOUND + 170, SFX_VOLUME_B_BOUND);
		g.drawString("+", VOLUME_L_BOUND + 170 + (MAXVOL * 30) + 5, SFX_VOLUME_B_BOUND);
		g.drawString("-", VOLUME_L_BOUND + 170, M_VOLUME_B_BOUND);
		g.drawString("+", VOLUME_L_BOUND + 170 + (MAXVOL * 30) + 5, M_VOLUME_B_BOUND);
	}
	
	public void setVolumeRectangles() {
    	for (int i = 0; i < MAXVOL; i++) {
    		sfxVolBoxes[i] = new Rectangle((VOLUME_L_BOUND + 190) + (27 * i), SFX_VOLUME_T_BOUND, 20, 20);
    		mVolBoxes[i] = new Rectangle((VOLUME_L_BOUND + 190) + (27 * i), M_VOLUME_T_BOUND, 20, 20);
    	}
	}
}