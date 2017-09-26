package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.levels.TileMap;
import java.lang.reflect.*;

/*
 * Created by Elyse Munemura
 */

public class TestTileMap {
	
	TileMap tileMap = new TileMap(10);
	final Field numRows = tileMap.getClass().getDeclaredField("numRows");
	
	public TestTileMap() throws NoSuchFieldException{
		// do nothing
	}
	
	@Test
	public void testOutOfBoundsGreater() throws IllegalAccessException{
		int output;
	
		numRows.setAccessible(true);
		numRows.set(tileMap, 10);
		
		output = tileMap.outOfBounds(11);
		
		assertEquals(1, output);
	}
	
	@Test
	public void testOutOfBoundsNegative() {
		TileMap newTileMap = new TileMap(10);
		int output = newTileMap.outOfBounds(-5);
		
		assertEquals(-1, output);
	}
	
	@Test
	public void testOutOfBoundsGeneral() throws IllegalAccessException{
		int output;

		numRows.setAccessible(true);
		numRows.set(tileMap, 10);
		
		output = tileMap.outOfBounds(2);
		
		assertEquals(0, output);
	}

}
