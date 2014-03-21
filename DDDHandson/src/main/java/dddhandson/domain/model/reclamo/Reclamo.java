package dddhandson.domain.model.reclamo;

import dddhandson.domain.support.DomainEntityState;
import dddhandson.domain.support.EventSourcedDomainEntity;

public class Reclamo extends EventSourcedDomainEntity<String> {

    @Override
    protected DomainEntityState<String> createStateObject() {
        return new ReclamoState();
    }
}
