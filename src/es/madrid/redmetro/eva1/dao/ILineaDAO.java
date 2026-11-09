/**
 * 
 */
package es.madrid.redmetro.eva1.dao;

import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.model.Linea;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma)
 * Fecha: 04 nov 2026
 */
public interface ILineaDAO {
	public Linea obtenerInformacionLinea(int idLinea) throws RedMetroException;
}
