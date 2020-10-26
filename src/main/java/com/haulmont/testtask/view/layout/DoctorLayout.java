package com.haulmont.testtask.view.layout;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

import java.util.ArrayList;
import java.util.List;

public class DoctorLayout extends CrudViewLayout<Doctor> {

    public DoctorLayout(CrudRepository<Doctor> repository) {
        super(repository, new Doctor());
    }

    @Override
    public List<Component> createFieldsForForm(Binder<Doctor> binder) {
        List<Component> components = new ArrayList<>();

        TextField firstName = new TextField("Firstname");
        firstName.setMaxLength(50);
        binder.forField(firstName).asRequired().bind(Doctor::getFirstName, Doctor::setFirstName);
        components.add(firstName);

        TextField lastName = new TextField("Lastname");
        lastName.setMaxLength(50);
        binder.forField(lastName).asRequired().bind(Doctor::getLastName, Doctor::setLastName);
        components.add(lastName);

        TextField patronymic = new TextField("Patronymic");
        patronymic.setMaxLength(50);
        binder.forField(patronymic).asRequired().bind(Doctor::getPatronymic, Doctor::setPatronymic);
        components.add(patronymic);

        TextField specialization = new TextField("Specialization");
        specialization.setMaxLength(50);
        binder.forField(specialization).asRequired().bind(Doctor::getSpecialization, Doctor::setSpecialization);
        components.add(specialization);

        return components;
    }

    @Override
    public void addColumnsToTable(Grid<Doctor> grid) {
        grid.addColumn(Doctor::getFirstName).setCaption("Firstname");
        grid.addColumn(Doctor::getLastName).setCaption("Lastname");
        grid.addColumn(Doctor::getPatronymic).setCaption("Patronymic");
        grid.addColumn(Doctor::getSpecialization).setCaption("Specialization");
    }
}
