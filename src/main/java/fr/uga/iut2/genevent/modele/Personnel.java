package fr.uga.iut2.genevent.modele;

/**
 * Cette Class permet de créer des objest de type Personnel. Cette class est une class fille de la class Participant.
 */
public class Personnel extends Participant {

    /**
     * Pour créer un objet de type Personnel, il faut renseigner le nom, prenom et salaire du personnel.
     * @param nom
     * @param prenom
     * @param salaire
     */
    public Personnel(String nom, String prenom, float salaire) {
        super(nom, prenom, salaire);
    }

}
