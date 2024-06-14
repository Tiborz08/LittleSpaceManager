package fr.uga.iut2.genevent.modele;

import java.util.Date;

/**
 * Evenement le plus simple, aucune spécialité, et ne possède qu'un artiste.
 */
public class OneManShow extends Evenement{

    //CONSTRUCTEUR
    public OneManShow(String nom, int capaciteParticipants, int capaciteSpectateur, float coutInitial, float prixTickets, Date debut, Date fin) {
        super(nom, capaciteParticipants, capaciteSpectateur, coutInitial, prixTickets, debut, fin);
    }

}
