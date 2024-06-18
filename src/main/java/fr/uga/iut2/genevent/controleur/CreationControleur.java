package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.CreateException;
import fr.uga.iut2.genevent.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML
    private Label laNomSalle,laCapaciteMax,laAdresse;

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
            } else if (typeCreation.equalsIgnoreCase("personnel")) {
                creerPersonnel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void creerSalle() {
        String nom = tfNomSalle.getText();
        String adresse = tfAdresse.getText();
        if (nom.isEmpty()){
            laNomSalle.setStyle("-fx-text-fill:#c8143c");
        }
        if (adresse.isEmpty()){
            laAdresse.setStyle("-fx-text-fill: #c8143c");
        }
        if (tfCapaciteMax.getText().isEmpty()){
            laCapaciteMax.setStyle("-fx-text-fill: #c8143c");
        }

        if (!(adresse.isEmpty() && nom.isEmpty() && tfCapaciteMax.getText().isEmpty())){
            int capaciteMax = Integer.parseInt(tfCapaciteMax.getText());
            Salle salle = new Salle(nom, adresse, capaciteMax);
            mainControleur.ajouterSalle(salle);
            Stage stage = (Stage) tfCapaciteMax.getScene().getWindow();
            stage.close();
        }
    }

    private void creerSpectateur() {
        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();

        Spectateur spectateur = new Spectateur(nom, prenom);
        mainControleur.ajouterSpectateur(spectateur);
        System.out.println("aaaa");
    }

    private void creerPersonnel(){

    }

    private void creerArtiste() {
        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();
        float salaire = Float.parseFloat(tfSalaire.getText());
        float popularite = Float.parseFloat(tfPopularite.getText());

        Artiste artiste = new Artiste(nom, prenom, salaire, popularite);
        mainControleur.ajouterArtiste(artiste);
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
