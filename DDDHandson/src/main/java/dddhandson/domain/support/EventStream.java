package dddhandson.domain.support;

import java.util.List;

public class EventStream {

    protected List<DomainEvent> events;

    protected int version;

    public EventStream(List<DomainEvent> events, int version) {
        this.events = events;
        this.version = version;
    }

    public List<DomainEvent> events() {
        return events;
    }

    public int version() {
        return version;
    }
}
