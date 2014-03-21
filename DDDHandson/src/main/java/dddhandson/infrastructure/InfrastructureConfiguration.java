package dddhandson.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dddhandson.domain.support.DomainRepository;
import dddhandson.domain.support.EventPublisher;
import dddhandson.domain.support.EventSourcingDomainRepository;
import dddhandson.domain.support.EventStore;
import dddhandson.infrastructure.eventpublisher.GuavaEventPublisher;
import dddhandson.infrastructure.port.persistence.eventsourcing.adapter.memorydb.MemoryEventStore;

@Configuration
public class InfrastructureConfiguration {

	
	private EventPublisher eventPublisher(){
		return new GuavaEventPublisher();
	}

	private EventStore<String> eventStore(){
		return new MemoryEventStore<String>(eventPublisher());
	}
	
	@Bean
	public DomainRepository<String> domainRepository(){
		return new EventSourcingDomainRepository<>(eventStore());
	}
}
