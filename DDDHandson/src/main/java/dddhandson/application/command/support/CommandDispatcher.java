package dddhandson.application.command.support;

public interface CommandDispatcher {

	void dispatch(Command command) throws Exception;
}
