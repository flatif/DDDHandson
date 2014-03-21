package dddhandson.port.persistence.eventsourcing.adapter.memorydb;

import dddhandson.domain.support.EventStore;
import dddhandson.domain.support.EventStream;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class MemoryEventStore<ID extends Serializable>  implements EventStore<ID>  {

    protected Map<ID, List<EventDescriptor>> memoryStore;

    @Override
    public void save(ID aggregateId, EventStream eventStream) {
        
    }

    @Override
    public EventStream eventStream(ID aggregateId) {
        return null;
    }
}
