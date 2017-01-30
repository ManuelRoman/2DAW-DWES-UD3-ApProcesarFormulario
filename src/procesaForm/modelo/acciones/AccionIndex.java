package procesaForm.modelo.acciones;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import procesaForm.controlador.Accion;
import procesaForm.modelo.beans.*;

/**
 * Encapsula la acción de ir al index.html
 */
public class AccionIndex implements Accion {

	/**
	 * Información de la vista que se devolverá
	 */
	private String vista;
	
	/**
	 * Información de la vista si no se producen errores
	 */
	private final String vistaOK = "index.html";
	
	/**
	 * Información de la vista si hay algún error
	 */
	private final String vistaError = "WEB-INF/errores.jsp";
	
	/**
	 * Objeto que encapsula la información a nivel de la aplicación
	 */
	private ServletContext sc;
	
	/**
	 * Información de la base de datos
	 */
	private DataSource ds;
	
	/**
	 * Información del modelo que se devolverá
	 */
	private String modelo = "ir a index.html";
	
	/**
	 * Informacón del error producido
	 */
	private BeanError error;

	/**
	 * Ejecuta el proceso de la acción
	 */
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.vista = this.vistaOK;
		return true;
	}

	/**
	* Devuelve la vista actual.
	* @param La vista a devolver al usuario.
	*/
	@Override
	public String getVista() {
		return vista;
	}

	/**
	 * Devuelve el modelo con el que trabajará la vista.
	 * @return El modelo a procesar por la vista. 
	 */
	@Override
	public Object getModelo() {
		return modelo;
	}

	/**
	 * Establece el contexto del servlet (nivel aplicación)
	 * @param sc Objeto ServletContext que encapsula el ámbito de aplicación.
	 */
	@Override
	public void setSc(ServletContext sc) {
		this.sc = sc;

	}

	/**
	 * Devuelve un objeto de error asociado al procesamiento de la acción.
	 * @return Objeto que encapsula una situación de error producida durante
	 * la ejecución de la acción.
	 */
	@Override
	public BeanError getError() {
		error = new BeanError(1, "Error al ir al indice.");
		return error;
	}

	/**
	 * Establece el DataSource de la base de datos
	 */
	@Override
	public void setDS(DataSource ds) {
		this.ds = ds;
	}

}
