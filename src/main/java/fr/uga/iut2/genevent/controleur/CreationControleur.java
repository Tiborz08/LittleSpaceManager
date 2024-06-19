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
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Contrôleur pour la création d'éléments tels que des événements, des salles, des spectateurs, des artistes et du personnel.
 * Cette classe gère les interactions avec l'interface utilisateur et la logique métier pour créer de nouveaux éléments.
 */

public class CreationControleur {
    private static final Log log = LogFactory.getLog(CreationControleur.class);

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
    private ComboBox<Integer> cbPopularite;

    @FXML
    private DatePicker dpDebut, dpFin;

    //Attribut création salle
    @FXML
    private TextField tfNomSalle, tfCapaciteMax, tfAdresse;
    @FXML
    private Label laNomSalle, laCapaciteMax, laAdresse;

    //Attribut création Personne
    @FXML
    private TextField tfNomPersonne, tfPrenomPersonne, tfSalaire;

    //Attribut ALL Button
    @FXML
    private Button btnAnnuler, btCreer;

    @FXML
    private TextArea taTags;

    //méthodes

    /**
     * Initialise la vue de la création d'un élément en fonction du type d'élément spécifié.
     * Cette méthode est appelée automatiquement après que le fichier FXML associé a été chargé.
     * Elle configure les éléments de l'interface utilisateur en fonction du type d'élément à créer.
     * Elle définit également les valeurs par défaut pour ces éléments.
     */
    public void initialize() {
        if (typeCreation.equalsIgnoreCase("événement")) {
            ObservableList<String> types = FXCollections.observableArrayList("Concert", "OneManShow", "Théàtre", "Autre");

            dpDebut.setValue(LocalDate.now());
            dpFin.setValue(LocalDate.now());

            cbType.setItems(types);
            cbType.getSelectionModel().selectFirst();

            if (mainControleur != null) {
                ArrayList<Salle> salleList = mainControleur.getSalles();
                ObservableList<Salle> salles = FXCollections.observableArrayList(salleList);

                cbSalle.setItems(salles);
                cbSalle.getSelectionModel().selectFirst();
            } else {
                System.err.println("MainControleur n'est pas initialisé");
            }

            configureNumericTextField(tfCoutInitial);
            configureNumericTextField(tfPrixTicket);
            configureNumericTextField(tfCapaciteParticipant);
        } else if (typeCreation.equalsIgnoreCase("personnel")) {
            configureNumericTextField(tfSalaire);
        } else if (typeCreation.equalsIgnoreCase("Artiste")) {
            configureNumericTextField(tfSalaire);
            ObservableList<Integer> options = FXCollections.observableArrayList(1, 2, 3, 4, 5);
            cbPopularite.setItems(options);
            cbPopularite.getSelectionModel().selectFirst();
        } else if (typeCreation.equalsIgnoreCase("salle")) {
            configureNumericTextField(tfCapaciteMax);
        }
    }

    /**
     * Définit le contrôleur principal de l'application.
     *
     * @param mainControleur le contrôleur principal de l'application.
     */
    public void setMainController(MainControleur mainControleur) {
        this.mainControleur = mainControleur;
    }

    /**
     * Définit le type d'élément à créer.
     *
     * @param typeCreation le type d'élément à créer.
     */
    public void setTypeCreation(String typeCreation) {
        this.typeCreation = typeCreation;
    }

    /**
     * Configure un champ de texte pour accepter uniquement des nombres.
     * Cette méthode ajoute un ChangeListener au champ de texte spécifié pour filtrer les entrées non numériques.
     *
     * @param textField le champ de texte à configurer.
     */
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

    /**
     * Ferme la fenêtre de création d'un élément lorsque le bouton Annuler est cliqué.
     *
     * @param event
     */

    @FXML
    public void onAnnulerClick(ActionEvent event) {
        Stage stage = (Stage) btnAnnuler.getScene().getWindow();
        stage.close();
    }

