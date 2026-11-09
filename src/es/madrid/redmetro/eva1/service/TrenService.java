package es.madrid.redmetro.eva1.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.madrid.redmetro.eva1.dao.ITrenDAO;
import es.madrid.redmetro.eva1.dao.impl.TrenDaoJDBC;
import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.gestores.GestorConexionesJDBC;
import es.madrid.redmetro.eva1.model.Tren;

/**
 *
 * TrenService: Clase que realiza la lógica de negocio de un tren.
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 4 nov 2026
 * 
 * 
 */

public class TrenService {
	public List<Tren> obtenerListaTrenes() throws RedMetroException {
		List<Tren> listaTrenes = null;

		try (Connection conexion = GestorConexionesJDBC.obtenerConexion();) {
			ITrenDAO iTrenDAO = new TrenDaoJDBC(conexion);
			listaTrenes = iTrenDAO.obtenerListaTrenes();
		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la conexión", e);
		}
		return listaTrenes;
	}

	public void insertarTren(Tren tren) throws RedMetroException {

		try (Connection conexion = GestorConexionesJDBC.obtenerConexion();) {
			ITrenDAO iTrenDAO = new TrenDaoJDBC(conexion);
			iTrenDAO.insertarTren(tren);
		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la conexión", e);
		}
	}

}

