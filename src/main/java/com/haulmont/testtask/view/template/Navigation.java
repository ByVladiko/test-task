package com.haulmont.testtask.view.template;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
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
        List<Link> links = new ArrayList<>();
        links.add(new Link("Doctors", new ExternalResource("http://localhost:8080//doctors")));
        links.add(new Link("Patients", new ExternalResource("http://localhost:8080//patients")));
        links.add(new Link("Recipes", new ExternalResource("http://localhost:8080//recipes")));
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
