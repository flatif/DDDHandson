package dddhandson.domain.support;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class EventSourcingDomainRepository<E extends EventSourcedDomainEntity<ID>, ID extends Serializable> implements DomainRepository<E, ID> {
	
	private final EventStore<ID> eventStore;

	public EventSourcingDomainRepository(EventStore<ID> eventStore) {
		this.eventStore = eventStore;
	}

	@Override
	public E findByIdentity(ID domainIdentity,	Class<E> entityClass) {
		final EventStream eventStream = eventStore.eventStream(domainIdentity);
		return create(entityClass, eventStream);
	}

	@Override
	public void save(E entity) {
		final EventStream stream = new EventStream(entity.mutatingEvents(), entity.mutatedVersion());
		eventStore.save(entity.identity(), stream);
	}

	private <E> E create(Class<E> clazz, EventStream eventStream){
		try {
			final Constructor<E> constr = clazz.getConstructor(EventStream.class);
			
			return constr.newInstance(eventStream);
		}
		catch (Exception e){
			throw new RuntimeException("Poi al messaggio ci pensiamo...", e);
		}
	}
}
