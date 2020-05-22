package com.susar.model.filedb;

public class FileDbNotFoundException extends Exception {
    public FileDbNotFoundException(){
        super();
    }

    public FileDbNotFoundException(Long id){
        super(String.format("File with id %d not found", id));
    }
}

