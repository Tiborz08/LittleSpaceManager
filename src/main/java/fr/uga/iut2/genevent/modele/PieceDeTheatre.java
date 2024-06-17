package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;
import java.util.Date;

public class PieceDeTheatre extends Evenement {

    //ATTRIBUTIONS
    private ArrayList<Accessoire> accessoires;

    //CONSTRUCTEUR
    public PieceDeTheatre(String nom, int capaciteParticipants, int capaciteSpectateur, float coutInitial, float prixTickets, Date debut, Date fin,String description, Salle salle) {
        super(nom, capaciteParticipants, capaciteSpectateur, coutInitial, prixTickets, debut, fin,description, salle);
        this.accessoires = new ArrayList<>();
    }

    //GETTER

    public ArrayList<Accessoire> getAccessoires() {
        return accessoires;
    }

    //METHODES

    /**
     * Permet de créer et d'ajouter un accessoire à une pièce de théatre.
     *
     * @param nom nom de l'accessorie à crée et ajouté.
     * @return l'accessorie crée
     */
    public Accessoire addAccessoire(String nom) {
        Accessoire a = new Accessoire(nom, this);
        accessoires.add(a);
        return a;
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
