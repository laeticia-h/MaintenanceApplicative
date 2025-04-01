
public class FrequenceEvenement {
    private final int jours;

    public FrequenceEvenement(int jours) {
        if (jours <= 0) {
            throw new IllegalArgumentException("La fréquence doit être un nombre de jours strictement positif");
        }
        this.jours = jours;
    }

    public int getJours() {
        return jours;
    }

    @Override
    public String toString() {
        return String.valueOf(jours);
    }
}
