package procesaForm.controlador;

import procesaForm.modelo.acciones.*;

/**
 * Encapsula la creacción de acciones
 */
public abstract class FactoriaAcciones {

	/**
	 * Método estático que crea un objeto acción.
	 * @param accion
	 * @return
	 */
	public static Accion creaAccion(String accion) {
		Accion accionSeleccionada = new AccionIndex();
		if (accion != null) {
			switch (accion) {
			case "login":
				accionSeleccionada = new AccionLogin();
				break;
			case "registro":
				accionSeleccionada = new AccionRegistro();
				break;
			case "info":
				accionSeleccionada = new AccionInfo();
			}
		}
		return accionSeleccionada;
	}
}
