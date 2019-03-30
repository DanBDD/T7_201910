package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import controller.Controller;
import model.data_structures.IQueue;
import model.vo.VOMovingViolations;

public class MovingViolationsManagerView 
{
	/**
	 * Constante con el nÃºmero maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;

	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 7----------------------");
		System.out.println("0. Cargar datos del cuatrimestre");
		System.out.println("1. Ver informaci�n de Object ID");
		System.out.println("2. Ver informaci�n de Object ID por rango");
		System.out.println("3. Salir");
		System.out.println("Digite el nï¿½mero de opciï¿½n para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
}
