package es.madrid.redmetro.eva1.gestores;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * GestorConfiguracion: Clase que carga la configuración de la aplicación desde
 * un fichero de propiedades
 * 
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 8 oct 2026
 * 
 * 
 */

public class GestorConfiguracion {
	private static final String RUTA_FICHERO_CONFIGURACION = "app.properties";
	private static final Properties PROPIEDADES_CONFIGURACION = new Properties();

	// Constructor privado: de esta manera conseguimos que esta clase no se puede
	// instanciar.
	private GestorConfiguracion() {
	}

	// Este bloque se ejecuta una sola vez, cuando se carga la clase
	// GestorConfiguracion
	static {
		try (InputStream inputStream = GestorConfiguracion.class.getClassLoader().getResourceAsStream(RUTA_FICHERO_CONFIGURACION);)
		        {

		    if (inputStream == null) {
		        throw new IOException(
		            "No se encuentra "+ RUTA_FICHERO_CONFIGURACION + " en el classpath");
		    }
			PROPIEDADES_CONFIGURACION.load(inputStream);
			// System.out.println(PROPIEDADES_CONFIGURACION);
		} catch (IOException e) {
			throw new RuntimeException("No se ha podido cargar el fichero de configuración", e);
		}
	}

	public static String getConexionURL() {
		return PROPIEDADES_CONFIGURACION.getProperty("sgdb.conexion.url");
	}

	public static String getNombreBBDD() {
		return PROPIEDADES_CONFIGURACION.getProperty("bbdd.nombre");
	}
	
	public static String getUsuarioBBDD() {
		return PROPIEDADES_CONFIGURACION.getProperty("bbdd.usuario");
	}
	
	public static String getClaveBBDD() {
		return PROPIEDADES_CONFIGURACION.getProperty("bbdd.clave");
	}

	public static String getRutaFicheroCSVCochera() {
		return PROPIEDADES_CONFIGURACION.getProperty("cochera.fichero.csv");
	}
	public static String getSeparadorFicheroCSVCochera() {
		return PROPIEDADES_CONFIGURACION.getProperty("cochera.csv.separador");
	}

	public static String getRutaFicheroLecturaXMLTren() {
		return PROPIEDADES_CONFIGURACION.getProperty("tren.fichero.lectura.xml");
	}

	public static String getRutaFicheroEscrituraXMLTren() {
		return PROPIEDADES_CONFIGURACION.getProperty("tren.fichero.escritura.xml");
	}
}
