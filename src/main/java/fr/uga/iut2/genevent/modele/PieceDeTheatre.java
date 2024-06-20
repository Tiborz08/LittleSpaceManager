package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Cette class permet de créer des objets de type PieceDeTheatre. Cette class hérite de la class Evenement.
 */

public class PieceDeTheatre extends Evenement implements Serializable {

    //ATTRIBUTIONS
    private ArrayList<Accessoire> accessoires;

    //CONSTRUCTEUR

    /**
     * Le constructeur des objets PieceDeTheatre prend en paramètre le nom de la Piece, la capacité de spectateur qu'elle va accueillir, le cout initial nécessaire, le prix des tickets, une date de débt et de fin, une description de la pièce de théàtre, ainsi qu'une salle dans laquelle la pièce va se passer.
     * @param nom
     * @param capaciteParticipants
     * @param coutInitial
     * @param prixTickets
     * @param debut
     * @param fin
     * @param description
     * @param salle
     * @throws CreateException
     */
    public PieceDeTheatre(GenEvent genevent,String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        super(genevent,nom, capaciteParticipants, coutInitial, prixTickets, debut, fin, description, salle);
        this.accessoires = new ArrayList<>();
    }

    //GETTER

    @Override
    /**
     * Cette fonction renvoie la liste des accessoires reliés à la pièce de théàtre.
     * @return
     */
    public ArrayList<Accessoire> getAccessoires() {
        return accessoires;
    }

    //METHODES

    @Override
    /**
     * Permet de créer et d'ajouter un accessoire à une pièce de théatre.
     *
     * @param nom nom de l'accessorie à crée et ajouté.
     */
    public void addAccessoire(String nom) {
        accessoires.add(new Accessoire(nom, this));
    }

    /**
     * Permet de retirer un accessoire d'une de théatre, si l'accessoire n'était pas dans l'évenement, rien ne se passe.
     *
     * @param a accessorie à retiré.
     */
    public void removeAccessoire(Accessoire a) {
        accessoires.remove(a);
    }
}
