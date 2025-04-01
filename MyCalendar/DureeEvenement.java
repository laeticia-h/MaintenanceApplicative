
public class DureeEvenement {
    private final int minutes;

    public DureeEvenement(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("La durée doit être positive");
        }
        this.minutes = minutes;
    }

    public int enMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return minutes + " minutes";
    }
}
