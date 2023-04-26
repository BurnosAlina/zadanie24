package com.example.zadanie24;

public enum Type {

    INCOME("przychód"),
    EXPENSE("wydatek");

    private String plDescription;

    public static Type findTypeByDescriptionPl(String descriptionPl) {
        Type[] values = Type.values();
        for (Type value : values) {
            if (value.plDescription.equals(descriptionPl)) {
                return value;
            }
        }
        throw new NotMatchingDescriptionException("Nieprawidłowy typ!");
    }

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
