package procesaForm.controlador;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.sql.DataSource;

import procesaForm.modelo.beans.*;

/**
 * Interfaz de las acciones de la aplicación.
 */
public interface Accion {
	
	/**
	 * Método que ejecuta la acción asociada a la petición
	 * @param request La petición encapsulada
	 * @param response La respuesta encapsulada
	 * @return true si se ha ejecutado con normalidad, false en caso contrario
	 * @throws ServletException Excepción de nivel Servlet
	 * @throws IOException Excepción de E/S 
	 */
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException;
	/**
	* Devuelve la vista que procesará el modelo respuesta
	* @return Nombre de la vista
	*/
	public String getVista();
	  
	/**
	* Devuelve un objeto que representa el modelo que deberá procesar la vista
	* @return El modelo a visualizar
	*/
	public Object getModelo();
	 
	/**
	* Establece el contexto del servlet (ámbito aplicación)
	* @param sc Objeto de tipo ServletContext
	*/
	public void setSc(ServletContext sc);
	  
	/**
	* Devuelve un objeto Exception que representa una excepción
	* @return Objeto Exception
	*/
	public BeanError getError();
	  
	/**
	* Establece el datasource con el que trabajará la acción
	* @param ds Objeto DataSource
	*/
	public void setDS(DataSource ds);

}
