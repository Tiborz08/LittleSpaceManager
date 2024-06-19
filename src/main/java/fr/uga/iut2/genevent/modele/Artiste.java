package fr.uga.iut2.genevent.modele;

/**
 * Cette Class nous permet de créer des objest de type Artiste en héritant de la class Participant. Les objets de type Artiste possèdent en plus l'attribut Popularité.
 */
public class Artiste extends Participant {
    private int popularite;

    /**
     * Le constructeur des objets Artiste à besoin du nom et du prenom de l'artiste, le salaire que l'artiste va percevoir ainsi que sa popularité.
     * @param nom
     * @param prenom
     * @param salaire
     * @param popularite
     */

    //CONSTRUCTEUR
    public Artiste(String nom, String prenom, float salaire, int popularite) {
        super(nom, prenom, salaire);
        setPopularite(popularite);
        setSalaire(salaire);
    }

    /**
     * Cette méthode permet de modifié le salaire de l'artiste.
     * @param salaire Réel à virgule qui représente le salaire du participant.
     */

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
    public void setPopularite(int popularite) {
        this.popularite = popularite;
    }



    //GUETTER

    /**
     * Cette méthode renvoie la popularité de l'Artiste.
     * @return
     */
    public float getPopularite() {
        return popularite;
    }

}
