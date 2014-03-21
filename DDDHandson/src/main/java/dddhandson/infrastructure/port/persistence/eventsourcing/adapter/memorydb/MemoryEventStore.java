package dddhandson.infrastructure.port.persistence.eventsourcing.adapter.memorydb;

import dddhandson.domain.support.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryEventStore<ID extends Serializable>  implements EventStore<ID>  {

    protected Map<ID, List<EventDescriptor>> memoryStore;

    protected EventPublisher eventPublisher;

    public MemoryEventStore(EventPublisher eventPublisher) {
        this.memoryStore = new HashMap<>();
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void save(ID aggregateId, EventStream eventStream) {
        List<EventDescriptor> eventDescriptors = memoryStore.get(aggregateId);

        if (eventDescriptors == null) {
            eventDescriptors = new ArrayList<>();
            memoryStore.put(aggregateId, eventDescriptors);
        }
        else if (eventDescriptors.get(eventDescriptors.size() - 1).getVersion() >= eventStream.version()) {
            throw new RuntimeException("Concurrency Exception");
        }

        for (DomainEvent event : eventStream.events()) {
            eventDescriptors.add(new EventDescriptor(eventStream.version(), event));
            eventPublisher.publish(event);
        }

    }

    @Override
    public EventStream eventStream(ID aggregateId) {
        List<EventDescriptor> eventDescriptors = memoryStore.get(aggregateId);
        if (eventDescriptors == null) {
            throw new RuntimeException("Stream not found");
        }

        List<DomainEvent> domainEvents = new ArrayList<>();

        int version = 0;

        for (EventDescriptor descriptor : eventDescriptors) {
            domainEvents.add(descriptor.getEvent());
            version = descriptor.getVersion();
        }

        return new EventStream(domainEvents, version);
    }
}
