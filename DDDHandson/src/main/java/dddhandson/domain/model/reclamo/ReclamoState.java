package dddhandson.domain.model.reclamo;

import dddhandson.domain.support.DomainEntityState;

public class ReclamoState extends DomainEntityState<String> {

    protected String reclamoId;

    @Override
    public String identity() {
        return reclamoId;
    }
}
