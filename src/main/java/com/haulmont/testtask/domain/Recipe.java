package com.haulmont.testtask.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Patient patient;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Doctor doctor;
    private LocalDate dateOfCreation;
    private LocalDate dateOfValidity;
    @Enumerated(value = EnumType.ORDINAL)
    private Priority priority;

    public Recipe() {
        this.priority = Priority.Default;
    }

    public Recipe(String description, Patient patient, Doctor doctor, LocalDate dateOfValidity, Priority priority) {
        this.description = description;
        this.patient = patient;
        this.doctor = doctor;
        this.dateOfCreation = LocalDate.now();
        this.dateOfValidity = dateOfValidity;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfValidity() {
        return dateOfValidity;
    }

    public void setDateOfValidity(LocalDate dateOfValidity) {
        this.dateOfValidity = dateOfValidity;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "description='" + description + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", dateOfCreation=" + dateOfCreation +
                ", dateOfValidity=" + dateOfValidity +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (!Objects.equals(description, recipe.description)) return false;
        if (!Objects.equals(patient, recipe.patient)) return false;
        if (!Objects.equals(doctor, recipe.doctor)) return false;
        if (!Objects.equals(dateOfCreation, recipe.dateOfCreation))
            return false;
        if (!Objects.equals(dateOfValidity, recipe.dateOfValidity))
            return false;
        return priority == recipe.priority;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (patient != null ? patient.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (dateOfValidity != null ? dateOfValidity.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }
}
