package com.gildedrose;

class GildedRose {
    public static final int MAX_QUALITY_VALUE = 50;
    public static final int MIN_QUALITY_VALUE = 0;
    public static final int MIN_SELLIN_VALUE = 0;
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
                }

                if (item.sellIn < 6) {
                    if (isQualityLessThanMax(item.quality)) {
                        item.quality++;
                    }
                }

                if (item.sellIn < 11) {
                    if (isQualityLessThanMax(item.quality)) {
                        item.quality++;
                    }
                }

            } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                if (isQualityMoreThanMin(item.quality)) {
                    item.quality--;
                }
            }

            if (isSellInLessThenMin(item.sellIn)) {
                if (item.name.equals("Aged Brie")) {
                    if (isQualityLessThanMax(item.quality)) {
                        item.quality++;
                    }
                } else if(item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
                    item.quality = item.quality - item.quality;
                } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    if (isQualityMoreThanMin(item.quality)) {
                        item.quality--;
                    }
                }
            }
        }
    }

    private static boolean isSellInLessThenMin(int itemSellInvValue) {
        return itemSellInvValue < MIN_SELLIN_VALUE;
    }

    private static boolean isQualityMoreThanMin(int itemQualityValue) {
        return itemQualityValue > MIN_QUALITY_VALUE;
    }

    private static boolean isQualityLessThanMax(int itemQualityValue) {
        return itemQualityValue < MAX_QUALITY_VALUE;
    }
}
