package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import org.junit.Test;

import logic.HypnosThread;
import logic.Queries;

/*
 * Created by Natasha Cortez
 */
public class TestSelectQueries {
	@Test
	public void testCheckForLevels() throws IOException {
		Queries test = new Queries(new HypnosThread(new Socket()));
		
		try {
			String output = test.checkForLevels("andyroofoo");
		
			assertEquals("[HAS LEVELS]\n2 1 2", output);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
