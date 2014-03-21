package dddhandson.domain.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class EventSourcedDomainEntity<E, ID extends Serializable> {

    protected DomainEntityState<ID> state;

    private List<DomainEvent> mutatingEvents;

    protected abstract DomainEntityState<ID> createStateObject();

    protected abstract DomainEntityState<ID> createStateObject(EventStream eventStream);

    public EventSourcedDomainEntity() {
        this.mutatingEvents = new ArrayList<DomainEvent>();
        this.state = createStateObject();
    }

    protected EventSourcedDomainEntity(
            EventStream eventStream) {

        this.mutatingEvents = new ArrayList<DomainEvent>();
        this.state = createStateObject(eventStream);
    }

    protected void apply(DomainEvent aDomainEvent) {

        this.state.mutate(aDomainEvent);
        this.mutatingEvents.add(aDomainEvent);
    }

}
