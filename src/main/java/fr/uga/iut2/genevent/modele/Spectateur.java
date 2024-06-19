package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class Spectateur extends Personne {
    private ArrayList<Ticket> tickets = new ArrayList<>();

    //CONSTRUCTEUR

    /**
     * Permet de créer un spectateur
     * @param nom le nom du spectateur
     * @param prenom le prénom du spectateur
     */
    public Spectateur(String nom, String prenom) {
        super(nom, prenom);
    }

    //GETTER

    /**
     * Cette fonction nous permet de récupérer la liste des tickets du spectateur.
     *
     * @return la liste des tickets du spectateur
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * La fonction permet d'ajouter un ticket dans la liste de ticket d'un spectateur. Au préalable la fonction vérifie que le ticket n'est pas déjà dans la liste.
     *
     * @param ticket le ticket rattaché au spectateur
     */
    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            tickets.add(ticket);
        }
    }

    //METHODE


}
