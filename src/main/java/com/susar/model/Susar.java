package com.susar.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "susars")
public class Susar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;

    @Column(name = "susar_study")
    private String study;

    @Column(name = "susar_country")
    private String country;

    @Column(name = "susar_site")
    private String site;

    @Column(name = "susar_number")
    private int number;

    @Column(name = "susar_type")
    private String type;

    @Column(name = "susar_recipt_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp reciptDate;

    @Column(name = "susar_sent_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp sentDate;

    @Column(name = "susar_aor_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp aorDate;

    public Susar() {
    }

    ;

    public Susar(int number, String type, Timestamp reciptDate, Timestamp sentDate, Timestamp aorDate) {
        this.number = number;
        this.type = type;
        this.reciptDate = reciptDate;
        this.sentDate = sentDate;
        this.aorDate = aorDate;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getReciptDate() {
        return reciptDate;
    }

    public void setReciptDate(Timestamp reciptDate) {
        this.reciptDate = reciptDate;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    public Timestamp getAorDate() {
        return aorDate;
    }

    public void setAorDate(Timestamp aorDate) {
        this.aorDate = aorDate;
    }

    @Override
    public String toString() {
        return "Susar{" +
                "iD=" + iD +
                ", study='" + study + '\'' +
                ", country='" + country + '\'' +
                ", site='" + site + '\'' +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", reciptDate='" + reciptDate + '\'' +
                ", sentDate='" + sentDate + '\'' +
                ", aorDate='" + aorDate + '\'' +
                '}';
    }
}


