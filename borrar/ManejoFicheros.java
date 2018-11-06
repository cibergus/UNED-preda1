/**
 * @author	Augusto Javier Ibañez Garcia
 * 	 email: aibanez122@alumno.uned.es
 * 	 DNI:   25.404.287M
 * @version 2.2 => 19 Octubre 2018  ///  1.0 => 7 Octubre 2018
 *
 */

package preda_scs;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManejoFicheros {

	public static boolean hayFicheroSalida;
	public static String  ficheroSalida;

	private static int[] vectorA;
	private static int mNumeroElementos;
	private static int cSumaElementos;

	public ManejoFicheros() {
		// vectorA = 
		setMNumeroElementos (0);
		setCSumaElementos (0);
	}

	public int[] getVectorA() {
		return vectorA; 
	}

	//	public void  setVectorA(int[] vectorA) {
	//		this.vectorA = vectorA; 
	//	}

	public int getMNumeroElementos() {
		return mNumeroElementos; 
	}

	@SuppressWarnings("static-access")
	public void setMNumeroElementos(int m) {
		this.mNumeroElementos = m; 
	}

	public int   getCSumaElementos() {
		return cSumaElementos; 
	}

	@SuppressWarnings("static-access")
	public void  setCSumaElementos(int c) {
		this.cSumaElementos = c; 
	}

	public static void leeEstandarEntrada(boolean esTrazaActiva) {
		// TODO Auto-generated method stub
		// Implementar entrada de datos por consola: vectorA, m, C
		System.out.println("Dame los datos de entrada: ");
		// vectorA = {,,,}
		// cSumaElementos = c;
		// mNumeroElementos = y; 
	}
	
	public static void leeFicheroEntrada(String ficheroEntrada, boolean esTrazaActiva) {
		int contadorLineas=1;
		try {
			FileReader archivoLectura = new FileReader(ficheroEntrada);
			BufferedReader buferLectura = new BufferedReader(archivoLectura);
			String lineaLeida;
			while((lineaLeida = buferLectura.readLine()) != null)
			{
				if (!lineaLeida.equals(""))
				{
					if (contadorLineas == 1) { 
						vectorA = Arrays.stream(lineaLeida.split(" ")).mapToInt(Integer::parseInt).toArray();
						contadorLineas++;
						lineaLeida = buferLectura.readLine();
					}
					if (contadorLineas == 2) { 
						cSumaElementos = Integer.parseInt(buferLectura.readLine());
						contadorLineas++;
					}
					if (contadorLineas == 3) { 
						mNumeroElementos = Integer.parseInt(lineaLeida);
					}
				}
			}
			// Se verifica que no hayan elementos duplicados en la entrada de datos
			List<Integer> vectorAOriginal = IntStream.of(vectorA).boxed().collect(Collectors.toCollection(ArrayList::new));
			Set<Integer> vectorASinDuplicados = new HashSet<>(vectorAOriginal);
			if (vectorASinDuplicados.size() != vectorAOriginal.size()) {
				System.out.println("ERROR:   ELEMENTOS DE ENTRADA DUPLICADOS");
				System.exit( -1);
			}
			buferLectura.close();
			archivoLectura.close();
			if (esTrazaActiva) {
				Traza("Los datos iniciales son: ");
			}
			//La salida es toda la información requerida para solucionar el laberinto: número de filas, número de columnas, y el contenido de cada elemento
			//			Laberinto laberinto = new Laberinto(filas, columnas, elementos);
			//			return laberinto;

		}
		catch(Exception excepcion) {
			Traza ("Error leyendo fichero "+ ficheroEntrada + ": " + excepcion);
			//return null;
			System.out.println("Error de entrada de datos");}
	}

	public static void Traza(String mensaje) {
		{
			// Procedimiento usado para escribir en consola o fichero de salida los mensajes de Traza, 
			// si es que la opción Traza está activa
			SimpleDateFormat formatoFechaHora = new SimpleDateFormat("HH:mm:ss");
			Date horaMensaje = new Date();

			if (!hayFicheroSalida) {
				System.out.println(formatoFechaHora.format(horaMensaje) + " Mensaje: " + mensaje);
			}
			else {
				BufferedWriter ficheroTraza = null;
				FileWriter archivoEscritura = null;
				try 
				{	
					archivoEscritura = new FileWriter(ficheroSalida, true); 
					ficheroTraza = new BufferedWriter(archivoEscritura); 
					ficheroTraza.write(formatoFechaHora.format(horaMensaje) + " Mensaje - " + mensaje + "\r\n");
					ficheroTraza.flush();  
				} 
				catch (IOException excepcion) {
					System.out.println(excepcion.getMessage());
				}
			}
		}
	}

	public void EscribeFichero(String ficheroSalida, String resultado) {

		FileWriter ficheroEscritura = null;
		PrintWriter impresionEscritura = null;
		SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fecha = new Date();

		//Código sencillo de escritura en fichero del resultado de la solución del laberinto
		try {
			ficheroEscritura   = new FileWriter(ficheroSalida, true);
			impresionEscritura = new PrintWriter(ficheroEscritura);
			impresionEscritura.println("\r\n");
			impresionEscritura.println("La solución es: \r\n" + resultado + "\r\n");
			impresionEscritura.println(formatoFechaHora.format(fecha) + "\r\n");
			impresionEscritura.println("Fin\r\n" );
			impresionEscritura.println("\r\n");
		} 
		catch (Exception excepcion1) { excepcion1.printStackTrace(); } 
		finally {
			try { if (null != ficheroEscritura) ficheroEscritura.close(); }
			catch (Exception excepcion2) { excepcion2.printStackTrace();
			}
		}
	}
}