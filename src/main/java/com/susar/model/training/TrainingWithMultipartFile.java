package com.susar.model.training;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;

public class TrainingWithMultipartFile {

    private MultipartFile multipartFile;

    private String name;

    private int version;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime effectiveDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime stopDate;

    public TrainingWithMultipartFile(){};

    public TrainingWithMultipartFile(MultipartFile multipartFile, String name, int version, ZonedDateTime effectiveDate, ZonedDateTime stopDate) {
        this.multipartFile = multipartFile;
        this.name=name;
        this.version = version;
        this.effectiveDate = effectiveDate;
        this.stopDate = stopDate;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ZonedDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(ZonedDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public ZonedDateTime getStopDate() {
        return stopDate;
    }

    public void setStopDate(ZonedDateTime stopDate) {
        this.stopDate = stopDate;
    }
}

