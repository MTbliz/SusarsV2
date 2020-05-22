package com.susar.model.filedb;

public class FileDbStorageException extends Exception {
    public FileDbStorageException(String message, Exception ex){
        super(message,ex);
    }

    public FileDbStorageException(String message){
        super(message);
    }

}
