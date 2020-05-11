package com.susar.repository;

import com.susar.model.Susar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SusarRepository extends CrudRepository<Susar, Long> {
    List<Susar> findByType(String type);
}
