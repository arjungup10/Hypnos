package logic.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.levels.Level1;
import logic.screens.*;


public class MouseInput implements MouseListener{
	
	private Game game;
	private Logger logger = Logger.getLogger(MouseInput.class.getName());
	public MouseInput(Game game) {
		super();
		this.game = game;
	}
	
	@Override
	public void mouseClicked(MouseEvent arGame0) {
		// Will add when necessary
	}

	@Override
	public void mouseEntered(MouseEvent arGame0) {
		// Will add when necessary
	}

	@Override
	public void mouseExited(MouseEvent arGame0) {
		// Will add when necessary
	}
	
	private void mousePressedMenu (int mx, int my) {
		if(game.getCurState() == Game.STATE.MENU) { //Menu Screen
			checkMenuClick(mx, my);
		}	
	}
	
	private void mousePressedOptions (int mx, int my) throws IOException {
		if(game.getCurState() == Game.STATE.OPTIONS) { //Options Screen
			//click on main menu
			checkOptionClicked(mx, my);
		}
	}
	
	private void mousePressedLevelSelect (int mx, int my) throws IOException {
		if (game.getCurState() == Game.STATE.LEVEL_SELECT) { //Level Select Screen
			checkLevelClicked(mx, my);
		}
	}
	
	private void mousePressedHighScores (int mx, int my) {
		if (game.getCurState() == Game.STATE.HIGH_SCORES){
			checkHighScoresClicked(mx,my);
		}
	}
	
	private void mousePressedHelp (int mx, int my) {
		if (game.getCurState() == Game.STATE.HELP) {
			checkHelpClicked(mx,my);
		}
	}
	
	private void mousePressedAchievement (int mx, int my) {
		if (game.getCurState() == Game.STATE.ACHIEVEMENT) {
			checkAchieveClicked(mx, my);
		}
	}
	
	private void mousePressedLogin (int mx, int my) {
		if (game.getCurState() == Game.STATE.LOGIN) {
			checkLoginClicked(mx, my); 
		}
	}
	
	private void mousePressedControls (int mx, int my) {
		if (game.getCurState() == Game.STATE.CONTROLS) {
			checkControlsClicked(mx, my);
		}
	}
	
	private void mousePressedGameOver (int mx, int my) throws IOException {
		if (game.getCurState() == Game.STATE.GAME_OVER) {
			checkGameOverClicked(mx, my);
		}
	}
	
