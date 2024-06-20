package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;

import java.io.Serializable;

/**
 * Cette class permet de créer des objets de type Accessoire qui nous servirons dans la Class PieceDeTheatre
 */

public class Accessoire implements Serializable {

    //ATTRIBUTION
    private String nom;
    private int id;
    private PieceDeTheatre pieceDeTheatre;

    //CONSTRUCTEUR

    /**
     * Le constructeur de cette class à besoin du nom de l'accessoire et de la pièce de Théàtre auquelle il est relié.
     * @param nom
     * @param pieceDeTheatre
     */

    public Accessoire(String nom, PieceDeTheatre pieceDeTheatre) {
        this.nom = nom;
        this.pieceDeTheatre = pieceDeTheatre;
        this.id = LittleSpaceManager_Utilitaire.newId();
    }

    //GETTER

    /**
     * Cette méthode renvoie le nom de l'accessoire.
     * @return
     */

    public String getNom() {
        return nom;
    }

    /**
     * Cette méthode renvoie l'id associé à l'accessoire. L'id est généré automatique.
     * @return
     */

    public int getId() {
        return id;
    }

    //SETTER

    /**
     * Cette méthode permet de changer le nom d'un accessoire.
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Cette méthode permet de changer l'id d'un accessoire.
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Méthode d'affichage d'un accessoire
     * @return id - nom
     */
    @Override
    public String toString() {
        return id+" - "+nom;
    }
}
