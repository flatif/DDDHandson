package dddhandson.port.persistence.adapter.memorydb.repository;

import dddhandson.domain.model.DummyAggregate;
import dddhandson.domain.model.DummyAggregateRepository;
import dddhandson.port.persistence.adapter.memorydb.support.BaseHashMapDomainRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryDummyAggregateRepository extends BaseHashMapDomainRepository <DummyAggregate, String> implements DummyAggregateRepository {

}
