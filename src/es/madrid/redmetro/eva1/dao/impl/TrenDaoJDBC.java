/**
 * 
 */
package es.madrid.redmetro.eva1.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.madrid.redmetro.eva1.dao.ITrenDAO;
import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.model.Cochera;
import es.madrid.redmetro.eva1.model.Color;
import es.madrid.redmetro.eva1.model.Linea;
import es.madrid.redmetro.eva1.model.Tren;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class TrenDaoJDBC implements ITrenDAO {
	private final Connection conexion;

	public TrenDaoJDBC(Connection conexion) {
		this.conexion = conexion;
	}

	public void insertarTren(Tren tren) throws RedMetroException {
		String sentenciaSQL = "INSERT INTO T_TREN VALUES (?, ?, ? , ?, ?, ?)";

		// try con recursos "cerrables": PreparedStatement, ResultSet
		try (PreparedStatement pstm = conexion.prepareStatement(sentenciaSQL);) {

			pstm.setInt(1, tren.getIdentificador());
			pstm.setString(2, tren.getModelo());
			pstm.setString(3, tren.getEmpresaConstructora());
			// Obtener java.sql.Date de Un java.util.Date
			pstm.setDate(4, Date.valueOf(tren.getFechaIncorporacion()));
			pstm.setInt(5, tren.getCochera().getIdentificador());
			pstm.setInt(6, tren.getLinea().getIdentificador());

			System.out.println(pstm);

			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new RedMetroException("Error al crear un nuevo tren.", e);
		}
	}

	@Override
	public List<Tren> obtenerListaTrenes() throws RedMetroException {
		List<Tren> listaTrenes = null;

		String sentenciaSQL = """
				SELECT *
				FROM T_TREN tren join T_LINEA linea
					ON tren.id_linea =linea.identificador
					join T_COCHERA cochera
					on tren.id_cochera =cochera.identificador
					join T_COLOR color
					on linea.id_color =color.identificador
				""";

		// try con recursos "cerrables": PreparedStatement, ResultSet
		try (PreparedStatement pstm = conexion.prepareStatement(sentenciaSQL);) {

			System.out.println(pstm);

			ResultSet resultadoSentencia = pstm.executeQuery();
			while (resultadoSentencia.next()) {
				if (listaTrenes == null) {
					listaTrenes = new ArrayList<Tren>();
				}
				Tren tren = new Tren();
				tren.setIdentificador(resultadoSentencia.getInt("tren.identificador"));
				tren.setEmpresaConstructora(resultadoSentencia.getString("tren.empresa_constructora"));
				tren.setModelo(resultadoSentencia.getString("tren.modelo"));
				tren.setFechaIncorporacion(resultadoSentencia.getDate("tren.fecha_incorporacion").toLocalDate());

				// Información de la Línea
				Linea linea = new Linea();
				linea.setIdentificador(resultadoSentencia.getInt("linea.identificador"));
				linea.setKilometros(resultadoSentencia.getFloat("linea.kilometros"));
				linea.setNombreCorto(resultadoSentencia.getString("linea.nombre_corto"));
				linea.setNombreLargo(resultadoSentencia.getString("linea.nombre_largo"));

				// Información de Color
				Color color = new Color();
				color.setIdentificador(resultadoSentencia.getInt("color.identificador"));
				color.setNombre(resultadoSentencia.getString("color.nombre"));
				color.setCod_hexadecimal(resultadoSentencia.getString("color.cod_hexadecimal"));

				linea.setColor(color);

				tren.setLinea(linea);

				// Información de la Cochera
				Cochera cochera = new Cochera();
				cochera.setIdentificador(resultadoSentencia.getInt("cochera.identificador"));
				cochera.setDeposito(resultadoSentencia.getBoolean("cochera.deposito"));
				cochera.setDireccion(resultadoSentencia.getString("cochera.direccion"));
				cochera.setNombre(resultadoSentencia.getString("cochera.nombre"));
				tren.setCochera(cochera);

				listaTrenes.add(tren);

			}

		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la lista de trenes existentes.", e);
		}

		return listaTrenes;
	}
}
