package com.example.javainterf.Domain;

public class ActivitateFizica {
    private String descriere;
    private int minuteMiscare;

    public ActivitateFizica(String descriere, int minuteMiscare) {
        this.descriere = descriere;
        this.minuteMiscare = minuteMiscare;
    }

    public String getDescriere() {
        return descriere;
    }

    public int getMinuteMiscare() {
        return minuteMiscare;
    }

    @Override
    public String toString() {
        return (this.descriere + "," + this.getMinuteMiscare() + ";");
    }
}
