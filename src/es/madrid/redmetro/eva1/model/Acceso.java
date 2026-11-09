/**
 * 
 */
package es.madrid.redmetro.eva1.model;

import java.io.Serializable;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class Acceso implements Serializable {
	private static final long serialVersionUID = 1;

	private int identificador;
	private String nombre;
	private boolean accesible;
	private Estacion estacion;


	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isAccesible() {
		return accesible;
	}

	public void setAccesible(boolean accesible) {
		this.accesible = accesible;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	@Override
	public String toString() {
		return "Acceso [identificador=" + identificador + ", nombre=" + nombre + ", accesible=" + accesible
				+ ", estacion=" + estacion + "]";
	}

}
