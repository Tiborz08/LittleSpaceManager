package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class GenEvent implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final Map<String, Utilisateur> utilisateurs;  // association qualifiée par l'email
    private final Map<String, OldEvenement> evenements;  // association qualifiée par le nom

    public GenEvent() {
        this.utilisateurs = new HashMap<>();
        this.evenements = new HashMap<>();
    }

    public boolean ajouteUtilisateur(String email, String nom, String prenom) {
        if (this.utilisateurs.containsKey(email)) {
            return false;
        } else {
            this.utilisateurs.put(email, new Utilisateur(email, nom, prenom));
            return true;
        }
    }

    public Map<String, OldEvenement> getEvenements() {
        return this.evenements;
    }

    public void nouvelEvenement(String nom, LocalDate dateDebut, LocalDate dateFin, String adminEmail) {
        assert !this.evenements.containsKey(nom);
        assert this.utilisateurs.containsKey(adminEmail);
        Utilisateur admin = this.utilisateurs.get(adminEmail);
        OldEvenement evt = OldEvenement.initialiseEvenement(this, nom, dateDebut, dateFin, admin);
        this.evenements.put(nom, evt);
    }

}
