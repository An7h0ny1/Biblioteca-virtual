package com.anthony.biblioteca_virtual.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("Activate_Account");


    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}
