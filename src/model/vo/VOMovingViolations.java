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
public class VOMovingViolations implements Comparable<VOMovingViolations>{

	/**
	 * Atributo que da el identificador de la ubicacion de la infraccion.
	 */
	private int addressID;
	
	private String streetSegId;
	
	private int xCoord;
	
	private int yCoord;

	/**
	 * Atributo que da la fecha de la infracci�n
	 */
	private String ticketIssueDate;

	/**
	 * Atributo que da la localizacion de la infraccion
	 */
	private String location;
	private int id;
	
	/**
	 * Constructor VOMovingViolations
	 * @param pLocation localizacion de la infraccion
	 * @param pIssueDate fecha de la infracci�n
	 * @param pAddress direccion de la infracci�n

	 */
	public VOMovingViolations(int Oid,String pIssueDate, int pAddress, String pLocation, String streetId, int x, int y){
		id=Oid;
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
	public int darxCoor()
	{
		return xCoord;
	}
	public int daryCoord()
	{
		return yCoord;
	}
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
	}

	public LocalDateTime darFechaLocalDateTime(){
		return convertirFecha_Hora_LDT(ticketIssueDate);
	}


	@Override
	public int compareTo(VOMovingViolations o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
