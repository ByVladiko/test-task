package com.haulmont.testtask.view.template.crudview;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Map;

public class CrudForm<T> extends FormLayout {

    protected CrudViewLayout<T> crudViewLayout;

    protected Map<String, Field> fields;
    protected final Button saveButton = new Button("Save");
    protected final Button deleteButton = new Button("Delete");

    private T entity;

    public CrudForm(CrudViewLayout<T> crudViewLayout, Map<String, Field> fields) {
        this.crudViewLayout = crudViewLayout;
        this.fields = fields;
        setup();
    }

    public void setup() {
        setSizeUndefined();

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, deleteButton);
        buttonsLayout.setSpacing(true);

        addComponent(buttonsLayout);

        configureButtons();
    }

    public void configureButtons() {
        saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        saveButton.addClickListener(clickEvent -> save(entity));

        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent -> delete(entity));
    }

    private void save(T obj) {
        crudViewLayout.getRepository().save(obj);
    }

    private void delete(T obj) {
        crudViewLayout.getRepository().delete(obj);
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
        BeanFieldGroup.bindFieldsUnbuffered(entity, fields);
        setVisible(true);
    }
}
