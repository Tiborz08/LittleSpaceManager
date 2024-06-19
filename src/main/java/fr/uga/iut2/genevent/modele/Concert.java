package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.util.Date;

/**
 * Evénement possèdant un genre musical. Et peut avoir plusieurs artistes.
 */
public class Concert extends Evenement {

    //ATTRIBUTION
    private String genre;

    //CONSTRUCTEUR
    public Concert(String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        super(nom, capaciteParticipants, coutInitial, prixTickets, debut, fin, description, salle);

        setGenre("Inconnu");
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
