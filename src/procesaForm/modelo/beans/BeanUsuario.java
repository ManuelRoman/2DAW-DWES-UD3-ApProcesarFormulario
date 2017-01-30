package procesaForm.modelo.beans;

import java.io.Serializable;

/**
 * Encapsula el concepto de usuario
 */
@SuppressWarnings("serial")
public class BeanUsuario implements Serializable{
	
	/**
	 * Información del login
	 */
	private String login;
	
	/**
	 * Información de la clave
	 */
	private String clave;
	
	/**
	 * Constructor por defecto
	 */
	public BeanUsuario(){
		this.login="";
		this.clave="";
	}
	
	/**
	 * Constructor con prámetros
	 * @param login
	 * @param clave
	 */
	public BeanUsuario(String login, String clave){
		this.login=login;
		this.clave=clave;
	}

	/**
	 * Devuelve el login
	 * @return String login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Establece el login
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Devuelve la clave
	 * @return clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Establece el login
	 * @param login
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	
}
