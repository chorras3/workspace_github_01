












/* Modificación (ligera) del archivo de curro:
 * D:\desarrollo\PRODUCTO\IDE\RSA70\wks_7.1\BAGO00CargaDocumentosContables\ProsaSrc\bagora\datos\dao\artabro\ConexionDB.java
 */

//#### REINICIO (Ver copia comentada del ConexionDB al final de este archivo)


/*
 * <<<<< OJO PEGO AQUÍ EL ConexionDB original (cambiando sólo
 * ruta de imports) que hay en SWPERSONAL workspace:
 * D:\desarrollo\PRODUCTO\IDE\RSA70\wks_personal\SWPERSONAL\src\personal\db\datos\ConexionDB.java 
 * */

package common; // <<<< Observar: protected

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import bago.db.ComunUtil;
//import bago.db.Logger;
////import bago.batch.exception.DBConexionException;
////import bago.batch.exception.DBQueryException;
////import bago.batch.exception.PersonalException;
// <<<<<<<<<FRAN INICIO
////import personal.comun.Util;
// <<<<<<<<<FRAN FIN

//import bagora.batch.exception.Usuario;
//import bagora.negocio.NgBagoramail;

public class ConexionDB {

	protected static DataSource ds = null;
	protected Connection conexion = null;
	protected int total = 0;
	protected PreparedStatement pstmt;
	// protected int cnt;
	protected boolean autoCommit = true;
	protected ArrayList values = null;
	protected boolean queryEnCurso = false;
	protected boolean CTE_ALLOW_CONSOLE = false;
	

	/**
	 * DEPRECATED. En desuso, usar el constructor equivalente:
	 * ConexionDB(Usuario usuario, String strJndiDatasource, boolean
	 * blnAutocommit) Esta conexión tiene varios problemas: -La conexión usa un
	 * JNDI de dataSource fijo (no podemos elegir la BD a conectar), -Este
	 * constructor da por hecho que cada operación hará un commit
	 * automáticamente, sin posibilidad de hacer un rollback en caso de que en
	 * una serie de operaciones atómicas pueda fallar una de ellas y por tanto
	 * sea necesario deshacer toda la serie de operaciones. -Obliga a usar
	 * escritura de logs, cuando no es tarea de una clase de acceso a datos y
	 * por tanto debería mantenerse fuera de esta clase.
	 * 
	 * @param usuario
	 * @throws DBConexionException
	 */
	////public ConexionDB(Usuario usuario) 
	public ConexionDB(String usuario)
	throws NamingException, SQLException 
	{
		try {
			if (ds == null) {
				//		

				// HttpServletRequest request =
				// (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				// String personalds =
				// request.getSession().getServletContext().getInitParameter("personalds");

				Hashtable env = new Hashtable();
				env.put(Context.INITIAL_CONTEXT_FACTORY,
						"com.ibm.websphere.naming.WsnInitialContextFactory");
				Context ctx = new InitialContext(env);

				// //ds = (DataSource)
				// ctx.lookup("java:comp/env/jdbc/auditorias");

				// [ConexionSource]
				// String strDatasource = new String(ComunUtil.JNDI_DATASOURCE);
				String strDatasource = new String("jdbc/oracle/dsAgora"); // AGORA

				// System.out.println("strDatasource=\""+strDatasource+"\"");
				// ds = (DataSource)
				// ctx.lookup("java:comp/env/jdbc/auditorias"); // Aplicación
				// SWPERSONAL: Nombre JNDI para web.xml y WAS (petición DESA,
				// PRE, ...). Ej. ctx.lookup("java:comp/env/jdbc/auditorias");
				try {
					ds = (DataSource) ctx.lookup(strDatasource);
				} catch (NamingException ne) {
					// TODO Bloque catch generado automáticamente
					throw ne;					
				} // Aplicación
				// SWPERSONAL:
				// Nombre JNDI
				// para web.xml
				// y WAS
				// (petición
				// DESA, PRE,
				// ...). Ej.
				// ctx.lookup("java:comp/env/jdbc/auditorias");
				// //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ConexionDB(Usuario usuario) ->lookup realizado con éxito");

				// values = new ArrayList();
			}

			values = new ArrayList(); // Mover fuera del if o
			// NullPointerException

			conexion = ds.getConnection();

//		} catch ( Exception e) {
		} catch (SQLException se) {  // TODO Fran. Crear excepción de usuario personalizada para fallos en la conexión. 
			
			// INICIO FRAN
//			Exception pe = new Exception(
//					"Error obteniendo datasource o creando conexión de datasource",
//					null);
			// //pe.rastroYRedireccion();
//			throw new Exception(
//					"Error obteniendo datasource o creando conexión de datasource",
//					null);
			throw se;
			// FIN FRAN

		}
	}

	/**
	 * DEPRECATED. En desuso, usar el constructor equivalente:
	 * ConexionDB(Usuario usuario, String strJndiDatasource, boolean
	 * blnAutocommit)
	 * 
	 * @param usuario
	 * @param autoCommit
	 *            Si está a false no se ejecutará el commit automático, y habrá
	 *            que hacerlo llamando al método commit(Usuario).
	 * @throws NamingException 
	 * @throws DBConexionException
	 *             Fran. Este constructor crea un objeto ConexionDB que abre la
	 *             conexión y permite ser usado para ejecutar queries a la base
	 *             de datos. Si autoCommit está a false no se ejecutará el
	 *             commit automático, y habrá que hacerlo llamando al método
	 *             commit(Usuario) o rollback(Usuario) si se quiere abortar.
	 *             Ésto permite atomicidad (comprometer o abortar una cadena de
	 *             modificaciones)
	 */
	////public ConexionDB(Usuario usuario, boolean autoCommit) 
	public ConexionDB(String usuario, boolean autoCommit)
	throws SQLException, NamingException 
	{
		// POTENTIAL NamingException. Required set "throws NamingException" on beginning of method (throws declaration). Can not set try-catch before "this()". 
		this(usuario);
		
		this.autoCommit = autoCommit;
		try {
			if (!autoCommit)
				conexion.setAutoCommit(false);
		} catch (SQLException se) {
			////throw new SQLException("Error fijando autocommit");
			throw se;
		}
	}

	/**
	 * Constructor recomendado. Retorna un objeto conexión, se le puede pasar el
	 * argumento autoCommit para establecerlo o desactivarlo (activado por
	 * defecto) y el JNDI para el dataSource. Observar que: *El JNDI del
	 * dataSource se pasa como argumento (así podremos elegir conectar a URL's
	 * de conexión diferentes). *También se debe indicar el argumento
	 * blnAutocommit, si está a true cada operación se comprometerá
	 * automáticamente, en caso de que queramos la posibilidad de ejecutar
	 * .rollback() entonces debemos establecer blnAutocommit=false
	 * 
	 * @param usuario
	 * @param strJndiDatasource
	 * @param blnAutocommit
	 * @throws DBConexionException
	 */
	////public ConexionDB(Usuario usuario, String strJndiDatasource, boolean blnAutocommit) 
	public ConexionDB(String usuario, String strJndiDatasource, boolean blnAutocommit)
	throws NamingException, SQLException
	{
		try {
			if (ds == null) {

				Hashtable env = new Hashtable();
				// //env.put(Context.INITIAL_CONTEXT_FACTORY,
				// "com.ibm.websphere.naming.WsnInitialContextFactory");
				env.put("java.naming.factory.initial",
						"com.ibm.websphere.naming.WsnInitialContextFactory");
				Context ctx = new InitialContext(env);

				/*
				 * ======================================== Establecer el JNDI
				 * (nos llega como argumento)
				 * ======================================== Ej. Para
				 * GestionPersonal sería: "jdbc_auditorias" ->
				 * ctx.lookup("java:comp/env/jdbc_auditorias") Para SWPERSONAL
				 * sería: "jdbc--auditorias" ->
				 * ctx.lookup("java:comp/env/jdbc--auditorias") Eso buscaría el
				 * JNDI "jdbc/auditorias" que contendrá la URL de conexión.
				 */
				ds = (DataSource) ctx.lookup(strJndiDatasource);

				values = new ArrayList();

			}

			conexion = ds.getConnection();

//		} catch (Exception e) {
//			throw new Exception(
//					"Error obteniendo datasource o creando conexión de datasource");
//		}
		} catch (NamingException ne) {  // TODO Fran. Crear excepción de usuario personalizada para fallos en la conexión. 
			throw ne;
		} catch (SQLException se){
			throw se;
		}

			
	}

	////public void commit(Usuario usuario) 
	public void commit(String usuario)
	throws SQLException 
	{
		if (autoCommit)
			Logger_escribeWarning("Warning: la conexión tiene el autocommit activado");
		// //Logger.warning("Warning: la conexión tiene el autocommit activado");

		try {
			conexion.commit();
		} catch (SQLException se) {
			////throw new Exception("Error haciendo commit");
			throw se;
		}
	}

	////public void rollback(Usuario usuario)
	public void rollback(String usuario) 
	throws SQLException 
	{
		try {
			conexion.rollback();
		} catch (SQLException se) {
			////throw new Exception("Error haciendo rollback");
			throw se;
		}
	}

	////public void desconectarDB(Usuario usuario) 
	public void desconectarDB(String usuario)
	throws SQLException 
	{
		try {
			conexion.close();
		} catch (SQLException se) {
			// //Logger.error("Error en desconexión");
			Logger_escribeError("Error en desconexión");
			////throw new Exception("Error en desconexión");
			throw se;
		}
	}

	////public void iniciarQuery(Usuario usuario) 
	public void iniciarQuery(String usuario)
	throws SQLException 
	{
		if (queryEnCurso == true) {
			// Tira una query exception porque el problema no está
			// en la conexión, sino en la manera como se hace la consulta
			throw new SQLException(
					"Error iniciando consulta. Las consultas no pueden anidarse");
		}
		queryEnCurso = true;
		values = new ArrayList();
	}

	public String set(long j) {
		values.add(new DatoDB(DatoDB.DATOLONG, new Long(j)));
		return "?";
	}

	public String set(int j) {
		values.add(new DatoDB(DatoDB.DATOINT, new Integer(j)));

		return "?";
	}

	public String set(String str) {
		values.add(new DatoDB(DatoDB.DATOSTRING, str));
		return "?";
	}

	public String set(String str, int endIndex) {
		// <<<<<<<<<FRAN INICIO
		// str = Comun.truncate(str, endIndex);
		// Sólo una llamada al método, no merece la pena hacer referencia a la
		// clase
		// ComunUtil.java-> str = ComunUtil.truncate(str, endIndex);
		str = truncarCadena(str, endIndex);
		// <<<<<<<<<FRAN FIN

		return set(str);
	}

	// Original. Deprecated, usa muchas referencias y Exceptions personalizadas,
	// mejor usar "executePreparedUpdate".
	////public void ejecutarUpdate(String sql, Usuario usuario) 
	public void ejecutarUpdate(String sql, String usuario)
	throws SQLException 
	{
		if (!queryEnCurso) {
//			SQLException DBQE = new SQLException("Error - query no inicializada "
//					+ sql);
//			SQLException Pe = new SQLException("Error ejecutar update", null);
//			// //Pe.rastroYRedireccion();
//			// //throw DBQE;
			SQLException exQueryEnCurso = new SQLException("ejecutarUpdate. Error, no se puede iniciar ejecución, ya hay otra query en curso");
			throw exQueryEnCurso;
		}

		try {
			pstmt = conexion.prepareStatement(sql);
		} catch (SQLException se) {
//			Exception Pe = new Exception("Error ejecutar update", null);
//			// //Pe.rastroYRedireccion();
//			throw new SQLException("Error al ejecutar update " + sql, null);
			throw se;
		} finally {
			queryEnCurso = false;
		}

		// Si el error da aquí, lo más probable es que sea una DBQueryException
		try {
			componerQuery();
			pstmt.executeUpdate();

		} catch (SQLException se) {

//			Exception pe = new Exception(e.getMessage() + "ejecutarUpdate");
//			// //pe.rastroYRedireccion();
//			throw new SQLException("Error al ejecutar update " + sql);
			throw se;

		} finally {
			queryEnCurso = false;
		}
	}

	// Recomendada, no usa tantas referencias ni excepciones personalizadas
	public void executePreparedUpdate(String sql)
	// throws Exception, DBQueryException
	throws SQLException {
		if (!queryEnCurso) {
			// DBQueryException DBQE = new
			// DBQueryException("Error - query no inicializada " + sql,
			// usuario);
			// PersonalException Pe= new
			// PersonalException(DBQE.getMessage(),"ejecutarUpdate");
			// Pe.rastroYRedireccion();
			// throw DBQE;
//			throw new SQLException("Query not initiated");
			SQLException exQueryNotInitiated = new SQLException("Query not initiated");
			throw exQueryNotInitiated;
		}

		try {
			pstmt = conexion.prepareStatement(sql);
		} catch (SQLException se) {
			Exception Pe = new SQLException(se.getMessage() + "ejecutarUpdate");
			// //pe.rastroYRedireccion();
			// //throw new DBConexionException("Error al ejecutar update " +
			// sql, usuario);
//			throw new SQLException("Error executing update, query: "
//					+ fillSqlValues(sql));
			throw se;
		} finally {
			queryEnCurso = false;
		}

		// Si el error da aquí, lo más probable es que sea una DBQueryException
		try {
			componerQuery();
			pstmt.executeUpdate();

		} catch (SQLException se2) {

//			Exception pe = new Exception(e.getMessage() + "ejecutarUpdate");
//			// //pe.rastroYRedireccion();
//			// //throw new DBQueryException("Error al ejecutar update " + sql,
//			// usuario);
//			throw new SQLException("Error executing update, query: "
//					+ fillSqlValues(sql));
			throw se2;

		} finally {
			queryEnCurso = false;
		}
	}

