package dddhandson.domain.support;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public abstract class IdentifiedDomainObject {
	
	@Id
	private Serializable id;
	
	protected IdentifiedDomainObject() {
		super();
	}
	
	public Serializable id() {
		return this.id;
	}
	
}
