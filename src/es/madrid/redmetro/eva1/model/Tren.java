/**
 * 
 */
package es.madrid.redmetro.eva1.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 04 nov 2026
 */
public class Tren implements Serializable {
	private static final long serialVersionUID = 3033036838329546838L;

	private int identificador;
	private String modelo;
	private String empresaConstructora;
	private LocalDate fechaIncorporacion;
	private Cochera cochera;
	private Linea linea;

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the empresaConstructora
	 */
	public String getEmpresaConstructora() {
		return empresaConstructora;
	}

	/**
	 * @param empresaConstructora the empresaConstructora to set
	 */
	public void setEmpresaConstructora(String empresaConstructora) {
		this.empresaConstructora = empresaConstructora;
	}

	/**
	 * @return the cochera
	 */
	public Cochera getCochera() {
		return cochera;
	}

	/**
	 * @param cochera the cochera to set
	 */
	public void setCochera(Cochera cochera) {
		this.cochera = cochera;
	}

	/**
	 * @return the linea
	 */
	public Linea getLinea() {
		return linea;
	}

	/**
	 * @param linea the linea to set
	 */
	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public LocalDate getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(LocalDate fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	@Override
	public String toString() {
		return "Tren [identificador=" + identificador + ", modelo=" + modelo + ", empresaConstructora="
				+ empresaConstructora + ", fechaIncorporacion=" + fechaIncorporacion + ", cochera=" + cochera
				+ ", linea=" + linea + "]";
	}

}
