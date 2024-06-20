package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.io.Serializable;
import java.util.Date;

/**
 * Evenement le plus simple, aucune spécialité, et ne possède qu'un artiste.
 */
public class OneManShow extends Evenement implements Serializable {

    //CONSTRUCTEUR


    public OneManShow(GenEvent genevent,String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        super(genevent,nom, capaciteParticipants, coutInitial, prixTickets, debut, fin, description, salle);
    }

}
