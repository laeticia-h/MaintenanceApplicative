
public class LieuEvenement {
    private final String valeur;

    public LieuEvenement(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            throw new IllegalArgumentException("Le lieu ne peut pas être vide");
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
