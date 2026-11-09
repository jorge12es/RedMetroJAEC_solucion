package es.madrid.redmetro.eva1.excepcion;


/**
 *
 * FormacionException: Clase abstracta que representa la excepción base del proyecto Formacion
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 15 oct 2026
 * 
 * 
 */

public class RedMetroException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public RedMetroException(String mensaje) {
        super(mensaje);
    }

	public RedMetroException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}

