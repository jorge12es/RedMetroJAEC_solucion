/**
 * 
 */
package es.madrid.redmetro.eva1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.madrid.redmetro.eva1.dao.ICocheraDAO;
import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.model.Cochera;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class CocheraDaoJDBC implements ICocheraDAO {
	private final Connection conexion;

	public CocheraDaoJDBC(Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public boolean existeCochera(int idCochera) throws RedMetroException{
		boolean existeCochera = false;
		String sentenciaSQL = """
				SELECT EXISTS(
					SELECT 1
					FROM T_COCHERA
					WHERE identificador = ?
					) AS existe_cochera
				""";
		// try con recursos "cerrables": Connection, Statement, ResultSet
		try (PreparedStatement pstm = conexion.prepareStatement(sentenciaSQL);) {

			pstm.setInt(1, idCochera);
			System.out.println(pstm);

			ResultSet resultadoSentencia = pstm.executeQuery();
			if (resultadoSentencia.next()) {
				existeCochera = resultadoSentencia.getBoolean("existe_cochera");
			}
		} catch (SQLException e) {
			throw new RedMetroException("Error al validar si existe una cochera.", e);
		}

		return existeCochera;
	}

	public void insertarCochera(Cochera cochera) throws RedMetroException{

		String sentenciaSQL = "INSERT INTO T_COCHERA VALUES (?, ?, ? , ?)";

		// try con recursos "cerrables": PreparedStatement, ResultSet
		try (PreparedStatement pstm = conexion.prepareStatement(sentenciaSQL);) {

			pstm.setInt(1, cochera.getIdentificador());
			pstm.setString(2, cochera.getNombre());
			pstm.setString(3, cochera.getDireccion());
			pstm.setBoolean(4, cochera.isDeposito());

			System.out.println(pstm);

			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new RedMetroException("Error al crear una nueva cochera.", e);
		}

	}

	public void borrarCochera(int idCochera) throws RedMetroException{

		String sentenciaSQL = "DELETE FROM T_COCHERA WHERE identificador = ?";

		// try con recursos "cerrables": PreparedStatement, ResultSet
		try (PreparedStatement pstm = conexion.prepareStatement(sentenciaSQL);) {

			pstm.setInt(1, idCochera);
			System.out.println(pstm);

			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new RedMetroException("Error al borrar una cochera.", e);
		}

	}

	public void actualizarCochera(Cochera cochera) throws RedMetroException{

		String sentenciaSQL = """
				UPDATE T_COCHERA
				SET nombre = ?, direccion = ? , deposito = ?
				WHERE identificador = ?
				""";

		// try con recursos "cerrables": PreparedStatement, ResultSet
		try (PreparedStatement pstm = conexion.prepareStatement(sentenciaSQL);) {
			System.out.println(sentenciaSQL);

			pstm.setString(1, cochera.getNombre());
			pstm.setString(2, cochera.getDireccion());
			pstm.setBoolean(3, cochera.isDeposito());
			pstm.setInt(4, cochera.getIdentificador());

			System.out.println(pstm);

			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new RedMetroException("Error al actualizar una cochera.", e);
		}

	}

}
