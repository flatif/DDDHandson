package dddhandson.application.command.reclamo;

import dddhandson.application.command.support.CommandHandler;
import dddhandson.domain.model.reclamo.Reclamo;
import dddhandson.domain.support.DomainRepository;

public class ApriReclamoCommandHandler implements CommandHandler<ApriReclamoCommand> {

    protected DomainRepository<Reclamo, String> reclamoRepository;

    public ApriReclamoCommandHandler(DomainRepository<Reclamo, String> reclamoRepository) {
        this.reclamoRepository = reclamoRepository;
    }

    @Override
    public void handle(ApriReclamoCommand command) throws Exception {
        Reclamo reclamo = new Reclamo();
        this.reclamoRepository.save(reclamo);
    }
}
