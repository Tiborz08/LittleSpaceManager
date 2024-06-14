package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class Application {
    private ArrayList<Salle> salles=new ArrayList<>();
    private ArrayList<Personne> personnes= new ArrayList<>();
    private ArrayList<Evenement> evenements=new ArrayList<>();

    //CONSTRUCTEUR
    public Application(){}

    //GUETTER
    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }
    public ArrayList<Personne> getPersonnes() {
        return personnes;
    }
    public ArrayList<Salle> getSalles() {
        return salles;
    }

    //METHODE
    public void addEvenement(Evenement evenement){
        evenements.add(evenement);
    }
    public void addPersonnes(Personne personne){
        personnes.add(personne);
    }
    public void addSalle(Salle salle){
        salles.add(salle);
    }



}
