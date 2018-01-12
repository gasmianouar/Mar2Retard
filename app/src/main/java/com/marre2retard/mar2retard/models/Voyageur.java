package com.marre2retard.mar2retard.models;

import java.util.List;

/**
 * Created by gasmi on 29/12/2017.
 */

public class Voyageur {
    // psoeudo voyageur
    private String psoeudo;
    // voyageur tavel
    private Train train;
    //retard
    private String retard;
    // commentaire
    private String commentaire;
    // if travaler has taked photo
    private List<String> listPhotoBase64;
    // date shared
    private String datePublication;


    // constructor
    public Voyageur(String psoeudo, Train train, String retard, String commentaire, String date, List<String> listPhotoBase64) {
        this.psoeudo = psoeudo;
        this.train = train;
        this.retard = retard;
        this.commentaire = commentaire;
        this.datePublication = date;
        this.listPhotoBase64 = listPhotoBase64;
    }
    // getter


    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getPsoeudo() {
        return psoeudo;
    }

    public void setPsoeudo(String psoeudo) {
        this.psoeudo = psoeudo;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    //setter

    public String getRetard() {
        return retard;
    }

    public void setRetard(String retard) {
        this.retard = retard;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public List<String> getListPhotoBase64() {
        return listPhotoBase64;
    }

    public void setListPhotoBase64(List<String> listPhotoBase64) {
        this.listPhotoBase64 = listPhotoBase64;
    }
}
