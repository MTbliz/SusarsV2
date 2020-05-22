package com.susar.model.specialist;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;

    @Column(name = "specialist_name")
    private String name;

    @Column(name = "specialist_lastname")
    private String lastName;

    @Column(name = "specialist_study")
    private String study;

    @Column(name = "specialist_country")
    private String country;

    public Specialist() {
    };

    public Specialist(Long iD, String name, String lastName, String study, String country) {
        this.iD = iD;
        this.name = name;
        this.lastName = lastName;
        this.study = study;
        this.country = country;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Specialist{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", study='" + study + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
