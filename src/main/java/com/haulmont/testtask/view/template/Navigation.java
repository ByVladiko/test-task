package com.haulmont.testtask.view.template;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

public class Navigation extends CustomComponent {

    private final HorizontalLayout content = new HorizontalLayout();

    public Navigation() {
        configureLayout();
        addLinksForNavigation();
    }

    private void addLinksForNavigation() {
        List<Button> links = new ArrayList<>();
        links.add(new Button("Doctors"));
        links.add(new Button("Patients"));
        links.add(new Button("Recipes"));
        links.forEach(content::addComponent);
    }

    private void configureLayout() {
        this.setCompositionRoot(content);
        this.addStyleName(ValoTheme.LINK_LARGE);
        this.setSizeUndefined();
        content.setSizeFull();
        content.setSpacing(true);
        this.setCompositionRoot(content);
    }
}
