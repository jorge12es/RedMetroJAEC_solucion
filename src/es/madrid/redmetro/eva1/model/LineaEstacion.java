package es.madrid.redmetro.eva1.model;

/**
 *
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 04 nov 2026*
 * 
 */

public class LineaEstacion {
	private Estacion estacion;
	private Linea linea;
	private int orden;

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public String toString() {
		return "LineaEstacion [estacion=" + estacion + ", linea=" + linea + ", orden=" + orden + "]";
	}

}
