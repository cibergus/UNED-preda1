/**
 * @author	Augusto Javier Ibañez Garcia
 * 	 email: aibanez122@alumno.uned.es
 * 	 DNI:   25.404.287M
 * @version 1.3 => L5 Noviembre 2018  ///  1.0 => 3 Noviembre 2018
 *
 */

package preda_scs;

import java.util.Arrays;
import java.util.List;

public class GestorDeTexto {

	public static String imprimeInfoInicial(int[] vectorA, int mNumeroElementos, int cSumaElementos) {
		return ("Conjunto inicial = " 
				+ Arrays.toString(vectorA) 
				+ "\nNúmero elementos a sumar = " 
				+ mNumeroElementos
				+ "\nTotal que deben de sumar = " 
				+ cSumaElementos
				+ "\nSubconjuntos encontrados:");
	}

	public static String convertirArrayEnterosEnListaEnteros(List<Integer> subconjSoluciones) {
		String textoEnLista = "";
		for (Integer linea : subconjSoluciones)
			textoEnLista += (linea.toString() + " ");
		return textoEnLista;
	}

	public static void imprimeMensajeAyuda() {
		System.out.println(
				"Programa que sirve para calcular los suconjuntos de una suma dada.\n\n"
						+ "SINTAXIS: \n"
						+ "suma [-t][-h][fichero_entrada][fichero_salida]\n"
						+ "  -t               Traza la selección de subconjuntos\n"
						+ "  -h               Muestra esta ayuda \n"
						+ "  fichero_entrada  Nombre del fichero de entrada \n"
						+ "  fichero_salida   Nombre del fichero de salida");
	}

	public static void enviarTextoA_Pantalla_O_Fichero(String texto) {
		if (InicialGestorParametros.hayFicheroSalida) {
			ManejadorDeFichero.EscribeFichero(InicialGestorParametros.ficheroSalida, texto);
		}
		else {
			System.out.println(texto);
		}
	}

//	public static void Traza(String mensaje) {
//		{
//			SimpleDateFormat formatoFechaHora = new SimpleDateFormat("mm:ss:S");
//			Date horaMensaje = new Date();
//			if (ManejadorDeFichero.hayFicheroSalida) {
//				ManejadorDeFichero.trazaEnFichero(mensaje, formatoFechaHora, horaMensaje);
//			}
//			else {
//				System.out.println(formatoFechaHora.format(horaMensaje) + " " + mensaje);
//			}
//		}
//	}

}