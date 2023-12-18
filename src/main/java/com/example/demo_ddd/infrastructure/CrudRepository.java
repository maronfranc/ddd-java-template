package com.maron.ddd_java_sample.infrastructure;


import org.springframework.data.repository.Repository;

public interface CrudRepository<T, ID> extends Repository<T, ID> {
}
