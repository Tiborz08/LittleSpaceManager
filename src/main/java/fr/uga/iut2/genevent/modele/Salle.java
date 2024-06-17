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
        setCapacite_max(capacite_max);
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
        if (capacite_max < 0) {
            this.capacite_max = 0;
        } else {
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
        if (!evenements.contains(evenement) && verifierDisponibilite(evenement.getDebut(), evenement.getFin())) {
            evenements.add(evenement);
            evenement.setSalle(this);
        }
    }

    public void enleverDeLHistorique(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.setSalle(null);
    }

    public boolean verifierDisponibilite(Date debut, Date fin) {
        for (Evenement evenement : evenements) {
            if ((debut.compareTo(evenement.getDebut()) >= 0 && debut.compareTo(evenement.getFin()) <= 0)
                    || (fin.compareTo(evenement.getDebut()) >= 0 && fin.compareTo(evenement.getFin()) <= 0)
                    || (debut.compareTo(evenement.getDebut()) <= 0 && fin.compareTo(evenement.getFin()) >= 0)) {
                return false;
            }
        }
        return true;
    }
}
