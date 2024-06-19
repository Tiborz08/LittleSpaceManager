package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.util.Date;

/**
 * Evénement possèdant un genre musical. Et peut avoir plusieurs artistes.
 */
public class Concert extends Evenement {

    //ATTRIBUTION
    private String genre;

    /**
     * Le constructeur des objets Concert prend en paramètre le nom du concert, la capacité de participants qu'il va accueillir, le cout initial nécessaire, le prix des tickets, une date de débt et de fin, une description du Concert, ainsi qu'une salle dans laquelle le concert va se passer.
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

    //CONSTRUCTEUR
    public Concert(String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        super(nom, capaciteParticipants, coutInitial, prixTickets, debut, fin, description, salle);

        setGenre("Inconnu");
    }

    //GETTER

    /**
     * Cette foncton renvoie le genre associé au concert.
     * @return
     */

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
