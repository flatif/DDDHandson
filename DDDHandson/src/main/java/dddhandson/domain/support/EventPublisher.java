package dddhandson.domain.support;

public interface EventPublisher {
	
	void publish(DomainEvent domainEvent);
}
