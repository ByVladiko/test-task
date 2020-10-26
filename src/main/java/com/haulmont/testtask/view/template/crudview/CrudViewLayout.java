package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.Optional;

public abstract class CrudViewLayout<T> {

    private final VerticalLayout content = new VerticalLayout();

    protected CrudRepository<T> repository;
    protected CrudTable<T> table;
    protected CrudForm<T> form;

    public CrudViewLayout(CrudRepository<T> repository, T entity) {
        this.repository = repository;
        this.table = new CrudTable<>(this);
        this.form = new CrudForm<>(this, entity);
        setup();
    }

    public abstract List<Component> createFieldsForForm(Binder<T> binder);

    public abstract void addColumnsToTable(Grid<T> grid);

    public void setup() {
        table.setSizeFull();

        HorizontalLayout crudButtons = createCrudButtons();
        content.addComponent(crudButtons);
        content.addComponents(table);
    }

    public CrudRepository<T> getRepository() {
        return repository;
    }

    public void setRepository(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public CrudTable<T> getTable() {
        return table;
    }

    public void setTable(CrudTable<T> table) {
        this.table = table;
    }

    public CrudForm<T> getForm() {
        return form;
    }

    public void setForm(CrudForm<T> form) {
        this.form = form;
    }

    public VerticalLayout getContent() {
        return content;
    }

    private HorizontalLayout createCrudButtons() {
        HorizontalLayout crudButtons = new HorizontalLayout();

        Button addButton = new Button("Add");
        addButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        addButton.addClickListener(event -> {
            form.setCaption("Adding new entity");
            form.setActionType(CrudForm.ActionType.CREATE);
            form.clearFields();
            UI.getCurrent().addWindow(form);
        });
        crudButtons.addComponent(addButton);

        Button editButton = new Button("Edit");
        editButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        editButton.addClickListener(event -> {
                    Optional<T> selectedItem = table.getSelectionModel().getFirstSelectedItem();
                    if (selectedItem.isPresent()) {
                        form.setCaption("Editing entity");
                        form.setEntity(selectedItem.get());
                        form.setActionType(CrudForm.ActionType.UPDATE);
                        UI.getCurrent().addWindow(form);
                    }
                }
        );
        crudButtons.addComponent(editButton);

        Button removeButton = new Button("Delete");
        removeButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        removeButton.addClickListener(event -> {
            Optional<T> selectedItem = table.getSelectionModel().getFirstSelectedItem();
            if(selectedItem.isPresent()) {
                repository.delete(selectedItem.get());
                table.updateTable();
                Notification.show("Successful", Notification.Type.TRAY_NOTIFICATION);
            }
        });
        crudButtons.addComponent(removeButton);

        return crudButtons;
    }
}
