package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.CreateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ConcertTest {

    private Concert concert;

    @BeforeEach
    public void setUp() throws CreateException {
        GenEvent genEvent = new GenEvent();
        Salle salle = new Salle(genEvent, "Salle de test", "1 rue du test", 200, "");
        Date debut = new Date();
        Date fin = new Date(debut.getTime() + 3600 * 1000); // 1 hour later
        concert = new Concert(genEvent, "Dansons la java", 100, 5000, 50, debut, fin, "Concert avec des danseurs de java", salle);
    }

    @Test
    public void getGenre() {
        assertEquals("INCONNU", concert.getGenre());
    }

    @Test
    public void setGenre() {
        concert.setGenre("Java");
        assertEquals("JAVA", concert.getGenre());
    }
}
