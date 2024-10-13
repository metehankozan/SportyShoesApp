package com.sportyshoes.SportyShoesApp.type;

public enum UserType {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private String value;

    private UserType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
