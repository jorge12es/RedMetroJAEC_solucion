package es.madrid.redmetro.eva1.service;

import java.sql.Connection;
import java.sql.SQLException;

import es.madrid.redmetro.eva1.dao.ILineaDAO;
import es.madrid.redmetro.eva1.dao.impl.LineaDaoJDBC;
import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.gestores.GestorConexionesJDBC;
import es.madrid.redmetro.eva1.model.Linea;

/**
 *
 * LineaService: Clase que realiza la lógica de negocio de una línea de metro.
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 4 nov 2026
 * 
 * 
 */

public class LineaService {
	public Linea obtenerLineaPorClave(int idLinea) throws RedMetroException {
		Linea linea = null;

		try (Connection conexion = GestorConexionesJDBC.obtenerConexion();) {
			ILineaDAO iLineaDAO = new LineaDaoJDBC(conexion);
			linea = iLineaDAO.obtenerInformacionLinea(idLinea);
		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la conexión", e);
		}
		return linea;
	}

}

