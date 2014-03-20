package dddhandson.domain.support;

import java.io.Serializable;

/**
 * Repository DDD, e' anche un repository spring data, ma nasconde l'id di persitenza, il tipo dell'id di persistenza
 * e' definito nel layer supertype @link Entity 
 */
public interface DomainRepository<E extends DomainEntity<E, DOMAINID>, DOMAINID extends Serializable> extends BaseRepository<E, Serializable> {
	
	E findByIdentity(DOMAINID domainIdentity);
	
	E findByIdentityReadOnly(DOMAINID domainIdentity);
	
//	<S extends E> S save(S entity);
//	
//	void delete(E entity);
//	
//	Iterable<E> findAll();
//	
//	long count();
	
}
