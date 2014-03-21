package dddhandson.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dddhandson.application.command.reclamo.ApriReclamoCommandHandler;
import dddhandson.application.command.support.CommandDispatcher;
import dddhandson.domain.support.EventPublisher;
import dddhandson.domain.support.EventSourcingDomainRepository;
import dddhandson.domain.support.EventStore;
import dddhandson.infrastructure.commanddispatcher.MapCommandDispatcher;
import dddhandson.infrastructure.eventpublisher.GuavaEventPublisher;
import dddhandson.infrastructure.port.persistence.eventsourcing.adapter.memorydb.MemoryEventStore;

@Configuration
public class InfrastructureConfiguration {
	
	@Autowired
	@Bean
	public CommandDispatcher commandDispatcher(EventSourcingDomainRepository repository){
		System.out.println("InfrastructureConfiguration.commandDispatcher()");
		final MapCommandDispatcher commandDispatcher = new MapCommandDispatcher();
		
		commandDispatcher.register(new ApriReclamoCommandHandler(repository));
		
		return commandDispatcher;
	}

	
	private EventPublisher eventPublisher(){
//		return new GuavaEventPublisherFactory().createAsync();
		return new GuavaEventPublisher();
	}

	private EventStore eventStore(){
		return new MemoryEventStore(eventPublisher());
	}
	
	@Bean
	public EventSourcingDomainRepository domainRepository(){
		return new EventSourcingDomainRepository<>(eventStore());
	}
	
	@Autowired
	@Bean
	public ApriReclamoCommandHandler apriReclamoCommandHandler(EventSourcingDomainRepository repository){
		return new ApriReclamoCommandHandler(repository);
	}
}
