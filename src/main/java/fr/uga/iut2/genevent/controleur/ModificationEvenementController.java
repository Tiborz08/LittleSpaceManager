package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Artiste;
import fr.uga.iut2.genevent.modele.Concert;
import fr.uga.iut2.genevent.modele.Participant;
import fr.uga.iut2.genevent.modele.Salle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Date;

public class ModificationEvenementController {

    //Bilan comptable
    @FXML
    private Button btnBilan;
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



    //Methodes
    @FXML
    public void onQuitterClick(ActionEvent event){
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onBilanClick(ActionEvent event){
        try{

            /*=================================================================
                A modifier quand La liste des événements sera fonctionelle
            ==================================================================*/

            Salle testSalle = new Salle("i", "Jardin d'Eden", 200);
            Concert eventActuel = new Concert("HeavenFest", 1, 1000, 15, new Date(), new Date(), "Feur", testSalle);

            //==================================================================

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/BilanComptable.fxml"));
            loader.setController(this);
            Stage stage = (Stage)btnQuitter.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            float total=0;
            labelCoutInitial.setText(""+eventActuel.getCoutInitial());
            total+=eventActuel.getCoutInitial();

            float f = 0;
            for(Participant a : eventActuel.getArtistes()){
                f+=a.getSalaire();
            }
            labelSalairesArt.setText(""+f);
            total+=f;

            f=0;
            for(Participant a : eventActuel.getPersonnels()){
                f+=a.getSalaire();
            }
            labelSalairesPer.setText(""+f);
            total+=f;

            labelPrixTickets.setText(""+eventActuel.getPrixTickets()*eventActuel.getNombreTickets());
            total+=eventActuel.getPrixTickets()*eventActuel.getNombreTickets();

            labelTotal.setText(""+total);

            stage.setScene(scene);
            stage.show();

        }catch (Exception e){e.printStackTrace();}


    }

}
