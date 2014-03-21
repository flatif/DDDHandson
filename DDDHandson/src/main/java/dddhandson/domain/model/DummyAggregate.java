package dddhandson.domain.model;

import dddhandson.domain.support.DomainEntity;

public class DummyAggregate extends DomainEntity<String> {

    public String name;

    public DummyAggregate(String name) {
        this.name = name;
    }

    @Override
    public String identity() {
        return name;
    }
}
