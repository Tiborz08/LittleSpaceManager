package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.CreateException;
import fr.uga.iut2.genevent.modele.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private Button btnModifier;
    @FXML
    private Button btnCreer, btnValider, btnAnnuler;
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
    @FXML
    private Label lbAssocier;
    @FXML
    private ComboBox<Personne> cbAssocier;

    //Modification événement
    @FXML
    private ListView<Participant> lvPersonnel;
    @FXML
    private ListView<Spectateur> lvSpectateur;
    @FXML
    private ListView<Participant> lvArtiste;
    @FXML
    private TextField tfNom, tfPrixTicket;
    @FXML
    private DatePicker dpDebut, dpFin;
    @FXML
    private ComboBox<Salle> cbSalle;
    @FXML
    private Button btnModifiactionValider;

    //Spec Piece de theatre
    @FXML
    private Button btnSpec;


    //Supprimer evenement
    @FXML
    private Button btnValiderSupre;
    @FXML
    private Button btnAnnulerSupre;

    @FXML
    private  Button btnCreerAssocier;


    private MainControleur mainControleur;
    private Evenement evenement;


    public void initialize() {
        if (lvSpectateur != null && lvPersonnel != null && lvArtiste != null) {
            actualisationListe();
        }
    }

    //Methodes

    public void setMainControleur(MainControleur mainControleur) {
        this.mainControleur = mainControleur;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @FXML
    public void onQuitterClick(ActionEvent event) {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onRetourClick(ActionEvent event) {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onBilanClick(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/BilanComptableView.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Bloc l'interaction avec la fenêtre parent jusqu'à ce que le popup soit fermé
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Définit la fenêtre parent
            Scene scene = new Scene(root, 498, 245);

            labelCoutInitial.setText(String.valueOf(evenement.getCoutInitial()));

            labelSalairesArt.setText(String.valueOf(evenement.getSalairesArtistes()));

            labelSalairesPer.setText(String.valueOf(evenement.getSalairesPersonnels()));

            labelPrixTickets.setText(String.valueOf(evenement.getGainsTickets()));

            labelTotal.setText(String.valueOf(evenement.getBenefices()));

            //=================================================================

            popupStage.setScene(scene);
            popupStage.setTitle("Bilan Comptable");
            popupStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void onSupr(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/PageValidationSuppressionView.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Bloc l'interaction avec la fenêtre parent jusqu'à ce que le popup soit fermé
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Définit la fenêtre parent
            Scene scene = new Scene(root, 498, 245);
            popupStage.setScene(scene);
            popupStage.setTitle("Supprimer " + evenement.getNom() + " ?");
            popupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onValiderSupre(Event event) {
        try {
            mainControleur.getEvenements().remove(evenement);
            evenement.setSalle(null);
            Stage stage = (Stage) btnValiderSupre.getScene().getWindow();
            stage.close();
            Stage stage1 = (Stage) btnRetour.getScene().getWindow();
            stage1.close();
            mainControleur.initialize();
            System.out.println(mainControleur.getEvenements().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void onAnnulerSupre(Event event) {
        try {
            Stage stage = (Stage) btnAnnulerSupre.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onButtonModifierClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/ModificationEvenementView.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Stage stage = mainControleur.getStage();

        tfNom.setText(evenement.getNom());
        dpDebut.setValue(evenement.getDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dpFin.setValue(evenement.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        ObservableList<Salle> listeSalle = FXCollections.observableArrayList(mainControleur.getSalles());
        cbSalle.setItems(listeSalle);
        cbSalle.setValue(evenement.getSalle());
        tfPrixTicket.setText(String.valueOf(evenement.getPrixTickets()));

        //Bouton spec qui s'active que si l'event est une pdt ou un concert.
        btnSpec.setDisable(!(evenement instanceof PieceDeTheatre | evenement instanceof Concert));

        Stage popupPrecedent = (Stage) btnModifier.getScene().getWindow();
        popupPrecedent.close();

        stage.setScene(new Scene(root));
    }

    private void actualisationListe() {

        //test
        ObservableList<Participant> listePersonnel = FXCollections.observableArrayList(evenement.getPersonnels());
        lvPersonnel.setItems(listePersonnel);

        ObservableList<Participant> listeArtiste = FXCollections.observableArrayList(evenement.getArtistes());
        lvArtiste.setItems(listeArtiste);

        ObservableList<Spectateur> listeSpectateur = FXCollections.observableArrayList(evenement.getSpectateurs());
        lvSpectateur.setItems(listeSpectateur);
    }

    private void ouvertureAssociationPage(String typePersonne) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/AssocierView.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Stage stage = new Stage();

        if (typePersonne.equalsIgnoreCase("Spectateur")) {
            stage.setTitle("Associer spectateur");
            lbAssocier.setText(lbAssocier.getText() + "spectateur");
            ObservableList<Personne> listSpectateur = FXCollections.observableArrayList(mainControleur.getSpectateurs());
            cbAssocier.setItems(listSpectateur);
        } else if (typePersonne.equalsIgnoreCase("Artiste")) {
            stage.setTitle("Associer artiste");
            lbAssocier.setText(lbAssocier.getText() + "artiste");
            ObservableList<Personne> listArtiste = FXCollections.observableArrayList(mainControleur.getArtistes());
            cbAssocier.setItems(listArtiste);
        } else {
            stage.setTitle("Associer personnel");
            lbAssocier.setText(lbAssocier.getText() + "personnel");
            ObservableList<Personne> listPersonnel = FXCollections.observableArrayList(mainControleur.getPersonnels());
            cbAssocier.setItems(listPersonnel);
        }

        stage.setScene(new Scene(root));
        stage.show();
    }

    private  void onCreerAssocierClick(Event event){

    }

    @FXML
    private void onSpectateurClick() throws IOException {
        ouvertureAssociationPage("spectateur");
    }

    @FXML
    private void onArtisteClick() throws IOException {
        ouvertureAssociationPage("artiste");
    }

    @FXML
    private void onPersonnelClick() throws IOException {
        ouvertureAssociationPage("personnel");
    }

    @FXML
    private void onAnnulerClick() {
        Stage stage = (Stage) btnAnnuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCreerClick(ActionEvent event) throws Exception {
        String typepersonne= lbAssocier.getText();
        String[] words = typepersonne.split(" ");
        ArrayList<String> tmp=new ArrayList<>();
        for (String word : words) {
            tmp.add(word);
        }
        ouvertureBonnePageCreer(tmp.get(2),event);
    }

    private  void ouvertureBonnePageCreer(String typepersonne,ActionEvent event) throws Exception {
        if (typepersonne.compareToIgnoreCase("spectateur")==0){
            mainControleur.onButtonCreerSpectateur(event);
        } else if (typepersonne.compareToIgnoreCase("personnel")==0) {
            mainControleur.onButtonCreerPersonnel(event);
        }else {
            mainControleur.onButtonCreerArtiste(event);
        }
    }

    @FXML
    private void onValiderAssociationClick() {
        Stage stage = (Stage) btnValider.getScene().getWindow();

        if (cbAssocier.getValue() != null) {
            Personne personne = cbAssocier.getValue();
            if (personne.getClass().equals(Artiste.class) || personne.getClass().equals(Personnel.class)) {
                evenement.addParticipant((Participant) personne);
            } else {
                evenement.addTicket((Spectateur) personne);
            }
        }
        initialize();
        stage.close();
    }

    @FXML
    private void onValiderModificationClick() throws CreateException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/AccueilView.fxml"));
        loader.setController(mainControleur);
        Parent root = loader.load();

        Stage stage = (Stage) btnModifiactionValider.getScene().getWindow();


        Date debut = Date.from(dpDebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(dpFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        if (!tfNom.getText().equalsIgnoreCase(evenement.getNom())){
            evenement.setNom(tfNom.getText());
        }
        if (!debut.equals(evenement.getDebut())){
            evenement.setDebut(debut);
        }
        if (!fin.equals(evenement.getFin())){
            evenement.setFin(fin);
        }
        if (!cbSalle.getValue().equals(evenement.getSalle())){
            evenement.setSalle(cbSalle.getValue());
        }
        if ((Float.parseFloat(tfPrixTicket.getText()) != evenement.getPrixTickets())){
            evenement.setPrixTickets(Float.parseFloat(tfPrixTicket.getText()));
        }

        stage.setScene(new Scene(root));
    }

    //Bouton Spec accessoires


    @FXML
    public void onSpecClick() throws IOException {

    }
}
