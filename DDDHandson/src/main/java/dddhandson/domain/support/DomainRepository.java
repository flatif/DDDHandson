package dddhandson.domain.support;

import java.io.Serializable;

public interface DomainRepository<E extends DomainEntity<ID>, ID extends Serializable> {

	E findByIdentity(ID domainIdentity, Class<E> entityClass);
	
	void save(E entity);
	
}
