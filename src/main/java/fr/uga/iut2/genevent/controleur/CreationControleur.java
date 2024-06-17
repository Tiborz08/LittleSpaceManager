package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Artiste;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Salle;
import fr.uga.iut2.genevent.modele.Spectateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;

public class CreationControleur {
    //Attribut local
    String typeCreation;
    private MainControleur mainControleur;

    //Attribut création événement
    @FXML
    private TextField tfNomEvenement, tfDateDebut, tfDateFin, tfPrixTicket;

    @FXML
    private ComboBox<Salle> cbSalle;

    @FXML
    private ComboBox<String> cbType;

    //Attribut création salle
    @FXML
    private TextField tfNomSalle, tfCapaciteMax, tfAdresse;

    //Attribut création Personne
    @FXML
    private TextField tfNomPersonne, tfPrenomPersonne, tfSalaire, tfPopularite;

    //Attribut ALL Button
    @FXML
    private Button btAnnuler, btCreer;

    //méthodes
    public void setMainController(MainControleur mainControleur) {
        this.mainControleur = mainControleur;
    }

    @FXML
    public void onAnnulerClick(ActionEvent event){
        Stage stage = (Stage) btAnnuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCreerClick(ActionEvent event) {
        Stage stage = (Stage) btCreer.getScene().getWindow();
        String title = stage.getTitle();
        try {
            if (typeCreation.equalsIgnoreCase("événement")) {
                //creerEvenement();
            } else if (typeCreation.equalsIgnoreCase("salle")) {
                creerSalle();
            } else if (typeCreation.equalsIgnoreCase("spectateur")) {
                creerSpectateur();
            } else if (typeCreation.equalsIgnoreCase("artiste")) {
                creerArtiste();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void creerSalle() {
        String nom = tfNomSalle.getText();
        int capaciteMax = Integer.parseInt(tfCapaciteMax.getText());
        String adresse = tfAdresse.getText();

        Salle salle = new Salle(nom, adresse, capaciteMax);
        mainControleur.ajouterSalle(salle);
    }

    private void creerSpectateur() {
        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();

        Spectateur spectateur = new Spectateur(nom, prenom);
        mainControleur.ajouterSpectateur(spectateur);
    }

    private void creerArtiste() {
        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();
        float salaire = Float.parseFloat(tfSalaire.getText());
        float popularite = Float.parseFloat(tfPopularite.getText());

        Artiste artiste = new Artiste(nom, prenom, salaire, popularite);
        mainControleur.ajouterArtiste(artiste);
    }

}
