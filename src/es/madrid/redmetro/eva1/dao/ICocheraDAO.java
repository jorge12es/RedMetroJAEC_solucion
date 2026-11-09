/**
 * 
 */
package es.madrid.redmetro.eva1.dao;

import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.model.Cochera;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma)
 * Fecha: 04 nov 2026
 */
public interface ICocheraDAO {
	public boolean existeCochera(int idCochera) throws RedMetroException;

	public void insertarCochera(Cochera cochera) throws RedMetroException;
	
	public void borrarCochera(int idCochera) throws RedMetroException;
	
	public void actualizarCochera(Cochera cochera) throws RedMetroException;
}
