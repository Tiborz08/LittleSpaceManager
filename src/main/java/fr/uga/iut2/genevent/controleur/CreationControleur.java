package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.CreateException;
import fr.uga.iut2.genevent.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.Date;

public class CreationControleur {
    //Attribut local
    String typeCreation;
    private MainControleur mainControleur;

    //Attribut création événement
    @FXML
    private TextField tfNomEvenement, tfPrixTicket, tfCapaciteParticipant, tfCoutInitial;

    @FXML
    private TextArea taDescription;

    @FXML
    private ComboBox<Salle> cbSalle;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private DatePicker dpDebut, dpFin;

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

    public void setTypeCreation(String typeCreation){
        this.typeCreation = typeCreation;
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
                creerEvenement();
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
        tfNomPersonne.setStyle("");
        tfPrenomPersonne.setStyle("");


        if (nom.isEmpty() || prenom.isEmpty()) {
            System.out.println("Vous devez entrer le nom et le prénom du spectateur.");

            if (nom.isEmpty()){
                tfNomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            if(prenom.isEmpty()){
                tfPrenomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
        } else{
            Spectateur spectateur = new Spectateur(nom, prenom);
            mainControleur.ajouterSpectateur(spectateur);
            System.out.println("Nom : " + nom);
            System.out.println("Prenom : " + prenom);

            Stage stage = (Stage) tfPrenomPersonne.getScene().getWindow();
            stage.close();

        }
    }



    private void creerArtiste() {
        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();
        float salaire = 0.0f;
        float popularite = 0.0f;

        try {
            salaire = Float.parseFloat(tfSalaire.getText());
        } catch (NumberFormatException e) {
        }

        try {
            popularite = Float.parseFloat(tfPopularite.getText());
        } catch (NumberFormatException e) {
        }

        tfNomPersonne.setStyle("");
        tfPrenomPersonne.setStyle("");
        tfSalaire.setStyle("");
        tfPopularite.setStyle("");



        if (nom.isEmpty() || prenom.isEmpty() || tfSalaire.getText().isEmpty() || tfPopularite.getText().isEmpty()) {
            if (nom.isEmpty()){
                tfNomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            if (prenom.isEmpty()){
                tfPrenomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            if (tfSalaire.getText().isEmpty()){
                tfSalaire.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            if (tfPopularite.getText().isEmpty()){
                tfPopularite.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }

            System.out.println("Vous devez renseigner tous les champs pour créer un artiste.");
        }else {
            Artiste artiste = new Artiste(nom, prenom, salaire, popularite);
            mainControleur.ajouterArtiste(artiste);
            Stage stage = (Stage) tfPrenomPersonne.getScene().getWindow();
            stage.close();
        }

    }



    private void creerEvenement() throws CreateException {
        String nom = tfNomEvenement.getText();
        int capaciteParticipant = Integer.parseInt(tfCapaciteParticipant.getText());
        float coutInitial = Float.parseFloat(tfCoutInitial.getText());
        float prixTicket = Float.parseFloat(tfPrixTicket.getText());
        Date debut = Date.from(dpDebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(dpFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String description = taDescription.getText();
        Salle salle = cbSalle.getValue();



        Evenement evenement;

        if (cbType.getValue().equalsIgnoreCase("Concert")){
            evenement = new Concert(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        } else if (cbType.getValue().equalsIgnoreCase("Théàtre")) {
            evenement = new PieceDeTheatre(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        } else {
            evenement = new OneManShow(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        }

        mainControleur.ajouterEvenement(evenement);
    }

}
