package dddhandson.domain.model.reclamo;

import dddhandson.domain.support.DomainEvent;

public class ReclamoAperto extends DomainEvent {

    public ReclamoAperto(String aggregateIdentity) {
        super(aggregateIdentity);
    }
}
