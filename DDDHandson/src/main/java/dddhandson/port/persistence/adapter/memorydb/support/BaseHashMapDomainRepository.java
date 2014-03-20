package dddhandson.port.persistence.adapter.memorydb.support;

import dddhandson.domain.support.DomainEntity;

import java.io.Serializable;
import java.util.HashMap;

public abstract class BaseHashMapDomainRepository<E extends DomainEntity<E, ID>, ID extends Serializable> extends AbstractHashMapDomainRepository<E, ID> {
	
	private HashMap<ID, E> memoryStore = new HashMap<>();

	@Override
	protected HashMap<ID, E> memoryStore() {
		return memoryStore;
	}

}
