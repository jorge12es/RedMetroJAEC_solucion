/**
 * 
 */
package es.madrid.redmetro.eva1.ficheros;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.madrid.redmetro.eva1.excepcion.RedMetroException;
import es.madrid.redmetro.eva1.gestores.GestorConfiguracion;
import es.madrid.redmetro.eva1.model.Cochera;
import es.madrid.redmetro.eva1.model.Linea;
import es.madrid.redmetro.eva1.model.Tren;
import es.madrid.redmetro.eva1.service.TrenService;

/**
 * @author Isidoro Nevares Martín (IES Virgen de la Paloma) Fecha: 28 nov 2024
 */
public class GestorFicherosXML {
	TrenService trenService;

	public void procesarListaTrenesDeDOM() throws RedMetroException {
		String nombreFicheroXML = GestorConfiguracion.getRutaFicheroLecturaXMLTren();
		// Tratamiento del fichero XML
		File file = new File(nombreFicheroXML);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// estos métodos podemos usarlos combinados para normalizar el archivo XML
		doc.getDocumentElement().normalize();

		// Obtener la lista de nodos cuya etiqueta es "tren"
		NodeList nList = doc.getElementsByTagName("tren");

		System.out.println("Número de Trenes: " + nList.getLength());
		trenService = new TrenService();
		// Recorrer los distintos nodos Tren
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nodoTren = nList.item(temp);

			if (nodoTren.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nodoTren;
				// Obtener información del tren
				int idTren = Integer.parseInt(eElement.getAttribute("id"));
				String modelo = eElement.getElementsByTagName("modelo").item(0).getTextContent();
				String anyoFabricacion = eElement.getElementsByTagName("anyo_incorporacion").item(0).getTextContent();
				String empresaConstructora = eElement.getElementsByTagName("empresa_constructora").item(0)
						.getTextContent();
				int idCochera = Integer.parseInt(eElement.getElementsByTagName("cod_cochera").item(0).getTextContent());
				int idLinea = Integer.parseInt(eElement.getElementsByTagName("id_linea").item(0).getTextContent());

				Tren tren = new Tren();
				tren.setIdentificador(idTren);
				tren.setEmpresaConstructora(empresaConstructora);
				tren.setModelo(modelo);

				LocalDate fechaFabricacion = LocalDate.of(Integer.parseInt(anyoFabricacion), 1, 1);
				tren.setFechaIncorporacion(fechaFabricacion);

				tren.setModelo(modelo);

				Cochera cochera = new Cochera();
				cochera.setIdentificador(idCochera);
				tren.setCochera(cochera);
				Linea linea = new Linea();
				linea.setIdentificador(idLinea);
				;
				tren.setLinea(linea);

				// Insertar el tren en la base de datos
				trenService.insertarTren(tren);
			}
		}

	}

	public void escribirFicheroXMLDOMDeBBDD() throws RedMetroException {
		// Obtener la información de Trenes-Línea-Color desde BBDD
		trenService = new TrenService();
		List<Tren> listaTrenes = trenService.obtenerListaTrenes();
		System.out.println(listaTrenes);

		String nombreFicheroXML = GestorConfiguracion.getRutaFicheroEscrituraXMLTren();
		// Tratamiento del fichero XML
		File file = new File(nombreFicheroXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Elemento raíz
		Element raiz = doc.createElement("info-trenes-linea-color");
		doc.appendChild(raiz);

		Element trenes = doc.createElement("trenes");
		raiz.appendChild(trenes);

		// Recorrer la lista de trenes
		for (Tren tren : listaTrenes) {
			// Elemento tren
			Element trenElement = doc.createElement("tren");
			trenElement.setAttribute("id", String.valueOf(tren.getIdentificador()));

			// Elemento modelo
			Element modeloElement = doc.createElement("modelo");
			modeloElement.appendChild(doc.createTextNode(tren.getModelo()));
			trenElement.appendChild(modeloElement);

			// Elemento anyo_incorporacion
			int anyoIncorporacion = tren.getFechaIncorporacion().getYear();
			Element anyoElement = doc.createElement("anyo_incorporacion");
			anyoElement.appendChild(doc.createTextNode(String.valueOf(anyoIncorporacion)));
			trenElement.appendChild(anyoElement);

			// Elemento empresa_constructora
			Element empresaElement = doc.createElement("empresa_constructora");
			empresaElement.appendChild(doc.createTextNode(tren.getEmpresaConstructora()));
			trenElement.appendChild(empresaElement);

			// Elemento cod_cochera
			Element cocheraElement = doc.createElement("cod_cochera");
			cocheraElement.appendChild(doc.createTextNode(String.valueOf(tren.getCochera().getIdentificador())));
			trenElement.appendChild(cocheraElement);

			// Elemento linea
			Element lineaElement = doc.createElement("linea");
			lineaElement.setAttribute("id", String.valueOf(tren.getIdentificador()));
			// Elemento nombre
			Element nombreLineaElement = doc.createElement("nombre_l");
			nombreLineaElement.appendChild(doc.createTextNode(tren.getLinea().getNombreLargo()));
			lineaElement.appendChild(nombreLineaElement);
			// Elemento color
			Element colorElement = doc.createElement("color");
			colorElement.setAttribute("id", String.valueOf(tren.getLinea().getColor().getIdentificador()));
			colorElement.setAttribute("cod_hex", String.valueOf(tren.getLinea().getColor().getCodigoHexadecimal()));
			// Elemento descripcion del color
			Element descripocionColorElement = doc.createElement("descripcion");
			descripocionColorElement.appendChild(doc.createTextNode(tren.getLinea().getColor().getNombre()));
			colorElement.appendChild(descripocionColorElement);

			// Añadir la línea al elemento tren
			lineaElement.appendChild(colorElement);

			// Añadir la línea al elemento tren
			trenElement.appendChild(lineaElement);

			// Añadir el tren al elemento raíz
			trenes.appendChild(trenElement);
		}

		// Con el objeto DOM se escribe el documento XML en el fichero
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			// Opcional: Configurar para una salida legible (indentación)
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{xml.apache.org}indent-amount", "4");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);

			// Transformar (escribir) el documento en el archivo
			transformer.transform(source, result);

			System.out.println("Archivo XML ' " + trenService + "' creado exitosamente.");
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
