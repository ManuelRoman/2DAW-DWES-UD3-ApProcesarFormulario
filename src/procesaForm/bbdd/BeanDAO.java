package procesaForm.bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import procesaForm.modelo.beans.*;

/**
 * Encapsula la comunicación con la base de datos
 */
public class BeanDAO implements DAO {

	/**
	 * Información de la base de datos
	 */
	private DataSource dataSource;

	/**
	 * Representa la conexión con la base de datos
	 */
	private Connection conexion = null;

	/**
	 * Constructor que recibe el DataSource
	 * @param dsBdValidaLogin
	 */
	public BeanDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Proceso que crea la conexión con la base de datos.
	 * @return la conexión
	 * @throws SQLException
	 */
	@Override
	public Connection getConexion() throws SQLException {
		if (conexion == null) {
			this.conexion = dataSource.getConnection();
		}
		return conexion;
	}

	/**
     * Proceso que cierra la conexión con la base de datos.
     * @throws SQLException
     */
	@Override
	public void close() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
		conexion = null;

	}

	/**
	 * Proceso que comprueba si existe un usuario(login), en la base de datos
	 * @param login
	 * @return devuelve true si existe 
	 * @throws SQLException
	 */
	@Override
	public Boolean ifExistUsuario(String login) throws SQLException {
		Boolean exist = false;
		boolean conexionNula = false;
		if (conexion == null) {
			getConexion();
			conexionNula = true;
		}
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("select login from usuario where login = '" + login + "'");
		// Se comprueba si existe el login
		if (rs.next()) {
			exist = true;
		}
		if (st != null) {
			st = null;
			if (conexionNula) {
				close();
			}
		}
		return exist;
	}

	/**
	 * Proceso que obtiene los datos del usuario de la base de datos, si no existe el login 
	 * o la clave es errónea lanza una excepción
	 * @param login
	 * @param clave
	 * @return devuelve un objeto BeanUsuario
	 * @throws SQLException
	 * @throws BeanError
	 */
	@Override
	public BeanUsuario getUsuario(String login, String clave) throws SQLException, BeanError {
		boolean conexionNula = false;
		if (conexion == null) {
			getConexion();
			conexionNula = true;
		}
		BeanUsuario usuario = new BeanUsuario();
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("select login,clave from usuario where login = '" + login + "'");
		// Se comprueba si existe el login
		if (rs.next()) {
			// Se comprueba si no coincide la clave y si coincide se obtiene el
			// nombre
			if (!rs.getString("clave").equals(clave)) {
				throw new BeanError(3, "La clave no coincide.");
			} else {
				usuario = new BeanUsuario(rs.getString("login"), rs.getString("clave"));
			}
		} else {
			throw new BeanError(4, "El login no existe.");
		}
		if (st != null) {
			st = null;
			if (conexionNula) {
				close();
			}
		}
		return usuario;
	}

	/**
	 * Proceso que inserta un usuario en la base de datos, si ya existe el logín lanza una excepción.
	 * @param usuario
	 * @throws SQLException
	 * @throws BeanError
	 */
	@Override
	public void setUsuario(BeanUsuario usuario) throws SQLException, BeanError {
		boolean conexionNula = false;
		if (conexion == null) {
			getConexion();
			conexionNula = true;
		}
		Statement st = conexion.createStatement();
		String insert = "INSERT INTO usuario (login,clave) VALUES ('" + usuario.getLogin() + "','" + usuario.getClave()
				+ "')";
		ResultSet rs = st.executeQuery("select login from usuario where login = '" + usuario.getLogin() + "'");
		// Se comprueba si existe el usuario, si existe se lanza una excepción,
		// si no se inserta en la bb.dd.
		if (rs.next()) {
			throw new BeanError(5, "El Usuario ya existe, pruebe con otro");
		} else {
			st.executeUpdate(insert);
		}
		if (st != null) {
			st = null;
			if (conexionNula) {
				close();
			}
		}
	}

}
