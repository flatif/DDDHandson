package dddhandson.domain.support;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class EventSourcingDomainRepository<E extends EventSourcedDomainEntity> implements DomainRepository<E, String> {
	
	private final EventStore eventStore;

	public EventSourcingDomainRepository(EventStore eventStore) {
		this.eventStore = eventStore;
	}

	@Override
	public E findByIdentity(String domainIdentity,	Class<E> entityClass) {
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
