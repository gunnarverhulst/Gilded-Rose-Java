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
                item.quality = Math.min(++item.quality, MAX_QUALITY_VALUE);
                if (isSellInLessThenMin(item.sellIn)) {
                    item.quality = Math.min(++item.quality, MAX_QUALITY_VALUE);
                }
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                int addedQuality = 1;

                if (item.sellIn < 6) {
                    addedQuality = 3;
                } else if (item.sellIn < 11) {
                    addedQuality = 2;
                }

                item.quality = Math.min(item.quality + addedQuality, MAX_QUALITY_VALUE);

            } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality = Math.max(--item.quality, MIN_QUALITY_VALUE);
            }

            if (isSellInLessThenMin(item.sellIn)) {
                if (!item.name.equals("Aged Brie")) {
                    if(item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
                        item.quality = 0;
                    } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality = Math.max(--item.quality, MIN_QUALITY_VALUE);
                    }
                }
            }
        }
    }

    private static boolean isSellInLessThenMin(int itemSellInvValue) {
        return itemSellInvValue < MIN_SELLIN_VALUE;
    }

}
