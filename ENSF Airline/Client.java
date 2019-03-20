
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Class used by the AdminGUI and ClientGUI to communicate with the server, 
 * and access the database from there
 * 
 * @version 1.0
 * @author Aidan Paterson, Maria Lau, Sara Li
 *
 */
public class Client {
	/**
	 * Socket used by this class
	 */
	private Socket socket;
	/**
	 * Input stream from the server
	 */
	BufferedReader in;
	/**
	 * Output stream to the server
	 */
	PrintWriter out;
	/**
	 * Serialized output stream to the server
	 */
	ObjectOutputStream objOut;
	/**
	 * Serialized input stream from the server
	 */
	ObjectInputStream objIn;
	/**
	 * Host name
	 */
	String host;
	/**
	 * Server port
	 */
	int port;
	/**
	 * Constructor for this class
	 * @param h		host name
	 * @param p		port number
	 */
	public Client(String h, int p) {
		host = h;
		port = p;
	}
	/**
	 * Method for initializing the connection to the server by setting up the socket
	 */
	public void connect() {
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			System.err.println("Error connecting: " + e.getMessage());
		}
	}
	/**
	 * Method for communicating with the server and setting up IOStreams
	 */
	public void communicate() {
		connect();
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter((socket.getOutputStream()), true);
			objOut = new ObjectOutputStream(socket.getOutputStream());
			objIn = new ObjectInputStream(socket.getInputStream());
			while (!socket.isClosed()) {}	// Doesn't need to do anything besides stay awake, GUI handles all in/outputs
			in.close();
			out.close();
			objIn.close();
			objOut.close();
		} catch (IOException e) {
			System.out.println("Sending error: " + e.getMessage());
		}		
	}
	
}




