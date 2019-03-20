import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.DefaultListModel;
/**
 * Server class, used to start the server and handle interactions between the client(s)
 * and the database
 * 
 * @author Aidan Paterson, Maria Lau, Sara Li
 * @version 1.0
 * @since March 31st, 2017
 */
public class Server {
	
	public static void main(String[] args) throws IOException {
		Server s = new Server(9898); 
		try {
			for (;;){
				s.pool.execute(s.new Handler(s.serverSocket.accept()));
			}
		} catch (IOException ex) {s.pool.shutdown();}
	}
	/**
	 * The AirlineDatabase
	 */
	AirlineDatabase adb;
	/**
	 * Connection to the database
	 */
	Connection serverConn;
	/**
	 * ServerSocket
	 */
	ServerSocket serverSocket;
	/**
	 * Pool for server ThreadPool
	 */
	ExecutorService pool;
	/**
	 * Constructor for this class, initiates the server at the port given in the argument, and
	 * initializes the server
	 * @param port		port used by the server
	 */
	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		pool = Executors.newCachedThreadPool();
		adb = new AirlineDatabase();
		System.out.println("Server is Running....");
		adb.getConnection();
		adb.createTable();
	}
	/**
	 * Handler class, used by the server ThreadPool
	 *
	 */
	class Handler implements Runnable
	{
		/**
		 * Socket for this class
		 */
		Socket socket;
		/**
		 * Input from the client
		 */
		BufferedReader in;
		/**
		 * Output to the client
		 */
		PrintWriter out;
		/**
		 * Input serialized objects from the client
		 */
		ObjectInputStream objIn;
		/**
		 * Output serialized objects to the client
		 */
		ObjectOutputStream objOut;
		/**
		 * Constructor for this class, initiates all IOStreams
		 * @param s			socket used by this class
		 */
		public Handler(Socket s) {
			socket = s;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter((socket.getOutputStream()), true);
				objIn = new ObjectInputStream(socket.getInputStream());
				objOut = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * This method is used to take inputs from the client, and perform the requisite task with the database
		 * and return some data in some cases
		 */
		@Override
		public void run() {
			String line = null;
			try {
				
				while(true) 
				{	
					line = in.readLine();
					if (line.equals("QUIT")) {
						System.out.print("Server is quitting...");
						out.println("Server exiting, goodbye!");
						break;
					}
					else if (line.equals("validAdmin")) {
						line = in.readLine();			// Admin name
						boolean b = adb.checkAdminName(line);
						out.println(b);
					}
					else if (line.equals("insertAdmin")) {
						String user = in.readLine();	// Admin name
						String pass = in.readLine();	// admin password
						adb.insertAdmin(user, pass);
					}
					else if (line.equals("validateAdmin")) {
						String user = in.readLine();	// Admin name
						String pass = in.readLine();	// Admin password
						int result = adb.validateAdmin(user, pass);
						out.println(result);
					}
					else if (line.equals("validUser")) {
						line = in.readLine();			// Username
						boolean b = adb.checkUserName(line);
						out.println(b);
					}
					else if (line.equals("insertUser")) {
						String user = in.readLine();	// Username
						String pass = in.readLine();	// User password
						adb.insertUser(user, pass);
					}
					else if (line.equals("validateUser")) {
						String user = in.readLine();	// Username
						String pass = in.readLine();	// User password
						int result = adb.validateUser(user, pass);
						out.println(result);
					}
					else if (line.equals("updateTicket")) {
						Ticket t = (Ticket) objIn.readObject();
						adb.getTicketNumber(t);
						objOut.writeObject(t);
					}
					else if (line.equals("addFlight")) {
						line = in.readLine();			// Flight data
						Flight f = new Flight(line);
						adb.addFlight(f);
					}
					else if (line.equals("bookFlight")) {
						try {
							Ticket t = (Ticket) objIn.readObject();
							int booked = adb.bookFlight(t);
							out.println(booked);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
					else if (line.equals("searchFlight")) {
						String flightNum = in.readLine();
						String info = adb.searchFlight(flightNum);
						out.println(info);
					}
					else if (line.equals("searchFlights")) {
						String date = in.readLine();
						String origin = in.readLine();
						String dest = in.readLine();
						DefaultListModel<String> info = adb.searchFlight(date, origin, dest);
						objOut.writeObject(info);
					}
					else if (line.equals("searchTicketsByFlight")) {
						line = in.readLine();						// flightNum
						try {
							DefaultListModel<String> temp = adb.searchTicketsByFlight(Integer.parseInt(line));
							objOut.writeObject(temp);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					else if (line.equals("searchTicketsByTicket")) {
						line = in.readLine();		// ticketNum
						try {
							DefaultListModel<String> temp = adb.searchTicketsByTicket(Integer.parseInt(line));
							objOut.writeObject(temp);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					else if (line.equals("browseAllTickets")) {
						try {
							DefaultListModel<String> temp = adb.searchAllTickets();
							objOut.writeObject(temp);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					else if (line.equals("cancelTicket")) {
						try {
							Ticket t = (Ticket) objIn.readObject();
							Boolean cancel = adb.cancelTicket(t);
							out.println(cancel);
						} catch (NumberFormatException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
					else {
						System.out.println(line);
					}
				}
				// Closing IOStreams
				in.close();
				out.close();
				objIn.close();
				objOut.close();
				socket.close();
			} catch (SocketException se) {
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}






