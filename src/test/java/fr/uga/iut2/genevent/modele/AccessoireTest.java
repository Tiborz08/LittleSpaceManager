package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AccessoireTest {

    private PieceDeTheatre pieceDeTheatre;
    private Accessoire accessoire;
    private Salle salle;
    private GenEvent genevent;

    @BeforeEach
    public void setUp() throws CreateException {
        genevent = new GenEvent();
        salle = new Salle(genevent, "Salle A", "1 Rue de Test", 100, "prise électrique,buvette");
        pieceDeTheatre = new PieceDeTheatre(genevent, "L'Avare", 5, 5, 5, new Date(), new Date(), "Molière", salle);
        accessoire = new Accessoire("Epée", pieceDeTheatre);
    }

    @Test
    public void getNom() {
        assertEquals("Epée", accessoire.getNom());
    }

    @Test
    public void setId() {
        assertNotNull(accessoire.getId());
    }

    @Test
    public void setNom() {
        accessoire.setNom("Dague");
        assertEquals("Dague", accessoire.getNom());
    }

    @Test
    public String toString() {
        String expected = accessoire.getId() + " - Epée";
        assertEquals(expected, accessoire.toString());
        return expected;
    }
}
