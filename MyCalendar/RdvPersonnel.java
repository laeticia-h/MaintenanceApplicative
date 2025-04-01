import java.util.ArrayList;
import java.util.List;

public class RdvPersonnel extends Event {

    public RdvPersonnel(TitreEvenement titre, Proprietaire proprietaire, DateEvenement debut, DureeEvenement duree, EventId id) {
        super(titre, proprietaire, debut, duree,id);
    }

    @Override
    public String description() {
        return "RDV : " + titre;
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
