package com.susar.service.filedbstorageserviceimplementation;

import com.susar.model.filedb.FileDb;
import com.susar.model.filedb.FileDbNotFoundException;
import com.susar.model.filedb.FileDbStorageException;
import com.susar.repository.FileDbRepository;
import com.susar.service.FileDbStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileDbStorageServiceImplementation implements FileDbStorageService {

    private FileDbRepository fileDbRepository;

    @Autowired
    private FileDbStorageServiceImplementation(FileDbRepository fileDbRepository) {
        this.fileDbRepository = fileDbRepository;
    }

    public FileDb storeFile(MultipartFile file) throws FileDbStorageException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileDbStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileDb fileDb = new FileDb(fileName, file.getContentType(), file.getBytes());

            return fileDbRepository.save(fileDb);
        } catch (IOException ex) {
            throw new FileDbStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public FileDb getFileDb(Long fileDbId) throws FileDbNotFoundException {
        return fileDbRepository.findById(fileDbId)
                .orElseThrow(() -> new FileDbNotFoundException(fileDbId));
    }
}