	// Original. Muchas dependencias. Si falla "conexion.prepareStatement(sql)"
	// en vez de hacer throw SQLException se hace con DBConexionException
	////public ArrayList ejecutarQuery(String sql, Usuario usuario)
	public ArrayList ejecutarQuery(String sql, String usuario)
	throws SQLException {
		if (!queryEnCurso) {
//			Exception Pe = new Exception("Error - query no inicializada " + sql
//					+ "ejecutarUpdate");
//			// //pe.rastroYRedireccion();
//			throw new SQLException("Error - query no inicializada " + sql);
			SQLException exQueryEnCurso = new SQLException("ConexionDB. No se puede ejecutarQuery pues ya hay una query en curso");
			throw exQueryEnCurso;
		}

		ResultSet resultSet = null;
		try {
			pstmt = conexion.prepareStatement(sql);
		} catch (SQLException se) {
//			Exception pe = new Exception(e.getMessage() + "ejecutarQuery");
//			// //pe.rastroYRedireccion();
//			throw new SQLException("Error al ejecutar Query " + sql);
			throw se;

		} finally {
			queryEnCurso = false;
		}

		try {
			/**
			 * Este método hace los setString, setInt, etc. de golpe ya que los
			 * tipos se van guardando en un array de tipos.
			 */
			componerQuery();
			resultSet = pstmt.executeQuery();
			ArrayList arlisListado = resultados(resultSet);
			resultSet.close();
			pstmt.close();
			return arlisListado;
		} catch (SQLException se) {
			Exception pe = new Exception("Error SQL al ejecutar query " + sql
					+ "\n" + se.getMessage() + "ejecutarQuery");
			// //pe.rastroYRedireccion();

			throw new SQLException("Error SQL al ejecutar query " + sql + "\n"
					+ se.getMessage());
		// TODO Fran. Cast no deja relanzar un Exception normal, debe ser una personalizado. 
//		} catch (Exception e) {
//			Exception pe = new Exception(
//					"Error indeterminado al ejecutar query " + pstmt.toString()
//							+ "ejecutarQuery");
//			// //pe.rastroYRedireccion();
//			throw new Exception("Error indeterminado al ejecutar query "
//					+ pstmt.toString());
		} finally {
			queryEnCurso = false;
		}
	}

	
	
	/**
	 * @@Fran. Variación del método .ejecutarQuery. Este método permite: -Sólo
	 *         se hace throws de: SQLException, DBQueryException
	 *         (DBConexionException no se necesita) -Ser independiente del
	 *         método .rastroYRedireccion (propio de JSF) -Diferenciar excepción
	 * @param sql
	 * @param usuario
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	////public ArrayList ejecutarQuery_reusable(String sql, Usuario usuario)
	public ArrayList ejecutarQuery_reusable(String sql, String usuario)
			throws SQLException {
		if (!queryEnCurso) {
			throw new SQLException("Error - query no inicializada " + sql);
		} 

		ResultSet resultSet = null;
		try {
			pstmt = conexion.prepareStatement(sql);
		} catch (SQLException se) {

			////throw new SQLException("Error al preparar la query. SQL= " + sql);
			throw se; 

		} finally {
			queryEnCurso = false;
		}

		try {
			/**
			 * Este método hace los setString, setInt, etc. de golpe ya que los
			 * tipos se van guardando en un array de tipos.
			 */
			componerQuery(); // Añadir los SET (sustituir en conexionDB los
			// valores ? por lo que hay en el ArrayList
			// this.values)
			writeTrace("sql no valuada=" + sql); // TODO Fran <<<<<<
			// quitar ésto
			writeTrace("Donde conexionDB.values=" + this.values); // TODO
			// Fran
			// <<<<
			// quitar
			// ésto
			resultSet = pstmt.executeQuery();
			ArrayList arlisListado = resultados(resultSet);
			// ArrayList arlisListado = recordsetToArrayList(resultSet); //<<<<
			// AÚN FALLA, MIRARLO. como resultados() pero evita nullPointers
			resultSet.close();
			pstmt.close();
			return arlisListado;
		} catch (SQLException se) {
//			throw new SQLException("Error SQL al ejecutar query " + sql + "\n"
//					+ e.getMessage());
			throw se;
		// TODO Fran. Cast no deja relanzar un Exception normal, debe ser una personalizado.
			//		} catch (Exception e) {
//			throw new Exception("Error indeterminado al ejecutar query "
//					+ pstmt.toString());
		} finally {
			queryEnCurso = false;
		}
	}

	/**
	 * CONEXIONDB VERSION!!!
	 * 
	 * Receives a query and an array with prepared values '?'. Returns
	 * bidimensional ArrayList with resulting recordset (as a ArrayList of
	 * ArrayList (String type)).
	 * 
	 * @param strSql
	 * @param lisValues
	 * @strJndiDatasource the internal name of the DataSource (not JNDI "\"
	 *                    name)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList executePreparedQueryUsingList(String strSql,
			ArrayList lisValues, String strJndiDatasource) 
	throws SQLException, NamingException {

		// ArrayList containing resulting recordset
		ArrayList lisRows = null;

		////NgBagoramail comun = new NgBagoramail();

		/*
		 * ======================================== Connect
		 * ========================================
		 */
		ConexionDB conexionDB = null;
		try {
			/*
			 * ======================================== Set datasource JNDI
			 * ======================================== It refers to the part
			 * after the prefix "java:comp/env/". This is,
			 * strJndiDataSource="java:comp/env/jdbc/oracle/agora" maps to a
			 * datasource within WAS named: jdbc/oracle/agora
			 */
			// String strJndiDataSource = CTE_JNDI_DATASOURCE; // AGORA
			// TODO Fran Renombrar clase ConexionDB a DataManager()
			// [ConexionSource]
			// //conexionDB = new ConexionDB(strJndiDataSource); //new
			// ConexionDB();
			
			try {
				conexionDB = new ConexionDB(null);
			} catch (NamingException ne) {
				// TODO Bloque catch generado automáticamente
				//ne.printStackTrace();
				throw ne;
			} // new ConexionDB();
			////escribeTraza("Connection ok. ");

		} catch (SQLException se) {
			////escribeTraza("Connection Exception. Details: "
					////+ obtenerMensajeExceptionJavaStyle(e));
			throw se;
		}

		/*
		 * ======================================== 
		 * Init query
		 * ======================================== 
		 * -Important: mandatory init
		 * to be able to add values '?' through conexionDB.set(strValue)
		 * otherwise all values will be null.
		 */
		try {
			// [ConexionSource]
			// //conexionDB.iniciarQuery();
			conexionDB.iniciarQuery(null);
		} catch (SQLException se) {
//			String strMensajeError = ("Error iniciando query.  "
//					+ "\n Detalles:" + "\n" + obtenerMensajeExceptionJavaStyle(e));
//			escribeTraza(strMensajeError);
//			throw new Exception(strMensajeError);
			throw se;
		}

		// // // OBTENER SQL QUE LISTA LA TABLA AUTORIZACIONES (con las
		// descripciones de los códigos)
		// // String strSql_TodasLasAutorizaciones =
		// comun.obtenerTextoSql_TablaAutorizaciones();
		//	
		// // Traza de la query con los valores ? ya mostrados explícitamente:
		// escribirTraza( "strSql_TodasLasAutorizaciones (con valores)=\n"+
		// conexionDB.fillSqlValues(strSql) ); // En realidad NO tiene valores
		// "?"

		// <<<<<<<<<<<<<<<<
		// Añadir a la query los valores preparados que se reciben como
		// argumento:

		// //strSqlFinal = obtenerPorcionString (strSql, "?"); // Coge texto
		// hasta encontrar un ?

		ArrayList lisSqlPieces = splitToArrayList("?", strSql);

		int posValue = 0;
		java.util.Iterator iterSqlPieces = lisSqlPieces.iterator();
		while (iterSqlPieces.hasNext()) {
			String strValor = (String) iterSqlPieces.next();
			String strPorcionSql = (String) lisSqlPieces.get(posValue);
//			escribeTraza("Por the SQL piece (String)lisSqlFinal.get(posValue)="
//					+ (String) lisSqlPieces.get(posValue));
			// Check if we are out of values. For example, the last SQL portion
			// could not require a '?' value
			if (posValue > lisValues.size() - 1) {
				// Do nothing
//				escribeTraza("Is not required to SET a value (no values left)");
			} else {
				// Do set
//				escribeTraza("It would be required to do a SET for the value lisValues(posValue)="
//						+ lisValues.get(posValue));
				String strValue = (String) lisValues.get(posValue);
				conexionDB.set(strValue);
			}
			// Increase value index
			posValue++;
		}

