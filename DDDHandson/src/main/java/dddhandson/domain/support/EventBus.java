package dddhandson.domain.support;

public interface EventBus {
	
	void publish(DomainEvent domainEvent);
}
