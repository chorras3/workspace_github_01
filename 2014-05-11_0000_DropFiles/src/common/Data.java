package common;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import exceptions.UserException;


public class Data {
	protected static DataSource ds = null;
	protected Connection conexion = null;
	protected ArrayList values = null;
	
	
	
	
	
	
	
	/** 
	 * Constructor recomendado. Retorna un objeto conexi�n, se le puede pasar el argumento autoCommit para  
	 * establecerlo o desactivarlo (activado por defecto) y el JNDI para el dataSource. 
	 * Observar que: 
	 *     *El JNDI del dataSource se pasa como argumento (as� podremos elegir conectar a URL's de conexi�n  
	 * diferentes). 
	 *     *Tambi�n se debe indicar el argumento blnAutocommit, si est� a true cada operaci�n se comprometer�   
	 * autom�ticamente, en caso de que queramos la posibilidad de ejecutar .rollback() entonces debemos 
	 * establecer blnAutocommit=false 
	 * @param usuario
	 * @param strJndiDatasource
	 * @param blnAutocommit
	 * @throws DBConexionException
	 */
	////public Data(Usuario usuario, String strJndiDatasource, boolean blnAutocommit) throws DBConexionException {
	public Data(String usuario, String strJndiDatasource, boolean blnAutocommit) throws Exception {
		try
		{
			if (ds == null)
			{
			
				Hashtable env = new Hashtable();
	        	////env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
				env.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
	        	Context ctx = new InitialContext(env);
        	
	        	/* ========================================
	        	 * Establecer el JNDI (nos llega como argumento)
	        	 * ========================================
	        	 * Ej. Para GestionPersonal ser�a: "jdbc_auditorias"  -> ctx.lookup("java:comp/env/jdbc_auditorias")
	        	 *     Para SWPERSONAL ser�a:      "jdbc--auditorias" -> ctx.lookup("java:comp/env/jdbc--auditorias")
	        	 * Eso buscar�a el JNDI "jdbc/auditorias" que contendr� la URL de conexi�n. 
	        	 */
	        	ds = (DataSource) ctx.lookup(strJndiDatasource);    
	

				
				values = new ArrayList();
				
			}
					
			conexion = ds.getConnection();
			
			
		} catch (Exception e)
		{
			UserException userException = new UserException();
			userException.setMessage("Error obteniendo datasource o creando conexi�n de datasource");
			throw userException;
		}
	}







	public ArrayList getValues() {
		return values;
	}

	public void setParameters(ArrayList parameters) {
		this.values = parameters;
	}	

	
}
