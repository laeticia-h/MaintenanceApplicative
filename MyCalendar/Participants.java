
public class Participants {
    private final String valeur;

    public Participants(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            throw new IllegalArgumentException("Les participants ne peuvent pas être vides");
        }
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}
