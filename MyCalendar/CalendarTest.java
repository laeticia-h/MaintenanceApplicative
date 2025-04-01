import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class CalendarTest {

    @Test
    void ajouterEntretien() {
        CalendarManager calendrier = new CalendarManager();
        calendrier.ajouterEntretien(
                new TitreEvenement("Master MIAGE"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 4, 6, 10, 0)),
                new DureeEvenement(30),
                new EventId()
        );

        Event evenement = calendrier.getEvents().get(0);
        assertEquals("Entretien : Master MIAGE", evenement.description());
    }

    @Test
    void ajouterRdv() {
        CalendarManager calendrier = new CalendarManager();
        calendrier.ajouterRdv(
                new TitreEvenement("Médecin"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 4, 7, 14, 0)),
                new DureeEvenement(45),
                new EventId()
        );

        Event e = calendrier.getEvents().get(0);
        assertEquals("RDV : Médecin", e.description());
    }

    @Test
    void plusieursRDV() {
        CalendarManager calendrier = new CalendarManager();
        calendrier.ajouterEntretien(
                new TitreEvenement("Soutenance"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 6, 1, 9, 0)),
                new DureeEvenement(60),
                new EventId()
        );
        calendrier.ajouterRdv(
                new TitreEvenement("Dentiste"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 6, 1, 11, 0)),
                new DureeEvenement(30),
                new EventId()
        );

        List<Event> evts = calendrier.getEvents();
        assertEquals("Entretien : Soutenance", evts.get(0).description());
        assertEquals("RDV : Dentiste", evts.get(1).description());
    }

    @Test
    void conflitsHoraires() {
        CalendarManager calendrier = new CalendarManager();
        calendrier.ajouterRdv(
                new TitreEvenement("Massage"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 4, 8, 10, 0)),
                new DureeEvenement(60),
                new EventId()
        );
        calendrier.ajouterEntretien(
                new TitreEvenement("Stage"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 4, 8, 10, 30)),
                new DureeEvenement(30),
                new EventId()
        );

        Event e1 = calendrier.getEvents().get(0);
        Event e2 = calendrier.getEvents().get(1);
        assertTrue(e1.enConflitAvec(e2));
    }

    @Test
    void pasDeConflitSiHoraires() {
        CalendarManager calendrier = new CalendarManager();
        calendrier.ajouterRdv(
                new TitreEvenement("Sport"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 4, 8, 8, 0)),
                new DureeEvenement(60),
                new EventId()
        );
        calendrier.ajouterEntretien(
                new TitreEvenement("Réunion"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 4, 8, 10, 0)),
                new DureeEvenement(30),
                new EventId()
        );

        Event e1 = calendrier.getEvents().get(0);
        Event e2 = calendrier.getEvents().get(1);
        assertFalse(e1.enConflitAvec(e2));
    }
    @Test
    void supprimerEvenementParId() {
        CalendarManager calendrier = new CalendarManager();
        calendrier.ajouterRdv(
                new TitreEvenement("Ophtalmo"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 5, 1, 10, 0)),
                new DureeEvenement(30),
                new EventId()
        );

        Event evenement = calendrier.getEvents().get(0);
        EventId id = evenement.getId();
        calendrier.supprimerParId(id);
        assertTrue(calendrier.getEvents().isEmpty());
    }
    @Test
    void neSupprimePasIdInexistant() {
        CalendarManager calendrier = new CalendarManager();
        calendrier.ajouterRdv(
                new TitreEvenement("Kiné"),
                new Proprietaire("Laeticia"),
                new DateEvenement(LocalDateTime.of(2025, 5, 2, 14, 0)),
                new DureeEvenement(45),
                new EventId()
        );

        EventId fauxId = new EventId("inexistant6");
        calendrier.supprimerParId(fauxId);
        assertEquals(1, calendrier.getEvents().size());
        assertEquals("RDV : Kiné", calendrier.getEvents().get(0).description());
    }
    @Test
    void filtrerEvenementsDansPeriode() {
        CalendarManager calendrier = new CalendarManager();

        DateEvenement debut = new DateEvenement(LocalDateTime.of(2025, 6, 1, 10, 0));
        DateEvenement horsPeriode = new DateEvenement(LocalDateTime.of(2026, 1, 1, 10, 0));

        calendrier.ajouterRdv(
                new TitreEvenement("Consultation"),
                new Proprietaire("Laeticia"),
                debut,
                new DureeEvenement(30),
                new EventId()
        );

        calendrier.ajouterRdv(
                new TitreEvenement("Vaccin"),
                new Proprietaire("Laeticia"),
                horsPeriode,
                new DureeEvenement(30),
                new EventId()
        );

        List<Event> dansPeriode = calendrier.eventsDansPeriode(
                debut.getValeur().minusDays(1),
                debut.getValeur().plusDays(1)
        );

        assertEquals(1, dansPeriode.size());
        assertEquals("RDV : Consultation", dansPeriode.get(0).description());
    }
    @Test
    void conflitMemeHoraireExact() {
        CalendarManager calendrier = new CalendarManager();
        LocalDateTime date = LocalDateTime.of(2025, 4, 10, 15, 0);

        calendrier.ajouterRdv(
                new TitreEvenement("RDV A"),
                new Proprietaire("Laeticia"),
                new DateEvenement(date),
                new DureeEvenement(60),
                new EventId()
        );
        calendrier.ajouterEntretien(
                new TitreEvenement("Entretien B"),
                new Proprietaire("Laeticia"),
                new DateEvenement(date),
                new DureeEvenement(60),
                new EventId()
        );

        Event a = calendrier.getEvents().get(0);
        Event b = calendrier.getEvents().get(1);

        assertTrue(a.enConflitAvec(b));
    }


}
