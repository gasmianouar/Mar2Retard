package com.marre2retard.mar2retard.tools;

import com.marre2retard.mar2retard.models.Voyageur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasmi on 01/01/2018.
 */

public class Singleton implements Serializable {

    /**
     * Instance unique pré-initialisée
     */
    private static Singleton INSTANCE = new Singleton();
    Voyageur voyageur;
    List<Voyageur> lsitVoyageur = new ArrayList<>();
    int like;
    int doNotLike;

    /**
     * Constructeur privé
     */
    private Singleton() {
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static Singleton getInstance() {
        return INSTANCE;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDoNotLike() {
        return doNotLike;
    }

    public void setDoNotLike(int doNotLike) {
        this.doNotLike = doNotLike;
    }

    public Voyageur getVoyageur() {
        return voyageur;
    }

    public void setVoyageur(Voyageur voyageur) {
        this.voyageur = voyageur;
    }

    public List<Voyageur> getLsitVoyageur() {
        return lsitVoyageur;
    }

    public void setLsitVoyageur(List<Voyageur> lsitVoyageur) {
        this.lsitVoyageur = lsitVoyageur;
    }

    /**
     * Sécurité anti-désérialisation
     */
    private Object readResolve() {
        return INSTANCE;
    }


}
