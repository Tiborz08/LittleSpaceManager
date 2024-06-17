package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainControleur {

    private ArrayList<Evenement> evenements = new ArrayList<>();
    private ArrayList<Artiste> artistes = new ArrayList<>();
    private ArrayList<Spectateur> spectateurs = new ArrayList<>();
    private ArrayList<Personnel> personnels = new ArrayList<>();
    private ArrayList<Salle> salles = new ArrayList<>();

    public void ajouterEvenement(Evenement evenement) {
        evenements.add(evenement);
    }

    public void ajouterArtiste(Artiste artiste) {
        artistes.add(artiste);
    }

    public void ajouterSpectateur(Spectateur spectateur) {
        spectateurs.add(spectateur);
    }

    public void ajouterPersonnel(Personnel personnel) {
        personnels.add(personnel);
    }

    public void ajouterSalle(Salle salle) {
        salles.add(salle);
    }

    public void ouvrirFenetreCreation(String typeCreation, ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/Creation" + typeCreation + "View.fxml"));

        CreationControleur creationController = new CreationControleur();
        creationController.setMainController(this);
        creationController.setTypeCreation(typeCreation);

        loader.setController(creationController);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Création de " + typeCreation);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onButtonCreerSalle(ActionEvent event) throws Exception{
        ouvrirFenetreCreation("Salle", event);
    }

//    @FXML
//    public void onButtonCreerEvenement(ActionEvent event) throws Exception{
//        ouvrirFenetreCreation("Evenement", event);
//    }

    @FXML
    public void onButtonCreerPersonnel(ActionEvent event) throws Exception{
        ouvrirFenetreCreation("Personnel", event);
    }

    @FXML
    public void onButtonCreerArtiste(ActionEvent event) throws Exception{
        ouvrirFenetreCreation("Artiste", event);
    }

    @FXML
    public void onButtonCreerSpectateur(ActionEvent event) throws Exception{
        ouvrirFenetreCreation("Spectateur", event);
    }
}
