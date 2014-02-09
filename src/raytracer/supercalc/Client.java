package raytracer.supercalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main (String [] args) {
		try {
			Socket socket = new Socket(Config.HOSTNAME.getValue(), Config.PORTNUMBER.getIntValue());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String fromServer;
			while (!(fromServer = in.readLine()).equals(States.DONE_READING_RENDER_RESULT.toString())) {
				
			}
			
		}
		catch (UnknownHostException e) {
			
		}
		catch (IOException e) {
			
			
		}
	}
}
