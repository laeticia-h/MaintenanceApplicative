
public class TitreEvenement {
    private final String valeur;

    public TitreEvenement(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
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
