package com.haulmont.testtask.view.recipe;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Priority;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import com.haulmont.testtask.repository.patient.PatientRepository;
import com.haulmont.testtask.repository.patient.PatientRepositoryImpl;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class RecipeLayout extends CrudViewLayout<Recipe> {

    private final DoctorRepository doctorRepository = new DoctorRepositoryImpl();
    private final PatientRepository patientRepository = new PatientRepositoryImpl();

    public RecipeLayout(CrudRepository<Recipe> repository) {
        super(repository, new Recipe());
    }

    @Override
    public List<Component> createFieldsForForm(Binder<Recipe> binder) {
        List<Component> components = new ArrayList<>();

        RichTextArea description = new RichTextArea("Description");
        binder.forField(description).asRequired().bind(Recipe::getDescription, Recipe::setDescription);
        components.add(description);

        ComboBox<Doctor> doctor = new ComboBox<>("Doctor");
        doctor.setItems(doctorRepository.getAll());
        binder.forField(doctor).asRequired().bind(Recipe::getDoctor, Recipe::setDoctor);
        components.add(doctor);

        ComboBox<Patient> patient = new ComboBox<>("Patient");
        patient.setItems(patientRepository.getAll());
        binder.forField(patient).asRequired().bind(Recipe::getPatient, Recipe::setPatient);
        components.add(patient);

        DateField dateOfCreate = new DateField("Date of create");
        binder.forField(dateOfCreate).asRequired().bind(Recipe::getDateOfCreation, Recipe::setDateOfCreation);
        components.add(dateOfCreate);

        DateField dateOfValidity = new DateField("Date of validity");
        binder.forField(dateOfValidity).asRequired().bind(Recipe::getDateOfValidity, Recipe::setDateOfValidity);
        components.add(dateOfValidity);

        ComboBox<Priority> priority = new ComboBox<>("Priority");
        priority.setItems(Priority.values());
        binder.forField(priority).asRequired().bind(Recipe::getPriority, Recipe::setPriority);
        components.add(priority);

        return components;
    }

    @Override
    public void addColumnsToTable(Grid<Recipe> grid) {
        grid.addColumn(Recipe::getDescription).setCaption("Description");
        grid.addColumn(Recipe::getDoctor).setCaption("Doctor");
        grid.addColumn(Recipe::getPatient).setCaption("Patient");
        grid.addColumn(Recipe::getDateOfCreation).setCaption("Date of create");
        grid.addColumn(Recipe::getDateOfValidity).setCaption("Date of validity");
        grid.addColumn(Recipe::getPriority).setCaption("Priority");
    }

}
