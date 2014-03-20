package dddhandson.infrastructure.eventbus;

import dddhandson.domain.support.DomainEvent;
import dddhandson.domain.support.EventBus;

public class GuavaEventBus implements EventBus {

	private final com.google.common.eventbus.EventBus eventBus;

	public GuavaEventBus(){
		this.eventBus = new com.google.common.eventbus.EventBus();
	}
	
	public GuavaEventBus(com.google.common.eventbus.EventBus eventBus) {
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
	
	public EventBus register(Object listener){
		eventBus.register(listener);
		return this;
	}
}
