import java.util.ArrayList;
import java.util.List;

public class Periodique extends Event {

    private final FrequenceEvenement frequence;

    public Periodique(TitreEvenement titre, Proprietaire proprietaire, DateEvenement debut, DureeEvenement duree, FrequenceEvenement frequence, EventId id) {
        super(titre, proprietaire, debut, duree, id);
        this.frequence = frequence;
    }

    @Override
    public String description() {
        return "Événement périodique : " + titre + " tous les " + frequence + " jours";
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