    /**
     * Crée un nouvel élément de type spécifié lorsque le bouton Créer est cliqué.
     * Cette méthode appelle la méthode de création appropriée en fonction du type d'élément à créer,
     * puis ferme la fenêtre de création et met à jour la vue principale de l'application.
     * Si une exception de création est levée, une fenêtre d'erreur est affichée avec le message d'erreur correspondant.
     *
     * @param event l'événement ActionEvent qui a déclenché la création de l'élément.
     * @throws Exception si une erreur se produit lors de la création de l'élément.
     */
    @FXML
    public void onCreerClick(ActionEvent event) throws Exception {
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
        } catch (CreateException e) {
            mainControleur.afficherFenetreErreur(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée une nouvelle salle avec les informations saisies dans l'interface utilisateur.
     * Cette méthode vérifie que tous les champs obligatoires sont remplis, puis crée un nouveau objet de type Salle
     * avec les informations saisies. Si la capacité maximale saisie n'est pas un nombre entier valide, une exception est levée.
     * Si un ou plusieurs champs obligatoires ne sont pas remplis, une exception de création est levée avec un message d'erreur approprié.
     *
     * @throws Exception
     */

    private void creerSalle() throws Exception {
        String nom = tfNomSalle.getText();
        int capaciteMax = 0;
        String adresse = tfAdresse.getText();
        String tagsLong = taTags.getText();
        if (nom.isEmpty()) {
            tfNomSalle.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            ;
        }
        if (adresse.isEmpty()) {
            tfAdresse.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            ;
        }
        if (tfCapaciteMax.getText().isEmpty()) {
            tfCapaciteMax.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            ;
        }

        if (!(adresse.isEmpty()) && !(nom.isEmpty()) && !(tfCapaciteMax.getText().isEmpty())) {
            try {
                capaciteMax = Integer.parseInt(tfCapaciteMax.getText());
                Salle salle = new Salle(nom, adresse, capaciteMax, tagsLong);
                mainControleur.ajouterSalle(salle);
                Stage stage = (Stage) tfCapaciteMax.getScene().getWindow();
                stage.close();
                log.info("Salle créée : " + salle.getNom() + ", " + salle.getAdresse() + ", " + salle.getCapacite_max() + " personnes maximum");
            } catch (NumberFormatException e) {
                tfCapaciteMax.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
                throw new CreateException("La capacité de la salle est trop élévée");
            }

        } else {
            if (nom.isEmpty()) {
                tfNomSalle.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
                ;
            }
            if (adresse.isEmpty()) {
                tfAdresse.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
                ;
            }
            if (tfCapaciteMax.getText().isEmpty()) {
                tfCapaciteMax.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
                ;
            }

            throw new CreateException("Vous devez remplir tous les champs");
        }
    }

    /**
     * Crée un nouveau spectateur avec les informations saisies dans l'interface utilisateur.
     * Cette méthode vérifie que les champs Nom et Prénom sont remplis, puis crée un nouveau objet de la classe Spectateur
     * avec les informations saisies. Si un ou plusieurs champs obligatoires ne sont pas remplis, une exception de création est levée
     * avec un message d'erreur approprié.
     *
     * @throws Exception
     */
    private void creerSpectateur() throws Exception {

        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();
        tfNomPersonne.setStyle("");
        tfPrenomPersonne.setStyle("");


        if (nom.isEmpty() || prenom.isEmpty()) {
            System.out.println("Vous devez entrer le nom et le prénom du spectateur.");

            if (nom.isEmpty()) {
                tfNomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }
            if (prenom.isEmpty()) {
                tfPrenomPersonne.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 3;");
            }

            throw new CreateException("Vous devez remplir tous les champs");
        } else {
            Spectateur spectateur = new Spectateur(nom, prenom);
            mainControleur.ajouterSpectateur(spectateur);

            Stage stage = (Stage) tfPrenomPersonne.getScene().getWindow();

            log.info("Spectateur créé : " + spectateur.getNom() + " " + spectateur.getPrenom());

        }
    }

    /**
     * Crée un nouveau membre du personnel avec les informations saisies dans l'interface utilisateur.
     * Cette méthode vérifie que les champs Nom, Prénom et Salaire sont remplis, puis crée une nouvel objet de la classe Personnel
     * avec les informations saisies. Si le salaire saisi n'est pas un nombre valide, une exception est levée.
     * Si un ou plusieurs champs obligatoires ne sont pas remplis, une exception de création est levée avec un message d'erreur approprié.
     *
     * @throws Exception
     */

    private void creerPersonnel() throws Exception {
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

            throw new CreateException("Vous devez remplir tous les champs");
        } else {
            Personnel personnel = new Personnel(nom, prenom, salaire);
            mainControleur.ajouterPersonnel(personnel);
            Stage stage = (Stage) tfPrenomPersonne.getScene().getWindow();
            stage.close();

            log.info("Personnel créé : " + personnel.getNom() + " " + personnel.getPrenom());
        }
    }

    /**
     * Crée un nouvel artiste avec les informations saisies dans l'interface utilisateur.
     * Cette méthode vérifie que les champs Nom, Prénom et Salaire sont remplis, puis crée une nouvel objet de la classe Artiste
     * avec les informations saisies. Si le salaire saisi n'est pas un nombre valide, une exception est levée.
     * Si un ou plusieurs champs obligatoires ne sont pas remplis, une exception de création est levée avec un message d'erreur approprié.
     *
     * @throws Exception
     */

    private void creerArtiste() throws Exception {
        String nom = tfNomPersonne.getText();
        String prenom = tfPrenomPersonne.getText();
        float salaire = 0.0f;
        int popularite = 0;

        try {
            salaire = Float.parseFloat(tfSalaire.getText());
        } catch (NumberFormatException e) {
        }

        try {
            popularite = cbPopularite.getValue();
        } catch (NumberFormatException e) {
        }

        tfNomPersonne.setStyle("");
        tfPrenomPersonne.setStyle("");
        tfSalaire.setStyle("");


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

            throw new CreateException("Vous devez remplir tous les champs");
        } else {
            Artiste artiste = new Artiste(nom, prenom, salaire, popularite);
            mainControleur.ajouterArtiste(artiste);
            Stage stage = (Stage) tfPrenomPersonne.getScene().getWindow();
            stage.close();
            log.info("Artiste créé : " + artiste.getNom() + " " + artiste.getPrenom());
        }

    }

