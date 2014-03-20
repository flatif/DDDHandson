package dddhandson.domain.support;

public interface CommandDispatcher {

	void dispatch(Command command) throws Exception;
}
