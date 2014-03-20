package dddhandson.infrastructure.eventbus;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;

import dddhandson.domain.support.EventBus;

public class GuavaEventBusFactory {

	public EventBus createAsync(){
		return new GuavaEventBus(new AsyncEventBus(Executors.newFixedThreadPool(1)));
	}
	
}
