package dddhandson.infrastructure.eventbus;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.eventbus.Subscribe;

import dddhandson.domain.support.DomainEvent;

public class GuavaEventBusTest {

	@Test
	public void test() {
		
		final Listener listener = new Listener();
		final GuavaEventBus eventBus = new GuavaEventBus();
		
		eventBus.register(listener);
		
		eventBus.publish(new TestEvent());
		
		assertTrue("The method should be called", listener.called);
		
	}

	public class TestEvent implements DomainEvent {
		
	}
	
	public class Listener{
		private boolean called = false;
		@Subscribe
		public void on(TestEvent event){
			called = true;
		}
	}
}
