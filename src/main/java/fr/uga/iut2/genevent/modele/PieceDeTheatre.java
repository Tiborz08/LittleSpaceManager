package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;
import java.util.Date;

public class PieceDeTheatre extends Evenement{

    //ATTRIBUTIONS
    private ArrayList<Accessoire> accessoires = new ArrayList<>();

    //CONSTRUCTEUR
    public PieceDeTheatre(String nom, int capaciteParticipants, int capaciteSpectateur, float coutInitial, float prixTickets, Date debut, Date fin) {
        super(nom, capaciteParticipants, capaciteSpectateur, coutInitial, prixTickets, debut, fin);
        this.accessoires=new ArrayList<>();
    }

    //GETTER

    public ArrayList<Accessoire> getAccessoires() {
        return accessoires;
    }

    //METHODES

    /**
     * Permet d'ajouter un accessoire à une pièce de théatre. Si l'accessoire est null ou est déjà dans l'évenement, rien ne se passe.
     * @param a accessoire à ajouté.
     */
    public void addAccessoire(Accessoire a){
        if(!accessoires.contains(a) && a!=null){
            accessoires.add(a);
        }
    }

    /**
     * Permet de retirer un accessoire d'une de théatre, si l'accessoire n'était pas dans l'évenement, rien ne se passe.
     * @param a accessorie à retiré.
     */
    public void removeAccessorie(Accessoire a){
        accessoires.remove(a);
    }
}
