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
    private TreeSet<String> tags;
    private TreeSet<Evenement> evenements;

    public Salle(String nom, String adresse, int capacite_max, String tagsLong) {
        setNom(nom);
        setAdresse(adresse);
        setCapacite_max(capacite_max);
        this.tags = new TreeSet<>();
        this.setTags(tagsLong);
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
        this.capacite_max = Math.max(capacite_max, 0);
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

    /**
     * Retourne la liste des évenements qui n'ont pas encore débutés
     *
     * @return La liste des évenements n'ayant pas encore débuté, trié par ordre chronologique
     */
    public TreeSet<Evenement> getEvenementsFuturs() {
        TreeSet<Evenement> evenementsFuturs = new TreeSet<>();
        Date aujourdhui = new Date();
        for (Evenement evenement : evenements) {
            if (evenement.getDebut().after(aujourdhui)) {
                evenementsFuturs.add(evenement);
            }
        }
        return evenementsFuturs;
    }

    //====== GESTION DES TAGS ====================================


    public TreeSet<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setTags(String tagsLong) {
        String[] tagsSplit = tagsLong.split(",");
        for (String tag : tagsSplit) {
            tags.add(tag.toLowerCase());
        }
    }


    @Override
    public String toString() {
        return idSalle + " - " + nom;
    }
}
