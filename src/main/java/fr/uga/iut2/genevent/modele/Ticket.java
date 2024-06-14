package fr.uga.iut2.genevent.modele;

public class Ticket {
    private int reference;
    private Evenement evenement;
    private Spectateur spectateur;
    public Ticket(int reference, Evenement evenement, Spectateur spectateur) {
        setReference(reference);
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
