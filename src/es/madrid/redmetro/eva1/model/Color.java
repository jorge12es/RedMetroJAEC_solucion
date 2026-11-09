/**
 * 
 */
package es.madrid.redmetro.eva1.model;

import java.io.Serializable;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class Color implements Serializable {
	private static final long serialVersionUID = 3295865115889018033L;

	private int identificador;
	private String nombre;
	private String codigoHexadecimal;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the cod_hexadecimal
	 */
	public String getCodigoHexadecimal() {
		return codigoHexadecimal;
	}

	/**
	 * @param cod_hexadecimal the cod_hexadecimal to set
	 */
	public void setCod_hexadecimal(String cod_hexadecimal) {
		this.codigoHexadecimal = cod_hexadecimal;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public void setCodigoHexadecimal(String codigoHexadecimal) {
		this.codigoHexadecimal = codigoHexadecimal;
	}

	@Override
	public String toString() {
		return "Color [identificador=" + identificador + ", nombre=" + nombre + ", codigoHexadecimal="
				+ codigoHexadecimal + "]";
	}
}
