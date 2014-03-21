package dddhandson.infrastructure.commanddispatcher;


import static org.junit.Assert.*;
import org.junit.Test;

import dddhandson.application.command.support.CommandHandler;
import dddhandson.application.command.support.Command;

public class MapCommandDispatcherTest {

	@Test
	public void testDispatch() throws Exception {
		final TestCommandHandler handler = new TestCommandHandler();
		final MapCommandDispatcher dispatcher = new MapCommandDispatcher();

		dispatcher.register(handler);
		
		dispatcher.dispatch(new TestCommand());
		
		assertTrue("The handler should be called", handler.called);
	}

	public static class TestCommand implements Command {

	}

	public static class TestCommandHandler implements CommandHandler<TestCommand> {

		private boolean called = false;
		@Override
		public void handle(TestCommand command) throws Exception {
			called = true;
		}
	};
}
