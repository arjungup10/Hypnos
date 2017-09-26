package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.screens.Menu;
import logic.main.Game;
import java.io.IOException;

/*
 * Created by Elyse Munemura
 */

public class TestMenu {

	@Test
	public void testMenuSelectExit() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		Game.STATE state = game.getCurState();
		
		menu.menuSelect(640, 570);
		
		assertEquals(state, game.getCurState());
	}
	
	@Test
	public void testMenuSelectLevelSelect() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(360, 100);
		
		assertEquals(Game.STATE.LEVEL_SELECT, game.getCurState());
	}
	
	@Test
	public void testMenuSelectHelp() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(360, 155);
		
		assertEquals(Game.STATE.HELP, game.getCurState());
	}
	
	@Test
	public void testMenuSelectOptions() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(380, 200);
		
		assertEquals(Game.STATE.OPTIONS, game.getCurState());
	}

	@Test
	public void testMenuSelectEndless() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(330, 250);
		
	}
	
	@Test
	public void testMenuSelectHighScores() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(340, 300);
		
		assertEquals(Game.STATE.HIGH_SCORES, game.getCurState());
	}
	
	@Test
	public void testMenuSelectAchievement() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(330, 350);
		
		assertEquals(Game.STATE.ACHIEVEMENT, game.getCurState());
	}
	
	@Test
	public void testMenuSelectCredits() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(360, 400);
		
		assertEquals(Game.STATE.CREDITS, game.getCurState());
	}
	
	@Test
	public void testMenuSelectLogin() throws IOException{
		Game game = Game.getInstance();
		Menu menu = new Menu(game);
		
		menu.menuSelect(360, 450);
		
		assertEquals(Game.STATE.LOGIN, game.getCurState());
	}
}
