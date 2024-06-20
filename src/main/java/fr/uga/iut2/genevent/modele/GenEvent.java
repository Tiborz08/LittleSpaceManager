package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class GenEvent implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    //private final Map<String, Utilisateur> utilisateurs;  // association qualifiée par l'email
    //private final Map<String, OldEvenement> evenements;  // association qualifiée par le nom

    private static AtomicInteger id;
    private final TreeSet<Evenement> evenements;
    private final ArrayList<Artiste> artistes;
    private final ArrayList<Spectateur> spectateurs;
    private final ArrayList<Personnel> personnels;
    private final ArrayList<Salle> salles;


    public GenEvent(){
        evenements = new TreeSet<>();
        artistes = new ArrayList<>();
        spectateurs = new ArrayList<>();
        personnels = new ArrayList<>();
        salles = new ArrayList<>();
        id=new AtomicInteger();

    }
    public void ajoutOneManShow(GenEvent genevent, OneManShow evt) throws CreateException {
        this.evenements.add(evt);
    }
    public void ajoutConcert(GenEvent genevent, Concert concert) throws CreateException {
        this.evenements.add(concert);
    }
    public void ajoutTheatre(GenEvent genevent, PieceDeTheatre evt) throws CreateException {
        this.evenements.add(evt);
    }
    public void ajoutAutre(GenEvent genevent, Autre evt) throws CreateException {
        this.evenements.add(evt);
    }
    public void ajoutArtiste(GenEvent genevent,String nom, String prenom, float salaire, int popularite){
        Artiste art=new Artiste(genevent,nom,prenom,salaire,popularite);
        this.artistes.add(art);
    }
    public void ajoutSpectateur(GenEvent genevent, Spectateur spec){
        this.spectateurs.add(spec);
    }
    public void ajoutPersonnel(GenEvent genevent, Personnel perso){
        this.personnels.add(perso);
    }
    public void ajoutSalle(GenEvent genevent, Salle salle){
        this.salles.add(salle);
    }
    public void ajoutID(AtomicInteger id){
        this.id=id;
    }

    public ArrayList<Artiste> getArtistes() {
        return artistes;
    }

    public ArrayList<Personnel> getPersonnels() {
        return personnels;
    }

    public ArrayList<Salle> getSalles() {
        return salles;
    }

    public ArrayList<Spectateur> getSpectateurs() {
        return spectateurs;
    }

    public TreeSet<Evenement> getEvenements() {
        return evenements;
    }

    public static AtomicInteger getId() {
        return id;
    }

    /*
    public GenEvent() {
        this.utilisateurs = new HashMap<>();
        this.evenements = new HashMap<>();
    }

    public boolean ajouteUtilisateur(String email, String nom, String prenom) {
        if (this.utilisateurs.containsKey(email)) {
            return false;
        } else {
            this.utilisateurs.put(email, new Utilisateur(email, nom, prenom));
            return true;
        }
    }

    public Map<String, OldEvenement> getEvenements() {
        return this.evenements;
    }

    public void nouvelEvenement(String nom, LocalDate dateDebut, LocalDate dateFin, String adminEmail) {
        assert !this.evenements.containsKey(nom);
        assert this.utilisateurs.containsKey(adminEmail);
        Utilisateur admin = this.utilisateurs.get(adminEmail);
        OldEvenement evt = OldEvenement.initialiseEvenement(this, nom, dateDebut, dateFin, admin);
        this.evenements.put(nom, evt);
    }
*/

}
