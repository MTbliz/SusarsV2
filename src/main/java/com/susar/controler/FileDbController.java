package com.susar.controler;

import com.susar.model.filedb.FileDb;
import com.susar.model.filedb.FileDbNotFoundException;
import com.susar.model.filedb.FileDbStorageException;
import com.susar.model.payload.response.FileDbResponse;
import com.susar.service.FileDbStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileDbController {

    FileDbStorageService fileDbStorageService;

    @Autowired
    private FileDbController(FileDbStorageService fileDbStorageService){
        this.fileDbStorageService = fileDbStorageService;
    }


    @PostMapping("/uploadFile")
    public FileDbResponse uploadTraining(@RequestParam("file") MultipartFile file) throws FileDbStorageException {
        FileDb fileDb = fileDbStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileDb.getId().toString())
                .toUriString();

        return new FileDbResponse(fileDb.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<FileDbResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return uploadTraining(file);
                    } catch (FileDbStorageException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws FileDbNotFoundException {
        // Load file from database
        FileDb fileDb = fileDbStorageService.getFileDb(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDb.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDb.getFileName() + "\"")
                .body(new ByteArrayResource(fileDb.getData()));
    }

}