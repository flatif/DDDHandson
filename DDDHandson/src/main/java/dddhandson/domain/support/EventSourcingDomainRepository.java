package dddhandson.domain.support;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class EventSourcingDomainRepository<ID extends Serializable> implements DomainRepository<ID> {
	
	private final EventStore<ID> eventStore;

	public EventSourcingDomainRepository(EventStore<ID> eventStore) {
		this.eventStore = eventStore;
	}

	@Override
	public <E extends EventSourcedDomainEntity<ID>> E findByIdentity(ID domainIdentity,	Class<E> entityClass) {
		final EventStream eventStream = eventStore.eventStream(domainIdentity);
		
		return create(entityClass, eventStream);
	}

	@Override
	public void save(EventSourcedDomainEntity<ID> entity) {
		final EventStream stream = new EventStream(entity.getMutatingEvents(), entity.state.mutatedVersion());
		eventStore.save(entity.state.identity(), stream);
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
