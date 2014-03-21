package dddhandson.infrastructure.port.interfaces.adapter.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dddhandson.application.command.reclamo.ApriReclamoCommand;
import dddhandson.application.command.support.CommandDispatcher;

@Controller
public class TestController {
	
	private final CommandDispatcher commandDispatcher;
	
	
	@Autowired
    public TestController(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
	}

	@RequestMapping("/test")
    @ResponseBody
    public String test() throws Exception {
		final ApriReclamoCommand command = new ApriReclamoCommand();
		commandDispatcher.dispatch(command);
        return "asd";
    }

}
