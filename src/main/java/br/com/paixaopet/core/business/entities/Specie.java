package br.com.paixaopet.core.business.entities;

public enum Specie {
    CAT(1), DOG(2);

    public final int id;

    Specie(int id) {
        this.id = id;
    }
}
