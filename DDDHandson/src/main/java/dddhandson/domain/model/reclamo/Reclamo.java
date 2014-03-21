package dddhandson.domain.model.reclamo;

import dddhandson.domain.support.DomainEntityState;
import dddhandson.domain.support.EventSourcedDomainEntity;

public class Reclamo extends EventSourcedDomainEntity {

    @Override
    protected DomainEntityState createStateObject() {
        return new ReclamoState();
    }
}
