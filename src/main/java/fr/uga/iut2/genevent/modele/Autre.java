package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.util.Date;

/**
 * Tout les autres événements, pour les locations exceptionnelles... (mariages, yoga, etc)
 */
public class Autre extends Evenement {

    //CONSTRUCTEUR

    public Autre(String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin,String description, Salle salle) throws CreateException {
        super(nom, capaciteParticipants, coutInitial, prixTickets, debut, fin,description, salle);
    }

}