import java.io.Serializable;
/**
 * The flight class provides data fields and methods with the 
 * purpose of representing a flight for the airline
 * 
 * @author Aidan Paterson, Maria Lau, Sara Li
 * @version 1.0
 * @since March 31st, 2017
 */
public class Flight implements Serializable {
	/**
	* serialVersionUID is the serial ID for serialization
	*/
	private static final long serialVersionUID = 1L;
	/**
	* flightID the unique ID of the flight
	*/
	int flightID;
	/**
	* source the place where plan is flying from
	*/
	String source;
	/**
	* dest the destination of the flight
	*/
	String dest;
	/**
	* date the date of the flight
	*/
	String date;
	/**
	* time the time of departure of the flight
	*/
	String time;
	/**
	* duration the length of flight in hours
	*/
	String duration;
	/**
	* totalSeats the total number of seats on flight
	*/
	String totalSeats;
	/**
	* openSeats the number of seats available on flight
	*/
	String openSeats;
	/**
	* price the price per ticket of flight
	*/
	String price;
	/**
	* default constructor of class Flight
	*/
	public Flight() {	
	}
	/**
	* constructor of Flight sets variables to input string
	* @input the string to be split up and assigned to different
	* variables of Flight
	*/
	public Flight(String input){
		String[] s = input.split(";");
		source = s[0];
		dest = s[1];
		date = s[2];
		time = s[3];
		duration = s[4];
		totalSeats = s[5];
		openSeats = s[6];
		price = s[7];
	}
	/**
	* the toString method of class Flight for serialization
	*/
	public String toString() {
		String s = "";
		s += "Flight ID: " + flightID;
		s += "\tsource: " + source;
		s += "\tdest: " + dest;
		s += "\tdate: " + date;
		s += "\ttime: " + time;
		s += "\tduration: " + duration + " hours";
		s += "\ttotalSeats: " + totalSeats;
		s += "\topenSeats: " + openSeats;
		s += "\tprice: " + price;
		return s;
	}
}
