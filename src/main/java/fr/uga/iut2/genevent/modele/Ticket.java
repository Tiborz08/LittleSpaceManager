package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

import static fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire.newId;

/**
 * Un ticket relie un spectateur avec un événement, les tickets possèdent une référence unique et sont achetable pour un tarif spécifique à l'événement
 */
public class Ticket implements Serializable {
    private int reference;
    private Evenement evenement;
    private Spectateur spectateur;

    /**
     * Constructeur d'un ticket
     * @param evenement l'évenement accessible via ce ticket
     * @param spectateur le spectateur qui détient ce ticket
     */
    public Ticket(Evenement evenement, Spectateur spectateur) {
        setReference(newId());
        setEvenement(evenement);
        setSpectateur(spectateur);
    }

    /**
     * Renvoie la référence de ce ticket, cette référence est unique au ticket
     * @return la référence du ticket
     */
    public int getReference() {
        return this.reference;
    }

    /**
     * Modifie la référence du ticket
     * @param reference la nouvelle référence du ticket
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     * Retourne l'événement relié à ce ticket
     * @return l'événement relié à ce ticket
     */
    public Evenement getEvenement() {
        return this.evenement;
    }

    /**
     * Modifie l'événement relié à ce ticket
     * @param evenement l'événement à relié au ticket
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * Renvoie le spectateur relié à ce ticket
     * @return le spectateur relié à ce ticket
     */
    public Spectateur getSpectateur() {
        return this.spectateur;
    }

    /**
     * Modifie le spectateur relié à ce ticket
     * @param spectateur le spectateur à relié au ticket
     */
    public void setSpectateur(Spectateur spectateur) {
        this.spectateur = spectateur;
    }
}
