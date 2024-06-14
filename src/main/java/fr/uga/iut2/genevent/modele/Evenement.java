package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;

import java.util.ArrayList;
import java.util.Date;

public abstract class Evenement implements Comparable<Evenement> {

    //ATTRIBUTIONS

    private int idEvent;
    private String nom;
    private int capaciteParticipants;
    private int capaciteSpectateur;
    private float coutInitial;
    private float prixTickets;
    private Date debut;
    private Date fin;

    private Salle salle;

    private ArrayList<Participant> participants;
    private ArrayList<Ticket> tickets;

    //CONSTRUCTEUR(S)

    public Evenement(String nom, int capaciteParticipants, int capaciteSpectateur, float coutInitial, float prixTickets, Date debut, Date fin) {
        this.idEvent = LittleSpaceManager_Utilitaire.newId();
        this.nom = nom;
        this.capaciteParticipants = capaciteParticipants;
        this.capaciteSpectateur = capaciteSpectateur;
        this.coutInitial = coutInitial;
        this.prixTickets = prixTickets;
        this.debut = debut;
        this.fin = fin;
        this.tickets = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public int getIdEvent() {
        return idEvent;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public float getPrixTickets() {
        return prixTickets;
    }

    public String getNom() {
        return nom;
    }

    public int getNombreParticipants(){
        return participants.size();
    }

    public int getNombreTickets(){
        return tickets.size();
    }

    /**
     *Ajoute un participant à la liste des participants de l'événement, en vérifiant s'il n'est pas déjà présent, et informe le participant qu'il est inscrit en tant que participant à cet événement.
     * @param participant Un participant qui participe à l'événement.
     */
    public void addParticipant(Participant participant){
        if (!participants.contains(participant) && salle.getCapacite_max() > participants.size() + tickets.size() + 1){
            participants.add(participant);
            participant.addEvenement(this);
        }
    }

    public void setSalle(Salle salle) {
        salle.addEvenement(this);
        this.salle = salle;
    }

    public Salle getSalle() {
        return salle;
    }

    public void removeParticipant(Participant participant){
        if (participants.contains(participant)){
            participants.remove(participant);
            participant.removeEvenement(this);
        }
    }

    /**
     * Trie dans l'odre : dateDebut, dateFin, nom, idEvent
     * @param evenement L'évenement à comparer.
     * @return
     */
    @Override
    public int compareTo(Evenement evenement){
        if(this.getDebut().compareTo(evenement.getDebut()) > 0 || this.getDebut().equals(evenement.getDebut()) && this.getFin().compareTo(evenement.getFin()) > 0 || this.getDebut().equals(evenement.getDebut()) && this.getFin().equals(evenement.getFin()) && this.getNom().compareTo(evenement.getNom()) > 0 || this.getDebut().equals(evenement.getDebut()) && this.getFin().equals(evenement.getFin()) && this.getNom().equals(evenement.getNom()) && this.getIdEvent() > evenement.getIdEvent()){
            return 1;
        }
        else if(this.getDebut().equals(evenement.getDebut()) && this.getFin().equals(evenement.getFin()) && this.getNom().equals(evenement.getNom()) && this.getIdEvent() == evenement.getIdEvent()){
            return 0;
        }
        else{
            return -1;
        }
    }
}