    /**
     * Crée un nouvel événement avec les informations saisies dans l'interface utilisateur.
     * Cette méthode vérifie que tous les champs obligatoires sont remplis, puis crée une nouvelle instance de la classe Evenement
     * ou de ses sous-classes (Concert, PieceDeTheatre, OneManShow ou Autre) avec les informations saisies.
     * Si un ou plusieurs champs obligatoires ne sont pas remplis, une exception de création est levée avec un message d'erreur approprié.
     *
     * @throws CreateException
     */

    private void creerEvenement() throws Exception {
        String nom;
        int capaciteParticipant;
        float coutInitial;
        float prixTicket;
        Date debut;
        Date fin;
        String description;
        Salle salle;

        nom = tfNomEvenement.getText();
        if (nom.isEmpty()) {
            throw new CreateException("Vous devez entrer un nom pour l'événement");
        }
        if (tfCapaciteParticipant.getText().isEmpty()) {
            throw new CreateException("Vous devez rentrer une valeur pour le nombre maximal d'employés");
        } else {

            try {
                capaciteParticipant = Integer.parseInt(tfCapaciteParticipant.getText());
                if(capaciteParticipant > 1e9){
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException e){
                throw new CreateException("Le nombre de salariés maximum est trop élevé");
            }
        }
        if (tfCoutInitial.getText().isEmpty()) {
            throw new CreateException("Vous devez rentrer une valeur pour le coût initial de l'événement");
        } else {
            try {
                coutInitial = Float.parseFloat(tfCoutInitial.getText());
                if(coutInitial > 1e9){
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException e){
                throw new CreateException("Le coût initial de l'événement est trop important");
            }
        }
        if (tfPrixTicket.getText().isEmpty()) {
            throw new CreateException("Vous devez renseigner le prix pour un ticket, si l'évenement est gratuit, renseignez 0");
        } else {
            try {
                prixTicket = Float.parseFloat(tfPrixTicket.getText());
                if(prixTicket > 1e9){
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException e){
                throw new CreateException("Le prix du ticket est trop élevé");
            }
        }
        debut = Date.from(dpDebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        fin = Date.from(dpFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        description = taDescription.getText();
        salle = cbSalle.getValue();


        Evenement evenement;

        if (cbType.getValue() != null && cbType.getValue().equalsIgnoreCase("Concert")) {
            evenement = new Concert(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        } else if (cbType.getValue() != null && cbType.getValue().equalsIgnoreCase("Théàtre")) {
            evenement = new PieceDeTheatre(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        } else if (cbType.getValue() != null && cbType.getValue().equalsIgnoreCase("OneManShow")) {
            evenement = new OneManShow(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        } else {
            evenement = new Autre(nom, capaciteParticipant, coutInitial, prixTicket, debut, fin, description, salle);
        }

        mainControleur.ajouterEvenement(evenement);
        log.info("Evenement de type " + evenement.getClass().getSimpleName().toLowerCase() + " crée : " + evenement.getNom());

    }

}
