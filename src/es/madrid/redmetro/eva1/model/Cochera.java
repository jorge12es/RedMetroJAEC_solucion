/**
 * 
 */
package es.madrid.redmetro.eva1.model;

import java.io.Serializable;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma)
 * Fecha: 04 nov 2026
 */
public class Cochera implements Serializable{
	private static final long serialVersionUID = 3155379009944780512L;

	  private int identificador;
	  private String nombre;
	  private String direccion;
	  private boolean deposito;


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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the deposito
	 */
	public boolean isDeposito() {
		return deposito;
	}
	/**
	 * @param deposito the deposito to set
	 */
	public void setDeposito(boolean deposito) {
		this.deposito = deposito;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	@Override
	public String toString() {
		return "Cochera [identificador=" + identificador + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", deposito=" + deposito + "]";
	}

}
