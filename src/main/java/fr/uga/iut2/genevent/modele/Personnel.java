package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

/**
 * Cette Class permet de créer des objest de type Personnel. Cette class est une class fille de la class Participant.
 */
public class Personnel extends Participant implements Serializable {

    /**
     * Pour créer un objet de type Personnel, il faut renseigner le nom, prenom et salaire du personnel.
     * @param nom le nom du salarié
     * @param prenom le prénom du salarié
     * @param salaire le montant qui lui sera attribué pour son travail
     */
    public Personnel(GenEvent genevent,String nom, String prenom, float salaire) {
        super(genevent,nom, prenom, salaire);
    }

}
