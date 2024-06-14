package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class Spectateur extends Personne{
    private ArrayList<Ticket> tickets = new ArrayList<>();
    //CONSTRUCTEUR
    public Spectateur(String nom,String prenom){
        super(nom,prenom);
    }

    //GETTER

    /**
     * Cette fonction nous permet de récupérer la liste des tickets du spectateur.
     * @return
     */

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * La fonction permet d'ajouter un ticket dans la liste de ticket d'un spectateur. Au préalable la fonction vérifie que le ticket n'est pas déjà dans la liste.
     * @param ticket
     */

    public void addTicket(Ticket ticket){
        if (!tickets.contains(ticket)){
            tickets.add(ticket);
        }
    }

    //METHODE



}
