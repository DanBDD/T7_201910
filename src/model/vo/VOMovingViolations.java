package model.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {

	private int addressID;
	
	private String streetSegId;
	
	private double xCoord;
	
	private double yCoord;

	private String ticketIssueDate;

	private String location;

	private int objectID;
	/**
	 * Constructor VOMovingViolations
	 * @param pLocation localizacion de la infraccion
	 * @param pIssueDate fecha de la infracciï¿½n
	 * @param pAddress direccion de la infracciï¿½n

	 */
	public VOMovingViolations(int pID,String pIssueDate, int pAddress, String pLocation, String streetId, double x, double y){
		objectID = pID;
		location = pLocation;
		addressID = pAddress;
		ticketIssueDate = pIssueDate;
		streetSegId=streetId;
		xCoord=x;
		yCoord=y;

	}

	public String darLocation(){
		return location;
	}

	public int darAddressID(){
		return addressID;
	}
	public String darFecha(){
		return ticketIssueDate;
	}
	public String darStreetId()
	{
		return streetSegId;
	}
	public double darxCoor()
	{
		return xCoord;
	}
	public double daryCoord()
	{
		return yCoord;
	}
	public int darID() {
		return objectID;
	}
	public String toString() {
		return "Datos de la infracción: Localización: " + location + " AddressID " + addressID + " StreetSegID " 
	+ streetSegId + " XCoord " + xCoord + " YCoord " + yCoord  + " TicketIssueDate " + ticketIssueDate ;
		
	}
}
