package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

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
        addItemClickListener(event -> {
                    if (event.getMouseEventDetails().isDoubleClick()) {
                        CrudForm<T> form = crudViewLayout.form;
                        form.setCaption("Editing entity");
                        form.setEntity(event.getItem());
                        form.setActionType(CrudForm.ActionType.UPDATE);
                        UI.getCurrent().addWindow(form);
                    }
                }
        );
        updateTable();
    }

    protected void updateTable() {
        CrudRepository<T> repository = crudViewLayout.getRepository();
        List<T> list = repository.getAll();
        this.setItems(list);
    }

    public CrudViewLayout<T> getCrudViewLayout() {
        return crudViewLayout;
    }

    public void setCrudViewLayout(CrudViewLayout<T> crudViewLayout) {
        this.crudViewLayout = crudViewLayout;
    }
}
