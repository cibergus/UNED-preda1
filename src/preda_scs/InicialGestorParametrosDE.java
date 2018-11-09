/**
 * @author	Augusto Javier Ibañez Garcia
 * 	 email: aibanez122@alumno.uned.es
 * 	 DNI:   25.404.287M
 * @version 2.4 => 6 Noviembre 2018  ///  1.0 => 7 Octubre 2018
 *
 */

package preda_scs;

public class InicialGestorParametros {
	static boolean esTrazaActiva;
	static boolean esAyudaActiva;
	static boolean hayFicheroSalida     = false;
	static boolean hayFicheroEntrada    = false;
	static String  ficheroEntrada		= null;
	static String  ficheroSalida		= null; 
	static String  parametroUltimo;
	static String  parametroPenultimo;
	static int [] vectorA;
	static int mNumeroElementos = 0;
	static int cSumaElementos = 0;
	static ManejadorDeFichero manejoFicheros = new ManejadorDeFichero();
	static AlgoritmoVueltaAtras vueltaAtras = new AlgoritmoVueltaAtras();

	public static void main(String[] args) {
		asignarParametros(args);
		if (esAyudaActiva)
			GestorDeTexto.imprimeMensajeAyuda();
		else
			gestionarLlamadaInicial();		
	}

	static void asignarParametros(String[] args) {
		esTrazaActiva = false;
		esAyudaActiva = false;
		parametroUltimo = null;
		parametroPenultimo = null;
		int numeroParametrosSiAyudaOTraza = 0;
		int numeroRealParametros = args.length;
		int numeroParametrosSinAyudaNiTraza = 0;

		try{
			if (numeroRealParametros != 0) {
				asignaParametrosUltimoYPenultimo(args, numeroRealParametros);
				numeroParametrosSiAyudaOTraza = cuentaSiAyuda_y_o_Traza(args, numeroRealParametros);
			}
			numeroParametrosSinAyudaNiTraza = numeroRealParametros - numeroParametrosSiAyudaOTraza;
			asignaParametrosFicherosES(numeroParametrosSinAyudaNiTraza);
		}
		catch (Exception ex){
			esAyudaActiva = false;
			esTrazaActiva = false;
		}
	}

	private static void asignaParametrosUltimoYPenultimo(String[] args, int numeroRealParametros) {
		parametroUltimo = args[numeroRealParametros - 1]; 
		if (numeroRealParametros  > 1) { 
			parametroPenultimo = args[numeroRealParametros - 2]; 
		}
	}

	public static int cuentaSiAyuda_y_o_Traza(String[] args, int numeroRealParametros) {
		int cuentaParametros = 0;
		for (int i=0; i<= numeroRealParametros - 1 ;i++) {
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

	private static void asignaParametrosFicherosES(int numeroParametrosSinAyudaNiTraza) {
		
		boolean paramUltimoNoEsTrazaNiEsAyuda = !(parametroUltimo.equals("-t")) && !(parametroUltimo.equals("-h"));
		if (numeroParametrosSinAyudaNiTraza ==  2) {	
			if (paramUltimoNoEsTrazaNiEsAyuda ) {
				hayFicheroSalida = true;
				ficheroSalida = parametroUltimo;
				ManejadorDeFichero.inicializarFicheroSalida(ficheroSalida);
			}
			boolean paramPenultimoNoEsTrazaNiEsAyuda = !(parametroPenultimo.equals("-t")) && !(parametroPenultimo.equals("-h"));
			if (paramPenultimoNoEsTrazaNiEsAyuda ) {
				hayFicheroEntrada = true;
				ficheroEntrada = parametroPenultimo;
			}
		}
		else {
			if (paramUltimoNoEsTrazaNiEsAyuda ) {
				hayFicheroEntrada = true;
				ficheroEntrada = parametroUltimo;
			}				
		}
		ManejadorDeFichero.hayFicheroSalida = hayFicheroSalida;
		ManejadorDeFichero.ficheroSalida	= ficheroSalida;
	}

	private static void gestionarLlamadaInicial() {
		try {
			asignarValoresEntradaAlgoritmo();
			AlgoritmoVueltaAtras.inicioAlgoritmoVueltaAtras(vectorA, mNumeroElementos, cSumaElementos);
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(-1);
		}
	}

	private static void asignarValoresEntradaAlgoritmo() {
		if (hayFicheroEntrada) {
			ManejadorDeFichero.leeFicheroEntrada(ficheroEntrada, esTrazaActiva);
		}
		else {
			ManejadorDeFichero.leeEstandarEntrada(esTrazaActiva);
		}
		vectorA = manejoFicheros.getVectorA();		
		mNumeroElementos = manejoFicheros.getMNumeroElementos();
		cSumaElementos = manejoFicheros.getCSumaElementos();
	}
	
}