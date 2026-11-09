/**
 * 
 */
package es.madrid.redmetro.eva1;

import java.util.Scanner;

import es.madrid.redmetro.eva1.ficheros.GestorFicheroCSV;
import es.madrid.redmetro.eva1.ficheros.GestorFicherosXML;
import es.madrid.redmetro.eva1.model.Linea;
import es.madrid.redmetro.eva1.service.LineaService;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 28 nov 2024
 */
public class RedMetroApp {
	public static void main(String[] args) {
		RedMetroApp app = new RedMetroApp();

		// Tratamiento de lógica de negocio
		String operacion = app.obtenerOperacionProcesar();

		app.ejecutarFuncionalidadesRedMetro(operacion);
	}

	private String obtenerOperacionProcesar() {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Menú:");
		System.out.println("  A: Consultar Información Línea-Color");
		System.out.println("  B: Procesar fichero Cocheras.csv");
		System.out.println("  C: Procesar Fichero XML de Trenes-Estaciones-Accesos");
		System.out.println("  D: Cargar información Trenes-Linea-Color en fichero XML");
		System.out.println("----------------------------------------------------------");
		System.out.println("Introduce la operación a realizar:");
		// Operación recogida
		String operacion = entrada.nextLine().trim();

		while (!("ABCD".contains(operacion))) {
			System.out.println("Has seleccionado un operación errónea (ha de ser un número entre A y D)");
			System.out.print("Introduce la operación a realizar:");

			// Operación recogida
			operacion = entrada.nextLine();
		}
		System.out.println("Operación a realizar: " + operacion);
		// Cerrar la entrada del Scanner
		entrada.close();

		return operacion;
	}

	public void ejecutarFuncionalidadesRedMetro(String operacion) {
		try {
			switch (operacion) {
			case "A": { // Consulta Linea-Color
				System.out.println("Ejecutar consultarLineaColor");
				LineaService lineaService= new LineaService();
				Linea linea = lineaService.obtenerLineaPorClave(13);
				
				String textoLinea = "La Línea %s (%d) tiene como color %s (%s)";
				textoLinea = textoLinea.formatted(linea.getNombreLargo(), linea.getIdentificador(),
						linea.getColor().getNombre(), linea.getColor().getCodigoHexadecimal());
				System.out.println(textoLinea);
				break;
			}
			case "B": { // Procesar Fichero CSV Cocheras
				System.out.println("Ejecutar procesarFicheroCocheraCSV");
				GestorFicheroCSV gestorFicheros = new GestorFicheroCSV();
				gestorFicheros.procesarFicheroCocheraCSV();
				break;
			}
			case "C": { // Procesar Fichero XML Trenes
				System.out.println("Ejecutar procesarFicheroTrenXML");
				GestorFicherosXML gestorFicheros = new GestorFicherosXML();

				gestorFicheros.procesarListaTrenesDeDOM();
				break;
			}
			case "D": { // Crear fichero XML a partir de información de Trenes-Línea-Color
				System.out.println("Ejecutar crearFicheroXMLInforTrenes");
				GestorFicherosXML gestorFicheros = new GestorFicherosXML();

				gestorFicheros.escribirFicheroXMLDOMDeBBDD();
				break;
			}
			default: {
				break;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
