package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;

public abstract class Personne {

    private int identifiant;
    private String nom;
    private String prenom;

    //CONSTRUCTEUR
    public Personne(String nom,String prenom){
        this.setIdentifiant();
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    //SETTER

    /**
     * Créer un identifiant unique pour la personne.
     */
    public void setIdentifiant(){
        identifiant= LittleSpaceManager_Utilitaire.newId();
    }

    /**
     * Uniformiser le nom pour qu'il soit en minuscule, sauf la première lettre qui sera en majuscule.
     * @param nom
     */
    public void setNom(String nom){
        this.nom=LittleSpaceManager_Utilitaire.capitalize(nom);
    }

    /**
     * Uniformiser le prénom pour qu'il soit en minuscule, sauf la première lettre qui sera en majuscule.
     * @param prenom
     */
    public void setPrenom(String prenom){
        this.prenom=LittleSpaceManager_Utilitaire.capitalize(prenom);
    }

    //GUETTER
    public int getIdentifiant() {
        return identifiant;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
}
