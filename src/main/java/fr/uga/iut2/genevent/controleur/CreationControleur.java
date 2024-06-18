package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.CreateException;
import fr.uga.iut2.genevent.modele.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.time.ZoneId;
import java.util.ArrayList;
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
    @FXML
    private Label laNomSalle,laCapaciteMax,laAdresse;

    //Attribut création Personne
    @FXML
    private TextField tfNomPersonne, tfPrenomPersonne, tfSalaire, tfPopularite;

    //Attribut ALL Button
    @FXML
    private Button btAnnuler, btCreer;

    //méthodes

    public void initialize() {
        if (typeCreation.equalsIgnoreCase("événement")){
            ObservableList<String> types = FXCollections.observableArrayList("Concert", "OneManShow", "Théàtre");

            cbType.setItems(types);

            if (mainControleur != null) {
                ArrayList<Salle> salleList = mainControleur.getSalles();
                ObservableList<Salle> salles = FXCollections.observableArrayList(salleList);

                cbSalle.setItems(salles);
            } else {
                System.err.println("MainControleur n'est pas initialisé");
            }

            configureNumericTextField(tfCoutInitial);
            configureNumericTextField(tfPrixTicket);
            configureNumericTextField(tfCapaciteParticipant);
        } else if (typeCreation.equalsIgnoreCase("personnel")){
            configureNumericTextField(tfSalaire);
        } else if (typeCreation.equalsIgnoreCase("Artiste")) {
            configureNumericTextField(tfSalaire);
            configureNumericTextField(tfPopularite);
        } else if (typeCreation.equalsIgnoreCase("salle")){
            configureNumericTextField(tfCapaciteMax);
        }
    }

    public void setMainController(MainControleur mainControleur) {
        this.mainControleur = mainControleur;
    }

    public void setTypeCreation(String typeCreation){
        this.typeCreation = typeCreation;
    }

    private void configureNumericTextField(TextField textField) {
        // Ajouter un ChangeListener au TextField pour filtrer les entrées non numériques
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
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
            } else if (typeCreation.equalsIgnoreCase("personnel")) {
                creerPersonnel();
            }
            stage.close();
            mainControleur.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void creerSalle() {
        String nom = tfNomSalle.getText();
        int capaciteMax=0;
        String adresse = tfAdresse.getText();
        if (nom.isEmpty()){
            tfNomSalle.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");;
        }
        if (adresse.isEmpty()){
            tfAdresse.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");;
        }
        if (tfCapaciteMax.getText().isEmpty()){
            tfCapaciteMax.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");;
        }

        if (!(adresse.isEmpty()) && !(nom.isEmpty()) && !(tfCapaciteMax.getText().isEmpty())){
            try {
                capaciteMax = Integer.parseInt(tfCapaciteMax.getText());
                Salle salle = new Salle(nom, adresse, capaciteMax);
                mainControleur.ajouterSalle(salle);
                Stage stage = (Stage) tfCapaciteMax.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                laCapaciteMax.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");;
            }

        }
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



    private void creerPersonnel(){
        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();
        float salaire = 0.0f;
        tfNomPersonne.setStyle("");
        tfPrenomPersonne.setStyle("");
        tfSalaire.setStyle("");



        try {
            salaire = Float.parseFloat(tfSalaire.getText());
        } catch (NumberFormatException e) {
        }

        if (nom.isEmpty() || prenom.isEmpty() || tfSalaire.getText().isEmpty()) {
            if (nom.isEmpty()) {
                tfNomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            if (prenom.isEmpty()) {
                tfPrenomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            if (tfSalaire.getText().isEmpty()) {
                tfSalaire.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            }else {
                Personnel personnel = new Personnel(nom, prenom, salaire);
                mainControleur.ajouterPersonnel(personnel);
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

        if (cbType.getValue() != null && cbType.getValue().equalsIgnoreCase("Concert")){
            evenement = new Concert(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        } else if (cbType.getValue() != null && cbType.getValue().equalsIgnoreCase("Théàtre")) {
            evenement = new PieceDeTheatre(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        } else {
            evenement = new OneManShow(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        }

        mainControleur.ajouterEvenement(evenement);

    }

}
