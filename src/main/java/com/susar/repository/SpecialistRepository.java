package com.susar.repository;

import com.susar.model.Specialist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecialistRepository extends CrudRepository<Specialist, Long> {
    List<Specialist> findByStudy(String study);
}
