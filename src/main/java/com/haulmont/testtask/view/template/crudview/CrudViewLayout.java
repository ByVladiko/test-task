package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;

import java.util.Map;

public class CrudViewLayout<T> extends CustomComponent {

    private CrudRepository<T> repository;
    private CrudTable<T> table;
    private CrudForm<T> form;

    private Map<String, Field> fieldsMap;

    public CrudViewLayout(CrudRepository<T> repository, Map<String, Field> fieldsMap) {
        this.repository = repository;
        this.table = new CrudTable<>(this, fieldsMap);
        this.form = new CrudForm<>(this, fieldsMap);
        this.fieldsMap = fieldsMap;
        setup();
    }

    public void setup() {
        HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();
        table.setSizeFull();
        this.setSizeUndefined();

        content.addComponents(table, form);
        content.setExpandRatio(table, 1);
        content.setSpacing(true);
        content.setWidth("70%");

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

    public Map<String, Field> getFieldsMap() {
        return fieldsMap;
    }

    public void setFieldsMap(Map<String, Field> fieldsMap) {
        this.fieldsMap = fieldsMap;
    }
}
