/**
 * @author	Augusto Javier Ibañez Garcia
 * 	 email: aibanez122@alumno.uned.es
 * 	 DNI:   25.404.287M
 * @version 2.2 => 20 Octubre 2018  ///  1.0 => 7 Octubre 2018
 *
 */

package preda_scs;

public class InicialGestionParametros {

	static boolean esTrazaActiva		= false;
	static boolean esAyudaActiva		= false;
	static boolean hayFicheroSalida     = false;
	static boolean hayFicheroEntrada    = false;
	static String  ficheroEntrada		= null;
	static String  ficheroSalida		= null; 
	static String  parametroUltimo		= null;
	static String  parametroPenultimo	= null;
	static int [] vectorA;
	static int mNumeroElementos = 0;
	static int cSumaElementos = 0;

	static ManejoFicheros manejoFicheros = new ManejoFicheros();
	static AlgoritmoVueltaAtras vueltaAtras = new AlgoritmoVueltaAtras();

	public static void main(String[] args) {
		int cuentaParametros = 0;
		int numeroParametros = args.length;

		System.out.println("__________INICIO______________");
		asignarParametros(args, cuentaParametros, numeroParametros);
		if (esAyudaActiva)
			mostrarAyudaYTerminar(); 
		else
			gestionarLlamadaInicial();
	}

	private static void asignarParametros(String[] args, int cuentaParametros, int numeroParametros) {
		try{
			if (numeroParametros != 0) {
				asignaParametrosUltimoYPenultimo(args, numeroParametros);
				cuentaParametros = checkSiAyudaOTraza(args, cuentaParametros, numeroParametros);
			}
			asignaParametrosFicherosES(args, cuentaParametros, numeroParametros);
		}
		catch (Exception ex){
			esAyudaActiva = false;
			esTrazaActiva = false;
		}
	}

	private static void asignaParametrosUltimoYPenultimo(String[] args, int numeroParametros) {
		parametroUltimo = args[numeroParametros - 1]; 
		if (numeroParametros  > 1) { 
			parametroPenultimo = args[numeroParametros - 2]; 
		}
	}

	public static int checkSiAyudaOTraza(String[] args, int cuentaParametros, int numeroParametros) {
		for (int i=0; i<= numeroParametros - 1 ;i++) {
			if (args[i].equals("-h")) {
				cuentaParametros++;
				esAyudaActiva = true;
			}
			if (args[i].equals("-t")) {						
				cuentaParametros++;
				esTrazaActiva = true;
			}
		}
		return cuentaParametros;
	}

	private static void asignaParametrosFicherosES(String[] args, int cuentaParametros, int numeroParametros) {


		boolean paramUltimoNoEsTrazaNiEsAyuda = !(parametroUltimo.equals("-t")) && !(parametroUltimo.equals("-h"));
		if (numeroParametros == cuentaParametros + 2) {
			if (paramUltimoNoEsTrazaNiEsAyuda ) {
				hayFicheroSalida = true;
				ficheroSalida = parametroUltimo;
			}
			boolean paramPenultimoNoEsTrazaNiEsAyuda = !(parametroPenultimo.equals("-t")) && !(parametroPenultimo.equals("-h"));
			if (paramPenultimoNoEsTrazaNiEsAyuda ) {
				hayFicheroEntrada = true;
				ficheroEntrada = parametroPenultimo;
				System.out.println("Fichero de Entrada:\n" + ficheroEntrada);
			}
		}
		else {
			if (paramUltimoNoEsTrazaNiEsAyuda ) {
				hayFicheroEntrada = true;
				ficheroEntrada = parametroUltimo;
				System.out.println("Fichero de Entrada:\n" + ficheroEntrada);
			}				
		}
		ManejoFicheros.hayFicheroSalida = hayFicheroSalida;
		ManejoFicheros.ficheroSalida	= ficheroSalida;
	}



	private static void mostrarAyudaYTerminar() {
		System.out.println(
				"Programa que sirve para calcular los suconjuntos de una suma dada.\n\n"
						+ "SINTAXIS: \n"
						+ "suma [-t][-h][fichero_entrada][fichero_salida]\n"
						+ "  -t               Traza la selección de subconjuntos\n"
						+ "  -h               Muestra esta ayuda \n"
						+ "  fichero_entrada  Nombre del fichero de entrada \n"
						+ "  fichero_salida   Nombre del fichero de salida \n"
				);
		//		System.exit(-1);
	}

	private static void gestionarLlamadaInicial() {
		try {
			asignarValoresAlgoritmoVueltaAtras();
			AlgoritmoVueltaAtras.SubconjuntosSumaDada(vectorA, mNumeroElementos, cSumaElementos);
		}
		catch (ArrayIndexOutOfBoundsException noArgs) {
			System.err.println("Error en el número de argumentos");
			System.exit(-1);
		}
		catch (IllegalArgumentException iae) {
			System.err.println("Carácter no valido.");
			System.exit(-1);
		}
		catch (Exception e) {
			System.err.println("Error inesperado: " + e.getMessage());
			System.exit(-1);
		}
	}

	private static void asignarValoresAlgoritmoVueltaAtras() {
		if (hayFicheroEntrada) {
			ManejoFicheros.leeFicheroEntrada(ficheroEntrada, esTrazaActiva);
		}
		else {
			ManejoFicheros.leeEstandarEntrada(esTrazaActiva);
		}
		vectorA = manejoFicheros.getVectorA();
		mNumeroElementos = manejoFicheros.getMNumeroElementos();
		cSumaElementos = manejoFicheros.getCSumaElementos();
	}
}