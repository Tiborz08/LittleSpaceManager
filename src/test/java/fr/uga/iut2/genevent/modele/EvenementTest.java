package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;

class EvenementTest {

    private Evenement evenement;
    private Salle salle;
    private GenEvent geneventTest;

    Date debut = new Date();
    Date fin = new Date(System.currentTimeMillis() + 24*60*60*1000);
    @BeforeEach
    void setUp() throws CreateException {
        geneventTest = new GenEvent();
        // Initialisation des objets nécessaires pour les tests
        salle = new Salle(geneventTest, "Nom salle", "123 Rue Test", 100, ""); // Création d'une salle
        evenement = new Evenement(geneventTest, "Nom évènement", 50, 1000, 20, debut, fin, "Description Test", salle) {
            // Implémentation anonyme de Evenement car c'est une classe abstraite
        };
    }

    @Test
    void setCapaciteParticipants() {
        // Cas normal
        evenement.setCapaciteParticipants(40);
        assertEquals(40, evenement.capaciteParticipants);
        assertEquals(60, evenement.capaciteSpectateur); // La capacité spectateur devrait être ajustée

        // Cas où la capacité est supérieure à la capacité de la salle
        evenement.setCapaciteParticipants(101);
        assertNotEquals(101, evenement.capaciteParticipants); // La capacité ne devrait pas être mise à jour
    }

    @Test
    void setCapaciteSpectateur() {
        // Cas normal
        evenement.setCapaciteSpectateur(40);
        assertEquals(40, evenement.capaciteSpectateur);
        assertEquals(50, evenement.capaciteParticipants); // La capacité participants devrait être ajustée

        // Cas où la capacité totale est supérieure à la capacité de la salle
        evenement.setCapaciteSpectateur(60);
        assertNotEquals(60, evenement.capaciteSpectateur); // La capacité ne devrait pas être mise à jour
    }

    @Test
    void setDebut() {
        Date newDebut = new Date(evenement.getDebut().getTime()); // Date de début
        evenement.setDebut(newDebut);
        assertEquals(newDebut, evenement.getDebut());

        Date invalidDebut = new Date(evenement.getFin().getTime() + 259200000); // Date de fin : 3 jours plus tard
        evenement.setDebut(invalidDebut);
        assertNotEquals(invalidDebut, evenement.getDebut()); // La date de fin ne devrait pas être mise à jour
    }

    @Test
    void setFin() {
        Date newFin = new Date(evenement.getFin().getTime()); // Date de début : 3 jours avant
        evenement.setFin(newFin);
        assertEquals(newFin, evenement.getFin());

        Date invalidFin = new Date(evenement.getDebut().getTime() + 259200000); // Date de fin : 3 jours après
        evenement.setFin(invalidFin);
        assertNotEquals(invalidFin, evenement.getFin()); // La date de fin ne devrait pas être mise à jour
    }
}