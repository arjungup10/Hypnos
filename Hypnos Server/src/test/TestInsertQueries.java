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

public class TestInsertQueries {
	@Test
	public void testUpdateHighScores() throws IOException {
		Queries test = new Queries(new HypnosThread(new Socket()));
		
		try {
			String output = test.updateHighScores("andyroofoo", "777");
			assertEquals("[SCORE ADDED]", output);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
