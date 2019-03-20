import java.sql.*;
import javax.swing.DefaultListModel;
/**
 * The AirlineDatabase class provides data fields and methods with the 
 * purpose of connecting the updating the sql database
 * 
 * @author Aidan Paterson, Maria Lau, Sara Li
 * @version 1.0
 * @since March 31st, 2017
 */
public class AirlineDatabase {
	/**
	* myConn the connection to database
	*/
	static Connection myConn;
	/**
	* mtStmt the statement
	*/
	static Statement myStmt;
	/**
	 * Creates table if they do not already exist in the database
	 */
	public void createTable(){
		try{
			PreparedStatement create1 = myConn.prepareStatement("CREATE TABLE IF NOT EXISTS flights(flightID int NOT NULL AUTO_INCREMENT, source VARCHAR(20) NOT NULL, destination VARCHAR(20) NOT NULL, date VARCHAR(8) NOT NULL, time VARCHAR(7) NOT NULL, duration VARCHAR(12) NOT NULL, totalSeats VARCHAR(12) NOT NULL, openSeats VARCHAR(12) NOT NULL, price VARCHAR(12) NOT NULL, PRIMARY KEY(flightID))");
			create1.executeUpdate();
			PreparedStatement create2 = myConn.prepareStatement("CREATE TABLE IF NOT EXISTS tickets(ticketNumber int NOT NULL AUTO_INCREMENT, flightID int NOT NULL, firstName VARCHAR(20) NOT NULL, lastName VARCHAR(20) NOT NULL, dOB VARCHAR(8) NOT NULL, PRIMARY KEY(ticketNumber))");
			create2.executeUpdate();
			PreparedStatement createUserTable = myConn.prepareStatement("CREATE TABLE IF NOT EXISTS users(userID int NOT NULL AUTO_INCREMENT, userName VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL, PRIMARY KEY(userID))");
			createUserTable.executeUpdate();
			PreparedStatement createAdminTable = myConn.prepareStatement("CREATE TABLE IF NOT EXISTS admins(userID int NOT NULL AUTO_INCREMENT, userName VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL, PRIMARY KEY(userID))");
			createAdminTable.executeUpdate();
			//System.out.println("Created table flights in the database");
			//System.out.println("Created table tickets in the database");
		}
		catch(Exception e){
			System.out.println("Error connection to server");
		}
	}
	/**
	 * sets up connection to the database
	 */
	public void getConnection(){
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			myStmt = myConn.createStatement();
		}
		catch(Exception e){
			System.out.println("Error connection to server");
		}
	}
	/**
	 * Searches flight by flight number
	 * @return flight as a string
	 */
	public String searchFlight(String flightNum) {
		String flight = "";
		try {
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE flightID LIKE ? ");
			ps1.setString(1, flightNum);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next() && flight == "")
				flight = rs1.getString("source") + ";" + rs1.getString("destination") + ";" + rs1.getString("date") + ";" + rs1.getString("time") +";" + rs1.getString("duration");
		}catch(SQLException e){
			
		}
		return flight;
	}
	/**
	 * Searches flight by date, source and destination
	 * @param date the date 
	 * @param source the source of flight
	 * @param dest the destination of flight
	 * @return the updated default list model
	 */
	public DefaultListModel<String> searchFlight(String date, String source, String dest) throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE (date LIKE ?) AND (source LIKE ?) AND (destination LIKE ?)");
			ps1.setString(1, date);
			ps1.setString(2, source);
			ps1.setString(3, dest);
			ResultSet rs1 = ps1.executeQuery();
		
			while(rs1.next()){
				listModel.addElement(rs1.getString("flightID") + ";" + rs1.getString("source") + ";" + rs1.getString("destination") + ";" + rs1.getString("date") + ";" + rs1.getString("time") + ";" + rs1.getString("duration") + ";"+ rs1.getString("totalSeats") + ";"+ rs1.getString("openSeats") + ";"+ rs1.getString("price") + ";");
			}
		}catch(SQLException e){
			
		}
		
		return listModel;
	}
	/**
	 * Searches flight by date, source and destination
	 * @param date the date 
	 * @return the updated default list model
	 */
	public static DefaultListModel<String> searchFlightByDate(String date) throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE date LIKE ? ");
			ps1.setString(1, date);
			ResultSet rs1 = ps1.executeQuery();
		
			while(rs1.next()){
				listModel.addElement(rs1.getString("flightID") + ";" + rs1.getString("source") + ";" + rs1.getString("destination") + ";" + rs1.getString("date") + ";" + rs1.getString("time") + ";" + rs1.getString("duration") + ";"+ rs1.getString("totalSeats") + ";"+ rs1.getString("openSeats") + ";"+ rs1.getString("price") + ";");
			}
		}catch(SQLException e){
			
		}
		return listModel;
	}
	/**
	 * Searches flight by date, source and destination
	 * @param source the source of flight
	 * @return the updated default list model
	 */
	public static DefaultListModel<String> searchFlightByOrigin(String source) throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE source LIKE ? ");
			ps1.setString(1, source);
			ResultSet rs1 = ps1.executeQuery();
		
			while(rs1.next()){
				listModel.addElement(rs1.getString("flightID") + ";" + rs1.getString("source") + ";" + rs1.getString("destination") + ";" + rs1.getString("date") + ";" + rs1.getString("time") + ";" + rs1.getString("duration") + ";"+ rs1.getString("totalSeats") + ";"+ rs1.getString("openSeats") + ";"+ rs1.getString("price") + ";");
			}
		}catch(SQLException e){
			
		}
		return listModel;
	}
	/**
	 * Searches flight by date, source and destination
	 * @param dest the destination of the flight
	 * @return the updated default list model
	 */
	public static DefaultListModel<String> searchFlightByDest(String dest) throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE destination LIKE ? ");
			ps1.setString(1, dest);
			ResultSet rs1 = ps1.executeQuery();
		
			while(rs1.next()){
				listModel.addElement(rs1.getString("flightID") + ";" + rs1.getString("source") + ";" + rs1.getString("destination") + ";" + rs1.getString("date") + ";" + rs1.getString("time") + ";" + rs1.getString("duration") + ";"+ rs1.getString("totalSeats") + ";"+ rs1.getString("openSeats") + ";"+ rs1.getString("price") + ";");
			}
		}catch(SQLException e){
			
		}
		return listModel;
	}
	/**
	 * Searches tickets by flight number
	 * @param flightNum the flight number wanted
	 * @return the updated default list model
	 */
	public DefaultListModel<String> searchTicketsByFlight(int flightNum) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM tickets WHERE flightID LIKE ? ");
			ps1.setInt(1, flightNum);
			ResultSet rs1 = ps1.executeQuery();
