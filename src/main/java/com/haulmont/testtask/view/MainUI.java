package com.haulmont.testtask.view;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import com.haulmont.testtask.view.doctor.DoctorLayout;
import com.haulmont.testtask.view.template.Navigation;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
public class MainUI extends UI {

    private final VerticalLayout content = new VerticalLayout();

    @Override
    public void init(VaadinRequest request) {
        setup();
    }

    private void setup() {
        setupContentLayout();
        addNavigation();
        addContent();
    }

    private void setupContentLayout() {
        content.setSpacing(true);
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        content.setMargin(true);
        setContent(content);
    }

    private void addNavigation() {
        Navigation navigation = new Navigation();
        content.addComponent(navigation);
    }

    private void addContent() {
        CrudRepository<Doctor> doctorRepository = new DoctorRepositoryImpl();
        CrudViewLayout<Doctor> doctorLayout = new DoctorLayout(doctorRepository);
        content.addComponent(doctorLayout);
    }
}