	private void mousePressedCredits (int mx, int my) {
		if (game.getCurState() == Game.STATE.CREDITS) {
			checkCreditsClicked(mx, my);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		try {
			checkState(mx, my);
		}
		catch (IOException exc) {
			logger.log(Level.FINE, exc.getMessage(), exc);
		}
	}

	public void checkState(int mx, int my) throws IOException {
		if (game.getCurState() == Game.STATE.MENU) {
			mousePressedMenu(mx, my);
		}
		else if (game.getCurState() == Game.STATE.OPTIONS) {
			mousePressedOptions(mx, my);
		}
		else if (game.getCurState() == Game.STATE.LEVEL_SELECT) {
			mousePressedLevelSelect(mx, my);
		}
		else if (game.getCurState() == Game.STATE.HIGH_SCORES) {
			mousePressedHighScores(mx, my);
		}
		else if (game.getCurState() == Game.STATE.HELP) {
			mousePressedHelp(mx, my);
		}
		else {
			checkOtherStates(mx, my);
		}
	}
	
	public void checkOtherStates(int mx, int my) throws IOException {
		if (game.getCurState() == Game.STATE.ACHIEVEMENT) {
			mousePressedAchievement(mx, my);
		}
		else if (game.getCurState() == Game.STATE.LOGIN) {
			mousePressedLogin(mx, my);
		}
		else if (game.getCurState() == Game.STATE.CONTROLS) {
			mousePressedControls(mx, my);
		}
		else if (game.getCurState() == Game.STATE.GAME_OVER) {
			mousePressedGameOver(mx, my);
		}
		else if (game.getCurState() == Game.STATE.CREDITS) {
			mousePressedCredits(mx, my);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent arGame0) {
		// Will add when necessary
	}
	
	private void checkOptionClicked(int mouseX, int mouseY) throws IOException {
		checkOptionSetState(mouseX, mouseY);
		checkOptionSetSFXVol(mouseX, mouseY);
		checkOptionSetMusicVol(mouseX, mouseY);
	}
	
	private void checkOptionSetState(int mouseX, int mouseY) throws IOException {
		if (mouseX > Options.CONTROL_L_BOUND &&
				mouseX < Options.CONTROL_R_BOUND &&
				mouseY > Options.CONTROL_T_BOUND &&
				mouseY < Options.CONTROL_B_BOUND) {
			game.setCurState(Game.STATE.CONTROLS);
		}
		else if (mouseX > Options.MENU_L_BOUND &&
				mouseX < Options.MENU_R_BOUND &&
				mouseY > Options.MENU_T_BOUND &&
				mouseY < Options.MENU_B_BOUND) {
			game.setCurState(Game.STATE.MENU);
		}
	}
	
	private boolean checkSFXVolLower(int mouseX, int mouseY) {
		if (mouseX > (Options.VOLUME_L_BOUND + 170) &&
				mouseX < (Options.VOLUME_L_BOUND + 190) &&
				mouseY > Options.SFX_VOLUME_T_BOUND &&
				mouseY < Options.SFX_VOLUME_B_BOUND)
			return true;
		
		return false;
	}
	
	private boolean checkSFXVolHigher(int mouseX, int mouseY) {
		if (mouseX > (Options.VOLUME_L_BOUND + 170 + (Options.MAXVOL * 30) + 5) &&
				mouseX < (Options.VOLUME_L_BOUND + 190 + (Options.MAXVOL * 30) + 5) &&
				mouseY > Options.SFX_VOLUME_T_BOUND &&
				mouseY < Options.SFX_VOLUME_B_BOUND)
			return true;
		
		return false;
	}
	
	private void checkOptionSetSFXVol(int mouseX, int mouseY) {
		if (Options.getSFXVol() > 0 && checkSFXVolLower(mouseX, mouseY)) { //minus
			Options.reduceSFX();
		}
		else if (Options.getSFXVol() < Options.MAXVOL && checkSFXVolHigher(mouseX, mouseY)) { //plus
			Options.increaseSFX();
		}
	}
	
	private boolean checkSetMusicVolLower (int mouseX, int mouseY) {
		if (mouseX > (Options.VOLUME_L_BOUND + 170) &&
				mouseX < (Options.VOLUME_L_BOUND + 190) &&
				mouseY > Options.M_VOLUME_T_BOUND &&
				mouseY < Options.M_VOLUME_B_BOUND)
			return true;
		
		return false;
	}
	
	private boolean checkSetMusicVolHigher (int mouseX, int mouseY) {
		if (mouseX > (Options.VOLUME_L_BOUND + 170 + (Options.MAXVOL * 30) + 5) &&
				mouseX < (Options.VOLUME_L_BOUND + 190 + (Options.MAXVOL * 30) + 5) &&
				mouseY > Options.M_VOLUME_T_BOUND &&
				mouseY < Options.M_VOLUME_B_BOUND)
			return true;
		
		return false;
	}
	
	private void checkOptionSetMusicVol(int mouseX, int mouseY) {
		if (Options.getMVol() > 0 && checkSetMusicVolLower(mouseX, mouseY)) { //minus
			Options.reduceM();
		}
		else if (Options.getMVol() < Options.MAXVOL && checkSetMusicVolHigher(mouseX, mouseY)) { //plus
			Options.increaseM();
		}
	}
	
	private void checkGameOverClicked(int mouseX, int mouseY) throws IOException {
		checkGOMenu(mouseX, mouseY);
		checkPlayAgain(mouseX, mouseY);
	}
	
	private void checkCreditsClicked(int mouseX, int mouseY) {
		if (mouseX > Credits.MENU_L_BOUND &&
				mouseX < Credits.MENU_R_BOUND &&
				mouseY > Credits.MENU_T_BOUND &&
				mouseY < Credits.MENU_B_BOUND) {
			game.setCurState(Game.STATE.MENU);
		}
	}
	
	private void checkGOMenu(int mouseX, int mouseY) {
		if (mouseX > Game.GAMEWIDTH / 2 - 70 && mouseX < Game.GAMEWIDTH / 2 + 70
				&& mouseY > Game.GAMEHEIGHT / 2 + 60 && mouseY < Game.GAMEHEIGHT / 2 + 100) {
				game.setCurState(Game.STATE.MENU);
		}
	}
	
	private void checkPlayAgain(int mouseX, int mouseY) throws IOException {
		if (mouseX > Game.GAMEWIDTH / 2 - 70 && mouseX < Game.GAMEWIDTH / 2 + 70
			&& mouseY > Game.GAMEHEIGHT / 2 && mouseY < Game.GAMEHEIGHT / 2 + 50) {
				createLevel();
				game.setCurState(game.getPrevState());
		}
	}
	private void createLevel() throws IOException {
	   if (game.getPrevState() == Game.STATE.LEVEL_1) {
		   game.setLevel1(new Level1(game));
	   }	
	}

	private void checkHighScoresClicked(int mouseX, int mouseY) {
		if ((mouseX > (Game.GAMEWIDTH/6 + 20)) && (mouseX < (Game.GAMEWIDTH/6 + 120)) && 
				 (mouseY > (Game.GAMEHEIGHT - Game.GAMEHEIGHT/5 - 10)) && (mouseY < (Game.GAMEHEIGHT - Game.GAMEHEIGHT/5))) {
			game.setCurState(Game.STATE.MENU);
		}
	}

	private void checkHelpClicked(int mouseX, int mouseY) {
		if ((mouseX > (Game.GAMEWIDTH/6 + 20)) && (mouseX < (Game.GAMEWIDTH/6 + 120)) && 
				 (mouseY > (Game.GAMEHEIGHT - Game.GAMEHEIGHT/5 - 10)) && (mouseY < (Game.GAMEHEIGHT - Game.GAMEHEIGHT/5))) {
			game.setCurState(Game.STATE.MENU);
		}
	}
	
	private void checkLevelClicked(int mouseX, int mouseY) throws IOException {
		if (level1Clicked(mouseX, mouseY)) {
			game.setLevel1(new Level1(game));
			game.setCurState(Game.STATE.LEVEL_1);
		}
		else if (level2Clicked(mouseX, mouseY)) {
			//Level 2
		}
		else if (level3Clicked(mouseX, mouseY)) {
			//Level 3
		}
		else if ((mouseX > (Game.GAMEWIDTH/6 + 20)) && (mouseX < (Game.GAMEWIDTH/6 + 120)) && 
				 (mouseY > (Game.GAMEHEIGHT - Game.GAMEHEIGHT/5 - 10)) && (mouseY < (Game.GAMEHEIGHT - Game.GAMEHEIGHT/5))) {
			game.setCurState(Game.STATE.MENU);
		}
	}
	
	private boolean level1Clicked(int mouseX, int mouseY) {
		if (LevelSelect.getLevels()[0].checkBound(0, mouseX, mouseY) &&
			LevelSelect.getLevels()[0].isUnlocked()) {
				return true;
		}
		return false;
	}
	
	private boolean level2Clicked(int mouseX, int mouseY) {
		if (LevelSelect.getLevels()[1].checkBound(1, mouseX, mouseY) &&
			LevelSelect.getLevels()[1].isUnlocked()) {
				return true;
		}
		return false;
	}
	
	private boolean level3Clicked(int mouseX, int mouseY) {
		if (LevelSelect.getLevels()[2].checkBound(2, mouseX, mouseY) &&
			LevelSelect.getLevels()[2].isUnlocked()) {
				return true;
		}
		return false;
	}
	
	private void checkAchieveClicked(int mouseX, int mouseY) {
		game.getAchievement().achieveSelect(mouseX, mouseY);
		
	}
	
	private void checkLoginClicked(int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - 150 && mouseX <= Game.GAMEWIDTH && mouseY >= Game.GAMEHEIGHT - 30 && mouseY <= Game.GAMEHEIGHT) {
			game.setClose(true);
		}

	}

	private void checkMenuClick(int mouseX, int mouseY) {
		game.getMenu().menuSelect(mouseX, mouseY);
	}
	
	private void checkControlsClicked(int mouseX, int mouseY) {
		checkControlsClickedKey(mouseX, mouseY);
		checkControlsClickedSetState(mouseX, mouseY);
	}
	
	private boolean checkUpKeyClicked (int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - Game.GAMEWIDTH/3 && mouseX <= Game.GAMEWIDTH - Game.GAMEWIDTH/3 + 120 &&
			mouseY >= Game.GAMEHEIGHT/4 - 20 && mouseY <= Game.GAMEHEIGHT/4 + 20)
			return true;
		
		return false;
	}
	
