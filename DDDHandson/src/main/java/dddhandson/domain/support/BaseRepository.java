package dddhandson.domain.support;

import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Repository NON DDD
 */
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
	
	<S extends T> S save(S entity);
	
	void delete(T entity);
	
	Iterable<T> findAll();
	
	T findOne(ID id);
	
	long count();
	
	void deleteAll();
}
