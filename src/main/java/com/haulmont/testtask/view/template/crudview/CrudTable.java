package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;

import java.util.List;
import java.util.Map;

public class CrudTable<T> extends Grid {

    protected CrudViewLayout<T> crudViewLayout;
    private Map<String, Field> fieldsMap;

    public CrudTable(CrudViewLayout<T> crudViewLayout, Map<String, Field> fieldsMap) {
        this.crudViewLayout = crudViewLayout;
        this.fieldsMap = fieldsMap;
        setup();
    }

    private void setup() {
        configureTable();
        updateTable();
        this.setColumns(fieldsMap.keySet().toArray());
    }

    private void configureTable() {
        this.addSelectionListener(event -> {
            CrudForm<T> form = crudViewLayout.getForm();
            if(event.getSelected() == null) {
                form.setVisible(false);
            } else {
                T obj = (T) event.getSelected().iterator().next();
                form.setEntity(obj);
            }
        });
    }

    protected void updateTable() {
        CrudRepository<T> repository = crudViewLayout.getRepository();
        List<T> list = repository.getAll();
        BeanItemContainer<T> container = new BeanItemContainer<>(list);
        this.setContainerDataSource(container);
    }

    public CrudViewLayout<T> getCrudViewLayout() {
        return crudViewLayout;
    }

    public void setCrudViewLayout(CrudViewLayout<T> crudViewLayout) {
        this.crudViewLayout = crudViewLayout;
    }

    public Map<String, Field> getFieldsMap() {
        return fieldsMap;
    }

    public void setFieldsMap(Map<String, Field> fieldsMap) {
        this.fieldsMap = fieldsMap;
    }
}
