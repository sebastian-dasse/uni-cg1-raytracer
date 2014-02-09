package raytracer.supercalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import raytracer.supercalc.protocol.IState;

public class Client {
	private static IState currentState;
	
	public static void main (String [] args) {
		try {
			Socket socket = new Socket(Config.HOSTNAME.getValue(), Config.PORTNUMBER.getIntValue());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String serverResponse;
			while (!(serverResponse = in.readLine()).equals(States.DONE_READING_RENDER_RESULT.toString())) {
				currentState.execute(serverResponse);
				out.println(currentState.talk());
			}
		}
		catch (UnknownHostException e) {
		}
		catch (IOException e) {
		}
	}
	
	public static void setState(IState state) {
		currentState = state;
	}
}
