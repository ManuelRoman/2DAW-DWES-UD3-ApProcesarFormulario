package procesaForm.controlador;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import procesaForm.modelo.beans.*;

/**
 * Servlet que encapsula el control de la aplicación.
 */
@SuppressWarnings("serial")
public class Controlador extends HttpServlet {

	/**
	 * Información de la Base de datos
	 */
	private DataSource dataSource;

	/**
	 * Objeto que encapsula la información a nivel de la aplicación
	 */
	private ServletContext sc;

	/**
	 * Inicializa el servlet y la fuente de datos
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		sc = config.getServletContext();
		String urlDataSource = (String) sc.getInitParameter("URLDataSource");
		InitialContext initCtx = null;
		try {
			initCtx = new InitialContext();
			this.dataSource = (DataSource) initCtx.lookup(urlDataSource);
		} catch (NamingException ne) {
			System.out.println("Error en el método init del servlet. " + ne.getMessage());
			sc.setInitParameter("appOperativa", "false");
		}
		sc.setInitParameter("appOperativa", "true");
	}

	/**
	 * Recibe las peticiones GET y las pasa al método doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Procesa las peticiones que vienen por la vía POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (sc.getInitParameter("appOperativa").equals("true")) {
			HttpSession sesion = request.getSession();
			AyudaSolicitud ayudaSol = new AyudaSolicitud(request);
			Accion accion = ayudaSol.getAccion();
			accion.setSc(sc);
			accion.setDS(dataSource);
			if (accion.ejecutar(request, response)) {
				String vista = accion.getVista();
				request.setAttribute("modelo", accion.getModelo());
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
			} else {
				gesError(accion.getVista(), accion.getError(), request, response);
			}
		} else {
			gesError("errores.jsp", new BeanError(1, "Aplicación no operativa"), request, response);
		}
	}

	/**
     * Reenvía el proceso hacia una vista de gestión de errores.
     * @param vistaError Página que gestionará el error.
     * @param excepcion Objeto encapsulador de la excepción.
     * @param request La petición.
     * @param response La respuesta.
     * @throws javax.servlet.ServletException Puede ser generada por forward().
     * @throws java.io.IOException Puede ser generada por forward().
     */
	private void gesError(String vistaError, BeanError excepcion, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(vistaError);
		request.setAttribute("error", excepcion);
		rd.forward(request, response);
	}

}
