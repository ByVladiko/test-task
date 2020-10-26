package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.ui.Grid;

import java.util.List;

public class CrudTable<T> extends Grid<T> {

    protected CrudViewLayout<T> crudViewLayout;

    public CrudTable(CrudViewLayout<T> crudViewLayout) {
        this.crudViewLayout = crudViewLayout;
        setup();
    }

    private void setup() {
        crudViewLayout.addColumnsToTable(this);
        setSelectionMode(SelectionMode.SINGLE);
        updateTable();
    }

    public void updateTable() {
        CrudRepository<T> repository = crudViewLayout.getRepository();
        List<T> list = repository.getAll();
        this.setItems(list);
    }
}
