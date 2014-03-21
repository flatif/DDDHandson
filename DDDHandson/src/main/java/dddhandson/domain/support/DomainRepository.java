package dddhandson.domain.support;

import java.io.Serializable;

public interface DomainRepository<E extends DomainEntity<ID>, ID extends Serializable> {

    //TODO: Aggiungere un header custom all'evento per non passare il class qui
	E findByIdentity(ID domainIdentity, Class<E> entityClass);
	
	void save(E entity);
	
}
