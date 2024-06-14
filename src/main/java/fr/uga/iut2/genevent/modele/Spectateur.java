package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class Spectateur extends Personne{
    private ArrayList<Evenement> evenements=new ArrayList<>();
    //CONSTRUCTEUR
    public Spectateur(String nom,String prenom){
        super(nom,prenom);
    }

    //GETTER
    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }
    //METHODE

}
