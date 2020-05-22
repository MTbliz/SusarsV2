package com.susar.repository;

import com.susar.model.filedb.FileDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbRepository extends CrudRepository<FileDb,Long> {
}

