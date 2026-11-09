/**
 * 
 */
package es.madrid.redmetro.eva1.model;

import java.io.Serializable;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class Estacion implements Serializable {
	private static final long serialVersionUID = -4550346449635360036L;

	private int identificador;
	private String nombre;
	private String direccion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	@Override
	public String toString() {
		return "Estacion [identificador=" + identificador + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}

}