//		// Show sql (showing values '?' explicitly):
//		escribeTraza("conexionDB.fillSqlValues(strSql)=\n"
//				+ conexionDB.fillSqlValues(strSql)); // En realidad NO tiene valores "?"

		/*
		 * ======================================== Execute query
		 * ========================================
		 */
		try {
			// //arlisRows = conexionDB.ejecutarQuery(strSql, null);
			// arlisRows = conexionDB.ejecutarQuery_reusable(strSql, null);
			lisRows = conexionDB.ejecutarQuery(strSql, null);
		} catch (SQLException se1) {
//			throw new Exception("ejecutarPreparedQuery Error. Details: "
//					+ obtenerMensajeExceptionJavaStyle(e1));
			throw se1;
		}

		/*
		 * ======================================== Finish query
		 * ========================================
		 */
		try {
			conexionDB.desconectarDB(null);
		} catch (SQLException se) {
			// //escribirTraza("Error al cerrar conexión. Detalles: "+obtenerMensajeExceptionJavaStyle(e));
//			throw new Exception(
//					"executePreparedQuery() Exception, can't close connection. Details: "
//							+ obtenerMensajeExceptionJavaStyle(e));
			throw se;
		}

		return lisRows;
	}
	
	
	
	/**
	 * Receives a query (NOT PREPARED, THIS IS NOT USING '?' VALUES). Returns
	 * bidimensional ArrayList with resulting recordset (as a ArrayList of
	 * ArrayList (String type)).
	 * 
	 * @param strSql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList executeQueryNotPrepared(String pstrSql, String pstrJndiDataSource) 
	throws SQLException, NamingException {

		// ArrayList containing resulting recordset
		ArrayList lisRegistros = null;

		////NgBagoramail comun = new NgBagoramail();

		/*
		 * ======================================== 
		 * Connect
		 * ========================================
		 */
		ConexionDB conexionDB = null;
		try {
			/*
			 * ======================================== 
			 * Set datasource JNDI
			 * ======================================== 
			 * It refers to the part
			 * after the prefix "java:comp/env/". This is,
			 * strJndiDataSource="java:comp/env/jdbc/oracle/agora" maps to a
			 * datasource within WAS named: jdbc/oracle/agora
			 */
//			String strJndiDataSource = CTE_JNDI_DATASOURCE; // AGORA
			// TODO Fran Renombrar clase ConexionDB a DataManager()
			// [ConexionSource]
			// //conexionDB = new ConexionDB(strJndiDataSource); //new
			// ConexionDB();
			try {
				conexionDB = new ConexionDB(null);
			} catch (NamingException ne) {
				// TODO Bloque catch generado automáticamente
				throw ne;
			} // new ConexionDB();
			writeTrace("Connection ok. ");

		} catch (SQLException se) {
			writeTrace("Connection Exception. Details: "
					+ obtenerMensajeExceptionJavaStyle(se));
			throw se;
		}

		/*
		 * ======================================== 
		 * Init query
		 * ======================================== 
		 * -Important: mandatory init
		 * to be able to add values '?' through conexionDB.set(strValue)
		 * otherwise all values will be null. Remember ConexionDB is a
		 * connection for prepared queries
		 */
		try {
			// [ConexionSource]
			// //conexionDB.iniciarQuery();
			conexionDB.iniciarQuery(null);
		} catch (SQLException se) {
			String strMensajeError = ("Error iniciando query.  "
					+ "\n Detalles:" + "\n" + obtenerMensajeExceptionJavaStyle(se));
			writeTrace(strMensajeError);
			////throw new Exception(strMensajeError);
			throw se;
		}


		// Show sql
		writeTrace("strSql=\n" + pstrSql);

		/*
		 * ======================================== 
		 * Execute query
		 * ========================================
		 */
		try {
			lisRegistros = conexionDB.ejecutarQuery_reusable(pstrSql, null);
		} catch (SQLException se1) {
//			throw new SQLException("executeQuery() Exception. Details: "
//					+ obtenerMensajeExceptionJavaStyle(se1));
			SQLException exQueryNotPrepared = new SQLException("executeQueryNotPrepared() Exception."
					//+" Details: " + obtenerMensajeExceptionJavaStyle(se1)
					);
			throw exQueryNotPrepared;
		}

		/*
		 * ======================================== 
		 * Finish query
		 * ========================================
		 */
		try {
			conexionDB.desconectarDB(null);
		} catch (SQLException se2) {
			// //escribirTraza("Error al cerrar conexión. Detalles: "+obtenerMensajeExceptionJavaStyle(e));
//			throw new SQLException(
//					"executeQuery() Exception, can't close connection. Details: "
//							+ obtenerMensajeExceptionJavaStyle(se2));
			throw se2;
		}

		return lisRegistros;
	}	

	////public String ejecutarProcedimiento(String sql, Usuario usuario)
	public String ejecutarProcedimiento(String sql, String usuario)
			throws SQLException {

		if (!queryEnCurso) {
//			SQLException se = new SQLException("Error - query no inicializada " + sql
//					+ "ejecutarProcedimiento");
			SQLException se = new SQLException("ejecutarProcedimiento. Error - query no inicializada");			
			throw se;
		}

		String errors = "";
		CallableStatement callstmt = null;
		try {
			callstmt = conexion.prepareCall(sql);

		} catch (SQLException se) {
//			SQLException se2 = new SQLException("Error - query no inicializada " + sql
//					+ "\n" + se.getMessage() + "ejecutarProcedimiento");
			// //pe.rastroYRedireccion();
			////throw new Exception("Error al ejecutar update " + sql);
			////throw se2;
			throw se;

		} finally {
			queryEnCurso = false;
		}

		try {
			componerCall(callstmt);
			callstmt.execute();
			errors = callstmt.getString(1);

			callstmt.close();
			return errors;

		} catch (SQLException se) {
//			Exception pe = new Exception("Error SQL al ejecutar query  " + sql
//					+ "\n" + se.getMessage() + "ejecutarProcedimiento");
			// //pe.rastroYRedireccion();
//			throw new SQLException("Error SQL al ejecutar query " + sql + "\n"
//					+ se.getMessage());
			throw se;
			// TODO Fran. Cast no deja relanzar una Exception normal, sólo una personalizada
			//		} catch (Exception e) {
//			Exception pe = new Exception(
//					"Error indeterminado al ejecutar update  " + sql + "\n"
//							+ e.getMessage() + "ejecutarProcedimiento");
//			// //pe.rastroYRedireccion();
//			throw new Exception("Error indeterminado al ejecutar update "
//					+ callstmt.toString());
		} finally {
			queryEnCurso = false;
		}
	}

	/*
	 * ==================== [Fran] Hacer todos los SET. ====================
	 * Para poder hacer de golpe todos los SET de una query preparada, lo que
	 * hacemos es ir guardando por cada ? de la SQL su tipo en ConexionDB.java,
	 * en su atributo pstmt, que es tipo "PreparedStatement" (la clase con los
	 * métodos ejecutarUpdate(), ejecutarQuery(), ejecutarProcedimiento(), etc.)
	 */
	private void componerQuery() 
	throws SQLException 
	{
		int cnt = 1;
		for (int i = 0; i < values.size(); i++) {
			DatoDB ddb = (DatoDB) values.get(i);

			switch (ddb.getTipo()) {
			case DatoDB.DATOSTRING:
				pstmt.setString(cnt++, (String) ddb.getDato());
				break;
			case DatoDB.DATOINT:
				pstmt.setInt(cnt++, ((Integer) ddb.getDato()).intValue());
				break;
			case DatoDB.DATOLONG:
				pstmt.setLong(cnt++, ((Long) ddb.getDato()).intValue());
				break;
			}
		}
	}

	private void componerCall(CallableStatement callstmt) throws 
	SQLException 
	{
		int cnt = 2;
		callstmt.registerOutParameter(1, Types.VARCHAR);

		for (int i = 0; i < values.size(); i++) {
			DatoDB ddb = (DatoDB) values.get(i);

			switch (ddb.getTipo()) {
			case DatoDB.DATOSTRING:
				callstmt.setString(cnt++, (String) ddb.getDato());
				break;
			case DatoDB.DATOINT:
				callstmt.setInt(cnt++, ((Integer) ddb.getDato()).intValue());
				break;
			case DatoDB.DATOLONG:
				callstmt.setLong(cnt++, ((Long) ddb.getDato()).intValue());
				break;
			}
		}
	}

	/*
	 * DEPRECATED, USAR recordsetToArrayList
	 * ======================================== Convertir el recordset en un
	 * ArrayList bidimensional ======================================== De este
	 * modo será fácil manejar la información en memoria, una vez se haya
	 * cerrado la conexión con la BD.
	 */
	private ArrayList resultados(ResultSet resultSet) 
	throws SQLException // Llega
	// un
	// conjunto
	// de
	// registros
	{
		// Aquí guardaremos el ResultSet en forma de ArrayList bidimensional
		ArrayList arlisListado = new ArrayList();
		try {
			// Por cada registro, guardamos su valor en el ArrayList
			// bidimensional en la columna correspondiente
			while (resultSet.next()) { // Tomar el siguiente registro
				// Aquí guardaremos un registro del recordset en formato
				// ArrayList
				ArrayList arlisRegistro = new ArrayList();

				// Por cada campo del registro actual, guardarlo en el ArrayList
				for (int intPosicionCampo = 1; intPosicionCampo <= resultSet
						.getMetaData().getColumnCount(); intPosicionCampo++) {
					Object obj1 = null;

					// //obj1 = resultSet.getBlob(intPosicionCampo);
					obj1 = resultSet.getObject(intPosicionCampo);
					if (obj1 == null) {
						writeTrace("Es nulo");
						arlisRegistro.add("");
					} else {
						writeTrace("No es nulo");
						if (resultSet.getObject(intPosicionCampo).getClass()
								.getName().equalsIgnoreCase("oracle.sql.BLOB")) {
							writeTrace("ES BLOB!");
							arlisRegistro.add(obj1);
						} else if (resultSet.getObject(intPosicionCampo)
								.getClass().getName().equalsIgnoreCase(
										"java.lang.String")) {
							writeTrace("Es String");
							arlisRegistro.add(resultSet.getString(
									intPosicionCampo).trim());
						} else { // Otros tipos como long etc.
							// //throw new
							// Exception("Excepción de usuario, tipo no contemplado");   // TODO Fran <<<<<< ANOMALÍA TIPOS!!!
							arlisRegistro.add(resultSet.getString(
									intPosicionCampo).trim());
						}
					}
				}
				arlisListado.add(arlisRegistro);

			}
			return arlisListado;
		} catch (SQLException se) {
//			Exception pe = new Exception(se.getMessage()
//					+ "ConexionDB.resultados");
//			// //pe.rastroYRedireccion();
			throw se;
		}
	}

	/*
	 * Recomendado. Creado a partir de resultados(ResultSet resultSet) pero
	 * evita nullPointers ======================================== Convertir el
	 * recordset en un ArrayList bidimensional
	 * ======================================== De este modo será fácil manejar
	 * la información en memoria, una vez se haya cerrado la conexión con la BD.
	 */
	private ArrayList recordsetToArrayList(ResultSet resultSet){
	// //throws Exception // Llega un conjunto de registros
	
		// Aquí guardaremos el ResultSet en forma de ArrayList bidimensional
		// (array de arrays: así tendremos filas y columnas)
		ArrayList arlisListado = new ArrayList();

		// Si no hay registros, retornar algo vacío
		boolean bHayRegistros = false;
		int numeroFilas = 0;
		int numeroColumnas = 0;
		try {
			bHayRegistros = resultSet.next(); // Retornará true si hay registros
			numeroFilas = resultSet.getFetchSize();
			numeroColumnas = resultSet.getMetaData().getColumnCount(); // Lanza
			// error
			// si no
			// hay
			// registros
		} catch (SQLException e) {
			bHayRegistros = false; // Sino, sigue en false
			numeroFilas = 0;
		}

		if (!bHayRegistros || numeroColumnas < 1) {
			// Entonces lo consideramos un array vacío
			arlisListado = new ArrayList();
		} else {

			// Por cada registro, guardamos su valor en el ArrayList
			// bidimensional en la columna correspondiente
			try {
				while (resultSet.next()) { // Tomar el siguiente registro
					// Aquí guardaremos un registro del recordset en formato
					// ArrayList
					ArrayList arlisRegistro = new ArrayList();

					// Por cada campo del registro actual, guardarlo en el
					// ArrayList
					for (int intPosicionCampo = 1; intPosicionCampo <= numeroColumnas; intPosicionCampo++) {
						Object obj1 = null;

						// //obj1 = resultSet.getBlob(intPosicionCampo);
						obj1 = resultSet.getObject(intPosicionCampo);

						if (obj1 == null) {
							writeTrace("Es nulo");
							arlisRegistro.add("");
						} else {
							writeTrace("No es nulo");
							if (resultSet.getObject(intPosicionCampo)
									.getClass().getName().equalsIgnoreCase(
											"oracle.sql.BLOB")) {
								writeTrace("ES BLOB!");
								arlisRegistro.add(obj1);
							} else if (resultSet.getObject(intPosicionCampo)
									.getClass().getName().equalsIgnoreCase(
											"java.lang.String")) {
								writeTrace("Es String");
								arlisRegistro.add(resultSet.getString(
										intPosicionCampo).trim());
							} else { // Otros tipos como long etc.
								// //throw new
								// Exception("Excepción de usuario, tipo no contemplado");
								arlisRegistro.add(resultSet.getString(
										intPosicionCampo).trim());
							}
						}

					} // Fin FOR (por cada campo..)
					arlisListado.add(arlisRegistro); // (Se traspapeló esta
					// línea)
				} // End WHILE (por cada registro..)
			} catch (SQLException e) {
				// Entonces lo consideramos un array vacío
				arlisListado = new ArrayList();
			}
		}

		return arlisListado;
	}

	/*
	 * ======================================== Obtener del contexto de WAS el
	 * end point del servicio web Gerex ========================================
	 */
	public static String getJNDI_EXCEPCIONES() {

		String urlEXCEPCIONES = null;

		try {
			java.util.Hashtable env = new java.util.Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.ibm.websphere.naming.WsnInitialContextFactory");

			// Context ctex = new InitialContext(env);
			Context ctex = new InitialContext();

			// Accedemos a la referencia
			urlEXCEPCIONES = ctex.lookup("java:comp/env/GEREX").toString();

		} catch (NamingException ex) {
			// //Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
			Logger_escribeError("getJNDI_EXCEPCIONES: " + ex.toString());
		// TODO Fran. Cast no permite relanzar Exception normal.			
//		} catch (Exception ex) {
//			// //Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
//			Logger_escribeError("getJNDI_EXCEPCIONES: " + ex.toString());
		}
		return urlEXCEPCIONES;
	}

	// ##[inicio_cambio_Fran]

	// /**
	// * ========================================
	// * OVERRIDE METHODS
	// * ========================================
	// */

	/**
	 * ======================================== 
	 * Método copiado de ConexionDB,
	 * debido a que el método es private y por tanto no se puede heredar.
	 * ========================================
	 */
	public byte[] obtenerBlob(ResultSet r) 
	throws SQLException   // Potential exceptions: r.getBlob, r.getBytes, etc. 
	{
		r.next();
		java.sql.Blob blob = r.getBlob(1);
		writeTrace("blob=" + blob);
		long inicioDocumento = 1;
		int finDocumento = (int) blob.length() - 1;
		byte[] bytesDocumento = blob.getBytes(inicioDocumento, finDocumento);
		writeTrace("bytesDocumento=" + bytesDocumento);

		writeTrace("blob.hashCode()=" + blob.hashCode());
		writeTrace("blob.toString()=" + blob.toString());
		writeTrace("blob.toString()=" + blob.getBinaryStream());

		return bytesDocumento;

		// try
		// {
		// ArrayList v1 = new ArrayList();
		// while (r.next())
		// {
		// ArrayList v2 = new ArrayList();
		// for (int i = 1; i <= r.getMetaData().getColumnCount(); i++)
		// {
		// if (r.getString(i) != null)
		// v2.add(r.getString(i).trim());
		// else
		// v2.add("");
		// }
		// v1.add(v2);
		// }
		// return v1;
		// } catch (Exception e)
		// {
		// throw e;
		// }
	}

	/**
	 * ======================================== Nuevos métodos. No son heredados
	 * (sólo se añade funcionalidad nueva , no existen métodos originales
	 * sobre-escritos). ========================================
	 */

	/**
	 * Método que retorna cuántos valores preparados ? hay en la conexión. con
	 * cada llamada a conexionPrepared.set(strValor) se agrega un valor que
	 * posteriormente será reemplazado por cada ? en la SQL.
	 */
	public int getValueCount() {
		return values.size();
	}

	/**
	 * Método que retorna un valor indicado en el índice.
	 * 
	 * @param posicion
	 *            El índice del ArrayList que contiene los valores de la
	 *            conexión.
	 * @return Retorna un String con el valor solicitado.
	 * @author Francisco Gómez Aparicio 99GU4668
	 */
	public String getValue(int posicion) {
		ArrayList myValues = new ArrayList();

		myValues = values;
		DatoDB datoDB = (DatoDB) myValues.get(posicion);
		String strValor = (String) (datoDB.getDato()+"");  // Use "" to force conversion to String. Note that a Blob value will raise a Class Exception
		return strValor;
	}

	/**
	 * Método que retorna el tipo del valor indicado en el índice.
	 * 
	 * @param posicion
	 *            El índice del ArrayList que contiene los valores de la
	 *            conexión.
	 * @return Retorna un String con el valor solicitado.
	 * @author Francisco Gómez Aparicio 99GU4668
	 */
	public int getType(int posicion) {
		ArrayList myValues = new ArrayList();

		myValues = values;
		DatoDB datoDB = (DatoDB) myValues.get(posicion);
		int intTipo = datoDB.getTipo();
		return intTipo;
	}

	/**
	 * Este método recibe una SQL preparada, con los símbolos ? y los reemplaza
	 * por los valores reales que se encuentran en la conexión. De esta manera
	 * es fácil cotejar si el orden de los valores es correcto.
	 * 
	 * @param strSql
	 * @return
	 * @author Francisco Gómez Aparicio 99GU4668
	 */
	public String fillSqlValues(String strSql) {
		String strSqlValuated = strSql;
		StringBuffer sbuValue = new StringBuffer();
		int posValue = 0;
		
//		// Imprimir
//		System.out.println ("--strSqlValuated="+strSqlValuated);
//		String strValues = "";
//		for (Iterator iteValues = values.iterator(); iteValues.hasNext();) {
//			DatoDB datoDBCurrent= (DatoDB)iteValues.next();
//			String strValueCurrent = (String)datoDBCurrent.dato;
//			strValues+=","+strValueCurrent;
//		}
//		System.out.println("strValues="+strValues);

		// Reemplazar los ? por valores
		while (posValue <= this.getValueCount() - 1) {
			// Get value
			if (this.getType(posValue) == 1) { // If String, put quotes around
				sbuValue.append("'" + this.getValue(posValue) + "'");
			} else { // Unknown type, use the same format than String by
				// default
				sbuValue.append("'" + this.getValue(posValue) + "'");
				// //escribeTraza("strValue=" + strValue);
			}
			// Poner valor en la sql
			strSqlValuated = strSqlValuated.toString().replaceFirst("\\?", sbuValue.toString());
			////System.out.println("strSqlValuated="+strSqlValuated);
			posValue++;
		}
		////System.out.println("strSqlValuated="+strSqlValuated);
		return strSqlValuated;
	}

	/**
	 * @param str
	 * @param endIndex
	 * @return
	 */
	protected String truncarCadena(String str, int endIndex) {
		return ((str.length() > endIndex) ? str.substring(0, endIndex) : str);

	}

	protected void writeTrace(String strMessage) {
		// //personal.comun.Logger.warning(strMessage);
		// if (ComunUtil.printDebugTraces)
		// System.out.println(strMessage);
		// if (CTE_ALLOW_CONSOLE)
		if (CTE_ALLOW_CONSOLE){
//			System.out.println(strMessage);
		}
			
		
	}

	protected void Logger_escribeWarning(String strMessage) {
		// if (bago.db.ComunUtil.blnEscribeLogs)
		// bago.db.Logger.warning(strMessage);

		// //bago.db.Logger.warning(strMessage);
	}

	public static void Logger_escribeError(String strMessage) {
		// if (bago.db.ComunUtil.blnEscribeLogs)
		// bago.db.Logger.error(strMessage);

		// //bago.db.Logger.error(strMessage);
	}
	
	
	/**
	 * Este método compone artificialmente un mensaje
	 * exception.printStackTrace(), con el estilo Java. <br>
	 * El objetivo es obtener el mismo mensaje original que imprime Java por
	 * consola, aunque no está comprobado que el mensaje es idéntico al original
	 * en el 100% de los casos. <br>
	 * El objetivo es poder comparar este mensaje con lo almacenado en GEREX.
	 * 
	 * @param exception
	 *            La excepción de la cual queremos obtener el mensaje de error
	 *            que produciría en caso de no envolverla con try-catch.
	 * @return String que contiene el mensaje de error.
	 * @author Francisco Gómez Aparicio 99GU4668
	 */
	public String obtenerMensajeExceptionJavaStyle(Exception exception) {

		String sTipoError = new String(exception.getClass().toString()
				.substring(6));
		// Mensaje que pone el usuario en un throws (cuando el programador
		// fuerza una Exception).
		String str = new String(sTipoError + ": " + exception.getMessage());
		// La pila de errores de Java que indica la(s) línea(s) donde se produce
		// la Exception.
		str = str + "\n" + obtenerStackTraceConAt(exception);

		return str;
	}

	/**
	 * Este método retorna un String con los elementos del Stacktrace de la
	 * Exception, es decir los renglones de información que aparecen debajo del
	 * mensaje de la Exception y que tienen este aspecto:
	 * "    at line N <tipo de error>...". Hay que añadir artificialmente los
	 * "	at " ya que este formato no viene en los valores del objeto StackTrace
	 * original.
	 * 
	 * @param exception
	 *            Objeto Exception del cual queremos obtener el stacktrace
	 * @author Francisco Gomez Aparicio, 99GU4669
	 * @return cadena con los renglones de la Stacktrace, incluyendo las
	 *         tabulaciones y los saltos de línea "\n".
	 * @author Francisco Gómez Aparicio
	 */
	protected static String obtenerStackTraceConAt(Exception exception) {
		StringBuffer strStackConAt = new StringBuffer();
		String strNuevaLinea = "";
		int intElementosAnadidos = 0;
		final String SALTO_LINEA = "\n";
		final String TABULADOR = "\t";
		StackTraceElement stackOriginal[] = exception.getStackTrace();
		int intPosicionStack = stackOriginal.length - 1; // Posición del
		// elemento final
		// (más bajo)
		// de la pila (copiaremos de abajo a arriba)

		/*
		 * ======================================== Obtener la primera línea
		 * (elemento) del StackTrace original
		 * ======================================== Nota: hay que añadir al
		 * comienzo el "at" a menos que lo tenga ya
		 */
		if (stackOriginal[intPosicionStack].toString().indexOf("at") < 0) {
			strNuevaLinea = TABULADOR + "at "
					+ stackOriginal[intPosicionStack].toString();
		} else {
			strNuevaLinea = stackOriginal[intPosicionStack].toString();
		}

		// Si la nueva línea cabe en el strStackTruncada temporal...
		while (intPosicionStack >= 0) {

			/*
			 * ======================================== Agregar la nueva línea
			 * al StackTrace temporal ========================================
			 * (Ojo no añadimos el strTextoMas ya que esto se añadiría sólo en
			 * caso de existir truncado)
			 */
			strStackConAt.append(strNuevaLinea + SALTO_LINEA + strStackConAt);
			intElementosAnadidos++;

			// Apuntar al siguiente elemento (ojo vamos hacia atrás)
			intPosicionStack--;

			/*
			 * ======================================== Obtener la siguiente
			 * línea (elemento) del StackTrace original
			 * ========================================
			 */
			if (intPosicionStack >= 0)
				// Si ya tiene el "at " no es necesario añadirlo...
				if (stackOriginal[intPosicionStack].toString().indexOf("at") < 0) {
					strNuevaLinea = TABULADOR + "at "
							+ stackOriginal[intPosicionStack].toString();
				} else {
					strNuevaLinea = stackOriginal[intPosicionStack].toString();
				}
		}

		return strStackConAt.toString();
	}
	
	
	/**
	 * Receives a separator and a String, and returns the splittered text into
	 * an ArrayList
	 * 
	 * @author Francisco Gómez Aparicio
	 * @since 27-04-2012 13:52
	 * @param strSeparator
	 * @param strTextValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static java.util.ArrayList splitToArrayList(String strSeparator,
			String strTextValue) {
		java.util.ArrayList lisValue = new java.util.ArrayList();

		/**
		 * ======================================== Escape reserved characters
		 * ======================================== If it's a reserved character
		 * in regexes: add "\\" as prefix to the separator to escape it
		 */
		if (strSeparator.equals("?") || strSeparator.equals("*")
				|| strSeparator.equals("+"))
			strSeparator = "\\" + strSeparator;

		// Get array
		String[] array = strTextValue.split(strSeparator);

		// convert Array to Arraylist
		for (int posFila = 0; posFila < array.length; posFila++) {
			////escribeTraza(array[posFila]);
			lisValue.add(array[posFila]);
		}

		return lisValue;
	}

	
	
	
	
	
	//$$$$$INICIO [NEW OBJECT jdbcObject]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	
	public void connectionClose(java.sql.Connection connection, java.sql.ResultSet resultSet)
	throws java.sql.SQLException
	{
		if (resultSet!=null){
			try {
				resultSet.close();
			} catch (java.sql.SQLException se) {
				throw se;
			}
		}
		
		try {
			connection.close();
		} catch (java.sql.SQLException se2) {
			throw se2;
		}
	}
	
	public void connectionClose(java.sql.Connection connection)
	throws java.sql.SQLException
	{
		try {
			connection.close();
		} catch (java.sql.SQLException se2) {
			throw se2;
		}	
	}
	
	
	
	
	/*
	 *  TODO Fran <<<<<< Note: this is a bad practice (E.g. "Cast" validation 
	 *  tool (GISS) will drop 
	 *  down it: weight=9 and "Moderated risk" (yellow)) because of the: 
	 *  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") 
	 *  You must not rely on call a third party class because:
	 *  Sun.*' Classes should not be used. Sun preserves the right to change 
	 *  the structure of those Classes without notice or documentation. Also, 
	 *  sun.* packages are not guaranteed to work on all Java-compatible 
	 *  platforms. These classes will not in general be present on another 
	 *  vendor's Java platform. If your Java program asks for a class 
	 *  "sun.package.Foo" by name, it may fail with ClassNotFoundError, and 
	 *  you will have lost a major advantage of developing in Java.
	 *  -Reference: http://java.sun.com/products/jdk/faq/faq-sun-packages.html
	 *  
	 *  Solution: do not use driver directly, use Windows registry instead
	 */
	public java.sql.Connection connectionOpen_Access(String strFileName)
	throws java.lang.ClassNotFoundException, java.sql.SQLException 
	{
		////return null;
		java.sql.Connection connection = null;
	
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// set this to a MS Access DB you have on your machine
			////String filename = "M:/User/WorkspaceEclipseJunoUsb/Misc/201210210208_BdAccess/mdbTEST.mdb";
			String filename = strFileName;
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database += filename.trim() + ";DriverID=22;READONLY=true}"; // add
																			// on
																			// to
																			// the
																			// end
			// @Potential Exception (ClassNotFoundException)
			// now we can get the connection from the DriverManager
			connection = java.sql.DriverManager.getConnection(database, "", "");
		} catch (java.lang.ClassNotFoundException ce) { // Class.forName() failed
			////System.out.println("Error: " + e);
			throw ce;
		} catch (java.sql.SQLException se) {    // getConnection() failed
			////System.out.println("Error: " + e);
			throw se;
		}
		
		return connection;
	}
	
	
	
	// -References:
	// -"Creating Applications That Use the JDBC API", http://docs.oracle.com/cd/E19316-01/820-4336/beamr/index.html
	public java.sql.Connection connectionOpen_Oracle(String strJndi)
	throws //java.lang.ClassNotFoundException, 
	javax.naming.NamingException, java.sql.SQLException
	{
		// @Potential Exception (NamingException)
		javax.naming.InitialContext ctx = new javax.naming.InitialContext();
		
//		com.sun.appserv.jdbc.DataSource ds = (com.sun.appserv.jdbc.DataSource) 
//		   ctx.lookup("jdbc/MyBase");
		javax.sql.DataSource ds = (javax.sql.DataSource) 
				   ctx.lookup(strJndi);
		
		
		// @Potential Exception (SQLException)
		java.sql.Connection connection = ds.getConnection();
		////java.sql.Connection drivercon = ds.getConnection(con);
		// @Potential Exception (SQLException)
		java.sql.Connection drivercon = ds.getConnection();		
		// Do db operations.
		// Do not close driver connection.
		////con.close(); // return wrapped connection to pool.

		
		return connection;
	}
	
	
	
	
	
	/**
	 * Executes a PreparedStatement. Requires an open Connection.  
	 * Receives a SQL and an ArrayList with the ? values.  Returns the ResultSet.  
	 * 
	 * Usage example:
	 * 
	 	<pre>
		 // Open connection
		java.sql.Connection con = null;
		try {
			con = openConnectionAccess("M:/User/WorkspaceEclipseJunoUsb/Misc/201210210208_BdAccess/mdbTEST.mdb");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 // Create SQL
		ArrayList lisValues = new ArrayList();
		String strSql = "select ID, NOMBRE, APELLIDO, DOCUMENTO, ALTA, BAJA "
						+"from CLIENTE"
						+" where ID >=? "; lisValues.add("1");


		 // Execute and get ResultSet
		java.sql.ResultSet resultSet = null;
		try {
			resultSet = access_executeQuery(con, strSql, lisValues);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 * 
	 * @param connection
	 * @param pstrSql
	 * @param lisValues
	 * @return
	 * @throws java.sql.SQLException
	 */
	public java.sql.ResultSet executeQueryPrepared(java.sql.Connection connection, String pstrSql, java.util.ArrayList lisValues)
	throws java.sql.SQLException
	{
		
		/* ========================================
		 * Build select (it includes ? prepared values)
		 * ========================================
		 * -Prepared values ? will be replaced by preparedStatementSelect.setString()
		 * E.g.: String strSql = "select ID, NOMBRE, APELLIDO, DOCUMENTO, ALTA, BAJA from CLIENTE where ID >=? "; 
		 */
		String strSql = pstrSql;
		
		
		/* ========================================
		 * Do sets 
		 * ========================================
		 * -To the PreparedStatement: add the sets (for each ? in the strSql, do setString() or setDate, etc.) 
		 */
		////preparedStatementSelect.setString(1, "1");
		java.sql.PreparedStatement preparedStatementSelect = null; // There is no point on use a simple Statement (we must avoid NOT-prepared SQLs)
		try {
			preparedStatementSelect = connection.prepareStatement(strSql);
		} catch (java.sql.SQLException se) {
			throw se;
		}
		/**
		 * Este método hace los setString, setInt, etc. de golpe ya que los
		 * tipos se van guardando en un array de tipos.
		 */
		componerQuery_ArrayList(preparedStatementSelect, lisValues);
		
				



		// Execute PreparedStatement:
		// ----------
		// @@Potential Exception (java.sql.SQLException)
		preparedStatementSelect.execute();

		
		// Get ResultSet:
		// ----------
		java.sql.ResultSet rs =  null;
		// @@Potential Exception (java.sql.SQLException)
		rs = preparedStatementSelect.getResultSet();

		
//		// get results:
//		// ----------
//		////resultados(resultSet);
//		
//		// @@Potential Exception (java.sql.SQLException)
//		for (Iterator iterator = lisValues.iterator(); iterator.hasNext();) {
//			Object oFieldCurrent = (Object) iterator.next();
//
//		}
		
		
		return rs;
		
	} // End of method
	
	
	
	
	
	
	/*
	 * ==================== [Fran] Hacer todos los SET. ====================
	 * Para poder hacer de golpe todos los SET de una query preparada, lo que
	 * hacemos es ir guardando por cada ? de la SQL su tipo en ConexionDB.java,
	 * en su atributo pstmt, que es tipo "PreparedStatement" (la clase con los
	 * métodos ejecutarUpdate(), ejecutarQuery(), ejecutarProcedimiento(), etc.)
	 */
	@SuppressWarnings("unchecked")
	protected void componerQuery_ArrayList(java.sql.PreparedStatement pstmt, java.util.ArrayList lisRawValues) 
	throws java.sql.SQLException 
	{
//		// Get values, as ConexionDB does (just in case)
//		ArrayList values = getValues(lisRawValues);
//		writeTrace("values.toString()=" + values.toString());
		
		/* 
		 * ========================================
		 * Do sets directly
		 * ========================================
		 * References:
		 * -"Datatype Mappings", "Default Mappings Between SQL Types and Java Types", http://docs.oracle.com/cd/A97335_02/apps.102/a83724/basic3.htm
		 */
		int intTipo = 0;
		int intQuestionMarkCounter = 1;  // Count ? symbols in the SQL
		for (int intLisrow = 0; intLisrow < lisRawValues.size(); intLisrow++) {
			Object objCurrentValue = lisRawValues.get(intLisrow);
			
			if (objCurrentValue == null) {
				writeTrace("Is NULL!");
//				DatoDB datoDB = new DatoDB(1, "");
//				values.add(datoDB);				
				pstmt.setString(intQuestionMarkCounter++, "");
			} else { // If not null value...
				

				
				
				// If java.util.Date type...
				if (objCurrentValue.getClass().getName().equalsIgnoreCase("java.util.Date")){
					writeTrace("Is Date!");
					// TODO <<<<< Fran establecer un formato primero validando que la fecha objCurrentValue es válida
					pstmt.setString(intQuestionMarkCounter++, (String)objCurrentValue);  // A text representing the date (to simply display it on the screen)					
					
				// If java.lang.Integer type...
				} else if (objCurrentValue.getClass().getName().equalsIgnoreCase("java.lang.Integer")){
					writeTrace("Is Date!");
					pstmt.setString(intQuestionMarkCounter++, (String)objCurrentValue);  // A text representing the date (to simply display it on the screen)
					
//				// TODO Fran <<<<<<< No tiene sentido en un set ? no se pondrá un blob
//				// If BLOB type...
//				if (objCurrentValue.getClass().getName().equalsIgnoreCase("oracle.sql.BLOB")){
//					writeTrace("Is BLOB!");
//					pstmt.setString(intQuestionMarkCounter++, "'A BLOB value'");  // A text representing the blob (to simply display it on the screen)					
					
				// If String type...
				} else if (objCurrentValue.getClass().getName().equalsIgnoreCase("java.lang.String")){
						writeTrace("Is String!");
						pstmt.setString(intQuestionMarkCounter++, (String)objCurrentValue);				
				
				// If is a not an already implemented type:
				} else {
					java.sql.SQLException se = new java.sql.SQLException("type='"+objCurrentValue.getClass().getName()+"' not implemented yet.");
					throw se;
				}  // End types
			} // End "if not null"
		}  // End for


		
//		int cnt = 1;
//		for (int i = 0; i < values.size(); i++) {
//			
//		
//			DatoDB ddb = (DatoDB) values.get(i);
//
//			switch (ddb.getTipo()) {
//			case DatoDB.DATOSTRING:
//				pstmt.setString(cnt++, (String) ddb.getDato());
//				break;
//			case DatoDB.DATOINT:
//				pstmt.setInt(cnt++, ((Integer) ddb.getDato()).intValue());
//				break;
//			case DatoDB.DATOLONG:
//				pstmt.setLong(cnt++, ((Long) ddb.getDato()).intValue());
//				break;
//			}
//		}
		
	}



	
	
	//$$$$$FIN [NEW OBJECT jdbcObject]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	
	

	// ##[fin_cambio_Fran]
	
	
	

}

