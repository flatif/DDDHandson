package dddhandson.infrastructure.eventpublisher;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;

import dddhandson.domain.support.EventPublisher;

public class GuavaEventPublisherFactory {

	public EventPublisher createAsync(){
		return new GuavaEventPublisher(new AsyncEventBus(Executors.newFixedThreadPool(1)));
	}
	
}
