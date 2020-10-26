package com.haulmont.testtask.view.layout;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Priority;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import com.haulmont.testtask.repository.patient.PatientRepository;
import com.haulmont.testtask.repository.patient.PatientRepositoryImpl;
import com.haulmont.testtask.repository.recipe.RecipeRepository;
import com.haulmont.testtask.repository.recipe.RecipeRepositoryImpl;
import com.haulmont.testtask.util.ValidatorUtil;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeLayout extends CrudViewLayout<Recipe> {

    public RecipeLayout(CrudRepository<Recipe> repository) {
        super(repository, new Recipe());
    }

    @Override
    public List<Component> createFieldsForForm(Binder<Recipe> binder) {
        List<Component> components = new ArrayList<>();

        RichTextArea description = new RichTextArea("Description");
        binder.forField(description)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .bind(Recipe::getDescription, Recipe::setDescription);
        components.add(description);

        DoctorRepository doctorRepository = new DoctorRepositoryImpl();
        ComboBox<Doctor> doctor = new ComboBox<>("Doctor");
        doctor.setItems(doctorRepository.getAll());
        binder.forField(doctor)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .bind(Recipe::getDoctor, Recipe::setDoctor);
        components.add(doctor);

        PatientRepository patientRepository = new PatientRepositoryImpl();
        ComboBox<Patient> patient = new ComboBox<>("Patient");
        patient.setItems(patientRepository.getAll());
        binder.forField(patient)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .bind(Recipe::getPatient, Recipe::setPatient);
        components.add(patient);

        DateField dateOfCreate = new DateField("Date of create");
        binder.forField(dateOfCreate)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .bind(Recipe::getDateOfCreation, Recipe::setDateOfCreation);
        components.add(dateOfCreate);

        DateField dateOfValidity = new DateField("Date of validity");
        binder.forField(dateOfValidity)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .bind(Recipe::getDateOfValidity, Recipe::setDateOfValidity);
        components.add(dateOfValidity);

        ComboBox<Priority> priority = new ComboBox<>("Priority");
        priority.setItems(Priority.values());
        binder.forField(priority)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .bind(Recipe::getPriority, Recipe::setPriority);
        components.add(priority);

        return components;
    }

    @Override
    public void addColumnsToTable(Grid<Recipe> table) {
        table.addColumn(Recipe::getDescription).setCaption("Description");
        table.addColumn(Recipe::getDoctor).setCaption("Doctor");
        table.addColumn(Recipe::getPatient).setCaption("Patient");
        table.addColumn(Recipe::getDateOfCreation).setCaption("Date of create");
        table.addColumn(Recipe::getDateOfValidity).setCaption("Date of validity");
        table.addColumn(Recipe::getPriority).setCaption("Priority");
    }

    @Override
    public HorizontalLayout createFilterForTable() {
        HorizontalLayout filter = new HorizontalLayout();
        filter.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);

        PatientRepository patientRepository = new PatientRepositoryImpl();
        ComboBox<Patient> filterPatient = new ComboBox<>("Patient", patientRepository.getAll());
        filter.addComponent(filterPatient);

        ComboBox<Priority> filterPriority = new ComboBox<>("Priority", Arrays.asList(Priority.values()));
        filter.addComponent(filterPriority);

        TextField filterDescription = new TextField("Description");
        filter.addComponent(filterDescription);

        Button applyFilter = new Button("Apply");
        RecipeRepository recipeRepository = new RecipeRepositoryImpl();
        applyFilter.addClickListener(event -> {
            Patient filterPatientValue = filterPatient.getValue();
            Priority filterPriorityValue = filterPriority.getValue();
            String filterDescriptionValue = filterDescription.getValue();
            List<Recipe> recipes =
                    recipeRepository.getAll(filterPatientValue, filterPriorityValue, filterDescriptionValue);
            table.setItems(recipes);
        });
        filter.addComponent(applyFilter);

        Button clearFilter = new Button("Clear");
        clearFilter.addClickListener(event -> {
            filter.forEach(field -> {
                if (field instanceof AbstractField) {
                    ((AbstractField<?>) field).clear();
                }
            });
            table.updateTable();
        });
        filter.addComponent(clearFilter);

        return filter;
    }

}
