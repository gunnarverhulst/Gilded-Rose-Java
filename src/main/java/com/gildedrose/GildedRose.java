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

            int addedQuality = 0;
            if(item.name.equals("Aged Brie") ) {
                addedQuality = isSellInLessThenMin(item.sellIn) ? 2 : 1;
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                addedQuality = 1;

                if(isSellInLessThenMin(item.sellIn)){
                    addedQuality = 0;
                    item.quality = 0;
                } else if (item.sellIn < 6) {
                    addedQuality = 3;
                } else if (item.sellIn < 11) {
                    addedQuality = 2;
                }

            } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                addedQuality = isSellInLessThenMin(item.sellIn) ? -2 : -1;
            }
            if(!item.name.equals("Sulfuras, Hand of Ragnaros")){
                item.quality = Math.min(Math.max(item.quality + addedQuality, MIN_QUALITY_VALUE), MAX_QUALITY_VALUE);
            }

        }
    }

    private static boolean isSellInLessThenMin(int itemSellInvValue) {
        return itemSellInvValue < MIN_SELLIN_VALUE;
    }

}
