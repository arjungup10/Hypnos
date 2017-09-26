package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.screens.Achievement;
import logic.main.Game;
import java.io.IOException;

/*
 * Created by Elyse Munemura
 */

public class TestAchievement {
	
	@Test
	public void testAchieveSelectExit() throws IOException{
		Game game = Game.getInstance();
		Achievement achievement = new Achievement(game);
		Game.STATE state = game.getCurState();
		
		achievement.achieveSelect(700, 570);
		
		assertEquals(state, game.getCurState());
	}
	
	@Test
	public void testAchieveSelectMenu() throws IOException{
		Game game = Game.getInstance();
		Achievement achievement = new Achievement(game);
		
		achievement.achieveSelect(400, 460);
		
		assertEquals(Game.STATE.MENU, game.getCurState());
	}

}
