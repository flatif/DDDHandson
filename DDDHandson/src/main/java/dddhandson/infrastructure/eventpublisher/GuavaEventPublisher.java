package dddhandson.infrastructure.eventpublisher;

import com.google.common.eventbus.EventBus;

import dddhandson.domain.support.DomainEvent;
import dddhandson.domain.support.EventPublisher;

public class GuavaEventPublisher implements EventPublisher {

	private final EventBus eventBus;

	public GuavaEventPublisher(){
		this.eventBus = new EventBus();
	}
	
	public GuavaEventPublisher(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void publish(DomainEvent domainEvent) {
		eventBus.post(domainEvent);
	}
	
	public void register(Object...listeners){
		for (Object listener : listeners){
			register(listener);
		}
	}
	
	public EventPublisher register(Object listener){
		eventBus.register(listener);
		return this;
	}
}
