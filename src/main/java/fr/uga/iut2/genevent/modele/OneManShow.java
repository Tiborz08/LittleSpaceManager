package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.io.Serializable;
import java.util.Date;

/**
 * Evenement le plus simple, aucune spécialité, et ne possède qu'un artiste.
 */
public class OneManShow extends Evenement implements Serializable {

    /**
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
    public OneManShow(GenEvent genevent,String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        super(genevent,nom, capaciteParticipants, coutInitial, prixTickets, debut, fin, description, salle);
    }

}