// Codigo original agosto-2011:

// package personal.db;
//
// import java.sql.CallableStatement;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Types;
// import java.util.ArrayList;
// import java.util.Hashtable;
//
// import javax.naming.Context;
// import javax.naming.InitialContext;
// import javax.naming.NamingException;
// import javax.sql.DataSource;
//
// import personal.comun.Logger;
// import personal.comun.Util;
// import personal.exceptions.DBConexionException;
// import personal.exceptions.DBQueryException;
// import personal.exceptions.PersonalException;
// import personal.seguridad.Usuario;
//
// public class ConexionDB
// {
//
// protected static DataSource ds = null;
// protected Connection conexion = null;
// public int total = 0;
// protected PreparedStatement pstmt;
// // protected int cnt;
// protected boolean autoCommit = true;
// protected ArrayList values = null;
// protected boolean queryEnCurso = false;
//	
// public ConexionDB(Usuario usuario) throws DBConexionException
// {
// try
// {
// if (ds == null)
// {
// //
//				
// // HttpServletRequest request =
// (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
// // String personalds =
// request.getSession().getServletContext().getInitParameter("personalds");
//				
// Hashtable env = new Hashtable();
// env.put(Context.INITIAL_CONTEXT_FACTORY,
// "com.ibm.websphere.naming.WsnInitialContextFactory");
// Context ctx = new InitialContext(env);
//	         			
// ds = (DataSource) ctx.lookup("java:comp/env/jdbc/auditorias");
//				
// values = new ArrayList();
//				
// }
//					
// conexion = ds.getConnection();
//			
//			
// } catch (Exception e)
// {
//						
// throw new
// DBConexionException("Error obteniendo datasource o creando conexión de datasource",
// usuario);
//		
//			
// }
// }
//
//
// public ConexionDB(Usuario usuario, boolean autoCommit) throws
// DBConexionException
// {
// this(usuario);
// this.autoCommit = autoCommit;
// try
// {
// if(!autoCommit)
// conexion.setAutoCommit(false);
// }catch(SQLException e)
// {
// throw new DBConexionException("Error fijando autocommit", usuario);
// }
// }
//	
// public void commit(Usuario usuario)throws DBConexionException
// {
// if(autoCommit)
// Logger.warning("Warning: la conexión tiene el autocommit activado");
// try
// {
// conexion.commit();
// } catch (SQLException e)
// {
// throw new DBConexionException("Error haciendo commit", usuario);
// }
// }
//	
// public void rollback(Usuario usuario)throws DBConexionException
// {
// try
// {
// conexion.rollback();
// } catch (SQLException e)
// {
// throw new DBConexionException("Error haciendo rollback", usuario);
// }
// }
//	
// public void desconectarDB(Usuario usuario)throws DBConexionException
// {
// try
// {
// conexion.close();
// } catch (SQLException e)
// {
// Logger.error("Error en desconexión");
// throw new DBConexionException("Error en desconexión", usuario);
// }
// }
//
// public void iniciarQuery(Usuario usuario) throws DBQueryException
// {
// if(queryEnCurso == true)
// {
// //Tira una query exception porque el problema no está
// //en la conexión, sino en la manera como se hace la consulta
// throw new
// DBQueryException("Error iniciando consulta. Las consultas no pueden anidarse",
// usuario);
// }
// queryEnCurso = true;
// values = new ArrayList();
// }
//	
// public String set(long j)
// {
// values.add(new DatoDB(DatoDB.DATOLONG, new Long(j)));
// return "?";
// }
//	
//	
// public String set(int j)
// {
// values.add(new DatoDB(DatoDB.DATOINT, new Integer(j)));
//		
// return "?";
// }
//	
// public String set(String str)
// {
// values.add(new DatoDB(DatoDB.DATOSTRING, str));
// return "?";
// }
//	
// public String set(String str, int endIndex)
// {
// str = Util.truncate(str, endIndex);
// return set(str);
// }
//	
//	
// public void ejecutarUpdate(String sql, Usuario usuario) throws
// DBConexionException, DBQueryException
// {
// if(!queryEnCurso)
// {
// throw new DBQueryException("Error - query no inicializada " + sql, usuario);
// }
//		
// try
// {
// pstmt = conexion.prepareStatement(sql);
// }catch(SQLException e)
// {
// throw new DBConexionException("Error al ejecutar update " + sql, usuario);
// }finally
// {
// queryEnCurso = false;
// }
//		
//		
// //Si el error da aquí, lo más probable es que sea una DBQueryException
// try
// {
// componerQuery();
// pstmt.executeUpdate();
//
// }catch(SQLException e)
// {
//			
// PersonalException pe = new
// PersonalException(e.getMessage(),"ejecutarUpdate");
// pe.rastroYRedireccion();
// throw new DBQueryException("Error al ejecutar update " + sql, usuario);
//			
// }finally
// {
// queryEnCurso = false;
// }
// }
//	
// public ArrayList ejecutarQuery(String sql, Usuario usuario) throws
// DBConexionException, DBQueryException
// {
// if(!queryEnCurso)
// {
// throw new DBQueryException("Error - query no inicializada " + sql, usuario);
// }
//		
// ResultSet r = null;
// try
// {
// pstmt = conexion.prepareStatement(sql);
// }catch(SQLException e)
// {
//		
// throw new Exception("Error al ejecutar update " + sql, usuario);
//		
// }finally
// {
// queryEnCurso = false;
// }
//			
//		
// try
// {
// componerQuery();
// r = pstmt.executeQuery();
// ArrayList var1 = resultados(r);
// r.close();
// pstmt.close();
// return var1;
// }catch(SQLException e)
// {
// throw new DBQueryException("Error SQL al ejecutar query " + sql +"\n"
// +e.getMessage(), usuario);
// }catch(Exception e)
// {
// throw new DBQueryException("Error indeterminado al ejecutar update " +
// pstmt.toString(), usuario);
// }
// finally
// {
// queryEnCurso = false;
// }
// }
//	
//	
// public String ejecutarProcedimiento(String sql, Usuario usuario) throws
// DBConexionException, DBQueryException
// {
//		
// if(!queryEnCurso)
// {
// throw new DBQueryException("Error - query no inicializada " + sql, usuario);
// }
//		
// String errors = "";
// CallableStatement callstmt = null;
// try
// {
// callstmt = conexion.prepareCall(sql);
//			
// }catch(SQLException e)
// {
//		
// throw new DBConexionException("Error al ejecutar update " + sql, usuario);
//		
// }finally
// {
// queryEnCurso = false;
// }
//			
//		
// try
// {
// componerCall(callstmt);
// callstmt.execute();
// errors = callstmt.getString(1);
//			
// callstmt.close();
// return errors;
//			
// }catch(SQLException e)
// {
// throw new DBQueryException("Error SQL al ejecutar query " + sql +"\n"
// +e.getMessage(), usuario);
// }catch(Exception e)
// {
// throw new DBQueryException("Error indeterminado al ejecutar update " +
// callstmt.toString(), usuario);
// }
// finally
// {
// queryEnCurso = false;
// }
// }
//	
// private void componerQuery()throws SQLException
// {
// int cnt = 1;
// for(int i = 0; i < values.size(); i++)
// {
// DatoDB ddb = (DatoDB)values.get(i);
//		 
// switch(ddb.getTipo())
// {
// case DatoDB.DATOSTRING:
// pstmt.setString(cnt++, (String)ddb.getDato());
// break;
// case DatoDB.DATOINT:
// pstmt.setInt(cnt++, ((Integer)ddb.getDato()).intValue());
// break;
// case DatoDB.DATOLONG:
// pstmt.setLong(cnt++, ((Long)ddb.getDato()).intValue());
// break;
// }
// }
// }
//	
// private void componerCall(CallableStatement callstmt)throws SQLException
// {
// int cnt = 2;
// callstmt.registerOutParameter(1, Types.VARCHAR);
//	   
// for(int i = 0; i < values.size(); i++)
// {
// DatoDB ddb = (DatoDB)values.get(i);
//		 
// switch(ddb.getTipo())
// {
// case DatoDB.DATOSTRING:
// callstmt.setString(cnt++, (String)ddb.getDato());
// break;
// case DatoDB.DATOINT:
// callstmt.setInt(cnt++, ((Integer)ddb.getDato()).intValue());
// break;
// case DatoDB.DATOLONG:
// callstmt.setLong(cnt++, ((Long)ddb.getDato()).intValue());
// break;
// }
// }
// }
//	
// private ArrayList resultados(ResultSet r) throws Exception
// {
// try
// {
// ArrayList v1 = new ArrayList();
// while (r.next())
// {
// ArrayList v2 = new ArrayList();
// for (int i = 1; i <= r.getMetaData().getColumnCount(); i++)
// {
// if (r.getString(i) != null)
// v2.add(r.getString(i).trim());
// else
// v2.add("");
// }
// v1.add(v2);
// }
// return v1;
// } catch (Exception e)
// {
// throw e;
// }
// }
//	
//
// public static String getJNDI_EXCEPCIONES() {
//
// String urlEXCEPCIONES = null;
//
// try {
// java.util.Hashtable env = new java.util.Hashtable();
// env.put(Context.INITIAL_CONTEXT_FACTORY,
// "com.ibm.websphere.naming.WsnInitialContextFactory");
//			
// //Context ctex = new InitialContext(env);
// Context ctex = new InitialContext();
//
// // Accedemos a la referencia
// urlEXCEPCIONES = ctex.lookup("java:comp/env/GEREX").toString();
//			
// } catch (NamingException ex) {
// Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
// } catch (Exception ex) {
// Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
// }
// return urlEXCEPCIONES;
// }
// }








































