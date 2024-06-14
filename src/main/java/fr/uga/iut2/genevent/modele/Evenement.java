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

    /**
     * Cette fonction permet d'enlever une participant dans la liste des participants de l'événement. La fonction vérifie que le participant est bien présent dans la liste puis elle le supprime et elle supprime l'événement dans la liste des événement du participant.
     * @param participant
     */

    public void removeParticipant(Participant participant){
        if (participants.contains(participant)){
            participants.remove(participant);
            participant.removeEvenement(this);
        }
    }

    /**
     * Cette fonction permet de créer un ticket pour un spectateur donné. La fonction ne vérifie pas si le spectateur possède déjà un ticket car il peut en posséder plusieurs.
     * @param spectateur
     */

    public void addTicket(Spectateur spectateur){
        Ticket ticket = new Ticket(this,spectateur);
        tickets.add(ticket);
        spectateur.addTicket(ticket);
    }

    /**
     * Cette fonction renvoie la capacité max que la salle peut accueillir. Ici, la capacité maximale de Participants et de Spectateur sont additionnées.
     * @return
     */

    public int getCapaciteTotal() {
        return capaciteParticipants + capaciteSpectateur;
    }

    /**
     * Cette fonction permet de changer la captacité de place attribuées pour les participants. La fonction se charge de vérifier que la capacité maximale de la salle n'est pas dépassée.
     * @param capacite
     */

    public void setCapaciteParticipants(int capacite) {
        if (capaciteSpectateur + capacite <= salle.getCapacite_max()){
            this.capaciteParticipants = capacite;
        }

    }

    /**
     * Cette fonction permet de changer la captacité de place attribuées pour les spectateurs. La fonction se charge de vérifier que la capacité maximale de la salle n'est pas dépassée.
     * @param capacite
     */

    public void setCapaciteSpectateur(int capacite) {
        if (capaciteParticipants + capacite <= salle.getCapacite_max()){
            this.capaciteParticipants = capacite;
        }
    }

    /**
     * Cette fonction sert à changer la date de début de l'événement en s'assurant que la salle est disponible.
     * @param debutEv
     */

    public void setDebut(Date debutEv) {
        if (salle.verifierDisponibilite(debutEv,this.fin)){
            this.debut = debut;
        }
    }

    /**
     * Cette fonction sert à changer la date de fin de l'événement en s'assurant que la salle est disponible.
     * @param finEv
     */

    public void setFin(Date finEv) {
        if (salle.verifierDisponibilite(this.debut,finEv)){
            this.fin = fin;
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
