package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.GenEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * La classe Persisteur est responsable de l'enregistrement et de la
 * restauration de l'état du modèle.
 * <p>
 * C'est une classe utilitaire, toutes les méthodes sont statiques.
 * La classe n'a pas vocation a être instanciée.
 *
 */
public final class Persisteur {

    private static final String NOM_BDD = "persistence/genevent.bdd";
    private static final Log log = LogFactory.getLog(Persisteur.class);

    private Persisteur() {
        // interdit l'instanciation de la classe utilitaire via un constructeur privé
        throw new IllegalStateException("Classe utilitaire.");
    }

    /**
     * Enregistre l'état de l'application dans un fichier persistant.
     * <p>
     * Le fichier de persistance est le fichier "{@value Persisteur#NOM_BDD}".
     *
     * @param genevent L'application dont l'état est persisté.
     *
     * @throws FileNotFoundException si le fichier de persistance est un
     *     dossier, ne peut pas être créé ou ne peut pas être ouvert.
     *
     * @throws IOException si une erreur d'entrée/sortie survient pendant
     *     l'enregistrement.
     */
    public static final void sauverEtat(final GenEvent genevent) throws FileNotFoundException, IOException {
        try (
            FileOutputStream fos = new FileOutputStream(Persisteur.NOM_BDD);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(genevent);
            // Les classes `FileOutputStream` et `ObjectOutputStream`
            // implémentent l'interface `AutoCloseable` : pas besoin de faire
            // un appel explicite à `.close()`.
            log.info("Sauvegarde de l'état réussie.");
            System.out.flush();
        }
        catch (FileNotFoundException fnfe) {
            log.error("Erreur à la création/ouverture du fichier de persistance.");
            System.err.flush();
            throw fnfe;
        }
        catch (IOException ioe) {
            log.error("Erreur lors de l'écriture du fichier de persistance.");
            System.err.flush();
            throw ioe;
        }
    }

    /**
     * Alimente une instance d'application avec l'état du fichier de
     * persistance.
     * <p>
     * Le fichier de persistance est le fichier "{@value Persisteur#NOM_BDD}".
     *
     * @return Une nouvelle instance vierge d'application si le fichier de
     *     persistance n'existe pas, une instance dans l'état enregistré sinon.
     *
     * @throws ClassNotFoundException si le fichier de persistance contient une
     *     classe inconnue (fichier corrompu).
     *
     * @throws IOException si le fichier de persistance est corrompu ou qu'une
     *     erreur d'entrée/sortie survient.
     */
    public static final GenEvent lireEtat() throws ClassNotFoundException, IOException {
        GenEvent genevent;

        try (
            FileInputStream fis = new FileInputStream(Persisteur.NOM_BDD);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            genevent = (GenEvent) ois.readObject();
            System.out.println("Restauration de l'état réussie.");
            System.out.flush();
            // Les classes `FileInputStream` et `ObjectInputStream`
            // implémentent l'interface `AutoCloseable` : pas besoin de faire
            // un appel explicite à `.close()`.
        }
        catch (FileNotFoundException ignored) {
            log.warn("Fichier de persistance inexistant : création d'une nouvelle instance.");
            System.out.flush();
            genevent = new GenEvent();
        }
        catch (IOException ioe) {
            log.error("Erreur de lecture du fichier de persistance.");
            System.err.flush();
            throw ioe;
        }
        catch (ClassNotFoundException cnfe) {
            log.error("Fichier de persistance corrompu.");
            System.err.flush();
            throw cnfe;
        }

        return genevent;
    }
}
