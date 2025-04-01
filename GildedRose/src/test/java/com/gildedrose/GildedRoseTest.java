package com.gildedrose;

import com.gildedrose.items.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { Item.createItem("foo", 0, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void testExpired() {
        Item[] items = new Item[] { Item.createItem("foo", -1, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void testQualityNoNegative() {
        Item[] items = new Item[] { Item.createItem("foo", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testNeverMore50() {
        Item[] items = new Item[] { Item.createItem("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testAgedBrie() {
        Item[] items = new Item[] {
                Item.createItem("Aged Brie", 0, 49),
                Item.createItem("Aged Brie", -1, 30)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
        assertEquals(32, items[1].quality);
    }

    @Test
    void testSulfuras() {
        Item[] items = new Item[] {
                Item.createItem("Sulfuras, Hand of Ragnaros", 5, 80)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void testBackstagePasses() {
        Item[] items = new Item[] {
                Item.createItem("Backstage passes to a TAFKAL80ETC concert", 10, 10),
                Item.createItem("Backstage passes to a TAFKAL80ETC concert", 5, 10),
                Item.createItem("Backstage passes to a TAFKAL80ETC concert", 0, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(13, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
    }
}
