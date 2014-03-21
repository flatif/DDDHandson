package dddhandson.domain.model.reclamo;

import dddhandson.domain.support.DomainEntityState;
import dddhandson.domain.support.EventSourcedDomainEntity;

public class Reclamo extends EventSourcedDomainEntity {

    @Override
    protected DomainEntityState createStateObject() {
        return new ReclamoState();
    }

    protected ReclamoState state() {
        return (ReclamoState) this.state;
    }

    public void apriReclamo() {
        if (!state().isCreato()) {
            throw new RuntimeException("assd");
        }
        this.apply(new ReclamoAperto(this.state().identity()));
    }
}
