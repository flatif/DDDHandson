package dddhandson.domain.support;

import dddhandson.domain.support.DomainEvent;

public class EventDescriptor {

    protected int version;

    //data

    protected DomainEvent event;

    public EventDescriptor(int version, DomainEvent event) {
        this.version = version;
        this.event = event;
    }

    public int getVersion() {
        return version;
    }

    public DomainEvent getEvent() {
        return event;
    }
}
