package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;

public class ModificationEvenementController {

    //Bilan comptable
    @FXML
    private Button btnBilan;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnQuitter;
    @FXML
    private Label labelCoutInitial;
    @FXML
    private Label labelSalairesArt;
    @FXML
    private Label labelSalairesPer;
    @FXML
    private Label labelPrixTickets;
    @FXML
    private Label labelTotal;
    //Supprimer evenement
    @FXML
    private Button btnValiderSupre;
    @FXML
    private Button btnAnnulerSupre;


    private MainControleur mainControleur;
    private Evenement evenement;



    //Methodes

    public void setMainControleur(MainControleur mainControleur){
        this.mainControleur = mainControleur;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @FXML
    public void onQuitterClick(ActionEvent event){
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onRetourClick(ActionEvent event){
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onBilanClick(ActionEvent event) throws Exception{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/BilanComptableView.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Bloc l'interaction avec la fenêtre parent jusqu'à ce que le popup soit fermé
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Définit la fenêtre parent
            Scene scene = new Scene(root, 498, 245);

            /*=================================================================
                A modifier quand La liste des événements sera fonctionelle
            ==================================================================*/

//            Salle testSalle = new Salle("i", "Jardin d'Eden", 200);
//            Concert eventActuel = new Concert("HeavenFest", 1, 1000, 15, new Date(), new Date(), "Feur", testSalle);
//            Artiste Adam = new Artiste("", "Adam", 100, 1.5f);
//            eventActuel.addParticipant(Adam);

            //==================================================================

            float total=0;
            labelCoutInitial.setText(""+evenement.getCoutInitial());
            total+=evenement.getCoutInitial();

            float f = 0;
            for(Participant a : evenement.getArtistes()){
                f+=a.getSalaire();
            }
            labelSalairesArt.setText(""+f);
            total+=f;

            f=0;
            for(Participant a : evenement.getPersonnels()){
                f+=a.getSalaire();
            }
            labelSalairesPer.setText(""+f);
            total+=f;

            labelPrixTickets.setText(""+evenement.getPrixTickets()*evenement.getNombreTickets());
            total+=evenement.getPrixTickets()*evenement.getNombreTickets();

            labelTotal.setText(""+total);

            //=================================================================

            popupStage.setScene(scene);
            popupStage.setTitle("Bilan Comptable");
            popupStage.show();

        }catch (Exception e){e.printStackTrace();}


    }

    @FXML
    public void onSupr(Event event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/PageValidationSuppressionView.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Bloc l'interaction avec la fenêtre parent jusqu'à ce que le popup soit fermé
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Définit la fenêtre parent
            Scene scene = new Scene(root, 498, 245);
            popupStage.setScene(scene);
            popupStage.setTitle("Suprimer "+evenement.getNom()+" ?");
            popupStage.show();
        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    public void onValiderSupre(Event event){
        try {
            mainControleur.getEvenements().remove(evenement);
            Stage stage = (Stage) btnValiderSupre.getScene().getWindow();
            stage.close();
            Stage stage1 = (Stage)  btnRetour.getScene().getWindow();
            stage1.close();
            mainControleur.initialize();
            System.out.println(mainControleur.getEvenements().toString());
        }catch (Exception e){e.printStackTrace();}


    }

    @FXML
    public void onAnnulerSupre(Event event){
        try {
            Stage stage = (Stage) btnAnnulerSupre.getScene().getWindow();
            stage.close();
        }catch (Exception e){e.printStackTrace();}
    }

}
