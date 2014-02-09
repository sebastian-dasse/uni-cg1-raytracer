package raytracer.supercalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerThread implements Callable<Object> {
	private Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	@Override
	public Object call() {
		String result = "";
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			String inputLine = Phrases.NOT_STARTED_JET.toString();
			while (!(inputLine = in.readLine()).equals(Phrases.DONE_RENDERING.toString())) {
				result = inputLine;
				
				System.out.println("Client says: >" + inputLine);
			}
			out.println(Phrases.DONE_READING_RENDER_RESULT.toString());
		} catch (IOException e) {
			System.err.println("Error with Server communication");
		}
		return result;
	}

}
