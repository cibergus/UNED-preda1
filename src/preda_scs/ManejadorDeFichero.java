/**
 * @author	cibergus
 * @version 2.4 => 6 Noviembre 2018  ///  1.0 => 7 Octubre 2018
 *
 */

package preda_scs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManejadorDeFichero {

	public static boolean hayFicheroSalida;
	public static String  ficheroSalida;

	private static int[] vectorA;
	private static int mNumeroElementos;
	private static int cSumaElementos;
	private static Scanner scanner;
	static String lineaLeida;

	public ManejadorDeFichero() {
		setMNumeroElementos (0);
		setCSumaElementos (0);
	}

	public int[] getVectorA() {
		return vectorA; 
	}

	public int getMNumeroElementos() {
		return mNumeroElementos; 
	}

	public int   getCSumaElementos() {
		return cSumaElementos; 
	}


	@SuppressWarnings("static-access")
	public void setMNumeroElementos(int m) {
		this.mNumeroElementos = m; 
	}

	@SuppressWarnings("static-access")
	public void  setCSumaElementos(int c) {
		this.cSumaElementos = c; 
	}

	public static void leeEstandarEntrada(boolean esTrazaActiva) {
		try {
			leeEstandarEntradaSimple();
		} 
		catch (Exception excepcion) {
			System.out.println("Error leyendo fichero " + ": " + excepcion);
		}
	}

	public static void leeEstandarEntradaSimple() {
		scanner = new Scanner(System.in);
		pedirPorConsolaVectorA();
		pedirPorConsolaMNumeroElementos();
		pedirPorConsolaCSumaElementos();
	}

	public static void pedirPorConsolaVectorA() {
		System.out.println("Dame los elementos a sumar y pulsa ENTER al acabar:");
		lineaLeida = scanner.nextLine();	
		crearVectorA(lineaLeida);
	}
	
	public static void crearVectorA(String lineaLeida) {
		vectorA = Arrays.stream(lineaLeida.split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	
	public static void pedirPorConsolaMNumeroElementos() {
		System.out.println("Dime cuantos elementos a sumar (parámetro m)");
		lineaLeida = scanner.nextLine();
		crearMNumeroElementos(lineaLeida);
	}

	public static void crearMNumeroElementos(String lineaLeida) {
		mNumeroElementos = Integer.parseInt(lineaLeida);
	}
	
	public static void pedirPorConsolaCSumaElementos() {
		System.out.println("Dime lo que tienen que sumar (parámetro C)");
		lineaLeida = scanner.nextLine();
		crearCSumaElementos(lineaLeida);
	}

	public static void crearCSumaElementos(String lineaLeida) {
		cSumaElementos = Integer.parseInt(lineaLeida);
	}
	
	public static void leeFicheroEntrada(String ficheroEntrada, boolean esTrazaActiva) {
		try {
			leeFicheroEntradaSimple(ficheroEntrada);
		}
		catch(Exception excepcion) {
			System.out.println("Error leyendo fichero "+ ficheroEntrada + ": " + excepcion);
		}
	}

	public static void leeFicheroEntradaSimple(String ficheroEntrada) throws FileNotFoundException, IOException {
		FileReader archivoLectura = new FileReader(ficheroEntrada);
		BufferedReader buferLectura = new BufferedReader(archivoLectura);
		String lineaLeida;

		int contadorLineas=1;
		while((lineaLeida = buferLectura.readLine()) != null){
			if (!lineaLeida.equals(""))
			{
				if (contadorLineas == 1) { 
					crearVectorA(lineaLeida);
					contadorLineas++;
					lineaLeida = buferLectura.readLine();
				}
				if (contadorLineas == 2) { 
					crearMNumeroElementos(lineaLeida);
					contadorLineas++;
				}
				if (contadorLineas == 3) { 
					crearCSumaElementos(lineaLeida);
				}
			}
		}
		terminarSiHayElementosDuplicados();
		buferLectura.close();
		archivoLectura.close();
	}

	public static void terminarSiHayElementosDuplicados() {
		List<Integer> vectorAOriginal = IntStream.of(vectorA).boxed().collect(Collectors.toCollection(ArrayList::new));
		Set<Integer> vectorASinDuplicados = new HashSet<>(vectorAOriginal);

		if (vectorASinDuplicados.size() != vectorAOriginal.size()) {
			System.out.println("ERROR:   ELEMENTOS DE ENTRADA DUPLICADOS");
			System.exit( -1);
		}
	}

	public static void trazaEnFichero(SimpleDateFormat formatoHora, Date horaAhora, String mensajeDeLaTraza) {
		BufferedWriter ficheroTraza = null;
		FileWriter archivoEscritura = null;

		try {	
			archivoEscritura = new FileWriter(ficheroSalida, true); 
			ficheroTraza = new BufferedWriter(archivoEscritura); 
			ficheroTraza.write(formatoHora.format(horaAhora) + " " + mensajeDeLaTraza + "\r\n");
			ficheroTraza.flush();  
		} 
		catch (IOException excepcion) {
			System.out.println(excepcion.getMessage());
		}
	}

	public static void inicializarFicheroSalida(String ficheroSalida) {
		File nuevoFichero = new File(ficheroSalida);

		nuevoFichero.delete();
		try {
			nuevoFichero.createNewFile();
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void EscribeFichero(String ficheroSalida, String resultado) {
		FileWriter ficheroEscritura = null;
		PrintWriter impresionEscritura = null;
		//		SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//		Date fecha = new Date();
		try {
			ficheroEscritura   = new FileWriter(ficheroSalida, true);
			impresionEscritura = new PrintWriter(ficheroEscritura);
			impresionEscritura.println(resultado);
		} 
		catch (Exception excepcion1) { 
			excepcion1.printStackTrace(); 
		} 
		finally {
			try { 
				if (null != ficheroEscritura) ficheroEscritura.close(); 
			}
			catch (Exception excepcion2) { 
				excepcion2.printStackTrace();
			}
		}
	}

	public static void Traza(String mensajeDeLaTraza) {
		SimpleDateFormat formatoHora = new SimpleDateFormat("mm:ss:S");
		Date horaAhora = new Date();
		if (hayFicheroSalida) {
			trazaEnFichero(formatoHora, horaAhora, mensajeDeLaTraza);
		}
		else {
			System.out.println(formatoHora.format(horaAhora) + " " + mensajeDeLaTraza);
		}
	}

}
