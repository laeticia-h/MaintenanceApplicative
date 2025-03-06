package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            String nom = items[i].name;
            boolean agedBrie = nom.equals("Aged Brie");
            boolean backStage = nom.equals("Backstage passes to a TAFKAL80ETC concert");
            boolean sulfuras = nom.equals("Sulfuras, Hand of Ragnaros");

            if (!agedBrie && !backStage) {
                if (items[i].quality > 0 && !sulfuras) {
                    items[i].quality--;
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality++;

                    if (backStage) {
                        if (items[i].sellIn < 11 && items[i].quality < 50) {
                            items[i].quality++;
                        }
                        if (items[i].sellIn < 6 && items[i].quality < 50) {
                            items[i].quality++;
                        }
                    }
                }
            }

            if (!sulfuras) {
                items[i].sellIn--;
            }

            if (items[i].sellIn < 0) {
                if (!agedBrie) {
                    if (!backStage) {
                        if (items[i].quality > 0 && !sulfuras) {
                            items[i].quality--;
                        }
                    } else {
                        items[i].quality = 0;
                    }
                } else if (items[i].quality < 50) {
                    items[i].quality++;
                }
            }
        }
    }
}
