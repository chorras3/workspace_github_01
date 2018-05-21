/*
 * Creado el 26/02/2008
 * 
 * Guillermo Corominas Megías 99GU3589
 *
 *
 **/
package common;

public class DatoDB 
{
	public static final int DATOINT = 0;
	public static final int DATOSTRING = 1;
	public static final int DATOLONG = 2;
	
	//Metemos el tipo a pelo
	//codificado. 
	protected int tipo;
	protected Object dato;
	
	private DatoDB(){};
	
	public DatoDB(int tipo, Object dato) 
	{
		this.tipo = tipo;
		this.dato = dato;
	}
	
	public Object getDato() 
	{
		return dato;
	}
	public void setDato(Object dato) 
	{
		this.dato = dato;
	}
	public int getTipo() 
	{
		return tipo;
	}
	public void setTipo(int tipo)
    {
		this.tipo = tipo;
	}
}
