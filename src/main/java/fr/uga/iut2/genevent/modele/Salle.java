package fr.uga.iut2.genevent.modele;

import java.util.Date;
import java.util.TreeSet;

import static fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire.capitalize;
import static fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire.newId;


public class Salle {
    private String nom;
    private String adresse;
    private int capacite_max;
    private int idSalle;
    private TreeSet<Evenement> evenements;

    public Salle(String nom, String adresse, int capacite_max) {
        setNom(nom);
        setAdresse(adresse);
        setAdresse(adresse);
        this.idSalle = newId();
        this.evenements = new TreeSet<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = capitalize(nom);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = capitalize(adresse);
    }

    public int getCapacite_max() {
        return capacite_max;
    }

    public void setCapacite_max(int capacite_max) {
        if(capacite_max < 0) {
            this.capacite_max = 0;
        }
        else {
            this.capacite_max = capacite_max;
        }
    }

    public int getIdSalle() {
        return idSalle;
    }

    public TreeSet<Evenement> getHistoriqueEvenements() {
        return evenements;
    }

    public void addEvenement(Evenement evenement) {
        //Disponibilité de la salle à verifier /!\
        if(!evenements.contains(evenement)){
            evenements.add(evenement);
            evenement.setSalle(this);
        }
    }
    
    public void enleverDeLHistorique(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.setSalle(null);
    }

    public boolean verifierDisponibilite(Date dateDebut, Date dateFin){
        for(Evenement evenement : this.evenements){
            if(evenement.getDebut().before(dateFin) && evenement.getFin().after(dateDebut)){
                return false;
            }
        }
        return true;
    }
}
