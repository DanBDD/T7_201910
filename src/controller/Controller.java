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
import model.data_structures.Comparaciones;
import model.util.Sort;
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


	private ArregloDinamico<VOMovingViolations> arreglo;

	public Controller() {
		view = new MovingViolationsManagerView();
		arreglo=new ArregloDinamico<VOMovingViolations>(160000);
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		int nMuestra = 0;
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
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
				//			case 1:
				//				view.printMessage("Dar tamaNo de la muestra: ");
				//				nMuestra = sc.nextInt();
				//				muestra = this.generarMuestra( nMuestra );
				//				int tam = muestra.length;
				//				view.printMessage("Muestra generada, tamano: " + tam);
				//				break;
				//			case 2: 
				//				if ( nMuestra > 0 && muestra != null)
				//				{    
				//					view.printDatosMuestra( nMuestra, muestra);
				//				}
				//				else
				//				{
				//					view.printMessage("Muestra invalida");
				//				}
				//				break;
				//			case 3:
				//				if ( nMuestra > 0 && muestra != null)
				//				{
				//					copia = this.obtenerCopia(muestra);
				//					startTime = System.currentTimeMillis();
				//					this.agregarColaPrioridad(copia);
				//					endTime = System.currentTimeMillis();
				//					duration = endTime - startTime;
				//					view.printMessage("Agregar terminado con Cola de Prioridad.");
				//					view.printMessage("Tiempo en agregar con Cola de Prioridad: " + duration + " milisegundos");
				//
				//				}
				//				else
				//				{
				//					view.printMessage("Muestra invalida");
				//				}
				//				break;
				//
				//			case 4:
				//				if ( nMuestra > 0 && muestra != null  )
				//				{
				//					copia = this.obtenerCopia(muestra);
				//					startTime = System.currentTimeMillis();
				//					this.agregarMaxHeap(copia);
				//					endTime = System.currentTimeMillis();
				//					duration = endTime - startTime;
				//					view.printMessage("Agregar terminado con HeapMAX.");
				//					view.printMessage("Tiempo en agregar con HeapMAX: " + duration + " milisegundos");
				//				}
				//				else
				//				{
				//					view.printMessage("Muestra invalida");
				//				}
				//				break;
				//			case 5:
				//				if ( nMuestra > 0 && muestra != null && cola.darNumElementos() > 0 )
				//				{					
				//					copia = this.obtenerCopia(muestra);
				//					startTime = System.currentTimeMillis();
				//					this.borrarMaxCola(copia);
				//					endTime = System.currentTimeMillis();
				//					duration = endTime - startTime;
				//					view.printMessage("Eliminar m�ximo terminado con Cola de Prioridad.");
				//					view.printMessage("Tiempo en eliminar m�ximo con Cola de Prioridad: " + duration + " milisegundos");
				//				}
				//				else
				//				{
				//					view.printMessage("Muestra invalida");
				//				}
				//				break;
				//
				//			case 6:
				//				if ( nMuestra > 0 && muestra != null && heap.darNumElementos() > 0)
				//				{
				//					copia = this.obtenerCopia(muestra);
				//					startTime = System.currentTimeMillis();
				//					this.borrarMaxHeap(copia);
				//					endTime = System.currentTimeMillis();
				//					duration = endTime - startTime;
				//					view.printMessage("Eliminar m�ximo terminado con HeapMAX.");
				//					view.printMessage("Tiempo en eliminar m�ximo con HeapMAX: " + duration + " milisegundos");
				//				}
				//				else
				//				{
				//					view.printMessage("Muestra invalida");
				//				}
				//				break;
				//			case 7:
				//				view.printMessage("Ingrese la fecha con hora inicial (Ej : 2018-01-02T20:02:22.000Z)");
				//				LocalDateTime fechaInicial = convertirFecha_Hora_LDT(sc.next());
				//
				//				view.printMessage("Ingrese la fecha con hora final (Ej : 2018-03-02T20:02:22.000Z)");
				//				LocalDateTime fechaFinal = convertirFecha_Hora_LDT(sc.next());
				//				view.printMessage("Ingrese la cantidad de vias que quiere ver: ");
				//				int num=sc.nextInt();
				//				startTime = System.currentTimeMillis();
				//				view.printElementos( num, this.crearMaxColaP(fechaInicial, fechaFinal));
				//				endTime = System.currentTimeMillis();
				//				duration = endTime - startTime;
				//				view.printMessage("Tiempo con Cola de Prioridad: " + duration + " milisegundos");
				//
				//				break;
				//			case 8:
				//				view.printMessage("Ingrese la fecha con hora inicial (Ej : 2018-01-02T20:02:22.000Z");
				//				LocalDateTime fechaInicial2 = convertirFecha_Hora_LDT(sc.next());
				//
				//				view.printMessage("Ingrese la fecha con hora final (Ej : 2018-03-02T20:02:22.000Z");
				//				LocalDateTime fechaFinal2 = convertirFecha_Hora_LDT(sc.next());
				//				view.printMessage("Ingrese la cantidad de vias que quiere ver: ");
				//				int num2=sc.nextInt();
				//				startTime = System.currentTimeMillis();
				//				view.printElementos2( num2, this.crearMaxHeapCP(fechaInicial2, fechaFinal2));
				//				endTime = System.currentTimeMillis();
				//				duration = endTime - startTime;
				//				view.printMessage("Tiempo con Heap: " + duration + " milisegundos");
				//				break;
			case 9:	
				fin=true;
				sc.close();
				break;
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
		int x=0;
		int y=0;
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
					x= actual.get("XCOORD").getAsInt();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsInt();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arreglo.agregar(new VOMovingViolations(obID, date, addID, loc, street, x,y));

				}
			}
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
					x= actual.get("XCOORD").getAsInt();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsInt();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arreglo.agregar(new VOMovingViolations(obID, date, addID, loc, street, x,y));


				}
			}
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
					x= actual.get("XCOORD").getAsInt();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsInt();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arreglo.agregar(new VOMovingViolations(obID, date, addID, loc, street, x,y));
				}
			}
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
					x= actual.get("XCOORD").getAsInt();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsInt();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arreglo.agregar(new VOMovingViolations(obID, date, addID, loc, street, x,y));


				}
			}
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
					x= actual.get("XCOORD").getAsInt();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsInt();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arreglo.agregar(new VOMovingViolations(obID, date, addID, loc, street, x,y));


				}
			}
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
					x= actual.get("XCOORD").getAsInt();	
				}
				if(actual.get("YCOORD") != null){
					y = actual.get("YCOORD").getAsInt();
				}

				if(obID != 0 && loc != null && addID != -1 && date !=null && street != null && x!=-1 && y!=-1){
					arreglo.agregar(new VOMovingViolations(obID, date, addID, loc, street, x,y));

				}
			}
		}
		catch(IOException e){
			e.getMessage();
		}
		return arreglo.darTamano();
	}

	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}


	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
	}


	public LocalTime darHora(String fecha){
		return convertirFecha_Hora_LDT(fecha).toLocalTime();
	}
	public Comparable<VOMovingViolations>[] pasarDinamicoArreglo()
	{
		Comparable<VOMovingViolations>[] temp = new Comparable[arreglo.darTamano()];	

		int pos=0;
		while(pos<arreglo.darTamano())
		{
			temp[pos] = arreglo.darElem(pos);
			pos++;
		}
		return temp;
	}

}