///*
//* <<<<< OJO PEGO AQUÍ EL ConexionDB original (cambiando sólo
//* ruta de imports) que hay en SWPERSONAL workspace:
//* D:\desarrollo\PRODUCTO\IDE\RSA70\wks_personal\SWPERSONAL\src\personal\db\datos\ConexionDB.java 
//* */
//
//protected package bagora.datos.dao.artabro;  // <<<< Observar: protected
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Hashtable;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
////import bago.db.ComunUtil;
////import bago.db.Logger;
//////import bago.batch.exception.DBConexionException;
//////import bago.batch.exception.DBQueryException;
//////import bago.batch.exception.PersonalException;
////<<<<<<<<<FRAN INICIO
//////import personal.comun.Util;
////<<<<<<<<<FRAN FIN
//import bagora.batch.exception.Usuario;
//
//public class ConexionDB
//{
//
//	protected static DataSource ds = null;
//	protected Connection conexion = null;
//	public int total = 0;
//	protected PreparedStatement pstmt;
////	protected int cnt;
//	protected boolean autoCommit = true;
//	protected ArrayList values = null;
// protected boolean queryEnCurso = false;
//	
// /** DEPRECATED. En desuso, usar el constructor equivalente: 
//  * ConexionDB(Usuario usuario, String strJndiDatasource, boolean blnAutocommit)
//  * Esta conexión tiene varios problemas:
//  *   -La conexión usa un JNDI de dataSource fijo (no podemos elegir la BD a  conectar), 
//  *   -Este constructor da por hecho que cada operación hará un commit automáticamente,  
//  *   sin posibilidad de hacer un rollback en caso de que en una serie de operaciones  
//  *   atómicas pueda fallar una de ellas y por tanto sea necesario deshacer  
//  *   toda la serie de operaciones.  
//  *   -Obliga a usar escritura de logs, cuando no es tarea de una clase de acceso a datos  
//  *   y por tanto debería mantenerse fuera de esta clase.  
//  * @param usuario
//  * @throws DBConexionException
//  */
// public ConexionDB(Usuario usuario) throws Exception
//	{
//		try
//		{
//			if (ds == null)
//			{
//		//		
//				
//			   // HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//			   // String personalds = request.getSession().getServletContext().getInitParameter("personalds");
//			
//				Hashtable env = new Hashtable();
//	        	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
//	        	Context ctx = new InitialContext(env);
//     	
//			    ////ds = (DataSource) ctx.lookup("java:comp/env/jdbc/auditorias");
//	        	
//	        	//[ConexionSource]
//	        	//String strDatasource = new String(ComunUtil.JNDI_DATASOURCE);
//	        	String strDatasource = new String("jdbc/oracle/dsAgora");  // AGORA
//	        	
//	        	//System.out.println("strDatasource=\""+strDatasource+"\"");
//			     //ds = (DataSource) ctx.lookup("java:comp/env/jdbc/auditorias");    // Aplicación SWPERSONAL: Nombre JNDI para web.xml y WAS (petición DESA, PRE, ...). Ej. ctx.lookup("java:comp/env/jdbc/auditorias");
//	        	ds = (DataSource) ctx.lookup(strDatasource);    // Aplicación SWPERSONAL: Nombre JNDI para web.xml y WAS (petición DESA, PRE, ...). Ej. ctx.lookup("java:comp/env/jdbc/auditorias");
//			    ////System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ConexionDB(Usuario usuario) ->lookup realizado con éxito");
//				
////				values = new ArrayList();
//				
//			}
//					
//			values = new ArrayList();  // Mover fuera del if o NullPointerException
//			
//			conexion = ds.getConnection();
//			
//			
//		} catch (Exception e)
//		{
//			// INICIO FRAN
//			Exception pe = new Exception("Error obteniendo datasource o creando conexión de datasource",null);
//			////pe.rastroYRedireccion();
//			throw new Exception("Error obteniendo datasource o creando conexión de datasource", null);
//			// FIN FRAN
//		
//			
//		}
//	}
// 
// 
//	/** DEPRECATED. En desuso, usar el constructor equivalente: 
//  * ConexionDB(Usuario usuario, String strJndiDatasource, boolean blnAutocommit)
//	 * @param usuario
//	 * @param autoCommit Si está a false no se ejecutará el commit automático, y habrá que hacerlo llamando al método commit(Usuario). 
//	 * @throws DBConexionException
//	 * Fran. Este constructor crea un objeto ConexionDB que abre la conexión y permite ser usado para ejecutar 
//	 * queries a la base de datos. Si autoCommit está a false no se ejecutará el commit automático, y habrá que 
//	 * hacerlo llamando al método commit(Usuario) o rollback(Usuario) si se quiere abortar. Ésto permite 
//	 * atomicidad (comprometer o abortar una cadena de modificaciones)
//	 */
//	public ConexionDB(Usuario usuario, boolean autoCommit) throws Exception
//	{
//		this(usuario);
//		this.autoCommit = autoCommit;
//		try
//		{
//			if(!autoCommit)
//				conexion.setAutoCommit(false);
//		}catch(SQLException e)
//		{
//			throw new Exception("Error fijando autocommit");
//		}
//	}
//	
//	
//	/** 
//	 * Constructor recomendado. Retorna un objeto conexión, se le puede pasar el argumento autoCommit para  
//	 * establecerlo o desactivarlo (activado por defecto) y el JNDI para el dataSource. 
//	 * Observar que: 
//	 *     *El JNDI del dataSource se pasa como argumento (así podremos elegir conectar a URL's de conexión  
//	 * diferentes). 
//	 *     *También se debe indicar el argumento blnAutocommit, si está a true cada operación se comprometerá   
//	 * automáticamente, en caso de que queramos la posibilidad de ejecutar .rollback() entonces debemos 
//	 * establecer blnAutocommit=false 
//	 * @param usuario
//	 * @param strJndiDatasource
//	 * @param blnAutocommit
//	 * @throws DBConexionException
//	 */
//	public ConexionDB(Usuario usuario, String strJndiDatasource, boolean blnAutocommit) throws Exception {
//		try
//		{
//			if (ds == null)
//			{
//			
//				Hashtable env = new Hashtable();
//	        	////env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
//				env.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
//	        	Context ctx = new InitialContext(env);
//     	
//	        	/* ========================================
//	        	 * Establecer el JNDI (nos llega como argumento)
//	        	 * ========================================
//	        	 * Ej. Para GestionPersonal sería: "jdbc_auditorias"  -> ctx.lookup("java:comp/env/jdbc_auditorias")
//	        	 *     Para SWPERSONAL sería:      "jdbc--auditorias" -> ctx.lookup("java:comp/env/jdbc--auditorias")
//	        	 * Eso buscaría el JNDI "jdbc/auditorias" que contendrá la URL de conexión. 
//	        	 */
//	        	ds = (DataSource) ctx.lookup(strJndiDatasource);    
//	
//
//				
//				values = new ArrayList();
//				
//			}
//					
//			conexion = ds.getConnection();
//			
//			
//		} catch (Exception e)
//		{
//			throw new Exception("Error obteniendo datasource o creando conexión de datasource");
//		}
//	}	
//	
//	public void commit(Usuario usuario)throws Exception
//	{
//		if(autoCommit)
//			Logger_escribeWarning("Warning: la conexión tiene el autocommit activado");
//			////Logger.warning("Warning: la conexión tiene el autocommit activado");
//			
//		
//		try 
//		{
//			conexion.commit();
//		} catch (SQLException e) 
//		{
//			throw new Exception("Error haciendo commit");
//		}
//	}
//	
//	public void rollback(Usuario usuario)throws Exception
//	{
//		try 
//		{
//			conexion.rollback();
//		} catch (SQLException e) 
//		{
//			throw new Exception("Error haciendo rollback");
//		}
//	}
//	
//	public void desconectarDB(Usuario usuario)throws Exception
//	{
//		try
//		{
//			conexion.close();
//		} catch (SQLException e)
//		{
//			////Logger.error("Error en desconexión");
//			Logger_escribeError("Error en desconexión");
//			throw new Exception("Error en desconexión");
//		}
//	}
//
//	public void iniciarQuery(Usuario usuario) throws Exception
//	{
//		if(queryEnCurso == true)
//		{
//			//Tira una query exception porque el problema no está 
//			//en la conexión, sino en la manera como se hace la consulta
//			throw new Exception("Error iniciando consulta. Las consultas no pueden anidarse");
//		}
//		queryEnCurso = true;
//		values = new ArrayList();
//	}
//	
//	public String set(long j)
//	{
//		values.add(new DatoDB(DatoDB.DATOLONG, new Long(j)));
//		return "?";	
//	}
//	
//	
//	public String set(int j)
//	{
//		values.add(new DatoDB(DatoDB.DATOINT, new Integer(j)));
//		
//		return "?";
//	}
//	
//	public String set(String str)
//	{
//		values.add(new DatoDB(DatoDB.DATOSTRING, str));
//		return "?";
//	}
//	
//	public String set(String str, int endIndex)
//	{
////		<<<<<<<<<FRAN INICIO		
//		//str = Comun.truncate(str, endIndex);
//		// Sólo una llamada al método, no merece la pena hacer referencia a la clase 
//		// ComunUtil.java-> str = ComunUtil.truncate(str, endIndex);
//		str = truncarCadena(str, endIndex);
////		<<<<<<<<<FRAN FIN 		
//		
//		return set(str);
//	}
//	
//	
//	// Original. Deprecated, usa muchas referencias y Exceptions personalizadas, mejor usar "executePreparedUpdate".
//	public void ejecutarUpdate(String sql, Usuario usuario) throws Exception
//	{
//		if(!queryEnCurso)
//		{
//			Exception DBQE = new Exception("Error - query no inicializada " + sql);
//			Exception Pe= new Exception("Error ejecutar update",null);
//			////Pe.rastroYRedireccion();
//			////throw DBQE;
//		}
//		
//		try
//		{
//			pstmt = conexion.prepareStatement(sql);
//		}catch(SQLException e)
//		{
//			Exception Pe= new Exception("Error ejecutar update", null);
//			////Pe.rastroYRedireccion();
//			throw new Exception("Error al ejecutar update " + sql, null);
//		}finally
//		{
//			queryEnCurso = false;
//		}
//		
//		
//		//Si el error da aquí, lo más probable es que sea una DBQueryException
//		try
//		{
//			componerQuery();
//			pstmt.executeUpdate();
//
//		}catch(SQLException e)
//		{
//			
//			Exception pe = new Exception(e.getMessage()+"ejecutarUpdate");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error al ejecutar update " + sql);
//			
//		}finally
//		{
//			queryEnCurso = false;
//		}
//	}
//	
//	// Recomendada, no usa tantas referencias ni excepciones personalizadas  
//	public void executePreparedUpdate(String sql) 
//	//throws Exception, DBQueryException
//	throws Exception
//	{
//		if(!queryEnCurso)
//		{
////			DBQueryException DBQE = new DBQueryException("Error - query no inicializada " + sql, usuario);
////			PersonalException Pe= new PersonalException(DBQE.getMessage(),"ejecutarUpdate");
////			Pe.rastroYRedireccion();
////			throw DBQE;
//			throw new Exception("Query not initiated");
//		}
//		
//		try
//		{
//			pstmt = conexion.prepareStatement(sql);
//		}catch(SQLException e)
//		{
//			Exception Pe= new Exception(e.getMessage()+"ejecutarUpdate");
//			////pe.rastroYRedireccion();
//			////throw new DBConexionException("Error al ejecutar update " + sql, usuario);
//			throw new SQLException("Error executing update, query: " + fillSqlValues(sql));
//		}finally
//		{
//			queryEnCurso = false;
//		}
//		
//		
//		//Si el error da aquí, lo más probable es que sea una DBQueryException
//		try
//		{
//			componerQuery();
//			pstmt.executeUpdate();
//
//		}catch(SQLException e)
//		{
//			
//			Exception pe = new Exception(e.getMessage()+"ejecutarUpdate");
//			////pe.rastroYRedireccion();
//			////throw new DBQueryException("Error al ejecutar update " + sql, usuario);
//			throw new SQLException("Error executing update, query: " + fillSqlValues(sql));
//			
//		}finally
//		{
//			queryEnCurso = false;
//		}
//	}
//
//	
//	// Original. Muchas dependencias. Si falla "conexion.prepareStatement(sql)"  en vez de hacer throw SQLException se hace con DBConexionException
//	public ArrayList ejecutarQuery(String sql, Usuario usuario) throws Exception, Exception
//	{
//		if(!queryEnCurso)
//		{
//			Exception Pe= new Exception("Error - query no inicializada " + sql+"ejecutarUpdate");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error - query no inicializada " + sql);
//		}
//		
//		ResultSet resultSet = null;
//		try
//		{
//			pstmt = conexion.prepareStatement(sql);
//		}catch(SQLException e)
//		{
//			Exception pe = new Exception(e.getMessage()+"ejecutarQuery");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error al ejecutar Query " + sql);
//		
//		}finally
//		{
//			queryEnCurso = false;
//		}	
//			
//		
//		try
//		{
//			/**
//			 * Este método hace los setString, setInt,  etc. de golpe ya que 
//			 * los tipos se van guardando en un array de tipos.
//			 */			
//			componerQuery();
//			resultSet = pstmt.executeQuery();
//			ArrayList arlisListado = resultados(resultSet);
//			resultSet.close();
//			pstmt.close();
//			return arlisListado;
//		}catch(SQLException e)
//		{
//			Exception pe = new Exception("Error SQL al ejecutar query " + sql +"\n" +e.getMessage()+"ejecutarQuery");
//			////pe.rastroYRedireccion();
//			
//			throw new Exception("Error SQL al ejecutar query " + sql +"\n" +e.getMessage());
//		}catch(Exception e)
//		{
//			Exception pe = new Exception("Error indeterminado al ejecutar query " + pstmt.toString()+"ejecutarQuery");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error indeterminado al ejecutar query " + pstmt.toString());
//		}
//		finally
//		{
//			queryEnCurso = false;
//		}
//	}
//	
//	
//	/** @@Fran. Variación del método .ejecutarQuery. 
//	 * Este método permite: 
//	 *   -Sólo se hace throws de: SQLException, DBQueryException (DBConexionException no se necesita)
//	 *   -Ser independiente del método .rastroYRedireccion (propio de JSF)
//	 *   -Diferenciar excepción
//	 * @param sql
//	 * @param usuario
//	 * @return
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public ArrayList ejecutarQuery_reusable(String sql, Usuario usuario) throws SQLException, Exception
//	{
//		if(!queryEnCurso)
//		{
//			throw new Exception("Error - query no inicializada " + sql);
//		}
//		
//		ResultSet resultSet = null;
//		try
//		{
//			pstmt = conexion.prepareStatement(sql);
//		}catch(SQLException e)
//		{
//		
//			throw new SQLException("Error al preparar la query. SQL= " + sql);
//		
//		}finally
//		{
//			queryEnCurso = false;
//		}	
//			
//		
//		try
//		{
//			/**
//			 * Este método hace los setString, setInt,  etc. de golpe ya que 
//			 * los tipos se van guardando en un array de tipos.
//			 */			
//			componerQuery();  // Añadir los SET (sustituir en conexionDB los valores ? por lo que hay en el ArrayList this.values)
//			System.out.println("sql no valuada="+sql); //TODO Fran <<<<<< quitar ésto
//			System.out.println("Donde conexionDB.values="+this.values); //TODO Fran <<<< quitar ésto
//			resultSet = pstmt.executeQuery();
//			ArrayList arlisListado = resultados(resultSet);
//			//ArrayList arlisListado = recordsetToArrayList(resultSet);  //<<<< AÚN FALLA, MIRARLO. como resultados() pero evita nullPointers
//			resultSet.close();
//			pstmt.close();
//			return arlisListado;
//		}catch(SQLException e)
//		{
//			throw new Exception("Error SQL al ejecutar query " + sql +"\n" +e.getMessage());
//		}catch(Exception e)
//		{
//			throw new Exception("Error indeterminado al ejecutar query " + pstmt.toString());
//		}
//		finally
//		{
//			queryEnCurso = false;
//		}
//	}
//	
//	
//	public String ejecutarProcedimiento(String sql, Usuario usuario) throws Exception, Exception
//	{
//		
//		if(!queryEnCurso)
//		{	
//			Exception pe = new Exception("Error - query no inicializada " + sql+"ejecutarProcedimiento");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error - query no inicializada " + sql);
//		}
//		
//		String errors = "";
//		CallableStatement callstmt = null;
//		try
//		{
//			callstmt = conexion.prepareCall(sql);
//			
//		}catch(SQLException e)
//		{
//			Exception pe = new Exception("Error - query no inicializada " + sql + "\n"+ e.getMessage()+"ejecutarProcedimiento");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error al ejecutar update " + sql);
//		
//		}finally
//		{
//			queryEnCurso = false;
//		}	
//			
//		
//		try
//		{
//			componerCall(callstmt);
//			callstmt.execute();
//			errors = callstmt.getString(1);    
//			
//			callstmt.close();
//			return errors;
//			
//		}catch(SQLException e)
//		{
//			Exception pe = new Exception("Error SQL al ejecutar query  " + sql + "\n"+ e.getMessage()+"ejecutarProcedimiento");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error SQL al ejecutar query " + sql +"\n" +e.getMessage());
//		}catch(Exception e)
//		{
//			Exception pe = new Exception("Error indeterminado al ejecutar update  " + sql + "\n"+ e.getMessage()+"ejecutarProcedimiento");
//			////pe.rastroYRedireccion();
//			throw new Exception("Error indeterminado al ejecutar update " + callstmt.toString());
//		}
//		finally
//		{
//			queryEnCurso = false;
//		}			
//	}	
//	
//	/* 
//	 * ====================
//	 * [Fran] Hacer todos los SET.
//	 * ==================== 
//	 * Para poder hacer de golpe todos los SET de una query preparada, lo que hacemos es ir guardando 
//	 * por cada ? de la SQL su tipo en ConexionDB.java, en su atributo pstmt, que es tipo 
//	 * "PreparedStatement" (la clase con los métodos ejecutarUpdate(), ejecutarQuery(), 
//	 *  ejecutarProcedimiento(), etc.) 
//	 */
//	private void componerQuery()throws SQLException
//	{
//	   int cnt = 1;
//	   for(int i = 0; i < values.size(); i++)
//	   {
//		 DatoDB ddb = (DatoDB)values.get(i);
//		 
//		 switch(ddb.getTipo())
//		 {
//		 case DatoDB.DATOSTRING:
//			 pstmt.setString(cnt++, (String)ddb.getDato());
//			 break;
//		 case DatoDB.DATOINT:
//			 pstmt.setInt(cnt++, ((Integer)ddb.getDato()).intValue());
//			 break;
//		 case DatoDB.DATOLONG:
//			 pstmt.setLong(cnt++, ((Long)ddb.getDato()).intValue());
//			 break;
//		 }
//	   }
//	}
//	
//	private void componerCall(CallableStatement callstmt)throws SQLException
//	{
//	   int cnt = 2;
//	   callstmt.registerOutParameter(1, Types.VARCHAR);
//	   
//	   for(int i = 0; i < values.size(); i++)
//	   {
//		 DatoDB ddb = (DatoDB)values.get(i);
//		 
//		 switch(ddb.getTipo())
//		 {
//		 case DatoDB.DATOSTRING:
//			 callstmt.setString(cnt++, (String)ddb.getDato());
//			 break;
//		 case DatoDB.DATOINT:
//			 callstmt.setInt(cnt++, ((Integer)ddb.getDato()).intValue());
//			 break;
//		 case DatoDB.DATOLONG:
//			 callstmt.setLong(cnt++, ((Long)ddb.getDato()).intValue());
//			 break;
//		 }
//	   }
//	}
//	
//	/* DEPRECATED, USAR recordsetToArrayList
//	 * ========================================
//	 * Convertir el recordset en un ArrayList bidimensional
//	 * ========================================
//	 * De este modo será fácil manejar la información en memoria, una vez se haya cerrado la conexión con la BD. 
//	 * */
//	private ArrayList resultados(ResultSet resultSet) throws Exception    // Llega un conjunto de registros
//	{
//		// Aquí guardaremos el ResultSet en forma de ArrayList bidimensional
//		ArrayList arlisListado = new ArrayList();   
//		try
//		{
//			// Por cada registro, guardamos su valor en el ArrayList bidimensional en la columna correspondiente
//			while (resultSet.next()) {   // Tomar el siguiente registro 
//				// Aquí guardaremos un registro del recordset en formato ArrayList				
//				ArrayList arlisRegistro = new ArrayList();  
//				
//				 //Por cada campo del registro actual, guardarlo en el ArrayList
//				for (int intPosicionCampo = 1; intPosicionCampo <= resultSet.getMetaData().getColumnCount(); intPosicionCampo++){
//					Object obj1 = null;
//					
//					////obj1 = resultSet.getBlob(intPosicionCampo);
//					obj1 = resultSet.getObject(intPosicionCampo);
//					if (obj1 == null){
//						escribeTraza("Es nulo");
//						arlisRegistro.add("");
//					} else {
//						escribeTraza("No es nulo");
//						if (resultSet.getObject(intPosicionCampo).getClass().getName().equalsIgnoreCase("oracle.sql.BLOB")) {
//							escribeTraza("ES BLOB!");
//							arlisRegistro.add(obj1);
//						} else if (resultSet.getObject(intPosicionCampo).getClass().getName().equalsIgnoreCase("java.lang.String")){
//							escribeTraza("Es String");
//							arlisRegistro.add(resultSet.getString(intPosicionCampo).trim());
//						} else {   // Otros tipos como long etc.
//							////throw new Exception("Excepción de usuario, tipo no contemplado");
//							arlisRegistro.add(resultSet.getString(intPosicionCampo).trim());
//						}
//					}
//				}
//				arlisListado.add(arlisRegistro);				
//				
//			}
//			return arlisListado;
//		} catch (Exception e) {
//			Exception pe = new Exception(e.getMessage()+"ConexionDB.resultados");
//			////pe.rastroYRedireccion();
//			throw e;
//		}
//	}
//	
//	
//	/* Recomendado. Creado a partir de resultados(ResultSet resultSet) pero evita nullPointers
//	 * ========================================
//	 * Convertir el recordset en un ArrayList bidimensional
//	 * ========================================
//	 * De este modo será fácil manejar la información en memoria, una vez se haya cerrado la conexión con la BD. 
//	 * */
//	private ArrayList recordsetToArrayList(ResultSet resultSet) 
//	////throws Exception    // Llega un conjunto de registros
//	{
//		// Aquí guardaremos el ResultSet en forma de ArrayList bidimensional (array de arrays: así tendremos filas y columnas)
//		ArrayList arlisListado = new ArrayList();
//		
//		// Si no hay registros, retornar algo vacío
//		boolean bHayRegistros = false;
//		int numeroFilas = 0;
//		int numeroColumnas = 0;
//		try {
//			bHayRegistros = resultSet.next();  // Retornará true si hay registros
//			numeroFilas = resultSet.getFetchSize();
//			numeroColumnas = resultSet.getMetaData().getColumnCount(); // Lanza error si no hay registros
//		} catch (SQLException e) {
//			bHayRegistros = false;   // Sino, sigue en false
//			numeroFilas = 0;
//		}
//		
//		if (!bHayRegistros || numeroColumnas<1 ) {
//			// Entonces lo consideramos un array vacío
//			arlisListado = new ArrayList();
//		} else {
//			
//			// Por cada registro, guardamos su valor en el ArrayList bidimensional en la columna correspondiente
//			try {
//				while (resultSet.next()) {   // Tomar el siguiente registro 
//					// Aquí guardaremos un registro del recordset en formato ArrayList				
//					ArrayList arlisRegistro = new ArrayList();  
//					
//					 //Por cada campo del registro actual, guardarlo en el ArrayList
//					for (int intPosicionCampo = 1; intPosicionCampo <= numeroColumnas; intPosicionCampo++){
//						Object obj1 = null;
//						
//						////obj1 = resultSet.getBlob(intPosicionCampo);
//						obj1 = resultSet.getObject(intPosicionCampo);
//						
//						if (obj1 == null){
//							escribeTraza("Es nulo");
//							arlisRegistro.add("");
//						} else {
//							escribeTraza("No es nulo");
//							if (resultSet.getObject(intPosicionCampo).getClass().getName().equalsIgnoreCase("oracle.sql.BLOB")) {
//								escribeTraza("ES BLOB!");
//								arlisRegistro.add(obj1);
//							} else if (resultSet.getObject(intPosicionCampo).getClass().getName().equalsIgnoreCase("java.lang.String")){
//								escribeTraza("Es String");
//								arlisRegistro.add(resultSet.getString(intPosicionCampo).trim());
//							} else {   // Otros tipos como long etc.
//								////throw new Exception("Excepción de usuario, tipo no contemplado");
//								arlisRegistro.add(resultSet.getString(intPosicionCampo).trim());
//							}
//						}
//						
//					} // Fin FOR (por cada campo..)
//					arlisListado.add(arlisRegistro);  // (Se traspapeló esta línea)
//				} // End WHILE (por cada registro..)
//			} catch (SQLException e) {
//				// Entonces lo consideramos un array vacío
//				arlisListado = new ArrayList();
//			}
//		}
//		
//		return arlisListado;
//	}
//	
//	
//	/* ========================================
//	 * Obtener del contexto de WAS el end point del servicio web Gerex
//	 * ========================================
//	 *  
//	 * */
//	public static String getJNDI_EXCEPCIONES() {
//
//		String urlEXCEPCIONES = null;
//
//		try {
//			java.util.Hashtable env = new java.util.Hashtable();
//			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
//			
//			//Context ctex = new InitialContext(env);
//			Context ctex = new InitialContext();
//
//			// Accedemos a la referencia
//			urlEXCEPCIONES = ctex.lookup("java:comp/env/GEREX").toString();
//			
//		} catch (NamingException ex) {
//			////Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
//			Logger_escribeError("getJNDI_EXCEPCIONES: " + ex.toString());
//		} catch (Exception ex) {
//			////Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
//			Logger_escribeError("getJNDI_EXCEPCIONES: " + ex.toString());
//		}
//		return urlEXCEPCIONES;
//	}
//	
//	
//	
//	
//	
////##[inicio_cambio_Fran]
//	
//	
////	/**
////	 * ======================================== 
////	 * OVERRIDE METHODS
////	 * ========================================
////	 */
//	
//	
//	/**
//	 * ======================================== 
//	 * Método copiado de ConexionDB,
//	 * debido a que el método es private y por tanto no se puede heredar.
//	 * ========================================
//	 */
//	public byte[] obtenerBlob(ResultSet r) throws Exception {
//		r.next();
//		java.sql.Blob blob = r.getBlob(1);
//		escribeTraza("blob=" + blob);
//		long inicioDocumento = 1;
//		int finDocumento = (int) blob.length() - 1;
//		byte[] bytesDocumento = blob.getBytes(inicioDocumento, finDocumento);
//		escribeTraza("bytesDocumento=" + bytesDocumento);
//
//		escribeTraza("blob.hashCode()=" + blob.hashCode());
//		escribeTraza("blob.toString()=" + blob.toString());
//		escribeTraza("blob.toString()=" + blob.getBinaryStream());
//
//		return bytesDocumento;
//
//		// try
//		// {
//		// ArrayList v1 = new ArrayList();
//		// while (r.next())
//		// {
//		// ArrayList v2 = new ArrayList();
//		// for (int i = 1; i <= r.getMetaData().getColumnCount(); i++)
//		// {
//		// if (r.getString(i) != null)
//		// v2.add(r.getString(i).trim());
//		// else
//		// v2.add("");
//		// }
//		// v1.add(v2);
//		// }
//		// return v1;
//		// } catch (Exception e)
//		// {
//		// throw e;
//		// }
//	}
//
//	
//	
//	/** ========================================
//	 *  Nuevos métodos. No son heredados (sólo se añade funcionalidad nueva
//	 * , no existen métodos originales sobre-escritos). 
//	 *  ========================================
//	 */
//
//	/**
//	 * Método que retorna cuántos valores preparados ? hay en la conexión. con cada
//	 * llamada a conexionPrepared.set(strValor) se agrega un valor que posteriormente 
//	 * será reemplazado por cada ? en la SQL.
//	 */
//	public int getValueCount() {
//		return values.size();
//	}
//	
//
//	/**
//	 * Método que retorna un valor indicado en el índice. 
//	 * 
//	 * @param posicion
//	 *            El índice del ArrayList que contiene los valores de la
//	 *            conexión.
//	 * @return Retorna un String con el valor solicitado.
//	 * @author Francisco Gómez Aparicio 99GU4668
//	 */
//	public String getValue(int posicion) {
//		ArrayList myValues = new ArrayList();
//
//		myValues = values;
//		DatoDB datoDB = (DatoDB) myValues.get(posicion);
//		String strValor = (String) datoDB.getDato();
//		return strValor;
//	}
//	
//	/**
//	 * Método que retorna el tipo del valor indicado en el índice.
//	 * 
//	 * @param posicion
//	 *            El índice del ArrayList que contiene los valores de la
//	 *            conexión.
//	 * @return Retorna un String con el valor solicitado.
//	 * @author Francisco Gómez Aparicio 99GU4668
//	 */
//	public int getType(int posicion) {
//		ArrayList myValues = new ArrayList();
//
//		myValues = values;
//		DatoDB datoDB = (DatoDB) myValues.get(posicion);
//		int intTipo = datoDB.getTipo();
//		return intTipo;
//	}
//	
//	/**
//	 * Este método recibe una SQL preparada, con los símbolos ? y los reemplaza
//	 * por los valores reales que se encuentran en la conexión. De esta manera
//	 * es fácil cotejar si el orden de los valores es correcto.
//	 * 
//	 * @param strSql
//	 * @return
//	 * @author Francisco Gómez Aparicio 99GU4668
//	 */
//	public String fillSqlValues(String strSql) {
//		String strSqlValuated = strSql;
//		String strValue = "";
//		int posValue = 0;
//
//		// Reemplazar los ? por valores
//		while (posValue <= this.getValueCount() - 1) {
//			// Get value
//			if (this.getType(posValue) == 1) { // If String, put quotes around
//				strValue = "'" + this.getValue(posValue) + "'";
//			} else { // Unknown type, use the same format than String by
//				// default
//				strValue = "'" + this.getValue(posValue) + "'";
//				// //escribeTraza("strValue=" + strValue);
//			}
//			// Poner valor en la sql
//			strSqlValuated = strSqlValuated.replaceFirst("\\?", strValue);
//			posValue++;
//		}
//		return strSqlValuated;
//	}
//
//	
//	/**
//	 * @param str
//	 * @param endIndex
//	 * @return
//	 */
//	protected String truncarCadena (String str, int endIndex){
//     return ((str.length() > endIndex) ? str.substring(0, endIndex): str);
//		
//	}
//	
//	
//	protected void escribeTraza(String strMessage){
//		////personal.comun.Logger.warning(strMessage);
////		if (ComunUtil.printDebugTraces)
////			System.out.println(strMessage);
//		System.out.println(strMessage);
//	}
//	
//	protected void Logger_escribeWarning (String strMessage) {
////		if (bago.db.ComunUtil.blnEscribeLogs)
////			bago.db.Logger.warning(strMessage);
//
//			////bago.db.Logger.warning(strMessage);
//	}
//	
//	public static void Logger_escribeError (String strMessage) {
////		if (bago.db.ComunUtil.blnEscribeLogs)
////			bago.db.Logger.error(strMessage);
//		
//		////bago.db.Logger.error(strMessage);
//	}	
//
//	
////##[fin_cambio_Fran]
//
//	
//	
//	
//	
//	
//}  
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////Codigo original agosto-2011:
//
////package personal.db;
////
////import java.sql.CallableStatement;
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.sql.Types;
////import java.util.ArrayList;
////import java.util.Hashtable;
////
////import javax.naming.Context;
////import javax.naming.InitialContext;
////import javax.naming.NamingException;
////import javax.sql.DataSource;
////
////import personal.comun.Logger;
////import personal.comun.Util;
////import personal.exceptions.DBConexionException;
////import personal.exceptions.DBQueryException;
////import personal.exceptions.PersonalException;
////import personal.seguridad.Usuario;
////
////public class ConexionDB
////{
////
////	protected static DataSource ds = null;
////	protected Connection conexion = null;
////	public int total = 0;
////	protected PreparedStatement pstmt;
//////	protected int cnt;
////	protected boolean autoCommit = true;
////	protected ArrayList values = null;
//// protected boolean queryEnCurso = false;
////	
////	public ConexionDB(Usuario usuario) throws DBConexionException
////	{
////		try
////		{
////			if (ds == null)
////			{
////		//		
////				
////			   // HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
////			   // String personalds = request.getSession().getServletContext().getInitParameter("personalds");
////				
////				Hashtable env = new Hashtable();
////	        	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
////	        	Context ctx = new InitialContext(env);
////	         			
////			    ds = (DataSource) ctx.lookup("java:comp/env/jdbc/auditorias");
////				
////				values = new ArrayList();
////				
////			}
////					
////			conexion = ds.getConnection();
////			
////			
////		} catch (Exception e)
////		{
////						
////			throw new DBConexionException("Error obteniendo datasource o creando conexión de datasource", usuario);
////		
////			
////		}
////	}
////
////
////	public ConexionDB(Usuario usuario, boolean autoCommit) throws DBConexionException
////	{
////		this(usuario);
////		this.autoCommit = autoCommit;
////		try
////		{
////			if(!autoCommit)
////				conexion.setAutoCommit(false);
////		}catch(SQLException e)
////		{
////			throw new DBConexionException("Error fijando autocommit", usuario);
////		}
////	}
////	
////	public void commit(Usuario usuario)throws DBConexionException
////	{
////		if(autoCommit)
////			Logger.warning("Warning: la conexión tiene el autocommit activado");
////		try 
////		{
////			conexion.commit();
////		} catch (SQLException e) 
////		{
////			throw new DBConexionException("Error haciendo commit", usuario);
////		}
////	}
////	
////	public void rollback(Usuario usuario)throws DBConexionException
////	{
////		try 
////		{
////			conexion.rollback();
////		} catch (SQLException e) 
////		{
////			throw new DBConexionException("Error haciendo rollback", usuario);
////		}
////	}
////	
////	public void desconectarDB(Usuario usuario)throws DBConexionException
////	{
////		try
////		{
////			conexion.close();
////		} catch (SQLException e)
////		{
////			Logger.error("Error en desconexión");
////			throw new DBConexionException("Error en desconexión", usuario);
////		}
////	}
////
////	public void iniciarQuery(Usuario usuario) throws DBQueryException
////	{
////		if(queryEnCurso == true)
////		{
////			//Tira una query exception porque el problema no está 
////			//en la conexión, sino en la manera como se hace la consulta
////			throw new DBQueryException("Error iniciando consulta. Las consultas no pueden anidarse", usuario);
////		}
////		queryEnCurso = true;
////		values = new ArrayList();
////	}
////	
////	public String set(long j)
////	{
////		values.add(new DatoDB(DatoDB.DATOLONG, new Long(j)));
////		return "?";	
////	}
////	
////	
////	public String set(int j)
////	{
////		values.add(new DatoDB(DatoDB.DATOINT, new Integer(j)));
////		
////		return "?";
////	}
////	
////	public String set(String str)
////	{
////		values.add(new DatoDB(DatoDB.DATOSTRING, str));
////		return "?";
////	}
////	
////	public String set(String str, int endIndex)
////	{
////		str = Util.truncate(str, endIndex);
////		return set(str);
////	}
////	
////	
////	public void ejecutarUpdate(String sql, Usuario usuario) throws DBConexionException, DBQueryException
////	{
////		if(!queryEnCurso)
////		{
////			throw new DBQueryException("Error - query no inicializada " + sql, usuario);
////		}
////		
////		try
////		{
////			pstmt = conexion.prepareStatement(sql);
////		}catch(SQLException e)
////		{
////			throw new DBConexionException("Error al ejecutar update " + sql, usuario);
////		}finally
////		{
////			queryEnCurso = false;
////		}
////		
////		
////		//Si el error da aquí, lo más probable es que sea una DBQueryException
////		try
////		{
////			componerQuery();
////			pstmt.executeUpdate();
////
////		}catch(SQLException e)
////		{
////			
////			PersonalException pe = new PersonalException(e.getMessage(),"ejecutarUpdate");
////			pe.rastroYRedireccion();
////			throw new DBQueryException("Error al ejecutar update " + sql, usuario);
////			
////		}finally
////		{
////			queryEnCurso = false;
////		}
////	}
////	
////	public ArrayList ejecutarQuery(String sql, Usuario usuario) throws DBConexionException, DBQueryException
////	{
////		if(!queryEnCurso)
////		{
////			throw new DBQueryException("Error - query no inicializada " + sql, usuario);
////		}
////		
////		ResultSet r = null;
////		try
////		{
////			pstmt = conexion.prepareStatement(sql);
////		}catch(SQLException e)
////		{
////		
////			throw new Exception("Error al ejecutar update " + sql, usuario);
////		
////		}finally
////		{
////			queryEnCurso = false;
////		}	
////			
////		
////		try
////		{
////			componerQuery();
////			r = pstmt.executeQuery();
////			ArrayList var1 = resultados(r);
////			r.close();
////			pstmt.close();
////			return var1;
////		}catch(SQLException e)
////		{
////			throw new DBQueryException("Error SQL al ejecutar query " + sql +"\n" +e.getMessage(), usuario);
////		}catch(Exception e)
////		{
////			throw new DBQueryException("Error indeterminado al ejecutar update " + pstmt.toString(), usuario);
////		}
////		finally
////		{
////			queryEnCurso = false;
////		}
////	}
////	
////	
////	public String ejecutarProcedimiento(String sql, Usuario usuario) throws DBConexionException, DBQueryException
////	{
////		
////		if(!queryEnCurso)
////		{
////			throw new DBQueryException("Error - query no inicializada " + sql, usuario);
////		}
////		
////		String errors = "";
////		CallableStatement callstmt = null;
////		try
////		{
////			callstmt = conexion.prepareCall(sql);
////			
////		}catch(SQLException e)
////		{
////		
////			throw new DBConexionException("Error al ejecutar update " + sql, usuario);
////		
////		}finally
////		{
////			queryEnCurso = false;
////		}	
////			
////		
////		try
////		{
////			componerCall(callstmt);
////			callstmt.execute();
////			errors = callstmt.getString(1);    
////			
////			callstmt.close();
////			return errors;
////			
////		}catch(SQLException e)
////		{
////			throw new DBQueryException("Error SQL al ejecutar query " + sql +"\n" +e.getMessage(), usuario);
////		}catch(Exception e)
////		{
////			throw new DBQueryException("Error indeterminado al ejecutar update " + callstmt.toString(), usuario);
////		}
////		finally
////		{
////			queryEnCurso = false;
////		}			
////	}	
////	
////	private void componerQuery()throws SQLException
////	{
////	   int cnt = 1;
////	   for(int i = 0; i < values.size(); i++)
////	   {
////		 DatoDB ddb = (DatoDB)values.get(i);
////		 
////		 switch(ddb.getTipo())
////		 {
////		 case DatoDB.DATOSTRING:
////			 pstmt.setString(cnt++, (String)ddb.getDato());
////			 break;
////		 case DatoDB.DATOINT:
////			 pstmt.setInt(cnt++, ((Integer)ddb.getDato()).intValue());
////			 break;
////		 case DatoDB.DATOLONG:
////			 pstmt.setLong(cnt++, ((Long)ddb.getDato()).intValue());
////			 break;
////		 }
////	   }
////	}
////	
////	private void componerCall(CallableStatement callstmt)throws SQLException
////	{
////	   int cnt = 2;
////	   callstmt.registerOutParameter(1, Types.VARCHAR);
////	   
////	   for(int i = 0; i < values.size(); i++)
////	   {
////		 DatoDB ddb = (DatoDB)values.get(i);
////		 
////		 switch(ddb.getTipo())
////		 {
////		 case DatoDB.DATOSTRING:
////			 callstmt.setString(cnt++, (String)ddb.getDato());
////			 break;
////		 case DatoDB.DATOINT:
////			 callstmt.setInt(cnt++, ((Integer)ddb.getDato()).intValue());
////			 break;
////		 case DatoDB.DATOLONG:
////			 callstmt.setLong(cnt++, ((Long)ddb.getDato()).intValue());
////			 break;
////		 }
////	   }
////	}
////	
////	private ArrayList resultados(ResultSet r) throws Exception
////	{
////		try
////		{
////			ArrayList v1 = new ArrayList();
////			while (r.next())
////			{
////				ArrayList v2 = new ArrayList();
////				for (int i = 1; i <= r.getMetaData().getColumnCount(); i++)
////				{
////					if (r.getString(i) != null)
////						v2.add(r.getString(i).trim());
////					else
////						v2.add("");
////				}
////				v1.add(v2);
////			}
////			return v1;
////		} catch (Exception e)
////		{
////			throw e;
////		}
////	}
////	
////
////	public static String getJNDI_EXCEPCIONES() {
////
////		String urlEXCEPCIONES = null;
////
////		try {
////			java.util.Hashtable env = new java.util.Hashtable();
////			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
////			
////			//Context ctex = new InitialContext(env);
////			Context ctex = new InitialContext();
////
////			// Accedemos a la referencia
////			urlEXCEPCIONES = ctex.lookup("java:comp/env/GEREX").toString();
////			
////		} catch (NamingException ex) {
////			Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
////		} catch (Exception ex) {
////			Logger.error("getJNDI_EXCEPCIONES: " + ex.toString());
////		}
////		return urlEXCEPCIONES;
////	}	
////}
