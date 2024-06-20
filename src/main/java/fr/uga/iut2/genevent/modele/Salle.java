package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.controleur.MainControleur;
import fr.uga.iut2.genevent.exception.CreateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

import static fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire.capitalize;
import static fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire.newId;


public class Salle implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final GenEvent genevent;
    private String nom;
    private String adresse;
    private int capacite_max;
    private int idSalle;
    private TreeSet<String> tags;
    private TreeSet<Evenement> evenements;

    private static final Log log = LogFactory.getLog(Salle.class);

    /**
     * Crée une nouvelle salle
     * @param nom le nom de la salle
     * @param adresse l'adresse de la salle
     * @param capacite_max le nombre maximum de personnes qui peuvent être dans la salle
     * @param tagsLong les caractéristiques propres à la salle (Exemple : si il y a des prises électrique ou une buvette)
     */
    public Salle(GenEvent genevent,String nom, String adresse, int capacite_max, String tagsLong) {
        this.genevent=genevent;
        setNom(nom);
        setAdresse(adresse);
        setCapacite_max(capacite_max);
        this.tags = new TreeSet<>();
        this.setTags(tagsLong);
        this.idSalle = newId();
        this.evenements = new TreeSet<>();
    }

    /**
     * Renvoie le nom de la salle
     * @return le nom de la salle
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de la salle, ce nom est capitalisé
     * @param nom le nom de la salle
     */
    public void setNom(String nom) {
        this.nom = capitalize(nom);
    }

    /**
     * Renvoie l'adresse de la salle
     * @return l'adresse de la salle
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie l'adresse de la salle, ce nom est capitalisé
     * @param adresse l'adresse de la salle
     */
    public void setAdresse(String adresse) {
        this.adresse = capitalize(adresse);
    }

    /**
     * Renvoie la capacité maximale de la salle
     * @return
     */
    public int getCapacite_max() {
        return capacite_max;
    }

    /**
     * Modifie la capacité maximale de la salle, cette capacité ne peut pas être négative
     * @param capacite_max la capacité maximale de la salle
     */
    public void setCapacite_max(int capacite_max) {
        this.capacite_max = Math.max(capacite_max, 0);
    }

    /**
     * Retourne l'identifiant unique de la salle
     * @return l'identifiant de la salle
     */
    public int getIdSalle() {
        return idSalle;
    }

    /**
     * Renvoie tous les événements qui ont déjà eu lieu ou qui sont prévu dans cette salle
     * @return tous les événements qui ont déjà eu lieu ou qui sont prévu dans cette salle
     */
    public TreeSet<Evenement> getHistoriqueEvenements() {
        return evenements;
    }

    /**
     * Rajoute un événement dans la salle
     * @param evenement l'événement à rajouter
     * @throws CreateException Averti des problèmes lors de la liaison avec une salle (Exemple : Un événement a déjà lieu dans la salle pour les dates de l'événement
     */
    public void addEvenement(Evenement evenement) throws CreateException {
        if (!evenements.contains(evenement) && verifierDisponibilite(evenement.getDebut(), evenement.getFin())) {
            evenements.add(evenement);
            evenement.setSalle(this);
        }
    }

    /**
     * Supprime la liaison entre une salle et un événement
     * @param evenement l'événement a enlever de la salle
     */
    public void enleverDeLHistorique(Evenement evenement) throws CreateException {
        this.evenements.remove(evenement);
        evenement.setSalle(null);
    }

    /**
     * Verifie si la salle est disponible aux dates fournies
     * @param debut la date de début
     * @param fin la date de fin
     * @return true ou false si la salle est disponible pour cet intervalle
     */
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


    /**
     * Revoie la liste des specificités de la salle
     * @return la liste des specificités de la salle
     */
    public TreeSet<String> getTags() {
        return tags;
    }

    /**
     * Permet d'ajouter une specificité à la salle
     * @param tag la specificité de la salle
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Permet d'ajouter une liste de specificité à une salle
     * @param tagsLong La liste des spécificités
     */
    public void setTags(String tagsLong) {
        if(tagsLong.isEmpty() || tagsLong == null){
            log.warn("Aucun tag de spécifié, , affectation d'un tag par défaut");
            tagsLong = "Aucune caractéristique spécifiée";
        }
        String[] tagsSplit = tagsLong.split(",");
        for (String tag : tagsSplit) {
            tags.add(tag.toLowerCase());
        }
    }


    /**
     * Affiche une salle
     * @return l'id et le nom de la salle
     */
    @Override
    public String toString() {
        return idSalle + " - " + nom;
    }
}
