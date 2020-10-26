package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.Optional;

@Theme("valo")
public abstract class CrudViewLayout<T> extends VerticalLayout {

    protected HorizontalLayout buttons;
    protected CrudRepository<T> repository;
    protected CrudTable<T> table;
    protected CrudForm<T> form;

    public CrudViewLayout(CrudRepository<T> repository, T entity) {
        this.repository = repository;
        this.buttons = new HorizontalLayout();
        this.table = new CrudTable<>(this);
        this.form = new CrudForm<>(this, entity);
        setup();
    }

    public abstract void addColumnsToTable(Grid<T> grid);

    public abstract HorizontalLayout createFilterForTable();

    public abstract List<Component> createFieldsForForm(Binder<T> binder);

    public void setup() {
        table.setSizeFull();

        buttons.addComponent(createCrudButtons());
        addComponent(buttons);

        HorizontalLayout filterForTable = createFilterForTable();
        if(filterForTable != null) {
            addComponent(filterForTable);
        }

        addComponents(table);
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

    private HorizontalLayout createCrudButtons() {
        HorizontalLayout crudButtons = new HorizontalLayout();

        Button addButton = new Button("Add", VaadinIcons.ADD_DOCK);
        addButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        addButton.addClickListener(event -> {
            form.setCaption("Adding new entity");
            form.setActionType(CrudForm.ActionType.CREATE);
            form.clearFields();
            UI.getCurrent().addWindow(form);
        });
        crudButtons.addComponent(addButton);

        Button editButton = new Button("Edit", VaadinIcons.EDIT);
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

        Button removeButton = new Button("Delete", VaadinIcons.DEL);
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
