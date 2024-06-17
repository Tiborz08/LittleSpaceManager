package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.*;
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

    public void ouvrirFenetreCreation(String typeCreation) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/CreationControleur.fxml"));
        Parent root = loader.load();

        CreationControleur creationController = loader.getController();
        creationController.setMainController(this);
        creationController.setTypeCreation(typeCreation);

        Stage stage = new Stage();
        stage.setTitle("Création de " + typeCreation);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
