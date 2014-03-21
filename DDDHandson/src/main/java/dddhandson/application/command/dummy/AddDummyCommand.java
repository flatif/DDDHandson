package dddhandson.application.command.dummy;

import dddhandson.domain.support.Command;

public class AddDummyCommand implements Command{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
