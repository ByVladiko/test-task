package com.haulmont.testtask.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Doctor {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String specialization;

    public Doctor(String firstName, String lastName, String patronymic, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + patronymic + " " + specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (!Objects.equals(firstName, doctor.firstName)) return false;
        if (!Objects.equals(lastName, doctor.lastName)) return false;
        if (!Objects.equals(patronymic, doctor.patronymic)) return false;
        return (!Objects.equals(specialization, doctor.specialization));
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        return result;
    }
}
