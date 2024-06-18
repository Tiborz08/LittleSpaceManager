package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.*;
import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class MainControleur {

    private TreeSet<Evenement> evenements = new TreeSet<>();
    private ArrayList<Artiste> artistes = new ArrayList<>();
    private ArrayList<Spectateur> spectateurs = new ArrayList<>();
    private ArrayList<Personnel> personnels = new ArrayList<>();
    private ArrayList<Salle> salles = new ArrayList<>();

    @FXML
    private Button btnAnnuler;

    //attribut accueil
    @FXML
    private ListView<Evenement> lvEvenement;

    @FXML
    private Label erreurLabel;

    public void initialize(){
        actualisationEvenement();
        lvEvenement.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Evenement selectedEvenement = lvEvenement.getSelectionModel().getSelectedItem();
                if (selectedEvenement != null) {
                    try {
                        ouvrirOptionEvenement(selectedEvenement);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void ajouterEvenement(Evenement evenement) {
        evenements.add(evenement);
    }

    @FXML
    public void onAnnulerClick(ActionEvent event){
        Stage stage = (Stage) btnAnnuler.getScene().getWindow();
        stage.close();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/Creation" + LittleSpaceManager_Utilitaire.removeAccents(typeCreation) + "View.fxml"));

        CreationControleur creationController = new CreationControleur();
        creationController.setMainController(this);
        creationController.setTypeCreation(typeCreation);

        loader.setController(creationController);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Création de " + typeCreation);
        stage.getIcons().add( new Image(String.valueOf(getClass().getResource("/fr/uga/iut2/genevent/vue/logo/logo-lsm.png"))));
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }




    public void afficherFenetreErreur(String textErreur) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/AlerteErreurView.fxml"));
        loader.setController(this);
        Parent root = loader.load();



        erreurLabel.setText(textErreur);

        if (root instanceof VBox) {
            VBox vbox = (VBox) root;
            vbox.setPrefSize(500, 300);
        } else {
            throw new Exception("Le root n'est pas une instance de VBox");
        }

        Stage stage = new Stage();
        stage.setTitle("Erreur");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/vue/logo/logo-lsm.png")));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }





    @FXML
    public void ouvrirOptionEvenement(Evenement evenement) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/OptionEvenementView.fxml"));

        ModificationEvenementController modificationControleur = new ModificationEvenementController();
        modificationControleur.setMainControleur(this);
        modificationControleur.setEvenement(evenement);

        loader.setController(modificationControleur);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Option événement");
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void onButtonCreerSalle(ActionEvent event) throws Exception{
        ouvrirFenetreCreation("Salle", event);
    }

    @FXML
    public void onButtonCreerEvenement(ActionEvent event) throws Exception {
        if (salles.isEmpty()) {
            afficherFenetreErreur("Vous devez paramétrer une salle avant de pouvoir créer un événement.");
        } else {
            ouvrirFenetreCreation("Événement", event);
        }
    }

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

    //getter
    public ArrayList<Salle> getSalles() {
        return salles;
    }
    public TreeSet<Evenement> getEvenements(){
        return evenements;
    }

    //méthode
    @FXML
    private void actualisationEvenement(){
        ObservableList<Evenement> listeEvenement = FXCollections.observableArrayList(new ArrayList<>(evenements));
        lvEvenement.setItems(listeEvenement);
    }

    @FXML
    public Stage getStage(){
        return (Stage) lvEvenement.getScene().getWindow();
    }
}
