import java.util.UUID;

public class EventId {
    private final String id;


    public EventId() {
        this.id = UUID.randomUUID().toString();
    }

    // Construit un EventId à partir d’un ID donné (utilisé pour la suppression)
    public EventId(String id) {
        this.id = id;
    }

    public String getValeur() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EventId && ((EventId) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
