package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;

import java.util.ArrayList;
import java.util.Date;

public abstract class Evenement {

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
    private ArrayList<Spectateur> spectateurs;

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
        this.spectateurs = new ArrayList<Spectateur>();
        this.participants = new ArrayList<Participant>();
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

    public ArrayList<Spectateur> getSpectateurs() {
        return spectateurs;
    }

    public float getPrixTickets() {
        return prixTickets;
    }

    public String getNom() {
        return nom;
    }

    public int getNombreParticipants(){
        return spectateurs.size();
    }

    public int getNombreSpectateurs(){
        return spectateurs.size();
    }



}
