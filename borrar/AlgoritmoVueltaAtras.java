/**
 * @author	Augusto Javier Ibañez Garcia
 * 	 email: aibanez122@alumno.uned.es
 * 	 DNI:   25.404.287M
 * @version 2.2 => 22 Octubre 2018  ///  1.0 => 7 Octubre 2018
 *
 */

package preda_scs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgoritmoVueltaAtras {
	static ManejoFicheros manejoFicheros = new ManejoFicheros();
	private static boolean haySolucion = false;

	public static void SubconjuntosSumaDada(int[] vectorA, int mNumeroElementos, int cSumaElementos) {
		int numeroElementosVector = vectorA.length;
		boolean [] vectorFormaBooleana = new boolean[vectorA.length];	
		int nElementosHastaAhora = 0; 
		int sumandosInicial = 0; /* m = numero de elementos del subconjunto hasta ahora */
		int sumaHastaAhora = 0; /* objetivo q sea C */

		imprimirInfoEnPantalla(vectorA, mNumeroElementos, cSumaElementos);		
		subconjuntosSumaDada (vectorA, mNumeroElementos, cSumaElementos, numeroElementosVector, nElementosHastaAhora, vectorFormaBooleana, sumandosInicial, sumaHastaAhora);	
		if (!getisHaySolucion()) { 
			System.out.println("NO HAY SOLUCIÓN"); 
			setHaySolucion(false);
			//			System.out.println(); 
			//			System.exit(0);
		}
		System.out.println("Fin programa."); 
	}

	private static void imprimirInfoEnPantalla(int[] vectorA, int mNumeroElementos, int cSumaElementos) {
		System.out.println(
				"Conjunto inicial = " 
						+ Arrays.toString(vectorA) 
						+ "\nNúmero elementos a sumar = " 
						+ mNumeroElementos
						+ "\nTotal que deben de sumar = " 
						+ cSumaElementos
						+ "\nSubconjuntos encontrados:");
	}

	private static void subconjuntosSumaDada(int[] vectorA, int mNumeroElementos, int cSumaElementos, int numeroElementosVector, int nElementosHastaAhora, boolean[] vectorFormaBooleana, int sumandosHastaAhora, int sumaHastaAhora) {
		if (sumandosHastaAhora == mNumeroElementos & sumaHastaAhora == cSumaElementos) {
			setHaySolucion(true);
			imprimirResultadoParcial(vectorA, vectorFormaBooleana, mNumeroElementos);
		}
		else {
			if (nElementosHastaAhora < numeroElementosVector & sumandosHastaAhora < mNumeroElementos) {
				vectorFormaBooleana[nElementosHastaAhora] = false;
				subconjuntosSumaDada (vectorA, mNumeroElementos, cSumaElementos, numeroElementosVector, nElementosHastaAhora + 1, vectorFormaBooleana, sumandosHastaAhora, sumaHastaAhora);
				int sumaDatos = sumaHastaAhora + vectorA[nElementosHastaAhora];
				if (sumaDatos <= cSumaElementos & sumandosHastaAhora < mNumeroElementos) {
					vectorFormaBooleana[nElementosHastaAhora] = true;
					sumaHastaAhora = sumaDatos;
					sumandosHastaAhora ++;
					subconjuntosSumaDada (vectorA, mNumeroElementos, cSumaElementos, numeroElementosVector, nElementosHastaAhora + 1, vectorFormaBooleana, sumandosHastaAhora, sumaHastaAhora);
				}
			}
		}
	}

	private static void imprimirResultadoParcial(int[] vectorA, boolean [] vectorFormaBooleana, int mNumeroElementos) {
		int posicionElemento = 0;
		int longitudVector = vectorFormaBooleana.length - 1;
		List<Integer> subconjSoluciones = new ArrayList<>();
		List<Integer>[] soluciones = new List[manejoFicheros.getMNumeroElementos()];

		int indiceSoluciones = 0;


		for (int indiceVector = 0; indiceVector <= longitudVector; indiceVector++) {
			if (vectorFormaBooleana[indiceVector]) {
				posicionElemento++;
				if (posicionElemento < mNumeroElementos + 1) { 
					subconjSoluciones.add(vectorA[indiceVector]);

				}


			}

		}
		soluciones[indiceSoluciones] = subconjSoluciones;
		indiceSoluciones++;

		for(int colum=0; colum < soluciones.length; colum++) { 
			if (soluciones[colum] != null) {
				System.out.println(soluciones[colum]);
			}
		}

	}

	public static boolean getisHaySolucion() {
		return haySolucion;
	}

	private static void setHaySolucion(boolean solucion) {
		AlgoritmoVueltaAtras.haySolucion = solucion;
	}
}