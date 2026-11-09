/**
 * 
 */
package es.madrid.redmetro.eva1.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class Linea implements Serializable {
	private static final long serialVersionUID = 2546017016457301339L;

	private int identificador;
	private String nombreCorto;
	private String nombreLargo;
	private LocalDate fechaInauguracion;
	private float kilometros;
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the nombreCorto
	 */
	public String getNombreCorto() {
		return nombreCorto;
	}

	/**
	 * @param nombreCorto the nombreCorto to set
	 */
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	/**
	 * @return the nombreLargo
	 */
	public String getNombreLargo() {
		return nombreLargo;
	}

	/**
	 * @param nombreLargo the nombreLargo to set
	 */
	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	/**
	 * @return the kilometros
	 */
	public float getKilometros() {
		return kilometros;
	}

	/**
	 * @param kilometros the kilometros to set
	 */
	public void setKilometros(float kilometros) {
		this.kilometros = kilometros;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public LocalDate getFechaInauguracion() {
		return fechaInauguracion;
	}

	public void setFechaInauguracion(LocalDate fechaInauguracion) {
		this.fechaInauguracion = fechaInauguracion;
	}

	@Override
	public String toString() {
		return "Linea [identificador=" + identificador + ", nombreCorto=" + nombreCorto + ", nombreLargo=" + nombreLargo
				+ ", fechaInauguracion=" + fechaInauguracion + ", kilometros=" + kilometros + ", color=" + color + "]";
	}

}
