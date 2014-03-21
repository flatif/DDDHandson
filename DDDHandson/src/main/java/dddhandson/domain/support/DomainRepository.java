package dddhandson.domain.support;

import java.io.Serializable;

/**
 * Repository DDD, e' anche un repository spring data, ma nasconde l'id di persitenza, il tipo dell'id di persistenza
 * e' definito nel layer supertype @link Entity 
 */
public interface DomainRepository<ID extends Serializable> {
	
	<E extends EventSourcedDomainEntity<ID>> E findByIdentity(ID domainIdentity, Class<E> entityClass);
	
	void save(EventSourcedDomainEntity<ID> entity);
	
}
