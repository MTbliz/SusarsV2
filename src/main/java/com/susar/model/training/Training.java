package com.susar.model.training;

import com.susar.model.filedb.FileDb;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class Training {

    @Id
    @GeneratedValue(generator = "TRAINING_ID_GENERATOR")
    @GenericGenerator(name = "TRAINING_ID_GENERATOR", strategy = "enhanced-sequence",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence_name", value = "training_sequence"))
    private Long id;

    private String name;

    private int version;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime effectiveDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime stopDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private FileDb fileDb;

    public Training(){};

    public Training(String name, int version, ZonedDateTime effectiveDate, ZonedDateTime stopDate, FileDb fileDb) {
        this.name = name;
        this.version = version;
        this.effectiveDate = effectiveDate;
        this.stopDate = stopDate;
        this.fileDb = fileDb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return version == training.version &&
                Objects.equals(id, training.id) &&
                name.equals(training.name) &&
                Objects.equals(effectiveDate, training.effectiveDate) &&
                Objects.equals(stopDate, training.stopDate) &&
                fileDb.equals(training.fileDb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, version, effectiveDate, stopDate, fileDb);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public FileDb getFileDb() {
        return fileDb;
    }

    public void setFileDb(FileDb fileDb) {
        this.fileDb = fileDb;
    }
}
