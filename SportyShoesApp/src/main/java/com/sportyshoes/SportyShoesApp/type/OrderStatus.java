package com.sportyshoes.SportyShoesApp.type;

public enum OrderStatus {

    PENDING("PENDING"),
    APPROVED("APPROVED"),
    CANCELLED("CANCELLED");

    private String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
