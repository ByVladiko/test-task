package com.haulmont.testtask.view.layout;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.util.ValidatorUtil;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
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
        binder.forField(firstName)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForString())
                .bind(Patient::getFirstName, Patient::setFirstName);
        components.add(firstName);

        TextField lastName = new TextField("Lastname");
        binder.forField(lastName)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForString())
                .bind(Patient::getLastName, Patient::setLastName);
        components.add(lastName);

        TextField patronymic = new TextField("Patronymic");
        binder.forField(patronymic)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForString())
                .bind(Patient::getPatronymic, Patient::setPatronymic);
        components.add(patronymic);

        TextField phone = new TextField("Phone");
        binder.forField(phone)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForPhone())
                .bind(Patient::getPhone, Patient::setPhone);
        components.add(phone);

        return components;
    }

    @Override
    public void addColumnsToTable(Grid<Patient> table) {
        table.addColumn(Patient::getFirstName).setCaption("Firstname");
        table.addColumn(Patient::getLastName).setCaption("Lastname");
        table.addColumn(Patient::getPatronymic).setCaption("Patronymic");
        table.addColumn(Patient::getPhone).setCaption("Phone");
    }

    @Override
    public HorizontalLayout createFilterForTable() {
        return null;
    }

}
