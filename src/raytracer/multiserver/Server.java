package raytracer.multiserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import raytracer.multiserver.enums.Config;

public class Server {
	public static boolean start () {
		try {
			ServerSocket serverSocket = new ServerSocket(Config.PORTNUMBER.getIntValue());
			while (true) {
				new ServerThread(serverSocket.accept()).call();
			}
		} catch (IOException e) {
			System.err.println("Error starting Server. Using Portnumber " + Config.PORTNUMBER.getIntValue()
					+ ".\nMake sure no other service is using this port.");
			return false;
		}
//		return true;
	}
	
	public static void main (String [] args) {
		start();
	}
}
