package com.haulmont.testtask.view.template.crudview;

import com.haulmont.testtask.repository.CrudRepository;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class CrudForm<T> extends Window {

    protected CrudViewLayout<T> crudViewLayout;

    private FormLayout formLayout = new FormLayout();
    private final Button saveButton = new Button("Save");
    private ActionType actionType;

    private Binder<T> binder;
    private T entity;

    public CrudForm(CrudViewLayout<T> crudViewLayout, T entity) {
        this.crudViewLayout = crudViewLayout;
        this.binder = new Binder<>();
        this.entity = entity;
        setup();
    }

    public T getEntity() {
        return entity;
    }

    public FormLayout getFormLayout() {
        return formLayout;
    }

    public void setFormLayout(FormLayout formLayout) {
        this.formLayout = formLayout;
    }

    public void setEntity(T entity) {
        this.entity = entity;
        binder.setBean(entity);
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Binder<T> getBinder() {
        return binder;
    }

    public void setBinder(Binder<T> binder) {
        this.binder = binder;
    }

    public void setup() {
        formLayout.setSizeUndefined();
        crudViewLayout.createFieldsForForm(binder).forEach(component -> formLayout.addComponent(component));

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton);
        formLayout.addComponent(buttonsLayout);
        configureButtons();

        formLayout.setMargin(true);
        setContent(formLayout);

        setModal(true);
        setResizable(false);
        center();
    }

    public void clearFields() {
        formLayout.forEach(field -> {
            if(field instanceof AbstractField) {
                ((AbstractField<?>) field).clear();
            }
        });
    }

    public void configureButtons() {
        saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        saveButton.addClickListener(clickEvent -> save(entity));
    }

    private void save(T entity) {
        CrudRepository<T> repository = crudViewLayout.getRepository();

        if (binder.writeBeanIfValid(entity)) {
            if (actionType == ActionType.UPDATE) {
                binder.setBean(entity);
                repository.update(entity);
                crudViewLayout.getTable().updateTable();
                close();
                Notification.show("Successful", Notification.Type.TRAY_NOTIFICATION);
            } else if (actionType == ActionType.CREATE) {
                repository.save(entity);
                crudViewLayout.getTable().updateTable();
                close();
                Notification.show("Successfully", Notification.Type.TRAY_NOTIFICATION);
            }
        }
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    protected enum ActionType {
        CREATE, UPDATE
    }
}
