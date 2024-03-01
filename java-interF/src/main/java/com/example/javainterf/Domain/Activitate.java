package com.example.javainterf.Domain;

import java.util.List;

public class Activitate extends Entity {
    private String data;
    private int nrPasi;
    private int nrOreSomn;

    private List<ActivitateFizica> activitati;

    public Activitate(Integer id, String data, int nrPasi, int nrOreSomn) {
        super(id);
        this.data = data;
        this.nrPasi = nrPasi;
        this.nrOreSomn = nrOreSomn;
    }

    public String getData() {
        return data;
    }

    public int getNrPasi() {
        return nrPasi;
    }

    public int getNrOreSomn() {
        return nrOreSomn;
    }

    public List<ActivitateFizica> getActivitati() {
        return activitati;
    }

    public void setActivitati(List<ActivitateFizica> activitati) {
        this.activitati = activitati;
    }

    public void setNrPasi(int nrPasi) {
        this.nrPasi = nrPasi;
    }

    public void setNrOreSomn(int nrOreSomn) {
        this.nrOreSomn = nrOreSomn;
    }

    @Override
    public String toString() {
        return (this.data + "; " + this.nrPasi + "; " + this.nrOreSomn + "; " + this.activitati.toString());
    }
}
