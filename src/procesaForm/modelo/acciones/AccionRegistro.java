package procesaForm.modelo.acciones;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import procesaForm.bbdd.BeanDAO;
import procesaForm.bbdd.DAO;
import procesaForm.controlador.Accion;
import procesaForm.modelo.beans.*;

/**
 * Encapsula la acción de registrarse
 */
public class AccionRegistro implements Accion {

	/**
	 * Información de la vista que se devolverá
	 */
	private String vista;
	
	/**
	 * Información de la vista si no se producen errores
	 */
	private final String vistaOK = "WEB-INF/registro.jsp";
	
	/**
	 * Información de la vista si hay algún error
	 */
	private final String vistaError = "WEB-INF/errores.jsp";
	
	/**
	 * Información del modelo que se devolverá
	 */
	private String modelo = "Registro correcto";
	
	/**
	 * Información del error producido
	 */
	private BeanError error;
	
	/**
	 * Objeto que encapsula la información a nivel de la aplicación
	 */
	private ServletContext sc;
	
	/**
	 * Información de la base de datos
	 */
	private DataSource ds;	
	
	/**
	 * Ejecuta el proceso de la acción
	 */
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean resultado = true;
		String login = request.getParameter("login");
		BeanUsuario usuario = new BeanUsuario(login, request.getParameter("clave"));
		DAO beanDAO = new BeanDAO(ds);
		try {
			beanDAO.getConexion();
			if (!beanDAO.ifExistUsuario(login)){
				beanDAO.setUsuario(usuario);
			} else{
				error = new BeanError(5, "El Usuario ya existe, pruebe con otro");
				resultado = false;
			}
		} catch (SQLException sqle) {
			resultado = false;
			error = new BeanError(2,"Error en conexión a base de datos");
			sqle.printStackTrace();
		} catch (BeanError be) {
			resultado= false;
			error = be;
			be.printStackTrace();
		} finally{
			try {
				beanDAO.close();
			} catch (SQLException sqle) {
				error = new BeanError(sqle.getErrorCode(),"Error al cerrar la conexión con la base de datos");
				sqle.printStackTrace();
			}
		}
		if (resultado==true)
			vista = vistaOK;
		else
			vista = vistaError;
		return resultado;
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
