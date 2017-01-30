package procesaForm.bbdd;

import java.sql.Connection;
import java.sql.SQLException;

import procesaForm.modelo.beans.*;

/**
 * Encapsula la interfaz de la comunicación con la base de datos.
 */
public interface DAO {
	
	/**
	 * Proceso que crea la conexión con la base de datos.
	 * @return la conexión
	 * @throws SQLException
	 */
	public Connection getConexion() throws SQLException;
	
	/**
     * Proceso que cierra la conexión con la base de datos.
     * @throws SQLException
     */
	public void close() throws SQLException;
	
	/**
	 * Proceso que comprueba si existe un usuario(login), en la base de datos
	 * @param login
	 * @return devuelve true si existe 
	 * @throws SQLException
	 */
	public Boolean ifExistUsuario(String login) throws SQLException;
	
	/**
	 * Proceso que obtiene los datos del usuario de la base de datos
	 * @param login
	 * @param clave
	 * @return devuelve un objeto BeanUsuario
	 * @throws SQLException
	 * @throws BeanError
	 */
	public BeanUsuario getUsuario(String login, String clave) throws SQLException, BeanError;
	
	/**
	 * Proceso que inserta un usuario en la base de datos.
	 * @param usuario
	 * @throws SQLException
	 * @throws BeanError
	 */
	public void setUsuario(BeanUsuario usuario) throws SQLException, BeanError;

}
