/**
 * @author	cibergus
 * @version 2.3 => 4 Noviembre 2018  ///  1.0 => 7 Octubre 2018
 *
 */

package preda_scs;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoVueltaAtras {
	static ManejadorDeFichero manejoFicheros = new ManejadorDeFichero();
	private static boolean haySolucion = false;

	public static void inicioAlgoritmoVueltaAtras(int[] vectorA, int mNumeroElementos, int cSumaElementos) {
		int numeroElementosVector = vectorA.length;
		boolean [] vectorFormaBooleana = new boolean[vectorA.length];	
		int nElementosHastaAhora = 0; 
		int sumandosInicial = 0; 
		int sumaHastaAhora = 0;

		setHaySolucion(false);
		String infoInicial = GestorDeTexto.imprimeInfoInicial(vectorA, mNumeroElementos, cSumaElementos);
		GestorDeTexto.enviarTextoA_Pantalla_O_Fichero(infoInicial);
	
		algoritmoVueltaAtrasSubconjuntosSumaDada (vectorA, mNumeroElementos, cSumaElementos, numeroElementosVector, nElementosHastaAhora, vectorFormaBooleana, sumandosInicial, sumaHastaAhora);	
		
		if (!haySolucion()) { 
			GestorDeTexto.enviarTextoA_Pantalla_O_Fichero("NO HAY SOLUCIÓN");	 
		}
		GestorDeTexto.enviarTextoA_Pantalla_O_Fichero("Fin del programa.");
	}
	
	private static void algoritmoVueltaAtrasSubconjuntosSumaDada(int[] vectorA, int mNumeroElementos, int cSumaElementos, int numeroElementosVector, int nElementosHastaAhora, boolean[] vectorFormaBooleana, int sumandosHastaAhora, int sumaHastaAhora) {
		siTrazaActivaImprime("Algoritmo" + " " + nElementosHastaAhora + " " + sumandosHastaAhora + " " + sumaHastaAhora);
		boolean solucionCompleta = sumandosHastaAhora == mNumeroElementos & sumaHastaAhora == cSumaElementos;
		if (solucionCompleta) {
			setHaySolucion(true);
			siTrazaActivaImprime("Solucion completa encontrada:");
			procesarSolucion(vectorA, vectorFormaBooleana, mNumeroElementos);
		}
		else {
			boolean completable = nElementosHastaAhora < numeroElementosVector & sumandosHastaAhora < mNumeroElementos;
			if (completable) {
				vectorFormaBooleana[nElementosHastaAhora] = false;
				siTrazaActivaImprime("Vuelta atrás");

				algoritmoVueltaAtrasSubconjuntosSumaDada (vectorA, mNumeroElementos, cSumaElementos, numeroElementosVector, nElementosHastaAhora + 1, vectorFormaBooleana, sumandosHastaAhora, sumaHastaAhora);

				int sumaDatos = sumaHastaAhora + vectorA[nElementosHastaAhora];
				boolean opcionesPendientes = sumaDatos <= cSumaElementos & sumandosHastaAhora < mNumeroElementos;
				if (opcionesPendientes) {
					vectorFormaBooleana[nElementosHastaAhora] = true;
					sumaHastaAhora = sumaDatos;
					sumandosHastaAhora ++;
					
					algoritmoVueltaAtrasSubconjuntosSumaDada (vectorA, mNumeroElementos, cSumaElementos, numeroElementosVector, nElementosHastaAhora + 1, vectorFormaBooleana, sumandosHastaAhora, sumaHastaAhora);
				}
			}
		}
	}

	public static void siTrazaActivaImprime(String textoAImprimir) {
		if (InicialGestorParametrosDE.esTrazaActiva)
			ManejadorDeFichero.Traza(textoAImprimir);
	}

	public static void procesarSolucion(int[] vectorA, boolean [] vectorFormaBooleana, int mNumeroElementos) {
		int posicionElemento = 0;
		int longitudVector = vectorFormaBooleana.length - 1;
		List<Integer> subconjSoluciones = new ArrayList<>();

		for (int indiceVector = 0; indiceVector <= longitudVector; indiceVector++) {
			if (vectorFormaBooleana[indiceVector]) {
				posicionElemento++;
				if (posicionElemento < mNumeroElementos + 1) { 
					subconjSoluciones.add(vectorA[indiceVector]);
				}
			}	 
		}
		String subconjuntoEncontrado = GestorDeTexto.convertirArrayEnterosEnListaEnteros(subconjSoluciones);
		GestorDeTexto.enviarTextoA_Pantalla_O_Fichero(subconjuntoEncontrado);
	}

	public static boolean haySolucion() {
		return haySolucion;
	}

	private static void setHaySolucion(boolean solucion) {
		AlgoritmoVueltaAtras.haySolucion = solucion;
	}
}
