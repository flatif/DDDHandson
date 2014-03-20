package dddhandson.port.persistence.adapter.memorydb.support;

import dddhandson.domain.support.DomainEntity;
import dddhandson.domain.support.DomainRepository;

import java.io.Serializable;
import java.util.HashMap;

public abstract class AbstractHashMapDomainRepository<E extends DomainEntity<E, ID>, ID extends Serializable> implements DomainRepository<E, ID> {
	
	protected abstract HashMap<ID, E> memoryStore();
	
	@Override
	public E findByIdentity(ID domainIdentity) {
		return memoryStore().get(domainIdentity);
	}

	@Override
	public E findByIdentityReadOnly(ID domainIdentity) {
		return findByIdentity(domainIdentity);
	}
	
	@Override
	public synchronized <S extends E> S save(S entity) {
		memoryStore().put(entity.identity(), entity);
		return entity;
	}
	
	@Override
	public synchronized void delete(E entity) {
		memoryStore().remove(entity.identity());
	}
	
	@Override
	public Iterable<E> findAll() {
		return memoryStore().values();
	}
	
	@Override
	public E findOne(Serializable id) {
		throw new UnsupportedOperationException("findOne non supportata nei memory domain repository");
	}
	
	@Override
	public long count() {
		return memoryStore().size();
	}
	
	@Override
	public void deleteAll() {
		this.memoryStore().clear();
	}
	
	public synchronized void clearRepository() {
		this.memoryStore().clear();
	}
	
}
