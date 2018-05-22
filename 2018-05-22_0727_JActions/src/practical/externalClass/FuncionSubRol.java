package practical.externalClass;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.Converters;

import com.SGPF.coopera2020.comun.Keys;
import com.SGPF.coopera2020.i18n.taglib.DbMessageTag;
import com.SGPF.coopera2020.modelo.auxiliares.mensaje.MensajeKeys;
import com.SGPF.coopera2020.modelo.funcion.FuncionIdioma;
import com.SGPF.coopera2020.modelo.tipos.TipoAcceso;
import com.SGPF.coopera2020.modelo.tipos.converters.TipoAccesoConverter;
import com.SGPF.coopera2020.servicios.consulta.Orden;
import com.SGPF.coopera2020.modelo.menu.Funcion;

@Entity
@Converters({
	@Converter(name = "tipoAccesoConverter", converterClass = TipoAccesoConverter.class),
})

@NamedQueries({
	@NamedQuery(name = FuncionSubRol.QUERY_FUNCION_SUBROLES_POR_ROL, query = "select new com.SGPF.coopera2020.modelo.items.ItemGeneral(o.id.codFuncion,o.actuaOconsulta) from FuncionSubRol o where o.id.codRol = :"+Keys.COD_ROL),
	@NamedQuery(name = FuncionSubRol.QUERY_FUNCION_PERMISO_ROL_SUBROL_FUNCION, query = "select o from FuncionSubRol o where o.id.codRol = :"+Keys.COD_ROL+" and o.id.codSubRol = :"+Keys.COD_SUB_ROL+" and o.id.codFuncion = :"+Keys.COD_FUNCION)
})

@Table(name="FUNCION_SUBROL")
@org.eclipse.persistence.annotations.Cache(
	type =CacheType.SOFT , // Cache everything until the JVM decides memory is low.
	size=64000,  // Use 64,000 as the initial cache size.
	expiry=600000,  // 10 minutes
	coordinationType=CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS  // if cache coordination is used, only send invalidation messages.
)

public class FuncionSubRol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9100107992496651995L;

	@EmbeddedId
	private FuncionSubRolPK id;
	
	@Column(name="ACTUA_O_CONSULTA")
	@Convert("tipoAccesoConverter")
	@NotNull(message="actuaOconsulta" + DbMessageTag.SEPARADOR_FUNCION + MensajeKeys.FCM_NOTBLANK)
	private TipoAcceso actuaOconsulta;
	
	public static final String QUERY_FUNCION_SUBROLES_POR_ROL= "QUERY_FUNCION_SUBROLES_POR_ROL";
	public static final String QUERY_FUNCION_PERMISO_ROL_SUBROL_FUNCION= "QUERY_FUNCION_PERMISO_ROL_SUBROL_FUNCION";
	public static final String COD_ROL = "id.codRol";
	public static final String COD_SUB_ROL = "id.codSubRol";
	public static final String COD_FUNCION = "id.codFuncion";
	public static final String FASE_VALIDACION = "id.faseValidacion";
	
	@ManyToOne
	@JoinColumn(name = "COD_ROL", referencedColumnName = "COD", insertable = false, updatable = false)
	private Rol rol;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(referencedColumnName="COD_ROL", name="COD_ROL",insertable=false,updatable=false),
		@JoinColumn(referencedColumnName="COD_SUBROL",name="COD_SUBROL",insertable=false,updatable=false)
	})
	private SubRol subrol;
	
	@ManyToOne
	@JoinColumn(name = "COD_FUNCION", referencedColumnName = "COD_FUNCION", insertable = false, updatable = false)
	private Funcion funcion;
	
	
	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public SubRol getSubrol() {
		return subrol;
	}

	public void setSubrol(SubRol subrol) {
		this.subrol = subrol;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public FuncionSubRolPK getId() {
		return id;
	}

	public void setId(FuncionSubRolPK id) {
		this.id = id;
	}

	public TipoAcceso getActuaOconsulta() {
		return actuaOconsulta;
	}

	public void setActuaOconsulta(TipoAcceso actuaOconsulta) {
		this.actuaOconsulta = actuaOconsulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actuaOconsulta == null) ? 0 : actuaOconsulta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionSubRol other = (FuncionSubRol) obj;
		if (actuaOconsulta == null) {
			if (other.actuaOconsulta != null)
				return false;
		} else if (!actuaOconsulta.equals(other.actuaOconsulta))
			return false;
		return true;
	}
	
	public static final List<Orden> getOrdenPorDefecto(){
		List<Orden> ordenacion = new Vector<Orden>();
		ordenacion.add(new Orden(FuncionSubRol.COD_ROL,Orden.ORDEN_ASCENDENTE));
		ordenacion.add(new Orden(FuncionSubRol.COD_SUB_ROL,Orden.ORDEN_ASCENDENTE));
		ordenacion.add(new Orden(FuncionSubRol.COD_FUNCION,Orden.ORDEN_ASCENDENTE));
		ordenacion.add(new Orden(FuncionSubRol.FASE_VALIDACION,Orden.ORDEN_ASCENDENTE));		
        return ordenacion;	
	}
	
	
}
