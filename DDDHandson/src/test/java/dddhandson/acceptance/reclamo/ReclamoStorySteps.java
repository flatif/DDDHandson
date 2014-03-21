package dddhandson.acceptance.reclamo;

import dddhandson.application.command.reclamo.ApriReclamoCommand;
import dddhandson.application.command.reclamo.ApriReclamoCommandHandler;
import dddhandson.domain.model.reclamo.Reclamo;
import dddhandson.domain.support.DomainRepository;
import dddhandson.domain.support.EventSourcingDomainRepository;
import dddhandson.domain.support.EventStore;
import dddhandson.domain.support.EventStream;
import dddhandson.infrastructure.eventpublisher.GuavaEventPublisher;
import dddhandson.infrastructure.port.persistence.eventsourcing.adapter.memorydb.MemoryEventStore;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class ReclamoStorySteps {

    DomainRepository<Reclamo, String> reclamoRepository;

	public ReclamoStorySteps() {
        reclamoRepository = new EventSourcingDomainRepository(new MemoryEventStore(new GuavaEventPublisher()));
	}
	
	@Given("il sistema non contiene reclami")
	public void sistemaNonContieneCcnl() {
        reclamoRepository = new EventSourcingDomainRepository(new MemoryEventStore(new GuavaEventPublisher()));
	}
	
	@When("il cliente apre un reclamo")
	public void quandoAggiungoCcnl() throws Exception{
        ApriReclamoCommand command = new ApriReclamoCommand();
        ApriReclamoCommandHandler handler = new ApriReclamoCommandHandler(reclamoRepository);
        handler.handle(command);
	}
	
	@Then("il sistema dovrebbe contenere un reclamo aperto")
	public void dovrebbeContenereCcnlConNome() {

	}

}
