package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;
import fr.uga.iut2.genevent.util.LittleSpaceManager_Utilitaire;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Les événements qui sont dans l'application
 */
public abstract class Evenement implements Comparable<Evenement> {
    private static final Log log = LogFactory.getLog(Evenement.class);

    //ATTRIBUTIONS

    private int idEvent;
    private String nom;
    public int capaciteParticipants;
    public int capaciteSpectateur;
    private float coutInitial;
    private float prixTickets;
    private Date debut;
    private String description;
    private Date fin;

    private Salle salle;

    private ArrayList<Participant> participants;
    private ArrayList<Ticket> tickets;

    //CONSTRUCTEUR(S)

    /**
     * Constructeur d'évenement
     * @param nom Le nom d'un évenement
     * @param capaciteParticipants Le nombre de personnes salariées présentes sur l'événement
     * @param coutInitial Le coût d'organisation de base d'un événement
     * @param prixTickets Le prix d'un seul ticket
     * @param debut La date de début de l'événement
     * @param fin La date de fin de l'événement
     * @param description La description de l'événement
     * @param salle La salle dans laquelle a lieu l'événement
     * @throws CreateException Averti des problèmes lors de la création d'une salle (Exemple : Le début qui a lieu après la fin)
     */
    public Evenement(String nom, int capaciteParticipants, float coutInitial, float prixTickets, Date debut, Date fin, String description, Salle salle) throws CreateException {
        setNom(nom);
        definirDates(salle, debut, fin);
        setSalleAdaptee(salle, capaciteParticipants);
        this.idEvent = LittleSpaceManager_Utilitaire.newId();
        setCapaciteParticipants(capaciteParticipants);
        setCoutInitial(coutInitial);
        setPrixTickets(prixTickets);
        setDescription(description);
        this.tickets = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    /**
     * Renvoie l'id de l'événement
     * @return l'id de l'événement, qui a été généré automatiquement lors de la création de l'événement
     */
    public int getIdEvent() {
        return idEvent;
    }

    /**
     * Renvoie le debut de l'événement
     * @return la date de début de l'événement
     */
    public Date getDebut() {
        return debut;
    }

    /**
     * Renvoie la fin de l'événement
     * @return la date de fin de l'événement
     */
    public Date getFin() {
        return fin;
    }

    /**
     * Renvoie la description de l'événement
     * @return la description de l'événement
     */
    public String getDescription() {
        return description;
    }

    /**
     * Renvoie la liste des personnes salariées de l'événement
     * @return la liste des personnes salariées reliées à cette événement
     */
    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    /**
     * Renvoie la liste des tickets pour cet evenement
     * @return  la liste des tickets pour cet evenement
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Renvoie la liste des personnes liés aux tickets de l'événement
     * @return tous les spectateurs qui ont un ticket pour cet événement
     */
    public ArrayList<Spectateur> getSpectateurs() {
        ArrayList<Spectateur> spectateurs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            spectateurs.add(ticket.getSpectateur());
        }
        return spectateurs;
    }

    /**
     * Renvoie le prix d'un ticket
     * @return le prix d'un ticket
     */
    public float getPrixTickets() {
        return prixTickets;
    }

    /**
     * Modifie le prix des tickets, le prix ne peut pas être nul
     * @param prixTickets le prix à définir
     */
    public void setPrixTickets(float prixTickets) {
        this.prixTickets = Math.max(0, prixTickets);
    }

    /**
     * Renvoie le nom de l'événement
     * @return le nom de l'événement
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de l'événement, le nom est capitalisé, si aucun nom n'es renseigné, l'évenent a comme nom : Aucun nom
     * @param string le nom de l'événement
     */
    public void setNom(String string){
        if (string != null && !string.isEmpty()) {
            this.nom = LittleSpaceManager_Utilitaire.capitalize(string);
        } else {
            this.nom = "Aucun nom";
        }
    }

    /**
     * Retourne le nombre de salariés participants à cet événement
     * @return le nombre de salariés participants à cet événement
     */
    public int getNombreParticipants() {
        return participants.size();
    }

    /**
     * Renvoie le nombre de tickets
     * @return le nombre de tickets
     */
    public int getNombreTickets() {
        return tickets.size();
    }

    /**
     * Renvoie les gains octroyés via la vente de billets
     * @return tout l'argent obtenu par la vente de tickets
     */
    public float getGainsTickets() {
        return getPrixTickets() * getNombreTickets();
    }

    /**
     * Renvoie le cout initial d'un événement
     * @return le cout initial d'un événement
     */
    public float getCoutInitial() {
        return coutInitial;
    }

