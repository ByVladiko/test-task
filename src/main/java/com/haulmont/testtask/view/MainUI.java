package com.haulmont.testtask.view;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import com.haulmont.testtask.view.doctor.DoctorLayout;
import com.haulmont.testtask.view.template.Navigation;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import java.util.HashMap;
import java.util.Map;

@Theme("valo")
public class MainUI extends UI {

    private final VerticalLayout content = new VerticalLayout();

    @Override
    public void init(VaadinRequest request) {
        setup();
    }

    private void setup() {
        setupContentLayout();
        addHeader();
        addContent();
    }

    private void setupContentLayout() {
        content.setSpacing(true);
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        content.setMargin(true);
        setContent(content);
    }

    private void addHeader() {
        Navigation navigation = new Navigation();
        content.addComponent(navigation);
    }

    private void addContent() {
        CrudRepository<Doctor> doctorRepository = new DoctorRepositoryImpl();
        CrudViewLayout<Doctor> doctorLayout = new DoctorLayout(doctorRepository, createFields());
        content.addComponent(doctorLayout);
    }

    private Map<String, Field> createFields() {
        Map<String, Field> fieldMap = new HashMap<>();
        fieldMap.put("firstName", new TextField("Firstname"));
        fieldMap.put("lastName", new TextField("Lastname"));
        fieldMap.put("patronymic", new TextField("Patronymic"));
        fieldMap.put("specialization", new TextField("Specialization"));
        return fieldMap;
    }
}