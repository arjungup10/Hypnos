package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class HypnosServer {
	// The port number the client should be reporting to
	private static int port = 1236;
	
	private HypnosServer(){
	}

	/*
	 * Description:
	 * 
	 * This method goes ahead and creates a ServerSocket that takes in a port
	 * number. The socket then uses the accept method to take information
	 * received by the port. The method then creates a new HypnosThread that
	 * will uniquely talk with the client.
	 * 
	 * The while loop constantly runs until a client asks to communicate with
	 * the server. The loop will keep running regardless (allows for unlimited
	 * clients to talk with).
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ServerSocket ssock = new ServerSocket(port);
		boolean notExited = true;
		Scanner s = new Scanner(System.in);
		
		while (notExited) {
			Socket sock = ssock.accept();
			new HypnosThread(sock).start();
			
			if (s.hasNextLine() && s.nextLine() == "EXIT") {
				notExited = false;
				ssock.close();
				s.close();
			}
		}
	}
}
