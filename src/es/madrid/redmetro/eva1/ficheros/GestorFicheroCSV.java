/**
 * 
 */
package es.madrid.redmetro.eva1.ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.gestores.GestorConfiguracion;
import es.madrid.redmetro.eva1.model.Cochera;
import es.madrid.redmetro.eva1.service.CocheraService;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha:  04 nov 20267
 */
public class GestorFicheroCSV {
	private CocheraService cocheraService;
	
	private final static String SEPARADOR_CSV = GestorConfiguracion.getSeparadorFicheroCSVCochera();

	public void procesarFicheroCocheraCSV() throws RedMetroException {

		String nombreFicheroCSV = GestorConfiguracion.getRutaFicheroCSVCochera();

		try (BufferedReader br = new BufferedReader(new FileReader(nombreFicheroCSV));) {
			br.readLine(); // Saltar la cabecera
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datosLinea = linea.split(SEPARADOR_CSV);
				int operacion = Integer.parseInt(datosLinea[0]);
				int idCochera = Integer.parseInt(datosLinea[1]);
				int depositoInt = Integer.parseInt(datosLinea[2]);
				boolean esDeposito = (depositoInt == 1) ? true : false;
				String nombre = datosLinea[3];
				String direccion = datosLinea[4];

				// se cargan los datos en el objeto Cochera
				Cochera cochera = new Cochera();
				cochera.setIdentificador(idCochera);
				cochera.setNombre(nombre);
				cochera.setDireccion(direccion);
				cochera.setDeposito(esDeposito);

				cocheraService  = new CocheraService();
				if (operacion == 1) {
					boolean existeCochera = cocheraService.existeCocheraPorClave(cochera.getIdentificador());
					if (!existeCochera) { // Si no existe, la insertamos
						cocheraService.insertarCochera(cochera);
					}
				} else if (operacion == 2) {
					cocheraService.actualizarCochera(cochera);
				} else if (operacion == 3) {
					cocheraService.borrarCochera(idCochera);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
