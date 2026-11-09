/**
 * 
 */
package es.madrid.redmetro.eva1.dao;

import java.util.List;

import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.model.Tren;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma)
 * Fecha: 04 nov 2026
 */
public interface ITrenDAO {
	public void insertarTren(Tren tren) throws RedMetroException;

	public List<Tren> obtenerListaTrenes() throws RedMetroException;

}
