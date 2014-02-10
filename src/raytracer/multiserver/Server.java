package raytracer.multiserver;

import java.io.IOException;
import java.net.ServerSocket;

import raytracer.multiserver.enums.Config;
import raytracer.multiserver.enums.ErrorMessage;

public class Server {
	public static boolean start () {
		try {
			ServerSocket serverSocket = new ServerSocket(Config.PORTNUMBER.getIntValue());
			while (true) {
				new ServerThread(serverSocket.accept()).call();
			}
		} catch (IOException e) {
			System.err.println(ErrorMessage.STARTING_SERVER.getValue());
			return false;
		}
//		return true;
	}
	
	public static void main (String [] args) {
		start();
	}
}
