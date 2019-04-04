package controller;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.data_structures.ArregloDinamico;
import model.data_structures.Cola;
import model.data_structures.Comparaciones;
import model.data_structures.RedBlackBST;
import model.util.Sort;
import model.vo.ObjectID;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;



	/**
	 * Ruta de archivo CSV Enero.
	 */
	public static final String rutaEnero = "./data/Moving_Violations_Issued_in_January_2018.json";

	/**
	 * Ruta de archivo CSV Febrero.
	 */
	public static final String rutaFebrero = "./data/Moving_Violations_Issued_in_February_2018.json";

	/**
	 * Ruta de archivo CSV Marzo.
	 */
	public static final String rutaMarzo = "./data/Moving_Violations_Issued_in_March_2018.json";

	/**
	 * Ruta de archivo CSV Abril.
	 */
	public static final String rutaAbril = "./data/Moving_Violations_Issued_in_April_2018.json";

	public static final String rutaMayo = "./data/Moving_Violations_Issued_in_May_2018.json";

	public static final String rutaJunio = "./data/Moving_Violations_Issued_in_June_2018.json";

	private RedBlackBST<ObjectID, VOMovingViolations> arbol;
	
	public Controller() {
		view = new MovingViolationsManagerView();
		arbol = new RedBlackBST<ObjectID, VOMovingViolations>();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		int nDatos = 0;
		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				nDatos = this.loadMovingViolations();
				view.printMessage("Datos cargados, total de datos: " + nDatos);
				
				break;
			case 1:
				System.out.println("Introduzca el ObjectID a buscar.");
				int id = sc.nextInt();
				ObjectID busqueda = new ObjectID(id);
				VOMovingViolations objRetorno = arbol.get(busqueda);
				System.out.println(objRetorno.toString());
				break;
			case 2: 
				System.out.println("Introduzca el rango inferior de ObjectID a buscar.");
				int inferior = sc.nextInt();
				System.out.println("Introduzca el rango superior de ObjectID a buscar.");
				int superior = sc.nextInt();
				ObjectID limInf = new ObjectID(inferior);
				ObjectID limSup = new ObjectID(superior);
				ArregloDinamico<VOMovingViolations> res = arbol.values(limInf, limSup);
				view.printRes2(res);
				break;
			case 3:	
				fin=true;
				sc.close();
				break;
			case 4:
				double resr = prom();
				System.out.println("Promedio " + resr);
			}
		}
	}
	public int loadMovingViolations() {
		int obID = 0;
		String loc = null;
		int addID = 0;
		String date=null;
		String address = null;
		String street=null;
		double x=0;
		double y=0;
		int cont = 0;
		JsonParser parser = new JsonParser();
		try{
			JsonArray ja = (JsonArray) parser.parse(new FileReader(rutaEnero));
			for(int i = 0; ja != null && i<ja.size(); i++){
				JsonObject actual = (JsonObject) ja.get(i);
				if(actual.get("OBJECTID") != null){
					obID = actual.get("OBJECTID").getAsInt();
				}
				if(actual.get("LOCATION") != null){
					loc = actual.get("LOCATION").getAsString();
				}
				if(actual.get("ADDRESS_ID") != null){
					if(actual.get("ADDRESS_ID").toString().equals("null")){
						address = "0";
						addID = Integer.parseInt(address);
					}
					else{
						addID = actual.get("ADDRESS_ID").getAsInt();
					}
				}
				if(actual.get("TICKETISSUEDATE") != null){
					date = actual.get("TICKETISSUEDATE").getAsString();
				}
				if(actual.get("STREETSEGID") != null){
					if(actual.get("STREETSEGID").toString().equals("null")){
						street = "0";
					}
					else{
						street = actual.get("STREETSEGID").getAsString();
					}
				}
				if(actual.get("XCOORD") != null){
					x= actual.get("XCOORD").getAsDouble();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsDouble();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arbol.put(new ObjectID(obID), new VOMovingViolations(obID,date, addID, loc, street, x, y));
					cont++;
				}
			}
			System.out.println("Datos Enero: " + cont);
			cont = 0;
			JsonArray ja1 = (JsonArray) parser.parse(new FileReader(rutaFebrero));
			for(int i = 0; ja1 != null && i<ja1.size(); i++){
				JsonObject actual = (JsonObject) ja1.get(i);
				if(actual.get("OBJECTID") != null){
					obID = actual.get("OBJECTID").getAsInt();			
				}
				if(actual.get("LOCATION") != null){
					loc = actual.get("LOCATION").getAsString();
				}
				if(actual.get("ADDRESS_ID") != null){
					if(actual.get("ADDRESS_ID").toString().equals("null")){
						address = "0";
						addID = Integer.parseInt(address);
					}
					else{
						addID = actual.get("ADDRESS_ID").getAsInt();
					}
				}
				if(actual.get("TICKETISSUEDATE") != null){
					date = actual.get("TICKETISSUEDATE").getAsString();
				}
				if(actual.get("STREETSEGID") != null){
					if(actual.get("STREETSEGID").toString().equals("null")){
						street = "0";
					}
					else{
						street = actual.get("STREETSEGID").getAsString();
					}
				}
				if(actual.get("XCOORD") != null){
					x= actual.get("XCOORD").getAsDouble();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsDouble();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arbol.put(new ObjectID(obID), new VOMovingViolations(obID, date, addID, loc, street, x, y));
					cont++;
					
				}
			}
			System.out.println("Datos Febrero: " + cont);
			cont = 0;
			
			JsonArray ja2 = (JsonArray) parser.parse(new FileReader(rutaMarzo));
			for(int i = 0; ja2 != null && i<ja2.size(); i++){
				JsonObject actual = (JsonObject) ja2.get(i);
				if(actual.get("OBJECTID") != null){
					obID = actual.get("OBJECTID").getAsInt();			
				}
				if(actual.get("LOCATION") != null){
					loc = actual.get("LOCATION").getAsString();
				}
				if(actual.get("ADDRESS_ID") != null){
					if(actual.get("ADDRESS_ID").toString().equals("null")){
						address = "0";
						addID = Integer.parseInt(address);
					}
					else{
						addID = actual.get("ADDRESS_ID").getAsInt();
					}
				}
				if(actual.get("TICKETISSUEDATE") != null){
					date = actual.get("TICKETISSUEDATE").getAsString();
				}
				if(actual.get("STREETSEGID") != null){
					if(actual.get("STREETSEGID").toString().equals("null")){
						street = "0";
					}
					else{
						street = actual.get("STREETSEGID").getAsString();
					}
				}
				if(actual.get("XCOORD") != null){
					x= actual.get("XCOORD").getAsDouble();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsDouble();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arbol.put(new ObjectID(obID), new VOMovingViolations(obID,date, addID, loc, street, x, y));
					cont++;
					
				}
			}
			
			System.out.println("Datos Marzo: " + cont);
			cont = 0;
			
			JsonArray ja3 = (JsonArray) parser.parse(new FileReader(rutaAbril));
			for(int i = 0; ja3 != null && i<ja3.size(); i++){
				JsonObject actual = (JsonObject) ja3.get(i);
				if(actual.get("OBJECTID") != null){
					obID = actual.get("OBJECTID").getAsInt();			
				}
				if(actual.get("LOCATION") != null){
					loc = actual.get("LOCATION").getAsString();
				}
				if(actual.get("ADDRESS_ID") != null){
					if(actual.get("ADDRESS_ID").toString().equals("null")){
						address = "0";
						addID = Integer.parseInt(address);
					}
					else{
						addID = actual.get("ADDRESS_ID").getAsInt();
					}
				}
				if(actual.get("TICKETISSUEDATE") != null){
					date = actual.get("TICKETISSUEDATE").getAsString();
				}
				if(actual.get("STREETSEGID") != null){
					if(actual.get("STREETSEGID").toString().equals("null")){
						street = "0";
					}
					else{
						street = actual.get("STREETSEGID").getAsString();
					}
				}
				if(actual.get("XCOORD") != null){
					x= actual.get("XCOORD").getAsDouble();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsDouble();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arbol.put(new ObjectID(obID), new VOMovingViolations(obID, date, addID, loc, street, x, y));
					cont++;
					
				}		
			}
			
			System.out.println("Datos Abril: " + cont);
			cont = 0;
			
			JsonArray ja4 = (JsonArray) parser.parse(new FileReader(rutaMayo));
			for(int i = 0; ja4 != null && i<ja4.size(); i++){
				JsonObject actual = (JsonObject) ja4.get(i);
				if(actual.get("OBJECTID") != null){
					obID = actual.get("OBJECTID").getAsInt();			
				}
				if(actual.get("LOCATION") != null){
					loc = actual.get("LOCATION").getAsString();
				}
				if(actual.get("ADDRESS_ID") != null){
					if(actual.get("ADDRESS_ID").toString().equals("null")){
						address = "0";
						addID = Integer.parseInt(address);
					}
					else{
						addID = actual.get("ADDRESS_ID").getAsInt();
					}
				}
				if(actual.get("TICKETISSUEDATE") != null){
					date = actual.get("TICKETISSUEDATE").getAsString();
				}
				if(actual.get("STREETSEGID") != null){
					if(actual.get("STREETSEGID").toString().equals("null")){
						street = "0";
					}
					else{
						street = actual.get("STREETSEGID").getAsString();
					}
				}
				if(actual.get("XCOORD") != null){
					x= actual.get("XCOORD").getAsDouble();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsDouble();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arbol.put(new ObjectID(obID), new VOMovingViolations(obID,date, addID, loc, street, x, y));
					cont++;
					
				}
			}
			System.out.println("Datos Mayo: " + cont);
			cont = 0;
			JsonArray ja5 = (JsonArray) parser.parse(new FileReader(rutaJunio));
			for(int i = 0; ja5 != null && i<ja5.size(); i++){
				JsonObject actual = (JsonObject) ja5.get(i);
				if(actual.get("OBJECTID") != null){
					obID = actual.get("OBJECTID").getAsInt();			
				}
				if(actual.get("LOCATION") != null){
					loc = actual.get("LOCATION").getAsString();
				}
				if(actual.get("ADDRESS_ID") != null){
					if(actual.get("ADDRESS_ID").toString().equals("null")){
						address = "0";
						addID = Integer.parseInt(address);
					}
					else{
						addID = actual.get("ADDRESS_ID").getAsInt();
					}
				}
				if(actual.get("TICKETISSUEDATE") != null){
					date = actual.get("TICKETISSUEDATE").getAsString();
				}
				if(actual.get("STREETSEGID") != null){
					if(actual.get("STREETSEGID").toString().equals("null")){
						street = "0";
					}
					else{
						street = actual.get("STREETSEGID").getAsString();
					}
				}
				if(actual.get("XCOORD") != null){
					x= actual.get("XCOORD").getAsDouble();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsDouble();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arbol.put(new ObjectID(obID), new VOMovingViolations(obID, date, addID, loc, street, x, y));
					cont++;
					
				}
			}
			System.out.println("Datos Junio: " + cont);
		}
		catch(IOException e){
			e.getMessage();
		}
		return arbol.size();
	}

	public double prom(){
		double prom = 0.0;
		int sum = 0;
		ArregloDinamico<VOMovingViolations> arr = arbol.values(arbol.min(), arbol.max());
		for(int i = 0; i < arr.darTamano(); i++){
			VOMovingViolations actual = arr.darElem(i);
			ObjectID llaveParam = new ObjectID(actual.darID());
			sum+=arbol.darAlt(llaveParam);
		}
		prom= (double)sum/arr.darTamano();
		return prom;
	}
	

}
