package com.iutclermont.labyrintheescape;

import java.io.Serializable;

public class Joueur implements Serializable {
    private String joueurName;
    private int niveauMax;

    public Joueur(String joueurName, int niveauMax){
        this.niveauMax=niveauMax;
        this.joueurName=joueurName;
    }

    public String getJoueurName() {
        return joueurName;
    }

    public int getNiveauMax() {
        return niveauMax;
    }

    public void setJoueurName(String joueurName) {
        this.joueurName = joueurName;
    }

    public void setNiveauMax(int niveauMax) {
        this.niveauMax = niveauMax;
    }
    @Override
    public String toString(){
        return this.joueurName + this.niveauMax;
    }
}