//			listModel.addElement("Ticket number;Flight number;First name;Last name;dOB;");
//			listModel.addElement("");
			while(rs1.next()){
				listModel.addElement(rs1.getString("ticketNumber") + ";" + rs1.getString("flightID") + ";" + rs1.getString("firstName") + ";" + rs1.getString("lastName") + ";" + rs1.getString("dOB") + ";");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listModel;
	}
	/**
	 * Searches tickets by ticket number
	 * @param ticketNum the ticket number
	 * @return the updated default list model
	 */
	public DefaultListModel<String> searchTicketsByTicket(int ticketNum) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM tickets WHERE ticketNumber LIKE ? ");
			ps1.setInt(1, ticketNum);
			ResultSet rs1 = ps1.executeQuery();
			listModel.addElement("Ticket number;Flight number;First name;Last name;dOB;");
			listModel.addElement(" ");
			while(rs1.next()){
				listModel.addElement(rs1.getString("ticketNumber") + ";" + rs1.getString("flightID") + ";" + rs1.getString("firstName") + ";" + rs1.getString("lastName") + ";" + rs1.getString("dOB") + ";");
			}
		}catch(SQLException e){
		}
		return listModel;
	}
	/**
	 * Searches all tickets
	 * @return the updated default list model
	 */
	public DefaultListModel<String> searchAllTickets() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM tickets");
			ResultSet rs1 = ps1.executeQuery();
			listModel.addElement("Ticket number;Flight number;First name;Last name;dOB;");
			listModel.addElement(" ");
			while(rs1.next()){
				listModel.addElement(rs1.getString("ticketNumber") + ";" + rs1.getString("flightID") + ";" + rs1.getString("firstName") + ";" + rs1.getString("lastName") + ";" + rs1.getString("dOB") + ";");
			}
		}catch(SQLException e){
		}
		return listModel;
	}
	/**
	 * function to check if flight is full
	 * @param flightNum the flight number to check
	 * @return true if flight is not full
	 */
	public static boolean flightNotFull(int flightNum){
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE flightID LIKE ? ");	
			ps1.setInt(1, flightNum);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				int openSeats = Integer.parseInt(rs1.getString("openSeats"));
				if(openSeats > 0 ){
					return true;
				}
				if(openSeats <= 0 ){
					return false;
				}
			}
		}catch(SQLException e){
			System.err.println("Error getting ticket number");
			return false;
		}
		return true;
	}
	/**
	 * Decreases open seats of a flight by 1
	 * @param flightNum the flight number
	 */
	public static void decreaseOpenSeats(int flightNum){
		try{
			//get current open seats
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE flightID LIKE ? ");	
			ps1.setInt(1, flightNum);
			ResultSet rs1 = ps1.executeQuery();
			
			int openSeats = 0;
			
			while(rs1.next()){
				openSeats = Integer.parseInt(rs1.getString("openSeats"));
			}
			
			//decrease open seats by 1
			openSeats--;
			
			PreparedStatement ps2 = myConn.prepareStatement("UPDATE flights SET openSeats=? WHERE flightID=?");	
			ps2.setInt(1, openSeats);
			ps2.setInt(2,flightNum);
			int rowUpdated = ps2.executeUpdate();
		}catch(SQLException e){
			System.err.println("Error decreasing open seats");
		}
	}
	/**
	 * Increases open seats of a flight by 1
	 * @param flightNum the flight number
	 */
	public void increaseOpenSeats(int flightNum){
		try{
			//openSeats would never exceed more than totalSeats since a ticket has to exist to be deleted
			
			//get current open seats
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM flights WHERE flightID LIKE ? ");	
			ps1.setInt(1, flightNum);
			ResultSet rs1 = ps1.executeQuery();
			
			int openSeats = 0;
			
			while(rs1.next()){
				openSeats = Integer.parseInt(rs1.getString("openSeats"));
			}
			//increase open seats by 1
			openSeats++;
			
			PreparedStatement ps2 = myConn.prepareStatement("UPDATE flights SET openSeats=? WHERE flightID=?");	
			ps2.setInt(1, openSeats);
			ps2.setInt(2, flightNum);
			int rowUpdated = ps2.executeUpdate();
		}catch(SQLException e){
			System.err.println("Error increasing open seats");
		}
	}
	/**
	 * Books ticket for a flight, which is syncrhonized
	 * @param t the Ticket to book
	 */
	synchronized public int bookFlight(Ticket t){
		// returns 1 if ticket booked successfully
		// returns 0 if ticket not booked due to system error
		// returns 2 if passenger already has a ticket for that same flight
		// return 3 if can't book ticket because flight is full
		try{
			//openSeats would never be less than 0 since a ticket wouldn't be booked if a fight was full
			Boolean notFull = flightNotFull(t.flightNum);
			
			if(notFull == false){
				return 3;
			}
	
			Boolean notDuplicateTik = true;
			ResultSet temp = myStmt.executeQuery("SELECT * from tickets");

			while(temp.next()){
				if(Integer.toString(t.flightNum).equals(temp.getString("flightID")) && t.pass.firstName.equals(temp.getString("firstName")) && t.pass.lastName.equals(temp.getString("lastName")) && t.pass.dOB.equals(temp.getString("dOB"))){
					notDuplicateTik = false;
					return 2;
				}
			}
			if(notDuplicateTik == true){
				String query = "INSERT INTO tickets (flightID, firstName, lastName, dOB)"
						+ "VALUES(?, ?, ?, ?)";
				PreparedStatement insert = myConn.prepareStatement(query);
				insert.setString(1, Integer.toString(t.flightNum));
				insert.setString(2, t.pass.firstName);
				insert.setString(3, t.pass.lastName);
				insert.setString(4, t.pass.dOB);
				insert.executeUpdate();
				
				//decrease openSeats by 1 in flights table
				decreaseOpenSeats(t.flightNum);
				
				return 1;
			}
		}catch(SQLException e){
			System.err.println("Error booking flight");
			return 0;
		}
		return 1;
	}
	/**
	 * Cancels an exisitng ticket
	 * @param t the ticket to be cancelled
	 * @return true is ticket has been cancelled
	 */
	public boolean cancelTicket(Ticket t){
		try{
			PreparedStatement delete = myConn.prepareStatement("DELETE FROM tickets WHERE ticketNumber = ?");
			delete.setInt(1, t.ticketNum);
			delete.executeUpdate();
				
			//increase openSeats by 1 in flights table
			increaseOpenSeats(t.flightNum);
			
			return true;
		}catch(SQLException e){
			System.err.println("Error booking flight");
			return false;
		}
	}
	/**
	 * Gets ticket number of a flight after ticket has been booked 
	 * since flight number is assigned by database
	 * @param t the ticket to get number for
	 */
	public void getTicketNumber(Ticket t){
		//update ticketNum of t
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM tickets WHERE firstName LIKE ? ");
			ps1.setString(1, t.pass.firstName);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				if(Integer.toString(t.flightNum).equals(rs1.getString("flightID")) && t.pass.lastName.equals(rs1.getString("lastName")) && t.pass.dOB.equals(rs1.getString("dOB"))){
					t.ticketNum = Integer.parseInt(rs1.getString("ticketNumber"));
				}
			}
		}catch(SQLException e){
			System.err.println("Error getting ticket number");
		}
	}	
	/**
	 * Checks username exists for login
	 * @param user the username
	 * @return true if username exists
	 */
	public boolean checkUserName(String user){
		try{
			ResultSet temp = myStmt.executeQuery("SELECT * from users");
		
			while(temp.next()){
				if(temp.getString("userName").equals(user)){
					return false;
				}		
			}
		}catch(SQLException e){
			System.err.println("Error creating user");
		}
		return true;	
	}
	/**
	 * Checks if username and password is correct for login
	 * @param user the username
	 * @param pass the password
	 * @return 0 if username not found, 1 if username and password correct
	 * 2 if username found but password is not correct
	 */
	public int validateUser(String user, String pass){
		//return 0 if username not found
		//return 1 if username and password found and matches
		//return 2 if username found but password not correct
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM users WHERE userName LIKE ? ");	
			ps1.setString(1, user);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				if(pass.equals(rs1.getString("password"))){
					return 1;
				}
				return 2;
			}
		}catch(SQLException e){
			return 0;
		}
		return 0;
	}
	/**
	 * Adds a new user
	 * @param user the username
	 * @param pass the password
	 */
	public void insertUser(String user, String pass){
		try{
			Boolean notDuplicateUser = true;
			ResultSet temp = myStmt.executeQuery("SELECT * from users");

			while(temp.next()){
				if((temp.getString("userName").equals(user)) && (temp.getString("passWord").equals(pass))){
					notDuplicateUser = false;
				}
			}
			if(notDuplicateUser == true){
				String query = "INSERT INTO users (userName, password)"
					+ "VALUES(?, ?)";
				PreparedStatement insert = myConn.prepareStatement(query);
				insert.setString(1, user);
				insert.setString(2, pass);
				insert.executeUpdate();
			}
		}catch(SQLException e){
			System.err.println("Error adding user");
		}
	}
	/**
	 * Checks username exists for login
	 * @param user the username
	 * @return true if username exists
	 */
	public boolean checkAdminName(String user){
		try{
			ResultSet temp = myStmt.executeQuery("SELECT * from admins");
		
			while(temp.next()){
				if(temp.getString("userName").equals(user)){
					return false;
				}		
			}
		}catch(SQLException e){
			System.err.println("Error creating admin");
		}
		return true;	
	}
	/**
	 * Checks if username and password is correct for login
	 * @param user the username
	 * @param pass the password
	 * @return 0 if username not found, 1 if username and password correct
	 * 2 if username found but password is not correct
	 */
	public int validateAdmin(String user, String pass){
		//return 0 if username not found
		//return 1 if username and password found and matches
		//return 2 if username found but password not correct
		try{
			PreparedStatement ps1 = myConn.prepareStatement("SELECT * FROM admins WHERE userName LIKE ? ");	
			ps1.setString(1, user);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				if(pass.equals(rs1.getString("password"))){
					return 1;
				}
				return 2;
			}
		}catch(SQLException e){
			return 0;
		}
		return 0;
	}
	/**
	 * Adds a new admin
	 * @param user the username
	 * @param pass the password
	 */
	public void insertAdmin(String user, String pass){
		try{
			Boolean notDuplicateUser = true;
			ResultSet temp = myStmt.executeQuery("SELECT * from admins");

			while(temp.next()){
				if((temp.getString("userName").equals(user)) && (temp.getString("passWord").equals(pass))){
					notDuplicateUser = false;
				}
			}
			if(notDuplicateUser == true){
				String query = "INSERT INTO admins (userName, password)"
					+ "VALUES(?, ?)";
				PreparedStatement insert = myConn.prepareStatement(query);
				insert.setString(1, user);
				insert.setString(2, pass);
				insert.executeUpdate();
			}
		}catch(SQLException e){
			System.err.println("Error adding admin");
		}
	}
	/**
	 * Adds a flight to the database
	 * @param f the flight to be added
	 */
	public void addFlight(Flight f) {
		try{
			Boolean notDuplicateFlight = true;
			ResultSet temp = myStmt.executeQuery("SELECT * from flights");
			
			while(temp.next()){
				if(f.date.equals(temp.getString("date")) && f.source.equals(temp.getString("source")) && f.dest.equals(temp.getString("destination")) && f.time.equals(temp.getString("time"))){
					notDuplicateFlight = false;
				}		
			}
			
			if(notDuplicateFlight == true){
				String query = "INSERT INTO flights (source, destination, date, time, duration, totalSeats, openSeats, price)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement insert = myConn.prepareStatement(query);
				insert.setString(1, f.source);
				insert.setString(2, f.dest);
				insert.setString(3, f.date);
				insert.setString(4, f.time);
				insert.setString(5, f.duration);
				insert.setString(6, f.totalSeats);	
				insert.setString(7, f.openSeats);	
				insert.setString(8, f.price);	
				insert.executeUpdate();
			}
		}catch(SQLException e){
			System.err.println("E");
		}
	}


}
