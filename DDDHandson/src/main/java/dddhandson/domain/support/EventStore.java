package dddhandson.domain.support;

import java.io.Serializable;

public interface EventStore {

    void save(String aggregateId, EventStream eventStream);

    EventStream eventStream (String aggregateId);

}
