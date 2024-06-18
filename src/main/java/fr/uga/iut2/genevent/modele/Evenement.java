package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;
import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;

public abstract class Evenement implements Comparable<Evenement> {
    private static final Log log = LogFactory.getLog(Evenement.class);

    //ATTRIBUTIONS

    private int idEvent;
    private String nom;
    private int capaciteParticipants;
    private int capaciteSpectateur;
    private float coutInitial;
    private float prixTickets;
    private Date debut;
    private String description;
    private Date fin;

    private Salle salle;

    private ArrayList<Participant> participants;
    private ArrayList<Ticket> tickets;

    //CONSTRUCTEUR(S)

    public Evenement(String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        this.nom = nom;
        definirDates(salle, debut, fin);
        setSalleAdaptee(salle, capaciteParticipants);
        this.idEvent = LittleSpaceManager_Utilitaire.newId();
        this.nom = nom;
        setCapaciteParticipants(capaciteParticipants);
        setCapaciteSpectateur(salle.getCapacite_max() - capaciteParticipants);
        this.coutInitial = coutInitial;
        this.prixTickets = prixTickets;
        setDescription(description);
        this.tickets = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public int getIdEvent() {
        return idEvent;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public ArrayList<Spectateur> getSpectateurs() {
        ArrayList<Spectateur> spectateurs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            spectateurs.add(ticket.getSpectateur());
        }
        return spectateurs;
    }

    public float getPrixTickets() {
        return prixTickets;
    }

    public String getNom() {
        return nom;
    }

    public int getNombreParticipants() {
        return participants.size();
    }

    public int getNombreTickets() {
        return tickets.size();
    }

    public float getGainsTickets() {
        return getPrixTickets() * getNombreTickets();
    }

    public float getCoutInitial() {
        return coutInitial;
    }

    /**
     * Ajoute un participant à la liste des participants de l'événement, en vérifiant s'il n'est pas déjà présent, et informe le participant qu'il est inscrit en tant que participant à cet événement.
     *
     * @param participant Un participant qui participe à l'événement.
     */
    public void addParticipant(Participant participant) {
        if (!participants.contains(participant) && salle.getCapacite_max() > participants.size() + tickets.size() + 1) {
            participants.add(participant);
            participant.addEvenement(this);
        }
    }

    public void setSalle(Salle salle) {
        if (capaciteParticipants + capaciteSpectateur <= salle.getCapacite_max()) {
            salle.addEvenement(this);
            this.salle = salle;
        } else {
            System.out.println("La salle ne pourra pas accueillir tout le monde, elle est trop petite");
        }
    }

    public void setSalleAdaptee(Salle salle, int capaciteParticipants) throws CreateException {
        if (capaciteParticipants <= salle.getCapacite_max()) {
            salle.addEvenement(this);
            this.salle = salle;
        } else {
            throw new CreateException("La salle ne pourra pas accueillir tout le monde, elle est trop petite");
        }
    }

    public void setDescription(String description) {

        if (description != null && !description.isEmpty()) {
            if (description.length() > 150) {
                description = description.substring(0, 150);
            }
            this.description = description;
        } else {
            this.description = "Aucune description";
        }

    }

    public Salle getSalle() {
        return salle;
    }

    /**
     * Cette fonction permet d'enlever une participant dans la liste des participants de l'événement. La fonction vérifie que le participant est bien présent dans la liste puis elle le supprime et elle supprime l'événement dans la liste des événement du participant.
     *
     * @param participant
     */

    public void removeParticipant(Participant participant) {
        if (participants.contains(participant)) {
            participants.remove(participant);
            participant.removeEvenement(this);
        }
    }

    /**
     * Cette fonction permet de créer un ticket pour un spectateur donné. La fonction ne vérifie pas si le spectateur possède déjà un ticket car il peut en posséder plusieurs.
     *
     * @param spectateur
     */

    public void addTicket(Spectateur spectateur) {
        Ticket ticket = new Ticket(this, spectateur);
        tickets.add(ticket);
        spectateur.addTicket(ticket);
    }

    /**
     * Cette fonction renvoie la capacité max que la salle peut accueillir. Ici, la capacité maximale de Participants et de Spectateur sont additionnées.
     *
     * @return
     */

    public int getCapaciteTotal() {
        return capaciteParticipants + capaciteSpectateur;
    }

    /**
     * Cette fonction permet de changer la captacité de place attribuées pour les participants. La fonction se charge de vérifier que la capacité maximale de la salle n'est pas dépassée.
     *
     * @param capacite
     */

    public void setCapaciteParticipants(int capacite) {
        if (capaciteSpectateur + capacite <= salle.getCapacite_max()) {
            this.capaciteParticipants = capacite;
        }

    }

    /**
     * Cette fonction permet de changer la captacité de place attribuées pour les spectateurs. La fonction se charge de vérifier que la capacité maximale de la salle n'est pas dépassée.
     *
     * @param capacite
     */

    public void setCapaciteSpectateur(int capacite) {
        if (capaciteParticipants + capacite <= salle.getCapacite_max()) {
            this.capaciteSpectateur = capacite;
        }
    }

    /**
     * Cette fonction permet de définir les dates de début et de fin de l'événement.
     * La fonction vérifie que la date de début est bien avant la date de fin et que la salle est disponible.
     *
     * @param debut Le début de l'événement
     * @param fin   La fin de l'événement
     */
    public void definirDates(Salle salle, Date debut, Date fin) throws CreateException {
        if (debut.after(fin)) {
            throw new CreateException("La date de début doit être avant la date de fin");
        } else if (!salle.verifierDisponibilite(debut, fin)) {
            throw new CreateException("La salle n'est pas disponible à ces dates");
        } else {
            this.debut = debut;
            this.fin = fin;
        }
    }

    /**
     * Cette fonction sert à changer la date de début de l'événement en s'assurant que la salle est disponible.
     *
     * @param debutEv
     */

    public void setDebut(Date debutEv) {
        if (salle.verifierDisponibilite(debutEv, this.fin) && (debutEv.before(this.fin) || debutEv.equals(this.fin))) {
            this.debut = debut;
        }
    }

    /**
     * Cette fonction sert à changer la date de fin de l'événement en s'assurant que la salle est disponible.
     *
     * @param finEv
     */

    public void setFin(Date finEv) {
        if (salle.verifierDisponibilite(this.debut, finEv) && (this.debut.before(finEv) || this.debut.equals(finEv))) {
            this.fin = fin;
        }
    }

    /**
     * Cette fonction renvoie la liste des membres du personnel qui participent à l'événement.
     *
     * @return
     */
    public ArrayList<Participant> getPersonnels() {
        ArrayList<Participant> personnels = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.getClass().getSimpleName().equals("Personnel")) {
                personnels.add(participant);
            }

        }
        return personnels;
    }

    public float getSalairesPersonnels() {
        float salaires = 0;
        for (Participant personnel : getPersonnels()) {
            salaires += personnel.getSalaire();
        }
        return salaires;
    }

    /**
     * Cette fonction renvoie la liste des artistes qui participent à l'événement
     *
     * @return
     */
    public ArrayList<Participant> getArtistes() {
        ArrayList<Participant> artistes = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.getClass().getSimpleName().equals("Artiste")) {
                artistes.add(participant);
            }

        }
        return artistes;
    }

    public float getSalairesArtistes() {
        float salaires = 0;
        for (Participant artiste : getArtistes()) {
            salaires += artiste.getSalaire();
        }
        return salaires;
    }

    /**
     * Permet de savoir les gains que l'événement a rapporté.
     *
     * @return Le bénéfice de l'événement.
     */
    public float getBenefices() {
        float totalSalaires = 0;
        for (Participant participant : participants) {
            totalSalaires += participant.getSalaire();
        }
        log.info("Génération des bénéfices pour l'événement " + this.getNom() + " : " + (prixTickets * tickets.size() - coutInitial - totalSalaires) + "€.");
        return prixTickets * tickets.size() - coutInitial - totalSalaires;
    }

    /**
     * Trie dans l'odre : dateDebut, dateFin, nom, idEvent
     *
     * @param evenement L'évenement à comparer.
     * @return
     */
    @Override
    public int compareTo(Evenement evenement) {
        if (this.getDebut().compareTo(evenement.getDebut()) > 0 || this.getDebut().equals(evenement.getDebut()) && this.getFin().compareTo(evenement.getFin()) > 0 || this.getDebut().equals(evenement.getDebut()) && this.getFin().equals(evenement.getFin()) && this.getNom().compareTo(evenement.getNom()) > 0 || this.getDebut().equals(evenement.getDebut()) && this.getFin().equals(evenement.getFin()) && this.getNom().equals(evenement.getNom()) && this.getIdEvent() > evenement.getIdEvent()) {
            return 1;
        } else if (this.getDebut().equals(evenement.getDebut()) && this.getFin().equals(evenement.getFin()) && this.getNom().equals(evenement.getNom()) && this.getIdEvent() == evenement.getIdEvent()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Nom : " + nom + "\n" +
                "Adresse : " + salle.getAdresse() + "\n" +
                "Début : " + debut + "\n" +
                "Fin : " + fin + "\n" +
                "Description : " + description;
    }
}
