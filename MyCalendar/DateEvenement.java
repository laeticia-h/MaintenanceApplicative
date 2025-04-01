import java.time.LocalDateTime;

public class DateEvenement {
    private final LocalDateTime valeur;

    public DateEvenement(LocalDateTime valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime getValeur() {
        return valeur;
    }

    public DateEvenement plusMinutes(int minutes) {
        return new DateEvenement(valeur.plusMinutes(minutes));
    }

    public DateEvenement plusJours(int jours) {
        return new DateEvenement(valeur.plusDays(jours));
    }

    public boolean estAvant(DateEvenement autre) {
        return valeur.isBefore(autre.getValeur());
    }

    public boolean estApres(DateEvenement autre) {
        return valeur.isAfter(autre.getValeur());
    }

    public boolean estEntre(DateEvenement debut, DateEvenement fin) {
        return !this.estAvant(debut) && !this.estApres(fin);
    }

    @Override
    public String toString() {
        return valeur.toString();
    }
}
