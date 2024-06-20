package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.MainControleur;
import fr.uga.iut2.genevent.modele.GenEvent;
import fr.uga.iut2.genevent.util.Persisteur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;


public class Main extends Application {



    public static final int EXIT_ERR_LOAD = 2;
    public static final int EXIT_ERR_SAVE = 3;
    private static final Log log = LogFactory.getLog(Main.class);

    static GenEvent genevent = null;
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue/AccueilView.fxml"));
            fxmlLoader.setController(new MainControleur(genevent));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("LittleSpaceManager");
            stage.getIcons().add( new Image(String.valueOf(getClass().getResource("/fr/uga/iut2/genevent/vue/logo/logo-lsm.png"))));
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                log.info("Application fermée");
            });
            stage.show();
            log.info("Application démarrée");
        }

        public static void main(String[] args) {
            try {
                genevent = Persisteur.lireEtat();
            }
            catch (ClassNotFoundException | IOException ignored) {
                System.err.println("Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
                System.err.flush();
                System.exit(Main.EXIT_ERR_LOAD);
            }
            launch();
            try {
                Persisteur.sauverEtat(genevent);
            }
            catch (IOException ignored) {
                System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
                System.err.flush();
                System.exit(Main.EXIT_ERR_SAVE);
            }

        }


    }
/*    public static void main(String[] args) {

    }*/
/*        GenEvent genevent = null;

        try {
            genevent = Persisteur.lireEtat();
        }
        catch (ClassNotFoundException | IOException ignored) {
            System.err.println("Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_LOAD);
        }

        OldControleur oldControleur = new OldControleur(genevent);
        // `Controleur.demarrer` garde le contrôle de l'exécution tant que
        // l'utilisa·teur/trice n'a pas saisi la commande QUITTER.
        oldControleur.demarrer();

        try {
            Persisteur.sauverEtat(genevent);
        }
        catch (IOException ignored) {
            System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_SAVE);
        }
    }*/


