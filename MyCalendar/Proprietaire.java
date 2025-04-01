
public class Proprietaire {
    private final String nom;

    public Proprietaire(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom du propriétaire ne peut pas être vide");
        }
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
