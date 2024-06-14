package fr.uga.iut2.genevent.modele;

import static fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire.newId;

public class Ticket {
    private int reference;
    private Evenement evenement;
    private Spectateur spectateur;

    public Ticket(Evenement evenement, Spectateur spectateur) {
        setReference(newId());
        setEvenement(evenement);
        setSpectateur(spectateur);
    }

    public int getReference() {
        return this.reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public Evenement getEvenement() {
        return this.evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Spectateur getSpectateur() {
        return this.spectateur;
    }

    public void setSpectateur(Spectateur spectateur) {
        this.spectateur = spectateur;
    }
}
