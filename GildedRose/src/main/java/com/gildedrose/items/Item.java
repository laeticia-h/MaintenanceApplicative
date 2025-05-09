package com.gildedrose.items;

import items.AgedBrie;
import items.Conjured;
import items.DefaultItem;
import items.Sulfuras;

public abstract class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public static Item createItem(String name, int sellIn, int quality) {
        switch (name) {
            case "Aged Brie":
                return new AgedBrie(sellIn, quality);
            case "Sulfuras, Hand of Ragnaros":
                return new Sulfuras(sellIn);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new com.gildedrose.items.BackstagePass(sellIn, quality);
            case "Conjured":
                return new Conjured(sellIn, quality);
            default:
                return new DefaultItem(name, sellIn, quality);
        }
//        return new Item(name, sellIn, quality);
    }

    public abstract void updateQuality();
}
