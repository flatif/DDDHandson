package dddhandson.application.command.dummy;

import dddhandson.application.command.support.CommandHandler;
import dddhandson.domain.model.DummyAggregate;
import dddhandson.domain.model.DummyAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AddDummyCommandHandler implements CommandHandler<AddDummyCommand> {

    @Autowired
    private DummyAggregateRepository dummyAggregateRepository;

    @Override
    public void handle(AddDummyCommand command) throws Exception {
        DummyAggregate dummy = new DummyAggregate(command.getName());
        dummyAggregateRepository.save(dummy);
    }

}
