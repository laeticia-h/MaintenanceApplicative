import java.util.List;

public abstract class Event {
    protected TitreEvenement titre;
    protected Proprietaire proprietaire;
    protected DateEvenement debut;
    protected DureeEvenement duree;
    protected EventId id;

    public Event(TitreEvenement titre, Proprietaire proprietaire, DateEvenement debut, DureeEvenement duree, EventId id) {
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.debut = debut;
        this.duree = duree;
        this.id = new EventId();

    }

    public abstract String description();

    public DateEvenement getDebut() {
        return debut;
    }

    public DureeEvenement getDuree() {
        return duree;
    }

    public DateEvenement getFin() {
        return debut.plusMinutes(duree.enMinutes());
    }

    public boolean enConflitAvec(Event autre) {
        return debut.estEntre(autre.getDebut(), autre.getFin())
                || getFin().estEntre(autre.getDebut(), autre.getFin());
    }

    public abstract List<Event> occurrencesDansPeriode(DateEvenement debut, DateEvenement fin);

    public EventId getId() {
        return id;
    }

}
