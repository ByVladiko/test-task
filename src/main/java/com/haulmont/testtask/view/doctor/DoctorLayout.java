package com.haulmont.testtask.view.doctor;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.ui.Field;

import java.util.Map;

public class DoctorLayout extends CrudViewLayout<Doctor> {

    public DoctorLayout(CrudRepository<Doctor> repository, Map<String, Field> fieldsMap) {
        super(repository, fieldsMap);
    }
}
