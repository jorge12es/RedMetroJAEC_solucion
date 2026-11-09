package es.madrid.redmetro.eva1.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

public class GestorConexionesJDBC {

	// Constructor privado: de esta manera conseguimos que esta clase no se puede
	// instanciar.
	private GestorConexionesJDBC() {
	}

	public static Connection obtenerConexion() throws SQLException {
		Connection conexion = null;

		String urlConexionCompleta = GestorConfiguracion.getConexionURL() + GestorConfiguracion.getNombreBBDD();
		String usuarioBBDD = GestorConfiguracion.getUsuarioBBDD();
		String claveBBDD = GestorConfiguracion.getClaveBBDD();
		conexion = DriverManager.getConnection(urlConexionCompleta, usuarioBBDD, claveBBDD);

		return conexion;
	}
}
