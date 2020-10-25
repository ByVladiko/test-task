package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.Optional;

public abstract class CrudViewLayout<T> extends CustomComponent {

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
        VerticalLayout content = new VerticalLayout();

        this.setSizeUndefined();
        table.setSizeFull();

        content.addComponent(createCrudButtons());
        content.addComponents(table);

        this.setCompositionRoot(content);
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

        Button addButton = new Button("Add");
        addButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.addClickListener(event -> {
            form.setCaption("Adding new entity");
            form.setActionType(CrudForm.ActionType.CREATE);
            form.clearFields();
            UI.getCurrent().addWindow(form);
        });
        crudButtons.addComponent(addButton);

//        Button edit = new Button("Edit");
//        edit.setStyleName(ValoTheme.BUTTON_FRIENDLY);
//        edit.setVisible(false);
//        edit.addClickListener(event -> {
//           form.setCaption("Editing entity");
//           form.setActionType(CrudForm.ActionType.UPDATE);
//           UI.getCurrent().addWindow(form);
//        });

        Button removeButton = new Button("Delete");
        removeButton.setStyleName(ValoTheme.BUTTON_DANGER);
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
