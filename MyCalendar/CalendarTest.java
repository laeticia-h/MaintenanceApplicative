import org.testng.annotations.Test;
import java.time.LocalDateTime;
import static org.testng.AssertJUnit.assertEquals;

public class CalendarTest {

    @Test
    void ajouterEntretien() {
        CalendarManager calendrier = new CalendarManager();
        LocalDateTime date = LocalDateTime.of(2025, 4, 6, 10, 0);

        calendrier.ajouterEvent(
                "ENTRETIEN",
                "Master MIAGE",
                "Laeticia",
                date,
                30,
                "", "", 0
        );

        Event evenement = calendrier.events.get(0);

        assertEquals("Entretien : Master MIAGE", evenement.description());
    }
}
