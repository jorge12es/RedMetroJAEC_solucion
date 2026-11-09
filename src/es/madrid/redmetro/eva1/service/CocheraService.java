package es.madrid.redmetro.eva1.service;

import java.sql.Connection;
import java.sql.SQLException;

import es.madrid.redmetro.eva1.dao.ICocheraDAO;
import es.madrid.redmetro.eva1.dao.impl.CocheraDaoJDBC;
import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.gestores.GestorConexionesJDBC;
import es.madrid.redmetro.eva1.model.Cochera;

/**
 *
 * CocheraService: Clase que realiza la lógica de negocio de una cochera.
 * 
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 4 nov 2026
 * 
 * 
 */

public class CocheraService {
	public boolean existeCocheraPorClave(int idCochera) throws RedMetroException {
		boolean existeFP = false;

		try (Connection conexion = GestorConexionesJDBC.obtenerConexion();) {
			ICocheraDAO iCocheraDAO = new CocheraDaoJDBC(conexion);
			existeFP = iCocheraDAO.existeCochera(idCochera);
		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la conexión", e);
		}
		return existeFP;
	}

	public void insertarCochera(Cochera cochera) throws RedMetroException {

		try (Connection conexion = GestorConexionesJDBC.obtenerConexion();) {
			ICocheraDAO iCocheraDAO = new CocheraDaoJDBC(conexion);
			iCocheraDAO.insertarCochera(cochera);
		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la conexión", e);
		}
	}

	public void actualizarCochera(Cochera cochera) throws RedMetroException {

		try (Connection conexion = GestorConexionesJDBC.obtenerConexion();) {
			ICocheraDAO iCocheraDAO = new CocheraDaoJDBC(conexion);
			iCocheraDAO.actualizarCochera(cochera);
		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la conexión", e);
		}
	}

	public void borrarCochera(int idCochera) throws RedMetroException {

		try (Connection conexion = GestorConexionesJDBC.obtenerConexion();) {
			ICocheraDAO iCocheraDAO = new CocheraDaoJDBC(conexion);
			iCocheraDAO.borrarCochera(idCochera);
		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la conexión", e);
		}

	}
}
