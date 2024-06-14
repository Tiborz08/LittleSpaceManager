package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;

public class Accessorie {

    //ATTRIBUTION
    private String nom;
    private int id;

    //CONSTRUCTEUR

    public Accessorie(String nom) {
        this.nom = nom;
        this.id = LittleSpaceManager_Utilitaire.newId();
    }

    //GETTER

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    //SETTER

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }
}
