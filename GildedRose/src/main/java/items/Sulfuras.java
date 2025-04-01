package items;
import com.gildedrose.items.Item;

public class Sulfuras extends Item {
    public Sulfuras(int sellIn) {
        super("Sulfuras, Hand of Ragnaros", sellIn, 80); // qualité fixe
    }

    @Override
    public void updateQuality() {
        // Légendaire, ne change jamais
    }
}
