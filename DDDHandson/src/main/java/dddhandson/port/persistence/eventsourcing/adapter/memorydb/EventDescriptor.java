package dddhandson.port.persistence.eventsourcing.adapter.memorydb;

import dddhandson.domain.support.DomainEvent;

public class EventDescriptor {

    protected int version;

    //data

    protected DomainEvent event;
}
