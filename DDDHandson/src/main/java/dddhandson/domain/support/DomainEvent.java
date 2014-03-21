package dddhandson.domain.support;

public class DomainEvent {

    protected String aggregateIdentity;

    protected DomainEvent(String aggregateIdentity) {
        this.aggregateIdentity = aggregateIdentity;
    }

    protected DomainEvent() {
    }

    public String getAggregateIdentity() {
        return aggregateIdentity;
    }

}
