package com.gildedrose;

class GildedRose {
    public static final int MAX_QUALITY_VALUE = 50;
    public static final int MIN_QUALITY_VALUE = 0;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn--;
            }

            if(item.name.equals("Aged Brie") ) {
                if (isQualityLessThanMax(item.quality)) {
                    item.quality++;
                }
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (isQualityLessThanMax(item.quality)) {
                    item.quality++;

                    if (item.sellIn < 11) {
                        if (isQualityLessThanMax(item.quality)) {
                            item.quality++;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (isQualityLessThanMax(item.quality)) {
                            item.quality++;
                        }
                    }
                }
            } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                if (isQualityMoreThanMin(item)) {
                    item.quality--;
                }
            }

            if (item.sellIn < 0) {
                if (item.name.equals("Aged Brie")) {
                    if (isQualityLessThanMax(item.quality)) {
                        item.quality++;
                    }
                } else if(item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
                    item.quality = item.quality - item.quality;
                } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    if (isQualityMoreThanMin(item)) {
                        item.quality--;
                    }
                }
            }
        }
    }

    private static boolean isQualityMoreThanMin(Item item) {
        return item.quality > MIN_QUALITY_VALUE;
    }

    private static boolean isQualityLessThanMax(int itemQualityValue) {
        return itemQualityValue < MAX_QUALITY_VALUE;
    }
}
