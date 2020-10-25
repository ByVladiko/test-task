package com.haulmont.testtask.view.patient;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

import java.util.ArrayList;
import java.util.List;

public class PatientLayout extends CrudViewLayout<Patient> {

    public PatientLayout(CrudRepository<Patient> repository) {
        super(repository, new Patient());
    }

    @Override
    public List<Component> createFieldsForForm(Binder<Patient> binder) {
        List<Component> components = new ArrayList<>();

        TextField firstName = new TextField("Firstname");
        firstName.setMaxLength(50);
        binder.forField(firstName).asRequired().bind(Patient::getFirstName, Patient::setFirstName);
        components.add(firstName);

        TextField lastName = new TextField("Lastname");
        lastName.setMaxLength(50);
        binder.forField(lastName).asRequired().bind(Patient::getLastName, Patient::setLastName);
        components.add(lastName);

        TextField patronymic = new TextField("Patronymic");
        patronymic.setMaxLength(50);
        binder.forField(patronymic).asRequired().bind(Patient::getPatronymic, Patient::setPatronymic);
        components.add(patronymic);

        TextField phone = new TextField("Phone");
        phone.setMaxLength(50);
        binder.forField(phone).asRequired().bind(Patient::getPhone, Patient::setPhone);
        components.add(phone);

        return components;
    }

    @Override
    public void addColumnsToTable(Grid<Patient> grid) {
        grid.addColumn(Patient::getFirstName).setCaption("Firstname");
        grid.addColumn(Patient::getLastName).setCaption("Lastname");
        grid.addColumn(Patient::getPatronymic).setCaption("Patronymic");
        grid.addColumn(Patient::getPhone).setCaption("Phone");
    }

}
