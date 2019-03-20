import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * The ClientGui class provides data fields and methods with the 
 * purpose of representing the window that the users use
 * 
 * @author Aidan Paterson, Maria Lau, Sara Li
 * @version 1.0
 * @since March 31st, 2017
 */
public class ClientGUI extends JFrame {
	/**
	* serialVersionUID is the serial ID for serialization
	*/
	private static final long serialVersionUID = 1L;
	/**
	* txtSearchForFlight is the field to enter the search parameter
	*/
	private JTextField txtSearchForFlight;
	/**
	* destTF is the field to enter the destination of flight
	*/
	private JTextField destTF;
	/**
	* dateTF is the field to enter the date of flight
	*/
	private JTextField dateTF;
	/**
	* originTF is the field to enter the origin of flight
	*/
	private JTextField originTF;
	/**
	* txtSearchResults is words for search results
	*/
	private JTextField txtSearchResults;
	/**
	* txtFlightDetails is words for flights details
	*/
	private JTextField txtFlightDetails;
	/**
	* tFDate the date of the flight
	*/
	private JTextField tFDate;
	/**
	* tFFlightNum the flight number of the flight
	*/
	private JTextField tFFlightNum;
	/**
	* tFOrigin the origin of the flight
	*/
	private JTextField tFOrigin;
	/**
	* tFDest the destination of the flight
	*/
	private JTextField tFDest;
	/**
	* tFTimeDepart the time of departure of the flight
	*/
	private JTextField tFTimeDepart;
	/**
	* tFDuration the duration of the flight
	*/
	private JTextField tFDuration;
	/**
	* tFTotNumSeats the total number of seats of flight
	*/
	private JTextField tFTotNumSeats;
	/**
	* tFRemSeats the remaining seats of flight
	*/
	private JTextField tFRemSeats;
	/**
	* tFPrice the price per ticket of flight
	*/
	private JTextField tFPrice;
	/**
	* tFUser where the username appears after user login
	*/
	private JTextField tFUser;
	/**
	* client the client of the airline
	*/
	private Client client;
	/**
	* btnClear the clear button for search parameter
	*/
	JButton btnClear;
	/**
	* btnSearch the search button
	*/
	JButton btnSearch;
	/**
	* btnClearResuls clears the search results 
	*/
	JButton btnClearResults;
	/**
	* btnBookFlight books ticket for a flight
	*/
	JButton btnBookFlight;
	/**
	* btnLogin is the login button for client
	*/
	JButton btnLogin;
	/**
	* btnLogOut if the log out button for client
	*/
	JButton btnLogOut;
	/**
	* btnRegister is to register a new client
	*/
	JButton btnRegister;
	/**
	* listModel contains the search results
	*/
	DefaultListModel<String> listModel;
	/**
	* text contains the listModel of search results
	*/
	JList<String> text;
	/**
	* searchResults contains text which makes search results scrollable
	*/
	JScrollPane searchResults;
	/**
	 * Default constructor of ClientGUI creates the frame.
	 */
	public ClientGUI() {
		
		client = new Client("localhost", 9898);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(153, 153, 153));
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClientGUI.class.getResource("/clientgui/resources/plane.jpg")));
		setTitle("ENSF Airlines - Passenger Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 480);
		getContentPane().setLayout(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setBorder(UIManager.getBorder("CheckBox.border"));
		panelTop.setOpaque(false);
		panelTop.setBounds(24, 50, 350, 175);
		getContentPane().add(panelTop);
		panelTop.setLayout(null);
		
		//
		JPanel panelTopRight = new JPanel();
		panelTopRight.setBorder(UIManager.getBorder("CheckBox.border"));
		panelTopRight.setOpaque(false);
		panelTopRight.setBounds(566, 65, 230, 100);
		getContentPane().add(panelTopRight);
		panelTopRight.setLayout(null);
		
		tFUser = new JTextField();
		tFUser.setEditable(false);
		tFUser.setEditable(false);
		tFUser.setColumns(10);
		tFUser.setBounds(115, 10, 95, 20);
		panelTopRight.add(tFUser);
		
		JLabel lblUser = new JLabel("User name:");
		lblUser.setAlignmentY(5.0f);
		lblUser.setBounds(0, 10, 100, 25);
		panelTopRight.add(lblUser);
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(20, 32, 100, 29);
		panelTopRight.add(btnLogin);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(115, 32, 100, 29);
		panelTopRight.add(btnLogOut);
		
		btnRegister = new JButton("Create an Account");
		btnRegister.setBounds(20, 58, 195, 29);
		panelTopRight.add(btnRegister);
		
		JPanel LoginBGColour = new JPanel();
		LoginBGColour.setBackground(new Color(255,255,255,150));
		LoginBGColour.setBounds(0, 0, 230, 100);
		panelTopRight.add(LoginBGColour);
		
		
		txtSearchForFlight = new JTextField();
		txtSearchForFlight.setFocusable(false);
		txtSearchForFlight.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		txtSearchForFlight.setBorder(null);
		txtSearchForFlight.setBounds(38, 10, 147, 25);
		panelTop.add(txtSearchForFlight);
		txtSearchForFlight.setText("Search for Flight by :");
		txtSearchForFlight.setColumns(10);
		
		JLabel date = new JLabel("Date (YYYYMMDD):");
		date.setHorizontalAlignment(SwingConstants.RIGHT);
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		date.setBounds(6, 35, 175, 35);
		panelTop.add(date);
		
		JLabel origin = new JLabel("Origin:");
		origin.setHorizontalAlignment(SwingConstants.RIGHT);
		origin.setBounds(6, 71, 175, 35);
		panelTop.add(origin);
		origin.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JLabel dest = new JLabel("Destination:");
		dest.setHorizontalAlignment(SwingConstants.RIGHT);
		dest.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		dest.setBounds(6, 106, 175, 35);
		panelTop.add(dest);
		
		
		dateTF = new JTextField();
		dateTF.setHorizontalAlignment(SwingConstants.LEFT);
		dateTF.setBounds(182, 36, 165, 35);
		panelTop.add(dateTF);
		dateTF.setColumns(10);
		
		originTF = new JTextField();
		originTF.setHorizontalAlignment(SwingConstants.LEFT);
		originTF.setColumns(10);
		originTF.setBounds(182, 71, 165, 35);
		panelTop.add(originTF);
		
		destTF = new JTextField();
		destTF.setHorizontalAlignment(SwingConstants.LEFT);
		destTF.setColumns(10);
		destTF.setBounds(182, 106, 165, 35);
		panelTop.add(destTF);
		
		btnClear = new JButton("Clear Search");
		btnClear.setBounds(68, 140, 117, 29);
		panelTop.add(btnClear);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(182, 140, 117, 29);
		panelTop.add(btnSearch);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(ClientGUI.class.getResource("/clientgui/resources/planesmall.jpg")));
		logo.setBounds(58, 48, 80, 80);
		panelTop.add(logo);
		
		
		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(null);
		panelBottom.setOpaque(false);
		panelBottom.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		panelBottom.setBounds(24, 225, 765, 214);
		getContentPane().add(panelBottom);
		
		JLabel lblclickOnFlight = new JLabel(" (Click on flight to see additional details)");
		lblclickOnFlight.setBounds(128, 15, 268, 16);
		panelBottom.add(lblclickOnFlight);

		
		listModel = new DefaultListModel<String>();
		text = new JList<String>(listModel);
		searchResults = new JScrollPane(text);
		searchResults.setBounds(10, 29, 386, 150);
		panelBottom.add(searchResults);
		

		txtSearchResults = new JTextField();
		txtSearchResults.setText("Search Results:");
		txtSearchResults.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		txtSearchResults.setColumns(10);
		txtSearchResults.setBorder(null);
		txtSearchResults.setBounds(10, 10, 117, 25);
		panelBottom.add(txtSearchResults);
		
		btnClearResults = new JButton("Clear Results");
		btnClearResults.setBounds(10, 180, 117, 29);
		panelBottom.add(btnClearResults);
		
		tFDate = new JTextField();
		tFDate.setEditable(false);
		tFDate.setColumns(10);
		tFDate.setBounds(675, 32, 75, 20);
		panelBottom.add(tFDate);
		
		tFFlightNum = new JTextField();
		tFFlightNum.setEditable(false);
		tFFlightNum.setColumns(10);
		tFFlightNum.setBounds(535, 32, 75, 20);
		panelBottom.add(tFFlightNum);
		
		tFOrigin = new JTextField();
		tFOrigin.setEditable(false);
		tFOrigin.setColumns(10);
		tFOrigin.setBounds(535, 55, 215, 20);
		panelBottom.add(tFOrigin);
		
		tFDest = new JTextField();
		tFDest.setEditable(false);
		tFDest.setColumns(10);
		tFDest.setBounds(535, 78, 215, 20);
		panelBottom.add(tFDest);
		
		tFTimeDepart = new JTextField();
		tFTimeDepart.setEditable(false);
		tFTimeDepart.setColumns(10);
		//tFTimeDepart.setBounds(535, 100, 75, 20);
		tFTimeDepart.setBounds(535, 103, 55, 20);
		panelBottom.add(tFTimeDepart);
		
		tFDuration = new JTextField();
		tFDuration.setEditable(false);
		tFDuration.setColumns(10);
		tFDuration.setBounds(675, 103, 75, 20);
		panelBottom.add(tFDuration);
		
		tFTotNumSeats = new JTextField();
		tFTotNumSeats.setEditable(false);
		tFTotNumSeats.setColumns(10);
		tFTotNumSeats.setBounds(535, 127, 75, 20);
		panelBottom.add(tFTotNumSeats);
		
		tFRemSeats = new JTextField();
		tFRemSeats.setEditable(false);
		tFRemSeats.setColumns(10);
		tFRemSeats.setBounds(535, 152, 75, 20);
		panelBottom.add(tFRemSeats);
		
		tFPrice = new JTextField();
		tFPrice.setEditable(false);
		tFPrice.setColumns(10);
		tFPrice.setBounds(675, 152, 75, 20);
		panelBottom.add(tFPrice);
		
		JLabel lblPrice = new JLabel("Price: $");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setAlignmentY(5.0f);
		lblPrice.setBounds(615, 150, 60, 25);
		panelBottom.add(lblPrice);
		
		JLabel lblRemSeats = new JLabel("Remaining # Seats:");
		lblRemSeats.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRemSeats.setAlignmentY(5.0f);
		lblRemSeats.setBounds(410, 150, 125, 25);
		panelBottom.add(lblRemSeats);
		
		JLabel lblTotNumSeats = new JLabel("Total # Seats:");
		lblTotNumSeats.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotNumSeats.setAlignmentY(5.0f);
		lblTotNumSeats.setBounds(410, 125, 125, 25);
		panelBottom.add(lblTotNumSeats);
		
		JLabel lblDuration = new JLabel("Duration (hr):");
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuration.setAlignmentY(5.0f);
		lblDuration.setBounds(585, 100, 92, 25);
		panelBottom.add(lblDuration);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setAlignmentY(5.0f);
		lblDate.setBounds(635, 28, 40, 25);
		panelBottom.add(lblDate);
		
		JLabel lblTimeDepart = new JLabel("Time of Departure:");
		lblTimeDepart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeDepart.setAlignmentY(5.0f);
		lblTimeDepart.setBounds(410, 100, 125, 25);
		panelBottom.add(lblTimeDepart);
		
		JLabel lblDest = new JLabel("Destination:");
		lblDest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDest.setAlignmentY(5.0f);
		lblDest.setBounds(410, 74, 125, 25);
		panelBottom.add(lblDest);
		
		JLabel lblOrigin = new JLabel("Origin:");
		lblOrigin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrigin.setAlignmentY(5.0f);
		lblOrigin.setBounds(410, 50, 125, 25);
		panelBottom.add(lblOrigin);
		
		JLabel lblFlightNum = new JLabel("Flight Number:");
		lblFlightNum.setAlignmentY(5.0f);
		lblFlightNum.setBounds(410, 28, 125, 25);
		panelBottom.add(lblFlightNum);
		lblFlightNum.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JTextArea FlightInfo = new JTextArea();
		FlightInfo.setForeground(Color.WHITE);
		FlightInfo.setCaretColor(Color.WHITE);
		FlightInfo.setOpaque(false);
		FlightInfo.setDisabledTextColor(Color.BLACK);
		FlightInfo.setBackground(Color.WHITE);
		FlightInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlightInfo.setEditable(false);
		FlightInfo.setBounds(410, 28, 343, 151);
		panelBottom.add(FlightInfo);
		
		JPanel FlightInfoBGColour = new JPanel();
		FlightInfoBGColour.setBackground(new Color(255,255,255,150));
		FlightInfoBGColour.setBounds(410, 28, 343, 151);
		panelBottom.add(FlightInfoBGColour);
		
		txtFlightDetails = new JTextField();
		txtFlightDetails.setText("Flight Details:");
		txtFlightDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		txtFlightDetails.setColumns(10);
		txtFlightDetails.setBorder(null);
		txtFlightDetails.setBounds(410, 10, 171, 21);
		panelBottom.add(txtFlightDetails);
		
		btnBookFlight = new JButton("Book Flight");
		btnBookFlight.setBounds(636, 180, 117, 29);
		panelBottom.add(btnBookFlight);
		
		JLabel Header = new JLabel("Welcome to ENSF Airlines Online Booking System");
		Header.setHorizontalAlignment(SwingConstants.CENTER);
		Header.setBounds(0, 10, 851, 52);
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
	* method to add purposes and functions to buttons in the client gui
	*/
	public void addButtonPurpose(){
		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dateTF.setText("");
				destTF.setText("");
				originTF.setText("");
			}
		});
		
		btnClearResults.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				listModel.clear();
				tFFlightNum.setText("");
				tFDate.setText("");
				tFOrigin.setText("");
				tFDest.setText("");
				tFTimeDepart.setText("");
				tFDuration.setText("");
				tFTotNumSeats.setText("");
				tFRemSeats.setText("");
				tFPrice.setText("");
			}
		});
		btnSearch.addActionListener(new ActionListener(){
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent evt){
				if(dateTF.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Please enter date of flight.");
					return;
				}
				else if(originTF.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Please enter origin of flight");
					return;
				}
				else if(destTF.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Please enter destination of flight");
					return;
				}
				
				if(!(dateTF.getText().equals("") || originTF.getText().equals("") || destTF.getText().equals(""))){
					listModel.clear();
					String date = dateTF.getText();
					String origin = originTF.getText();
					String dest = destTF.getText();
					client.out.println("searchFlights");
					try{						
						client.out.println(date);
						client.out.println(origin);
						client.out.println(dest);
						listModel = (DefaultListModel<String>) client.objIn.readObject();
						if (listModel.isEmpty()) {
							listModel.addElement("Nothing found!");
							return;
						}
						text.setModel(listModel);
					}catch(ClassNotFoundException | IOException e){
						e.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter an item to search for!");
				}
			}
		});
		btnBookFlight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
			
				if(tFFlightNum.getText().equals("") && text.isSelectionEmpty()){
					JOptionPane.showMessageDialog(null, "Please search and select flight to display and book");
					return;
				}				
				
				Object[] options = {"Book flight", "Cancel"};
				JTextField firstField = new JTextField(20);
				JTextField lastField = new JTextField(20);
				JTextField dOBField = new JTextField(8);
			
				Object[] message = { "Please enter your information:\n\n",
						"First Name: ", firstField,
						"Last Name: ", lastField,
						"Date of birth (YYYYMMDD):", dOBField,
				};
				int result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
			
				if(result == JOptionPane.OK_OPTION){
					while(firstField.getText().equals("") || lastField.getText().equals("") || dOBField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Please fill out all fields");
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
				
					Ticket t = new Ticket();
					t.pass = p;
					t.flightNum = Integer.parseInt(tFFlightNum.getText());
					t.source = tFOrigin.getText();
					t.dest = tFDest.getText();
					t.date = tFDate.getText();
					t.time = tFTimeDepart.getText();
					t.duration = tFDuration.getText();
				
					Object[] msg = { "Would you like to book the following flight:\n\n",
						"First Name: "+ t.pass.firstName,
						"Last Name: "+ t.pass.lastName,
						"Date of birth (YYYYMMDD): " + t.pass.dOB,
						"Flight Number: " + t.flightNum,
						"Origin: " + t.source,
						"Destination: " + t.dest,
						"Date of Departure: " + t.time,
						"Duration: " + t.duration + " hours",
					};
					int confirm = JOptionPane.showOptionDialog(null, msg, "Confirm to book flight", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
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
					client.out.println("validateUser");
					client.out.println(userField.getText());
					client.out.println(pass);
					int validateUser;
					try {
						validateUser = Integer.parseInt(client.in.readLine());
						while(validateUser == 0){
							JOptionPane.showMessageDialog(null, "User name not found");
							result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
							if(result != JOptionPane.OK_OPTION){
								return;
							}
							pass = new String(passField.getPassword());
							client.out.println("validateAdmin");
							client.out.println(userField.getText());
							client.out.println(pass);
							validateUser = client.objIn.readInt();
						}
						while(validateUser == 2){
							JOptionPane.showMessageDialog(null, "Password not correct");
							result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
							if(result != JOptionPane.OK_OPTION){
								return;
							}
							pass = new String(passField.getPassword());
							client.out.println("validateAdmin");
							client.out.println(userField.getText());
							client.out.println(pass);
							validateUser = client.objIn.readInt();
						}
						if(validateUser == 1){
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
					client.out.println("validUser");
					client.out.println(userField.getText());
					try {
						String validUserName = client.in.readLine();
						while(validUserName.equals("false")){
							JOptionPane.showMessageDialog(null, "Username already taken, please choose another one");
							result = JOptionPane.showOptionDialog(null, message, "", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
							if(result != JOptionPane.OK_OPTION){
								return;
							}
							client.out.println("validUser");
							client.out.println(userField.getText());
							validUserName = client.in.readLine();
						}
						if(validUserName.equals("true")){
							String pass = new String(passField.getPassword());
							client.out.println("insertUser");
							client.out.println(userField.getText());
							client.out.println(pass);
							JOptionPane.showMessageDialog(null, "User successfully created, you can now use it to login");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		text.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!text.isSelectionEmpty()) {
					String[] s = text.getSelectedValue().split(";");
					tFFlightNum.setText(s[0]);
					tFOrigin.setText(s[1]);
					tFDest.setText(s[2]);
					tFDate.setText(s[3]);
					tFTimeDepart.setText(s[4]);
					tFDuration.setText(s[5]);
					tFTotNumSeats.setText(s[6]);
					tFRemSeats.setText(s[7]);
					tFPrice.setText(s[8]);
				}
				
			}
		});
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
	 * Launch the client window
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		ClientGUI frame = new ClientGUI();
		frame.setVisible(true);
		frame.client.communicate();
	}
}
