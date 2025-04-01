import java.util.ArrayList;
import java.util.List;

public class Reunion extends Event {

    private final LieuEvenement lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, Proprietaire proprietaire, DateEvenement debut, DureeEvenement duree, LieuEvenement lieu, Participants participants, EventId id) {
        super(titre, proprietaire, debut, duree,id);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + titre + " à " + lieu + " avec " + participants;
    }

    @Override
    public List<Event> occurrencesDansPeriode(DateEvenement d1, DateEvenement d2) {
        if (debut.estEntre(d1, d2)) {
            List<Event> liste = new ArrayList<>();
            liste.add(this);
            return liste;
        }
        return new ArrayList<>();
    }
}
