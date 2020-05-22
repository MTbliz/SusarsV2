package com.susar.service;

import com.susar.model.filedb.FileDb;
import com.susar.model.filedb.FileDbNotFoundException;
import com.susar.model.filedb.FileDbStorageException;
import org.springframework.web.multipart.MultipartFile;

public interface FileDbStorageService {

    FileDb storeFile(MultipartFile file) throws FileDbStorageException;

    FileDb getFileDb(Long fileDbId) throws FileDbNotFoundException;
}