    /**
     * Modifie le coût initial d'un événement, ce coût ne peux pas être négatif
     * @param coutInitial le coût initial de l'événement
     */
    public void setCoutInitial(float coutInitial) {
        this.coutInitial = Math.max(0, coutInitial);
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

    /**
     * Relie la salle à l'événement
     * @param salle la salle dans laquelle se déroule l'événement
     * @throws CreateException Averti des problèmes lors de la création d'une salle (Exemple : La salle n'est pas disponible aux dates de l'événement)
     */
    public void setSalle(Salle salle) throws CreateException {
        if (salle == null) {
            Salle temporaire = this.salle;
            this.salle = null;
            if(temporaire != null){
                temporaire.enleverDeLHistorique(this);
            }

        }
        else if (capaciteParticipants <= salle.getCapacite_max()) {
            salle.addEvenement(this);
            this.salle = salle;
            setCapaciteParticipants(capaciteParticipants);
        } else {
            throw new CreateException("La salle ne pourra pas accueillir tout le monde, elle est trop petite");
        }
    }

    /**
     * Permet de définir une salle assez grande pour accueillir tout le monde
     * @param salle La salle dans laquelle se déroule l'événement
     * @param capaciteParticipants Le nombre maximum de salariés
     * @throws CreateException Averti des problèmes lors de la création d'une salle (Exemple : La salle est trop petite)
     */
    public void setSalleAdaptee(Salle salle, int capaciteParticipants) throws CreateException {
        if (capaciteParticipants <= salle.getCapacite_max()) {
            salle.addEvenement(this);
            this.salle = salle;
        } else {
            throw new CreateException("La salle ne pourra pas accueillir tout le monde, elle est trop petite");
        }
    }

    /**
     * Permet de modifier la description de la salle
     * La description fait 150 charactères maximum
     * Si aucune descritption n'est renseignée, la description par défaut est "Aucune description"
     * @param description
     */
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

    /**
     * Renvoie la salle dans laquelle se déroule l'événement
     * @return la salle dans laquelle se déroule l'événement
     */
    public Salle getSalle() {
        return salle;
    }

    /**
     * Cette fonction permet d'enlever une participant dans la liste des participants de l'événement. La fonction vérifie que le participant est bien présent dans la liste puis elle le supprime et elle supprime l'événement dans la liste des événement du participant.
     *
     * @param participant Le salarié qui ne participe plus à l'événement
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
     * @param spectateur Le spectateur qui assiste à l'événement
     */

    public void addTicket(Spectateur spectateur) {
        Ticket ticket = new Ticket(this, spectateur);
        tickets.add(ticket);
        spectateur.addTicket(ticket);
    }

    /**
     * Cette fonction prends en paramètre un spectateur et supprime le ticket relié
     * @param spectateur le spectateur qui predra son ticket
     */
    public void removeTicket(Spectateur spectateur){
        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getSpectateur().equals(spectateur)) {
                ticket.setEvenement(null);
                ticket.setSpectateur(null);
                iterator.remove();
                spectateur.getTickets().remove(ticket);
            }
        }
        }


        /**
         * Cette fonction renvoie la capacité max que la salle peut accueillir. Ici, la capacité maximale de Participants et de Spectateur sont additionnées.
         *
         * @return le nombre maximum de personne, tous genres confondus
         */

    public int getCapaciteTotal() {
        return capaciteParticipants + capaciteSpectateur;
    }

    /**
     * Cette fonction permet de changer la captacité de place attribuées pour les participants. La fonction se charge de vérifier que la capacité maximale de la salle n'est pas dépassée.
     *
     * @param capacite Le nombre maximum de salaries
     */

    public void setCapaciteParticipants(int capacite) {
        if (capacite <= salle.getCapacite_max()) {
            this.capaciteParticipants = capacite;
            capaciteSpectateur = salle.getCapacite_max() - capaciteParticipants;
        }

    }

    /**
     * Cette fonction permet de changer la captacité de place attribuées pour les spectateurs. La fonction se charge de vérifier que la capacité maximale de la salle n'est pas dépassée.
     *
     * @param capacite Le npmbre maximum de spectateurs
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
     * @param debutEv Le début de l'événement
     */

    public void setDebut(Date debutEv) {
        if (salle.verifierDisponibilite(debutEv, this.fin) && (debutEv.before(this.fin) || debutEv.equals(this.fin))) {
            this.debut = debutEv;
            log.info("La date de début de l'événement " + this.getNom() + " a été modifiée à " + LittleSpaceManager_Utilitaire.afficherDateChiffre(debutEv) + ".");
        }
    }

    /**
     * Cette fonction sert à changer la date de fin de l'événement en s'assurant que la salle est disponible.
     *
     * @param finEv La fin de l'événement
     */

    public void setFin(Date finEv) {
        if (salle.verifierDisponibilite(this.debut, finEv) && (this.debut.before(finEv) || this.debut.equals(finEv))) {
            this.fin = finEv;
            log.info("La date de fin de l'événement " + this.getNom() + " a été modifiée à " + LittleSpaceManager_Utilitaire.afficherDateChiffre(finEv));
        }
    }

    /**
     * Cette fonction renvoie la liste des membres du personnel qui participent à l'événement.
     *
     * @return la liste des salariés qui font parti du personnel
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

    /**
     * Renvoie le salaire de tous les membres du personnels qui participent à cet événement
     * @return le salaire de tous les membres du personnels qui participent à cet événement
     */
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
     * @return la liste des artistes qui participent à l'événement
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

    /**
     * Renvoie le salaire de tous les artistes qui jouent dans l'événement
     * @return le salaire de tous les artistes qui jouent dans l'événement
     */
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
     * @return 1 ou -1 pour trier en fonction de l'odre correspondant, si les 2 evenements sont identiques, nous renvoyons 0
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

    /**
     * Méthode d'affichage d'un événement
     * @return Le nom, l'adresse, la date de début et de fin ainsi que la description de l'événement
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " : " + nom + "\n" +
                "Adresse : " + salle.getAdresse() + "\n" +
                "Début : " + LittleSpaceManager_Utilitaire.afficherDateChiffre(debut) + "\n" +
                "Fin : " + LittleSpaceManager_Utilitaire.afficherDateChiffre(fin) + "\n" +
                "Description : " + description;
    }

    public ArrayList<Accessoire> getAccessoires(){
        return null;
    }

    public void addAccessoire(String nom){}
}
