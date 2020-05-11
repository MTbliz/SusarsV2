package com.susar.repository;

import com.susar.model.Specialist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistRepository extends CrudRepository<Specialist, Long> {
    List<Specialist> findByStudy(String study);
}
