package raytracer.clustering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import raytracer.clustering.client.states.IState;
import raytracer.clustering.enums.Config;
import raytracer.clustering.enums.ErrorMessage;
import raytracer.clustering.enums.States;

public class Client {
	private static IState currentState;
	
	public static void main (String [] args) {
		try {
			Socket socket = new Socket(Config.HOSTNAME.getValue(), Config.PORTNUMBER.getIntValue());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String serverResponse;
			while (!(serverResponse = in.readLine()).equals(States.CLOSING_CONNECTION.toString())) {
				currentState.talk(out);
				currentState.execute(serverResponse);
			}
		}
		catch (UnknownHostException e) {
			System.err.println(ErrorMessage.HOST_UNKNOWN.getValue());
		}
		catch (IOException e) {
			System.err.println(ErrorMessage.IO.getValue());
		}
	}
	
	public static void setState(IState state) {
		currentState = state;
	}
}
