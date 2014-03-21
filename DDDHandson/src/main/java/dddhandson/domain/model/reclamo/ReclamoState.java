package dddhandson.domain.model.reclamo;

import dddhandson.domain.support.DomainEntityState;

public class ReclamoState extends DomainEntityState {

    protected enum Stato {
        CREATO,
        APERTO,
        CHIUSO
    }

    protected Stato stato;

    public ReclamoState() {
        stato = Stato.CREATO;
    }

    protected Boolean isCreato() {
        return stato.equals(Stato.CREATO);
    }

    protected Boolean isAperto() {
        return stato.equals(Stato.APERTO);
    }

    protected void when(ReclamoAperto reclamoAperto) {
        stato = Stato.APERTO;
    }
}
