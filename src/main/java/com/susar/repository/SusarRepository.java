package com.susar.repository;

import com.susar.model.Susar;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SusarRepository extends CrudRepository<Susar, Long> {
    List<Susar> findByType(String type);
}
