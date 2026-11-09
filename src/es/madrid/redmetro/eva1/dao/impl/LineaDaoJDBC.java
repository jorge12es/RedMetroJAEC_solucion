/**
 * 
 */
package es.madrid.redmetro.eva1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.madrid.redmetro.eva1.dao.ILineaDAO;
import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.model.Color;
import es.madrid.redmetro.eva1.model.Linea;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class LineaDaoJDBC implements ILineaDAO {
	private final Connection conexion;

	public LineaDaoJDBC(Connection conexion) {
		this.conexion = conexion;
	}

	public Linea obtenerInformacionLinea(int idLinea) throws RedMetroException{
		Linea linea = null;

		String sentenciaSQL = """
				SELECT * 
				FROM T_COLOR color JOIN T_LINEA linea
					ON color.identificador = linea.id_color
				WHERE linea.identificador = ?
				""";
		// try con recursos "cerrables": PreparedStatement, ResultSet
		try (PreparedStatement pstm = conexion.prepareStatement(sentenciaSQL);) {
			pstm.setInt(1, idLinea);
//			System.out.println(pstm);
			ResultSet resultadoSentencia = pstm.executeQuery();

			if (resultadoSentencia.next()) {

				linea = new Linea();
				linea.setIdentificador(resultadoSentencia.getInt("linea.identificador"));
				linea.setNombreCorto(resultadoSentencia.getString("linea.nombre_corto"));
				linea.setNombreLargo(resultadoSentencia.getString("linea.nombre_largo"));
				linea.setKilometros(resultadoSentencia.getFloat("linea.kilometros"));

				Color color = new Color();
				color.setIdentificador(resultadoSentencia.getInt("color.identificador"));
				color.setCod_hexadecimal(resultadoSentencia.getString("color.cod_hexadecimal"));
				color.setNombre(resultadoSentencia.getString("color.nombre"));

				linea.setColor(color);
			}

		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la información de una línea de metro.", e);
		}

		return linea;
	}
}
