package fr.uga.iut2.genevent.modele;

public class Artiste extends Participant {
    private float popularite;

    //CONSTRUCTEUR
    public Artiste(String nom, String prenom, float salaire, float popularite) {
        super(nom, prenom, salaire);
        setPopularite(popularite);
        setSalaire(salaire);
    }

    //SETTER
    @Override
    public void setSalaire(float salaire) {
        super.setSalaire(salaire * popularite);
    }

    /**
     * La popularité d'un artiste est définie par la valeur 'popularite'. Si 'popularite' est négatif, alors la popularité est mise à 0.
     *
     * @param popularite Popularité rattachée à l'artiste.
     */
    public void setPopularite(float popularite) {
        if (popularite < 0) {
            this.popularite = 0;
        } else {
            this.popularite = popularite;
        }
    }

    //GUETTER
    public float getPopularite() {
        return popularite;
    }

}
