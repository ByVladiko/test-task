package com.haulmont.testtask.view.layout;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.repository.CrudRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepository;
import com.haulmont.testtask.repository.doctor.DoctorRepositoryImpl;
import com.haulmont.testtask.util.ValidatorUtil;
import com.haulmont.testtask.view.template.crudview.CrudViewLayout;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoctorLayout extends CrudViewLayout<Doctor> {

    public DoctorLayout(CrudRepository<Doctor> repository) {
        super(repository, new Doctor());
        addButtonStatistic();
    }

    @Override
    public List<Component> createFieldsForForm(Binder<Doctor> binder) {
        List<Component> components = new ArrayList<>();

        TextField firstName = new TextField("Firstname");
        binder.forField(firstName)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForString())
                .bind(Doctor::getFirstName, Doctor::setFirstName);
        components.add(firstName);

        TextField lastName = new TextField("Lastname");
        binder.forField(lastName)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForString())
                .bind(Doctor::getLastName, Doctor::setLastName);
        components.add(lastName);

        TextField patronymic = new TextField("Patronymic");
        binder.forField(patronymic)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForString())
                .bind(Doctor::getPatronymic, Doctor::setPatronymic);
        components.add(patronymic);

        TextField specialization = new TextField("Specialization");
        specialization.setMaxLength(50);
        binder.forField(specialization)
                .asRequired(ValidatorUtil.errorNotBeEmpty())
                .withValidator(ValidatorUtil.getValidatorForString())
                .bind(Doctor::getSpecialization, Doctor::setSpecialization);
        components.add(specialization);

        return components;
    }

    @Override
    public void addColumnsToTable(Grid<Doctor> table) {
        table.addColumn(Doctor::getFirstName).setCaption("Firstname");
        table.addColumn(Doctor::getLastName).setCaption("Lastname");
        table.addColumn(Doctor::getPatronymic).setCaption("Patronymic");
        table.addColumn(Doctor::getSpecialization).setCaption("Specialization");
    }

    @Override
    public HorizontalLayout createFilterForTable() {
        return null;
    }

    public void addButtonStatistic() {
        Button button = new Button("Statistic", VaadinIcons.CLIPBOARD_TEXT);
        button.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        button.addClickListener(event -> {
            createModalWindowStatistic();
        });

        buttons.addComponent(button);
    }

    private void createModalWindowStatistic() {
        Window window = new Window();
        window.setResizable(false);
        window.setModal(true);
        window.center();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);

        DoctorRepository doctorRepository = new DoctorRepositoryImpl();

        Grid<Map.Entry<Doctor, Long>> grid = new Grid<>();
        Map<Doctor, Long> statistic = doctorRepository.getStatistic();

        if (statistic.isEmpty()) {
            Notification.show("Empty statistic", Notification.Type.WARNING_MESSAGE);
            return;
        }

        grid.addColumn(Map.Entry::getKey).setCaption("Doctor");
        grid.addColumn(Map.Entry::getValue).setCaption("Count recipes");

        Set<Map.Entry<Doctor, Long>> statisticSet = statistic.entrySet();
        grid.setItems(statisticSet);

        verticalLayout.addComponent(grid);

        Button buttonClose = new Button("Close");
        buttonClose.addClickListener(event -> window.close());
        verticalLayout.addComponent(buttonClose);

        window.setContent(verticalLayout);
        UI.getCurrent().addWindow(window);
    }
}
