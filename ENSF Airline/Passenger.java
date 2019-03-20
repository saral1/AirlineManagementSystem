import java.io.Serializable;
/**
 * The Passenger class provides data fields and methods with the 
 * purpose of representing the passenger of an airline
 * 
 * @author Aidan Paterson, Maria Lau, Sara Li
 * @version 1.0
 * @since March 31st, 2017
 */
public class Passenger implements Serializable {
	/**
	* serialVersionUID is the serial ID for serialization
	*/
	private static final long serialVersionUID = 1L;
	/**
	* firstName the first name of passenger
	*/
	String firstName;
	/**
	* lastName the last name of passenger
	*/
	String lastName;
	/**
	* dOB the date of birth of passenger
	*/
	String dOB;
	/**
	* default constructor of class Passenger
	*/
	public Passenger(){
	}
}