	private boolean checkLeftKeyClicked (int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - Game.GAMEWIDTH/3 && mouseX <= Game.GAMEWIDTH - Game.GAMEWIDTH/3 + 120 &&
				 mouseY >= Game.GAMEHEIGHT/4 + Game.GAMEHEIGHT/15 - 20 && mouseY <= Game.GAMEHEIGHT/4 + Game.GAMEHEIGHT/15 + 20)
			return true;
		
		return false;
	}

	private boolean checkDownKeyClicked (int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - Game.GAMEWIDTH/3 && mouseX <= Game.GAMEWIDTH - Game.GAMEWIDTH/3 + 120 &&
				 mouseY >= Game.GAMEHEIGHT/4 + 2*Game.GAMEHEIGHT/15 - 20 && mouseY <= Game.GAMEHEIGHT/4 + 2*Game.GAMEHEIGHT/15 + 20)
			return true;
		
		return false;
	}

	private boolean checkRightKeyClicked (int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - Game.GAMEWIDTH/3 && mouseX <= Game.GAMEWIDTH - Game.GAMEWIDTH/3 + 120 &&
				 mouseY >= Game.GAMEHEIGHT/4 + 3*Game.GAMEHEIGHT/15 - 20 && mouseY <= Game.GAMEHEIGHT/4 + 3*Game.GAMEHEIGHT/15 + 20)
			return true;
		
		return false;
	}
	
	private boolean checkCompanionKeyClicked (int mouseX, int mouseY) {
		if (mouseX >= Game.GAMEWIDTH - Game.GAMEWIDTH/3 && mouseX <= Game.GAMEWIDTH - Game.GAMEWIDTH/3 + 120 &&
				 mouseY >= Game.GAMEHEIGHT/4 + 4*Game.GAMEHEIGHT/15 - 20 && mouseY <= Game.GAMEHEIGHT/4 + 4*Game.GAMEHEIGHT/15 + 20)
			return true;
		
		return false;
	}
	
	private void checkControlsClickedKey(int mouseX, int mouseY) {
		if (checkUpKeyClicked(mouseX, mouseY)) { 
			//remap up key
		}
		else if (checkLeftKeyClicked(mouseX, mouseY)) { 
			//remap left key
		}
		else if (checkDownKeyClicked(mouseX, mouseY)) { 
			//remap down key
		}
		else if (checkRightKeyClicked(mouseX, mouseY)) { 
			//remap right key
		}
		else if (checkCompanionKeyClicked(mouseX, mouseY)) { 
			//remap companion key
		}
	}
	
	private void checkControlsClickedSetState(int mouseX, int mouseY) {
		if (mouseX > Controls.CONTROL_L_BOUND &&
				mouseX < Controls.CONTROL_R_BOUND &&
				mouseY > Controls.CONTROL_T_BOUND &&
				mouseY < Controls.CONTROL_B_BOUND) {
			game.setCurState(Game.STATE.OPTIONS);
		}
		else if (mouseX > Controls.MENU_L_BOUND &&
				mouseX < Controls.MENU_R_BOUND &&
				mouseY > Controls.MENU_T_BOUND &&
				mouseY < Controls.MENU_B_BOUND) {
			game.setCurState(Game.STATE.MENU);
		}
	}
}
