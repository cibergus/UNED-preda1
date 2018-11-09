package preda_scs;

import        org.junit.Test;
//import        org.junit.Ignore;
import        org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZTestGusSubconjSumaDada {

	// /PrEDA-18-19-PED1-SubconjuntosSumaDada/archivosTests/ficheroEntradaPED1Test01.txt
	//	

	@Before
	public final void Inicio() {
		System.out.print("\n----> TEST: ");
	}
	
	@Test
	public final void testArgs08_t_AlFinal() {
		System.out.println("testArgs08_t_AlFinal - NO_FUNCIONA_COMO_QUISIERA");
		String[] args = {"fichEntrada.txt", "fichSalida.txt", "-t"};
		InicialGestorParametrosDE.asignarParametros(args);
		assertFalse(InicialGestorParametrosDE.esAyudaActiva);
		assertTrue(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals("fichSalida.txt", InicialGestorParametrosDE.parametroPenultimo);
		assertEquals("-t", InicialGestorParametrosDE.parametroUltimo);
	}
	
	@Test
	public final void testArgs07_VACIO() {
		System.out.println("testArgs07_VACIO");
		String[] args = {};
		InicialGestorParametrosDE.asignarParametros(args);
		assertFalse(InicialGestorParametrosDE.esAyudaActiva);
		assertFalse(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals(null, InicialGestorParametrosDE.parametroUltimo);
		assertEquals(null, InicialGestorParametrosDE.parametroPenultimo);
	}
	
	@Test
	public final void testArgs06_entradaTxt_salidaTxt() {
		System.out.println("testArgs06_entradaTxt_salidaTxt");
		String[] args = {"fichEntrada.txt", "fichSalida.txt"};
		InicialGestorParametrosDE.asignarParametros(args);
		assertFalse(InicialGestorParametrosDE.esAyudaActiva);
		assertFalse(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals("fichSalida.txt", InicialGestorParametrosDE.parametroUltimo);
		assertEquals("fichEntrada.txt", InicialGestorParametrosDE.parametroPenultimo);
	}
	
	@Test
	public final void testArgs05_entradaTxt() {
		System.out.println("testArgs05_entradaTxt");
		String[] args = {"fichEntrada.txt"};
		InicialGestorParametrosDE.asignarParametros(args);
		assertFalse(InicialGestorParametrosDE.esAyudaActiva);
		assertFalse(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals("fichEntrada.txt", InicialGestorParametrosDE.parametroUltimo);
		assertEquals(null, InicialGestorParametrosDE.parametroPenultimo);
	}

	@Test
	public final void testArgs04_niH_niT() {
		System.out.println("testArgs04_niH_niT");
		String[] args = {"otraCosa"};
		InicialGestorParametrosDE.asignarParametros(args);
		assertFalse(InicialGestorParametrosDE.esAyudaActiva);
		assertFalse(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals("otraCosa", InicialGestorParametrosDE.parametroUltimo);
		assertEquals(null, InicialGestorParametrosDE.parametroPenultimo);
	}

	@Test
	public final void testArgs03_h_t() {
		System.out.println("testArgs03_h_t");
		String[] args = {"-h", "-t"};
		InicialGestorParametrosDE.asignarParametros(args);
		assertTrue(InicialGestorParametrosDE.esAyudaActiva);
		assertTrue(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals("-t", InicialGestorParametrosDE.parametroUltimo);
		assertEquals("-h", InicialGestorParametrosDE.parametroPenultimo);
	}

	@Test
	public final void testArgs02_t() {
		System.out.println("testArgs02_t");
		String[] args = {"-t"};
		InicialGestorParametrosDE.asignarParametros(args);
		assertFalse(InicialGestorParametrosDE.esAyudaActiva);
		assertTrue(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals("-t", InicialGestorParametrosDE.parametroUltimo);
		assertEquals(null, InicialGestorParametrosDE.parametroPenultimo);
	}

	@Test
	public final void testArgs01_h() {
		System.out.println("testArgs01_h");
		String[] args = {"-h"};
		InicialGestorParametrosDE.asignarParametros(args);
		assertTrue(InicialGestorParametrosDE.esAyudaActiva);
		assertFalse(InicialGestorParametrosDE.esTrazaActiva);
		assertEquals("-h", InicialGestorParametrosDE.parametroUltimo);
		assertEquals(null, InicialGestorParametrosDE.parametroPenultimo);
	}
	
	@Test
	public final void test002_main_t() {
		System.out.println("test002_main_t");
		String[] args = {"-t", "archivosTests/ficheroEntradaPED1Test01.txt"};
		InicialGestorParametrosDE.main(args);
		assertTrue(AlgoritmoVueltaAtras.haySolucion());
	}
	
	@Test
	// suma -h
	public final void test001_main_h() {
		System.out.println("test001_main_h");
		String[] args = {"-h"};
		InicialGestorParametrosDE.main(args);
		assertTrue(InicialGestorParametrosDE.esAyudaActiva);
	}

	@Test
	public final void test10_NO_haySolucion_test10_txt() {
		System.out.println("haySolucion_test10_txt");
		String[] args = {"archivosTests/ficheroEntradaPED1Test10.txt"};
		InicialGestorParametrosDE.main(args);
		assertFalse(AlgoritmoVueltaAtras.haySolucion());
	}

//	@Ignore
	@Test
	public final void test07_haySolucion_test07_txt() {
		System.out.println("");
		String[] args = {"archivosTests/ficheroEntradaPED1Test07.txt"};
		InicialGestorParametrosDE.main(args);
		boolean resultado = AlgoritmoVueltaAtras.haySolucion();
		assertTrue(resultado);
	}

	@Test
	public final void test06_haySolucion_test06_txt() {
		System.out.println("");
		String[] args = {"archivosTests/ficheroEntradaPED1Test06.txt"};
		InicialGestorParametrosDE.main(args);
		boolean resultado = AlgoritmoVueltaAtras.haySolucion();
		assertTrue(resultado);
	}

	@Test
	public final void test05_haySolucion_test05_txt() {
		System.out.println("");
		String[] args = {"archivosTests/ficheroEntradaPED1Test05.txt"};
		InicialGestorParametrosDE.main(args);
		assertTrue(AlgoritmoVueltaAtras.haySolucion());
	}

	@Test
	public final void test04_haySolucion_test04_txt() {
		System.out.println("");
		String[] args = {"archivosTests/ficheroEntradaPED1Test04.txt"};
		InicialGestorParametrosDE.main(args);
		assertTrue(AlgoritmoVueltaAtras.haySolucion());
	}

	@Test
	public final void test03_haySolucion_test03_txt() {
		System.out.println("");
		String[] args = {"archivosTests/ficheroEntradaPED1Test03.txt"};
		InicialGestorParametrosDE.main(args);
		assertTrue(AlgoritmoVueltaAtras.haySolucion());
	}

	@Test
	public final void test02_haySolucion_test02_txt() {
		System.out.println("");
		String[] args = {"archivosTests/ficheroEntradaPED1Test02.txt"};
		InicialGestorParametrosDE.main(args);
		assertTrue(AlgoritmoVueltaAtras.haySolucion());
	}

	@Test
	public final void test01_haySolucion_test01_txt() {
		System.out.println("test01_haySolucion_test01_txt");
		String[] args = {"archivosTests/ficheroEntradaPED1Test01.txt"};
		InicialGestorParametrosDE.main(args);
		assertTrue(AlgoritmoVueltaAtras.haySolucion());
	}

	@Test
	// suma test.txt
	public final void test00_haySolucion_test_txt() {
		System.out.println("test00_haySolucion_test_txt");
		String[] args = {"test.txt"};
		InicialGestorParametrosDE.main(args);
		assertTrue(AlgoritmoVueltaAtras.haySolucion());
	}


}