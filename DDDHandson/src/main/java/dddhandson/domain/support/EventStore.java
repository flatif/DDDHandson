package dddhandson.domain.support;

import java.io.Serializable;

public interface EventStore<ID extends Serializable> {

    void save(ID aggregateId, EventStream eventStream);

    EventStream eventStream (ID aggregateId);

}
