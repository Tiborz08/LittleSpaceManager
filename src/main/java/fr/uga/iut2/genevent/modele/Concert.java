package fr.uga.iut2.genevent.modele;

import java.util.Date;

/**
 * Evénement possèdant un genre musical. Et peut avoir plusieurs artistes.
 */
public class Concert extends Evenement {

    //ATTRIBUTION
    private String genre;

    //CONSTRUCTEUR
    public Concert(String nom, int capaciteParticipants, int capaciteSpectateur, float coutInitial, float prixTickets, Date debut, Date fin,Salle salle) {
        super(nom, capaciteParticipants, capaciteSpectateur, coutInitial, prixTickets, debut, fin,salle);
    }

    //GETTER

    public String getGenre() {
        return genre;
    }

    //SETTER

    /**
     * Met le genre du concert en MAJUSCULE.
     *
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre.toUpperCase();
    }
}
