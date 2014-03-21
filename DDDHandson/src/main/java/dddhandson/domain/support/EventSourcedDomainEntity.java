package dddhandson.domain.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class EventSourcedDomainEntity extends DomainEntity<String> {

    protected DomainEntityState state;

    private List<DomainEvent> mutatingEvents;

    //TODO: Do it with reflection and naming conventions
    protected abstract DomainEntityState createStateObject();

    public EventSourcedDomainEntity() {
        this.mutatingEvents = new ArrayList<DomainEvent>();
        this.state = createStateObject();
    }

    protected EventSourcedDomainEntity(
            EventStream eventStream) {
        this();
        this.state.loadFromHistory(eventStream);
    }

    @Override
    public String identity() {
        return state.identity();
    }

    protected void apply(DomainEvent aDomainEvent) {

        this.state.mutate(aDomainEvent);
        this.mutatingEvents.add(aDomainEvent);
    }

    public int mutatedVersion() {
        return this.state.mutatedVersion();
    }

    public List<DomainEvent> mutatingEvents() {
		return mutatingEvents;
	}

}
