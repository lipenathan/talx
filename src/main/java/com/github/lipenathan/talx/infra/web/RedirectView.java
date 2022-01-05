package com.github.lipenathan.talx.infra.web;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class RedirectView {

    private String viewName;

    public RedirectView() {
    }

    public RedirectView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String toString() {
        return this.viewName + "?faces-redirect=true";
    }
}
