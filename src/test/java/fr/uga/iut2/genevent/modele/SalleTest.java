package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class SalleTest {
    private Salle salle;
    private GenEvent genevent;

    @BeforeEach
    void setUp() {
        genevent = new GenEvent(); // Vous devez implémenter une version simple de GenEvent pour les tests.
        salle = new Salle(genevent, "Salle de test", "1 Rue de Test", 100, "prise électrique,buvette");
    }

    @Test
    void CreateSalle() {
        assertEquals("Salle de test", salle.getNom());
        assertEquals("1 rue de test", salle.getAdresse());
        assertEquals(100, salle.getCapacite_max());
        assertTrue(salle.getTags().contains("prise électrique"));
        assertTrue(salle.getTags().contains("buvette"));
    }

    @Test
    void SetNom() {
        salle.setNom("nouveau nom");
        assertEquals("Nouveau nom", salle.getNom());
    }

    @Test
    void SetAdresse() {
        salle.setAdresse("2 Rue De Test");
        assertEquals("2 rue de test", salle.getAdresse());
    }

    @Test
    void SetCapaciteMax() {
        salle.setCapacite_max(200);
        assertEquals(200, salle.getCapacite_max());

        salle.setCapacite_max(-10);
        assertEquals(0, salle.getCapacite_max());
    }

    @Test
    void AddEvenement() throws CreateException {
        Evenement evenement = new OneManShow(genevent, "OneManShow", 5, 5, 5, new Date(), new Date(), "OneManShow très drole", salle);
        assertDoesNotThrow(() -> salle.addEvenement(evenement));
        assertTrue(salle.getHistoriqueEvenements().contains(evenement));
    }

    @Test
    void VeriferDisponibilite() throws CreateException {
        Evenement evenement = new OneManShow(genevent, "OneManShow", 5, 5, 5, new Date(), new Date(), "OneManShow très drole", salle);
        assertDoesNotThrow(() -> salle.addEvenement(evenement));

        Date startConflict = new Date(System.currentTimeMillis() - 1800 * 1000);
        Date endConflict = new Date(System.currentTimeMillis() + 1800 * 1000);
        assertFalse(salle.verifierDisponibilite(startConflict, endConflict));

        Date startNoConflict = new Date(System.currentTimeMillis() + 3600 * 1000);
        Date endNoConflict = new Date(System.currentTimeMillis() + 7200 * 1000);
        assertTrue(salle.verifierDisponibilite(startNoConflict, endNoConflict));
    }

    @Test
    void GetEvenementsFuturs() throws CreateException {
        Evenement pastEvent = new OneManShow(genevent, "OneManShow", 5, 5, 5, new Date(), new Date(), "OneManShow très drole", salle);
        Evenement futureEvent = new OneManShow(genevent, "OneManShow", 5, 5, 5, new Date(), new Date(), "OneManShow très drole", salle);;
        assertDoesNotThrow(() -> salle.addEvenement(pastEvent));
        assertDoesNotThrow(() -> salle.addEvenement(futureEvent));

        TreeSet<Evenement> evenementsFuturs = salle.getEvenementsFuturs();
        assertTrue(evenementsFuturs.contains(futureEvent));
    }

    @Test
    void tetTags() {
        salle.setTags("nouveau,tag");
        assertTrue(salle.getTags().contains("nouveau"));
        assertTrue(salle.getTags().contains("tag"));
    }

    @Test
    void AddTag() {
        salle.addTag("wifi");
        assertTrue(salle.getTags().contains("wifi"));
    }
}
