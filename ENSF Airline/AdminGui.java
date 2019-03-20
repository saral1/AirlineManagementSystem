import java.io.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import java.util.Arrays;
import java.util.Date;
import java.text.*;
/**
 * The AdminGui class provides data fields and methods with the 
 * purpose of representing the window that the admin uses
 * 
 * @author Aidan Paterson, Maria Lau, Sara Li
 * @version 1.0
 * @since March 31st, 2017
 */
public class AdminGui extends JFrame {
	/**
	* serialVersionUID is the serial ID for serialization
	*/
	private static final long serialVersionUID = 1L;
	/**
	* RadioButtonGroup is the group to contain radio buttons so they 
	* cannot be selected at the same time
	*/
	private final ButtonGroup RadioButtonGroup = new ButtonGroup();
	/**
	* searchField is the field to enter the search parameter
	*/
	private JTextField searchField;
	/**
	* client the Client of the system
	*/
	private Client client;
	/**
	* tFUser is where username appears after user logs in
	*/
	JTextField tFUser;
	/**
	* btnSearch the search button
	*/
	JButton btnSearch;
	/**
	* btnBrowse the browse all tickets button
	*/
	JButton btnBrowse;
	/**
	* btnClearSearch clears search results
	*/
	JButton btnClearSearch;
	/**
	* btnCancelTicket is button for cancelling ticket
	*/
	JButton btnCancelTicket;
	/**
	* btnAddFlight is for adding a single flight
	*/
	JButton btnAddFlight;
	/**
	* btnAddFlights is for adding flighs from a text file
	*/
	JButton btnAddFlights;
	/**
	* btnAddTicket books ticket from admin window
	*/
	JButton btnAddTicket;
	/**
	* btnLogin the login button
	*/
	JButton btnLogin;
	/**
	* btnLogOut the log out button
	*/
	JButton btnLogOut;
	/**
	* btnRegister the register for an admin account button
	*/
	JButton btnRegister;
	/**
	* rdbtnTicketNum to select ticket number as search criteria
	*/
	JRadioButton rdbtnTicketNum;
	/**
	* rdbtnFlightNum to select flight number as search criteria
	*/
	JRadioButton rdbtnFlightNum;
	/**
	* listModel is where search results are displayed
	*/
	DefaultListModel<String> listModel;
	/**
	* text the wrap of listModel
	*/
	JList<String> text;
	/**
	* searchResults enables search results to be scrollable
	*/
	JScrollPane searchResults;
	/**
	* searchField the search paramter
	*/
	JTextField sourceField;
	/**
	 * Default constructor creates the frame
	 */
	public AdminGui() {
		
		client = new Client("localhost", 9898);

		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(153, 153, 153));
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminGui.class.getResource("/clientgui/resources/plane.jpg")));
		setTitle("ENSF Airlines - Ticket Administration System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 480);
		getContentPane().setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBackground(new Color(255, 255, 255, 100));
		logo.setIcon(new ImageIcon(AdminGui.class.getResource("/clientgui/resources/planesmall.jpg")));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setBounds(751, 360, 95, 92);
		getContentPane().add(logo);
		
		btnBrowse = new JButton("Browse All Existing Tickets");
		btnBrowse.setBounds(30, 374, 208, 29);
		getContentPane().add(btnBrowse);
		
		btnClearSearch = new JButton("Clear Search Results");
		btnClearSearch.setBounds(237, 404, 171, 29);
		getContentPane().add(btnClearSearch);
		
		btnAddTicket = new JButton("Book Ticket for Client");
		btnAddTicket.setBounds(30, 404, 208, 29);
		getContentPane().add(btnAddTicket);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(297, 117, 111, 29);
		getContentPane().add(btnSearch);
		
		btnCancelTicket = new JButton("Cancel Selected Ticket");
		btnCancelTicket.setBounds(237, 374, 171, 29);
		getContentPane().add(btnCancelTicket);
		
		searchField = new JTextField();
		searchField.setBounds(30, 117, 267, 26);
		getContentPane().add(searchField);
		searchField.setColumns(10);
		
		rdbtnTicketNum = new JRadioButton("Ticket Number");
		RadioButtonGroup.add(rdbtnTicketNum);
		rdbtnTicketNum.setBounds(153, 92, 138, 25);
		getContentPane().add(rdbtnTicketNum);
		
		rdbtnFlightNum = new JRadioButton("Flight Number");
		RadioButtonGroup.add(rdbtnFlightNum);
		rdbtnFlightNum.setBounds(30, 92, 141, 25);
		getContentPane().add(rdbtnFlightNum);

		//for search results
		listModel = new DefaultListModel<String>();
		text = new JList<String>(listModel);
		searchResults = new JScrollPane(text);
		searchResults.setOpaque(false);
		searchResults.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		searchResults.setBounds(30, 148, 378, 223);
		getContentPane().add(searchResults);

		
		JLabel lblSearchForTickets = new JLabel("Search for Tickets by:");
		lblSearchForTickets.setFocusable(false);
		lblSearchForTickets.setBounds(30, 74, 311, 21);
		getContentPane().add(lblSearchForTickets);
		
		//panel for login
		JPanel panelTopRight = new JPanel();
		panelTopRight.setBorder(UIManager.getBorder("CheckBox.border"));
		panelTopRight.setOpaque(false);
		panelTopRight.setBounds(536, 65, 260, 100);
		getContentPane().add(panelTopRight);
		panelTopRight.setLayout(null);
		
		tFUser = new JTextField();
		tFUser.setEditable(false);
		tFUser.setEditable(false);
		tFUser.setColumns(10);
		tFUser.setBounds(145, 10, 95, 20);
		panelTopRight.add(tFUser);
		
		JLabel lblUser = new JLabel("Admin Username:");
		lblUser.setAlignmentY(5.0f);
		lblUser.setBounds(0, 10, 140, 25);
		panelTopRight.add(lblUser);
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(50, 32, 100, 29);
		panelTopRight.add(btnLogin);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(145, 32, 100, 29);
		panelTopRight.add(btnLogOut);
		
		btnRegister = new JButton("Create an Account");
		btnRegister.setBounds(50, 58, 195, 29);
		panelTopRight.add(btnRegister);
		
		JPanel LoginBGColour = new JPanel();
		LoginBGColour.setBackground(new Color(255,255,255,150));
		LoginBGColour.setBounds(0, 0, 260, 100);
		panelTopRight.add(LoginBGColour);
		
		//panel for adding flights
		JPanel panelFlights = new JPanel();
		panelFlights.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		panelFlights.setOpaque(false);
		panelFlights.setBounds(453, 175, 343, 106);
		getContentPane().add(panelFlights);
		panelFlights.setLayout(null);
		
		btnAddFlight = new JButton("Add a New Flight");
		btnAddFlight.setBounds(93, 36, 151, 29);
		sourceField = new JTextField();
		panelFlights.add(sourceField);
		panelFlights.add(btnAddFlight);
		
		JLabel lblFlightAdminOps = new JLabel("Flight Administration Options");
		lblFlightAdminOps.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlightAdminOps.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFlightAdminOps.setBounds(0, 6, 343, 29);
		panelFlights.add(lblFlightAdminOps);
		
		btnAddFlights = new JButton("Add Flights from File");
		btnAddFlights.setBounds(86, 67, 170, 29);
		panelFlights.add(btnAddFlights);
		
		JPanel FlightAdminBGColour = new JPanel();
		FlightAdminBGColour.setBackground(new Color(255, 255, 255, 150));
		FlightAdminBGColour.setBounds(453, 175, 343, 106);
		getContentPane().add(FlightAdminBGColour);
		
		JLabel Header = new JLabel("ENSF Airlines Ticket Administration System");
		Header.setHorizontalAlignment(SwingConstants.CENTER);
		Header.setBounds(0, 10, 846, 52);
		getContentPane().add(Header);
		Header.setFont(new Font("SignPainter", Font.BOLD, 40));
		
		JLabel background = new JLabel("");
		background.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		background.setBounds(0, 0, 851, 458);
		getContentPane().add(background);
		background.setOpaque(true);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setBackground(new Color(255, 255, 255));
		background.setIcon(new ImageIcon(ClientGUI.class.getResource("/clientgui/resources/Background.jpg")));
		
		addButtonPurpose();
	}
	/**
	* Adds purpose to the buttons of the admin gui
	*/
  	public void addButtonPurpose(){
  		btnClearSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				listModel.clear();
				RadioButtonGroup.clearSelection();
				searchField.setText("");
			}
		});
  		btnAddFlight.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent evt){
  				Object[] options = {"Add", "Cancel"};
  				JTextField sourceField = new JTextField(20);
  				JTextField destField = new JTextField(20);
				JTextField dateField = new JTextField(8);
  				JTextField timeField = new JTextField(5);
  				JTextField durField = new JTextField(5);
  				JTextField totSeatsField = new JTextField(20);
  				JTextField remSeatsField = new JTextField(20);
  				JTextField priceField = new JTextField(20);
  				
				Object[] message = { "Add a new flight:\n",
						"Origin (City Name):         ", sourceField,
						"Destination (City Name):    ", destField,
						"Date of flight (YYYYMMDD):  ", dateField,
						"Time of Departure (hh:mm):  ", timeField,
						"Duration of flight (hours): ", durField,
						"Total Number of Seats:      ", totSeatsField,
						"Available  Number of Seats: ", remSeatsField,
						"Price Per Ticket:$          ", priceField
				};
  				
  				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);

  				if(result == JOptionPane.OK_OPTION){
  					while(sourceField.getText().equals("") || destField.getText().equals("") || dateField.getText().equals("") |
   						   timeField.getText().equals("") || totSeatsField.getText().equals("") || remSeatsField.getText().equals("") ||
   						   priceField.getText().equals("")){
  						JOptionPane.showMessageDialog(null, "Please fill out all fields");
  						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
  						if(result != JOptionPane.OK_OPTION){
  							break;
  						}
  					}
  					while(! isValidDate(dateField.getText())){
   						JOptionPane.showMessageDialog(null, "Date not valid");
   						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
   						if(result != JOptionPane.OK_OPTION){
   							break;
   						}
   					}
  					while(! isValidTime(timeField.getText())){
   						JOptionPane.showMessageDialog(null, "Time not valid");
   						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
   						if(result != JOptionPane.OK_OPTION){
   							break;
   						}
   					}
  					while(! isNumeric(totSeatsField.getText())){
   						JOptionPane.showMessageDialog(null, "Total seats must be a number");
   						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
   						if(result != JOptionPane.OK_OPTION){
   							break;
   						}
   					}
  					while(! isNumeric(remSeatsField.getText())){
   						JOptionPane.showMessageDialog(null, "Available seats must be a number");
   						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
   						if(result != JOptionPane.OK_OPTION){
   							break;
   						}
   					}
  					while(! isNumeric(priceField.getText())){
   						JOptionPane.showMessageDialog(null, "Price must be a number");
   						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
   						if(result != JOptionPane.OK_OPTION){
   							break;
   						}
   					}
  					String output = sourceField.getText()+";"+destField.getText()+";"+dateField.getText()+";"+timeField.getText()+";"
								+durField.getText()+";"+totSeatsField.getText()+";"+remSeatsField.getText()+";"+priceField.getText();
  					Flight f = new Flight();
  					f.source = sourceField.getText();
  					f.dest = destField.getText();
  					f.date = dateField.getText();
  					f.time = timeField.getText();
  					f.duration = durField.getText();
  					f.totalSeats = totSeatsField.getText();
  					f.openSeats = remSeatsField.getText();
  					f.price = priceField.getText();

  					client.out.println("addFlight");
  					client.out.println(output);
  				}
  			}
		});
		btnAddFlights.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Object[] options = {"Add", "Cancel"};
				JTextField fileField = new JTextField(20);
				
				Object[] message = { "Please enter file to add flights from:\n\n",
					"File: ", fileField,
				};
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				
				if(result == JOptionPane.OK_OPTION){
					while(fileField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill out all fields");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while( ! (fileField.getText().endsWith(".txt")) ){
						JOptionPane.showMessageDialog(null, "File must end in .txt");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					String file = fileField.getText();
					readFile(file);
				}
			}
		});
		btnSearch.addActionListener(new ActionListener(){
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent evt){
				if(searchField.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Please enter search parameter");
					return;
				}
				if(rdbtnFlightNum.isSelected()){
					listModel.clear();
					if(isNumeric(searchField.getText())){
						int flightNum = Integer.parseInt(searchField.getText());
						client.out.println("searchTicketsByFlight");
						client.out.println(flightNum);
						try{
							listModel = (DefaultListModel<String>) client.objIn.readObject();
							if(listModel.size() > 2){
								text.setModel(listModel);
							}
							else{
								listModel.clear();
								text.setModel(listModel);
								JOptionPane.showMessageDialog(null, "No tickets for that flight number");
							}
						}catch(ClassNotFoundException | IOException e){
							System.err.println("Error");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Flight number must be a number");
					}
				}
				else if(rdbtnTicketNum.isSelected()){
					listModel.clear();
					if(isNumeric(searchField.getText())){
						int ticketNum = Integer.parseInt(searchField.getText());
						client.out.println("searchTicketsByTicket");
						client.out.println(ticketNum);
						try{
							listModel = (DefaultListModel<String>) client.objIn.readObject();
							if(listModel.size() > 2){
								text.setModel(listModel);
							}
							else{
								listModel.clear();
								text.setModel(listModel);
								JOptionPane.showMessageDialog(null, "No tickets with that ticket number");
							}
						}catch(ClassNotFoundException | IOException e){
							System.err.println("Error");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Ticket number must be a number");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Please specify type of search");
					return;
				}
			}
		});
		
		btnAddTicket.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){	
				Object[] options = {"Book flight", "Cancel"};
				JTextField flightNum = new JTextField(20);
				JTextField firstField = new JTextField(20);
				JTextField lastField = new JTextField(20);
				JTextField dOBField = new JTextField(8);
			
				Object[] message = { "Please enter the flight number for the ticket you are booking:\n\n",
						"Flight Number", flightNum,
						"Please enter the passenger's information for the ticket:\n\n",
						"First Name: ", firstField,
						"Last Name: ", lastField,
						"Date of birth (YYYYMMDD):", dOBField,
				};
				
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
			
				if(result == JOptionPane.OK_OPTION){
					while(flightNum.getText().equals("") || firstField.getText().equals("") || lastField.getText().equals("") || dOBField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "All fields must be completed for booking.");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while( (isValidDateForDOB(dOBField.getText()) == 0)){
						JOptionPane.showMessageDialog(null, "Date not valid");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while( (isValidDateForDOB(dOBField.getText()) == 2)){
						JOptionPane.showMessageDialog(null, "You must be at least 18 years of age to book a flight");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					while( (isValidDateForDOB(dOBField.getText()) == 3)){
						JOptionPane.showMessageDialog(null, "Are you sure you are older than 99 years old?");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							break;
						}
					}
					
					Passenger p = new Passenger();
					p.firstName = firstField.getText();
					p.lastName = lastField.getText();
					p.dOB = dOBField.getText();
					String ticketinfo = "";
					try {
						client.out.println("searchFlight");
						client.out.println(flightNum.getText());
						ticketinfo = client.in.readLine();
					} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
					}
					String[] s = ticketinfo.split(";"); 
					Ticket t = new Ticket();
					t.source = s[0];
					t.dest = s[1];
					t.date = s[2];
					t.time = s[3];
					t.duration = s[4];
					t.pass = p;
					t.flightNum = Integer.parseInt(flightNum.getText());
					
					////t.source = get from parsing ticketinfo
					////t.dest = get from parsing ticketinfo
					////t.date = get from parsing ticketinfo
					////t.time = get from parsing ticketinfo
					////t.duration = get from parsing ticketinfo
				
					Object[] msg = { "Please confirm you'd like to book the following flight:\n\n",
						"First Name: "+ t.pass.firstName,
						"Last Name: "+ t.pass.lastName,
						"Date of birth (YYYYMMDD): " + t.pass.dOB,
						"Flight Number: " + t.flightNum,
						"Origin: " + t.source,
						"Destination: " + t.dest,
						"Date of Departure: " + t.time,
						"Duration: " + t.duration + " hours",
					};
					
					int confirm = JOptionPane.showOptionDialog(null, msg, "Book flight", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
					if(confirm == JOptionPane.YES_OPTION){
						client.out.println("bookFlight");
						try {
							client.objOut.writeObject(t);
						} catch (IOException e) {
							e.printStackTrace();
						}
						int booked = 0;
						try {
							booked = Integer.parseInt(client.in.readLine());
						} catch (NumberFormatException | IOException e) {
							e.printStackTrace();
						}
						client.out.println("updateTicket");
						try {
							client.objOut.writeObject(t);
							t = (Ticket) client.objIn.readObject();
						} catch (IOException | ClassNotFoundException e) {
							e.printStackTrace();
						}
						
						if(booked == 1){
							t.writeTicketToFile();
							JOptionPane.showMessageDialog(null, "Ticket has been booked and the .txt file is ready");
						}
						if(booked == 2){
							JOptionPane.showMessageDialog(null, "Ticket was not booked because the passenger already has a ticket for that flight");
						}
						if(booked == 0){
							JOptionPane.showMessageDialog(null, "Ticket was not successfully booked");
						}
						if(booked == 3){
							JOptionPane.showMessageDialog(null, "Ticket cannot be booked because flight is full!");
						}
					}
				}
			}
		});
		btnBrowse.addActionListener(new ActionListener(){
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent evt){
				listModel.clear();
				client.out.println("browseAllTickets");
				try{
					listModel = (DefaultListModel<String>) client.objIn.readObject();
					if(listModel.size() > 2){
						text.setModel(listModel);
					}
					else{
						listModel.clear();
						text.setModel(listModel);
						JOptionPane.showMessageDialog(null, "No tickets exist in the system yet");
					}
				}catch(ClassNotFoundException | IOException e){
					System.err.println("Error");
				}
			}
		});
		btnCancelTicket.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				if(listModel.size() == 0){
					return;
				}
				Ticket ticket = new Ticket();
				String[] tmp = text.getSelectedValue().split(";");
				ticket.ticketNum = Integer.parseInt(tmp[0]);
				ticket.flightNum = Integer.parseInt(tmp[1]);
				ticket.pass.firstName = tmp[2];
				ticket.pass.lastName = tmp[3];
				ticket.pass.dOB = tmp[4];
				
				Object[] msg = { "Are you sure you want to cancel this ticket:\n\n",
						"Ticket Number: " + ticket.ticketNum,
						"For Flight: " + ticket.flightNum,
						"Passenger First Name:  " + ticket.pass.firstName,
						"Passenger Last Name:  " + ticket.pass.lastName,
						"Passenger DOB (YYYMMDD): " + ticket.pass.dOB,
					};
				int confirm = JOptionPane.showOptionDialog(null, msg, "Confirm to cancel ticket", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				
				if(confirm == JOptionPane.YES_OPTION){
					try {
						client.out.println("cancelTicket");
						client.objOut.writeObject(ticket);
						String cancelled = client.in.readLine();			
						if(cancelled.equals("true")){
							JOptionPane.showMessageDialog(null, "Ticket has been cancelled and open seats of that flight has increased by 1");
						}
						if(cancelled.equals("false")){
							JOptionPane.showMessageDialog(null, "Ticket was not successfully cancelled");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnLogOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if((tFUser.getText().equals(""))){
					JOptionPane.showMessageDialog(null, "You are not logged in");
					return;
				}
				JOptionPane.showMessageDialog(null, "You have been logged out");
				tFUser.setText("");
			}
		});
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(!tFUser.getText().equals("")){
					JOptionPane.showMessageDialog(null, "You are already logged in");
					return;
				}
				Object[] options = {"Login", "Cancel"};
				JTextField userField = new JTextField(20);
				JPasswordField passField = new JPasswordField(20);
				
				Object[] message = { "Please enter your username and password:\n\n",
					"Username: ", userField,
					"Password: ", passField,
				};
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				String pass = new String(passField.getPassword());
				
				if(result == JOptionPane.OK_OPTION){
					while(userField.getText().equals("") || passField.getPassword().length == 0){
						JOptionPane.showMessageDialog(null, "Please fill out all fields");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							return;
						}
					}
					client.out.println("validateAdmin");
					client.out.println(userField.getText());
					client.out.println(pass);
					int validateAdmin;
					try {
						validateAdmin = Integer.parseInt(client.in.readLine());
						while(validateAdmin == 0){
							JOptionPane.showMessageDialog(null, "User name not found");
							result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
							if(result != JOptionPane.OK_OPTION){
								return;
							}
							pass = new String(passField.getPassword());
							client.out.println("validateAdmin");
							client.out.println(userField.getText());
							client.out.println(pass);
							validateAdmin = client.objIn.readInt();
						}
						while(validateAdmin == 2){
							JOptionPane.showMessageDialog(null, "Password not correct");
							result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
							if(result != JOptionPane.OK_OPTION){
								return;
							}
							pass = new String(passField.getPassword());
							client.out.println("validateAdmin");
							client.out.println(userField.getText());
							client.out.println(pass);
							validateAdmin = client.objIn.readInt();
						}
						if(validateAdmin == 1){
							JOptionPane.showMessageDialog(null, "You have successfully been logged in");
							tFUser.setText(userField.getText());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Object[] options = {"Register", "Cancel"};
				JTextField userField = new JTextField(20);
				JPasswordField passField = new JPasswordField(20);
				JPasswordField passField2 = new JPasswordField(20);
				
				Object[] message = { "Please enter your information:\n\n",
					"Username: ", userField,
					"Password: ", passField,
					"Confirm Password: ", passField2,
				};
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				
				if(result == JOptionPane.OK_OPTION){
					while(userField.getText().equals("") || passField.getPassword().length == 0 || passField2.getPassword().length == 0){
						JOptionPane.showMessageDialog(null, "Please fill out all fields");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							return;
						}
					}
					while(! Arrays.equals(passField.getPassword(), passField2.getPassword())){
						JOptionPane.showMessageDialog(null, "Passwords do not match");
						result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
						if(result != JOptionPane.OK_OPTION){
							return;
						}
					}
					client.out.println("validAdmin");
					client.out.println(userField.getText());
					try {
						String validUserName = client.in.readLine();
						while(validUserName.equals("false")){
							JOptionPane.showMessageDialog(null, "Admin username already taken, please choose another one");
							result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
							if(result != JOptionPane.OK_OPTION){
								return;
							}
							client.out.println("validAdmin");
							client.out.println(userField.getText());
							validUserName = client.in.readLine();
						}
						if(validUserName.equals("true")){
							String pass = new String(passField.getPassword());
							client.out.println("insertAdmin");
							client.out.println(userField.getText());
							client.out.println(pass);
							JOptionPane.showMessageDialog(null, "Admin successfully created, you can now use it to login");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
  	/**
	* function to read flighs from text file and add the flights
	* to the database
	*/
	public void readFile(String fileName){
		String line = null;
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			line = br.readLine();
			while( (line = br.readLine()) != null){
				Flight f = new Flight();
				String s = "", d = "", date = "", time = "", dur = "", total = "", open = "", price = "";
				
				int place = 0;
				
				while(line.charAt(place) != ';'){
					s += line.charAt(place);
					place++;
				}
				place++;
				while(line.charAt(place) != ';'){
					d += line.charAt(place);
					place++;
				}
				place++;
				while(line.charAt(place) != ';'){
					date += line.charAt(place);
					place++;
				}
				place++;
				while(line.charAt(place) != ';'){
					time += line.charAt(place);
					place++;
				}
				place++;
				while(line.charAt(place) != ';'){
					dur += line.charAt(place);
					place++;
				}
				place++;
				while(line.charAt(place) != ';'){
					total += line.charAt(place);
					place++;
				}
				place++;
				while(line.charAt(place) != ';'){
					open += line.charAt(place);
					place++;
				}
				place++;
				while(line.charAt(place) != ';'){
					price += line.charAt(place);
					place++;
				}
//				System.out.println(s + " " + d + " " + date +  " " + time +  " " + dur +  " " + total + " " + open + " " + price);
				f.source = s;
				f.dest = d;
				f.date = date;
				f.time = time;
				f.duration = dur;
				f.totalSeats = total;
				f.openSeats = open;
				f.price = price;
				
				if(isValidTime(f.time) && isValidDate(f.date) && isNumeric(f.duration) && isNumeric(f.totalSeats) && isNumeric(f.openSeats) && isNumeric(f.price)){
					client.out.println("addFlight");
					client.out.println(line);
				}
			}
			br.close();
		}catch(FileNotFoundException ex){
			System.err.println("File not found");
		}catch(IOException ex){
			System.err.println("Error with input file");
		}finally{
			JOptionPane.showMessageDialog(null, "Finished adding flights from file");
		}
	}
	/**
	* function checks if a string is numeric
	* @param a the string to be checked
	* @return true if numeric
	*/
	public boolean isNumeric(String a){
		try{
			@SuppressWarnings("unused")
			double d = Double.parseDouble(a);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	/**
	* function checks if a string is a valid time
	* @param a the string to be checked
	* @return true if time entered is valid
	*/
	public boolean isValidTime(String time){
		try{
			DateFormat df = new SimpleDateFormat("HH:mm");
			df.setLenient(false);
			@SuppressWarnings("unused")
			Date d = df.parse(time);
			return true;
		}
		catch(ParseException e){
			return false;
		}
	}
	/**
	* function checks if a string is a valid date
	* @param date the string to be checked
	* @return true if date entered is valid
	*/
	public boolean isValidDate(String date){
		try{
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			df.setLenient(false);
			
			String year = "";
			for(int i=0; i<4; i++){
				year += date.charAt(i);
			}
			int y = Integer.parseInt(year);
			if(y < 2017){
				return false;
			}
			
			String month = "";
			for(int i=4; i<6; i++){
				month += date.charAt(i);
			}
			int m = Integer.parseInt(month);
			if(m < 4){
				return false;
			}
			
			String day = "";
			for(int i=6; i<8; i++){
				day += date.charAt(i);
			}
			int d = Integer.parseInt(day);
			if(d < 4){
				return false;
			}
			
			df.parse(date);
			return true;
		}catch(ParseException e){
			return false;
		}
	}
	/**
	* function checks if a string is a valid date for date of birth
	* @param date the string to be checked
	* @return 1 if dOB is valid, 0 if not valid, 2 if dOB makes the person
	* less than 18, and 3 if age is more than 99
	*/
	public int isValidDateForDOB(String date){
		//returns 1 if dOB is valid
		//returns 0 if dOB is not valid
		//returns 2 if less than 18
		//returns 3 if more than 99
		try{
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			df.setLenient(false);
			df.parse(date);
			
			String year = "";
			for(int i=0; i<4; i++){
				year += date.charAt(i);
			}
			int y = Integer.parseInt(year);
			if((2017-y) < 18){
				return 2;
			}
			if((2017-y) > 99){
				return 3;
			}
			return 1;
		}catch(ParseException e){
			return 0;
		}
	}
	/**
	 * Launches the admin window
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		AdminGui frame = new AdminGui();
		frame.setVisible(true);
		frame.client.communicate();
	}
}
