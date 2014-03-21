package dddhandson.domain.support;

import java.io.Serializable;
import java.util.List;

public interface EventStore<ID extends Serializable> {

    void save(ID aggregateId, EventStream eventStream);

    EventStream eventStream (ID aggregateId);

}
