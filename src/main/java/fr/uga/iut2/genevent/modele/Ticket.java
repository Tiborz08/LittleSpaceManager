package fr.uga.iut2.genevent.modele;

public class Ticket {
    private int reference;
    private Evenement evenement;
    private Spectateurs spectateur;
    public Ticket(int reference, Evenement evenement, Spectateurs spectateur) {
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

    public Spectateurs getSpectateur() {
        return this.spectateur;
    }

    public void setSpectateur(Spectateurs spectateur) {
        this.spectateur = spectateur;
    }
}
