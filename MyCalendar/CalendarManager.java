import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class CalendarManager {
    private List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEntretien(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, DureeEvenement duree, EventId id) {
        events.add(new Entretien(titre, proprietaire, date, duree,id));
    }

    public void ajouterRdv(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, DureeEvenement duree, EventId id) {
        events.add(new RdvPersonnel(titre, proprietaire, date, duree,id));
    }

    public void ajouterReunion(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, DureeEvenement duree, LieuEvenement lieu, Participants participants, EventId id) {
        events.add(new Reunion(titre, proprietaire, date, duree, lieu, participants,id));
    }

    public void ajouterPeriodique(TitreEvenement titre, Proprietaire proprietaire, DateEvenement date, DureeEvenement duree, FrequenceEvenement frequence, EventId id) {
        events.add(new Periodique(titre, proprietaire, date, duree, frequence,id));
    }

    public List<Event> eventsDansPeriode(LocalDateTime d, LocalDateTime f) {
        DateEvenement debut = new DateEvenement(d);
        DateEvenement fin = new DateEvenement(f);

        return events.stream()
                .flatMap(e -> e.occurrencesDansPeriode(debut, fin).stream())
                .collect(Collectors.toList());
    }

    public boolean conflit(Event e1, Event e2) {
        return e1.enConflitAvec(e2);
    }

    public void afficherEvenements() {
        events.forEach(e -> System.out.println(e.description()));
    }

    public List<Event> getEvents() {
        return events;
    }
    public void supprimerParId(EventId id) {
        events.removeIf(e -> e.getId().equals(id));
    }

}
