package br.com.paixaopet.core.business.entities;

public enum Gender {
    FEMALE(1), MALE(2);

    public final int id;

    Gender(int id) {
        this.id = id;
    }
}
