package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.io.Serializable;
import java.util.Date;

/**
 * Evénement possèdant un genre musical. Et peut avoir plusieurs artistes.
 */
public class Concert extends Evenement implements Serializable {

    //ATTRIBUTION
    private String genre;

    /**
     * Le constructeur des objets Concert prend en paramètre le nom du concert, la capacité de participants qu'il va accueillir, le cout initial nécessaire, le prix des tickets, une date de débt et de fin, une description du Concert, ainsi qu'une salle dans laquelle le concert va se passer.
     * @param genevent le gestionnaire de sauvegarde
     * @param nom le nom de l'événement
     * @param capaciteParticipants le nombre de salariés maximal
     * @param coutInitial le cout de base d'organisation de l'événement
     * @param prixTickets le prix d'un seul ticket
     * @param debut la date de début de l'événement
     * @param fin la date de fin de l'événement
     * @param description la description de l'événement
     * @param salle la salle dans laquelle se déroulé l'événement
     * @throws CreateException Le gestionaire des erreurs qui force certainnes caractéristiques pour un événement
     */

    //CONSTRUCTEUR
    public Concert(GenEvent genevent,String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        super(genevent,nom, capaciteParticipants, coutInitial, prixTickets, debut, fin, description, salle);

        setGenre("Inconnu");
    }

    //GETTER

    /**
     * Cette foncton renvoie le genre associé au concert.
     * @return le genre du concert
     */

    public String getGenre() {
        return genre;
    }

    //SETTER

    /**
     * Met le genre du concert en MAJUSCULE.
     *
     * @param genre le nouveau genre du concert
     */
    public void setGenre(String genre) {
        this.genre = genre.toUpperCase();
    }
}
