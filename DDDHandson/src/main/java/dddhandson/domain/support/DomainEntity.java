package dddhandson.domain.support;

import dddhandson.domain.support.supertype.IdentifiedDomainObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


/**
 * Entita' di dominio.
 * 
 * @param <E>
 *            entita'
 * @param <ID>
 *            identificatore
 */
public abstract class DomainEntity<E extends DomainEntity<E, ID>, ID extends Serializable>  {
	
	/**
	 * @return identity
	 */
	public abstract ID identity();
	
	/**
	 * Verifica se {@code other} ha la stessa identità di questa instanza.
	 * 
	 * @param other
	 *            da paragonare
	 * @return {@code true} se la condizione e' verificata, altrimenti
	 *         {@code false}
	 */
	public boolean sameIdentityAs(E other) {
		if (other == null) {
			return false;
		}
		ID identity = identity();
		ID otherIdentity = other.identity();
		return identity.equals(otherIdentity);
	}
	
	/**
	 * UID per {@link java.io.Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore vuoto per la reflection.
	 */
	protected DomainEntity() {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return identity().hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		return sameIdentityAs((E) object);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
