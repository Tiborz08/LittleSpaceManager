package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;

public class Accessoire {

    //ATTRIBUTION
    private String nom;
    private int id;
    private PieceDeTheatre pieceDeTheatre;

    //CONSTRUCTEUR

    public Accessoire(String nom, PieceDeTheatre pieceDeTheatre) {
        this.nom = nom;
        this.pieceDeTheatre = pieceDeTheatre;
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

    @Override
    public String toString() {
        return id+" - "+nom;
    }
}
