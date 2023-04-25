package com.example.zadanie24;

public enum Type {

    INCOME("przychód"),
    EXPENSE("wydatek");

    private String plDescription;

    Type(String plDescription) {
        this.plDescription = plDescription;
    }

    public String getPlDescription() {
        return plDescription;
    }

    public void setPlDescription(String plDescription) {
        this.plDescription = plDescription;
    }
}
