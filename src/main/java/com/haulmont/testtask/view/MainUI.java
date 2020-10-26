package com.haulmont.testtask.view;

import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import com.haulmont.testtask.repository.patient.PatientRepository;
import com.haulmont.testtask.repository.patient.PatientRepositoryImpl;
import com.haulmont.testtask.repository.recipe.RecipeRepository;
import com.haulmont.testtask.repository.recipe.RecipeRepositoryImpl;
import com.haulmont.testtask.view.layout.DoctorLayout;
import com.haulmont.testtask.view.layout.PatientLayout;
import com.haulmont.testtask.view.layout.RecipeLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

@Theme("valo")
public class MainUI extends UI {

    private final VerticalLayout main = new VerticalLayout();
    private Layout contentLayout = new VerticalLayout();

    @Override
    public void init(VaadinRequest request) {
        setup();
    }

    private void setup() {
        setupContentLayout();
        addNavigation();
        setWelcomeLayout();
    }

    private void setupContentLayout() {
        main.setSpacing(true);
        main.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        main.setMargin(true);
        setContent(main);
        main.addComponent(contentLayout);
    }

    private void addNavigation() {
        HorizontalLayout navigation = new HorizontalLayout();

        Button doctorLink = new Button("Doctors");
        doctorLink.addClickListener(event -> {
            DoctorRepositoryImpl repository = new DoctorRepositoryImpl();
            Layout layout = new DoctorLayout(repository).getContent();
            setContentLayout(layout);
        });
        navigation.addComponent(doctorLink);

        Button patientLink = new Button("Patients");
        patientLink.addClickListener(event -> {
            PatientRepository repository = new PatientRepositoryImpl();
            Layout layout = new PatientLayout(repository).getContent();
            setContentLayout(layout);
        });
        navigation.addComponent(patientLink);

        Button recipeLink = new Button("Recipes");
        recipeLink.addClickListener(event -> {
            RecipeRepository repository = new RecipeRepositoryImpl();
            Layout layout = new RecipeLayout(repository).getContent();
            setContentLayout(layout);
        });
        navigation.addComponent(recipeLink);

        main.addComponent(navigation);
    }

    private void setContentLayout(Layout layout) {
        main.replaceComponent(contentLayout, layout);
        contentLayout = layout;
    }

    private void setWelcomeLayout() {
        Label welcomeLabel = new Label("Welcome!");
        contentLayout.addComponent(welcomeLabel);
    }
}
