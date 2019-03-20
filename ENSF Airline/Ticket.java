import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
/**
 * The ticket class provides data fields and methods with the 
 * purpose of being the ticket that users and admins can book
 *
 * @author Aidan Paterson, Maria Lau, Sara Li
 * @version 1.0
 * @since March 30th, 2017
 *
 */
public class Ticket implements Serializable{
	/**
	* serialVersionUID is the ID of the serialization
	*/
	private static final long serialVersionUID = 1L;
	/**
	* ticketNum is the unique ticket number assigned to the ticket
	*/
	int ticketNum;
	/**
	* pass is the passenger the ticket is for
	*/
	Passenger pass;
	/**
	* flightNum is the flight number of the ticket
	*/
	int flightNum;
	/**
	* source is where the flight is flying from
	*/
	String source;
	/**
	* dest is the destination of the flight
	*/
	String dest;
	/**
	* date is the date of the flight
	*/
	String date;
	/**
	* time is the time of departure of the flight
	*/
	String time;
	/**
	* duration is the duration of the flight
	*/
	String duration;
	/**
	* price is the price per ticket
	*/
	String price;
	/**
	* default constructor in class Ticket
	* make a new null passenger
	*/
	public Ticket(){
		pass = new Passenger();
	}
	/**
	* writes the ticket to a .txt file saved on the computer
	*/
	public void writeTicketToFile(){
		try{
			String fileName = "flight";
			fileName += Integer.toString(flightNum);
			fileName += "ticket";
			fileName += Integer.toString(ticketNum);
			fileName += ".txt";
			FileWriter fw = new FileWriter(fileName, false);
			BufferedWriter bw = new BufferedWriter(fw);	

			bw.write("ENSF Airline Ticket:\n\n");
			bw.write("Passenger first name:    " + pass.firstName + "\n");
			bw.write("Passenger last name:     " + pass.lastName + "\n");
			bw.write("Passenger date of birth: " + pass.dOB + "\n\n");
			bw.write("Flight number:           " + flightNum + "\n");
			bw.write("Flight origin:           " + source + "\n");
			bw.write("Flight destination:      " + dest + "\n");
			bw.write("Flight date (YYYYMMDD):  " + date + "\n");
			bw.write("Flight time (HH:MM):     " + time + "\n");
			bw.write("Flight duration:         " + duration +" hours");
			
			bw.close();
		}
		catch(IOException ex){
			System.out.println("Error writing to output file 1\n");
		}
	}
}
