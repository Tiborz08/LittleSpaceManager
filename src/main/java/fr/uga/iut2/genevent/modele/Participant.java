package fr.uga.iut2.genevent.modele;

public abstract class Participant extends Personne {

    private float salaire;

    public Participant(String nom,String prenom,float salaire){
        super(nom,prenom);
        this.setSalaire(salaire);
    }

    /**
     * Définit la valeur du salaire d'un participant. Si le salaire du participant est négatif, alors le salaire est égal à 0.
     * @param salaire Réel à virgule qui représente le salaire du participant.
     */
    public void setSalaire(float salaire) {
        if (salaire<0){
            this.salaire=0;
        }else {
        this.salaire = salaire;}
    }
}
