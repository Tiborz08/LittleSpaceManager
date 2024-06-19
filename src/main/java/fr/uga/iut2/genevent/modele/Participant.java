package fr.uga.iut2.genevent.modele;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

public abstract class Participant extends Personne {

    private static final Log log = LogFactory.getLog(Participant.class);
    private ArrayList<Evenement> evenements = new ArrayList<>();
    private float salaire;

    //CONSTRUCTEUR
    public Participant(String nom, String prenom, float salaire) {
        super(nom, prenom);
        setSalaire(salaire);
    }
    //SETTER

    /**
     * Définit la valeur du salaire d'un participant. Si le salaire du participant est négatif, alors le salaire est égal à 0.
     *
     * @param salaire Réel à virgule qui représente le salaire du participant.
     */
    public void setSalaire(float salaire) {
        this.salaire = Math.max(0, salaire);
        log.info("Le salaire de " + this.getNom() + " est de " + this.getSalaire() + "€.");
    }

    //GETTER
    public float getSalaire() {
        return salaire;
    }

    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }
    //METHODE

    /**
     * Ajoute un événement à la liste des événements auxquels participe le participant, en vérifiant s'il n'est pas déjà présent, et informe l'événement qu'il fait partie de ses participants.
     *
     * @param evenement Un événement auquel les participants participent.
     */
    public void addEvenement(Evenement evenement) {
        if (!evenements.contains(evenement)) {
            this.evenements.add(evenement);
            evenement.addParticipant(this);
        }
    }

    /**
     * Retire un événement de la liste des événements auxquels participe le participant, en vérifiant s'il est présent
     * Informe l'événement que le participant n'est plus un de ses participants.
     *
     * @param evenement Un événement auquel le participant participe.
     */
    public void removeEvenement(Evenement evenement) {
        if (evenements.contains(evenement)) {
            evenement.removeParticipant(this);
            evenements.remove(evenement);
        }
    }
}
