package dddhandson.infrastructure.commanddispatcher;

import static com.google.common.collect.FluentIterable.from;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;

import dddhandson.application.command.support.CommandHandler;
import dddhandson.application.command.support.Command;
import dddhandson.application.command.support.CommandDispatcher;

public class MapCommandDispatcher implements CommandDispatcher{
	
	private final Map<Class<Command>, CommandHandler<Command>> handlers = new HashMap<>();
	
	@Override
	public void dispatch(Command command) throws Exception {
		handlers.get(command.getClass()).handle(command);
	}
	
	@SuppressWarnings("unchecked")
	public <C extends Command> void register(CommandHandler<C> handler){
		handlers.put(handledType(handler.getClass()), (CommandHandler<Command>) handler);
	}
	
	@SuppressWarnings("unchecked")
	private Class<Command> handledType(Class<?> handlerClass){
		final Method handleMethod = retrieveHandleMethod(handlerClass);
		return (Class<Command>) handleMethod.getParameterTypes()[0];
	}

	private <C extends Command> Method retrieveHandleMethod(Class<?> handlerClass) {
		return from(methods(handlerClass))
				.firstMatch(method("handle"))
				.get();
	}

	private Predicate<? super Method> method(final String name) {
		return new Predicate<Method>() {
			@Override
			public boolean apply(Method input) {
				return input.getName().equals(name);
			}
		};
	}

	private List<Method> methods(Class<?> handlerClass) {
		return Arrays.asList(handlerClass.getMethods());
	}
}
