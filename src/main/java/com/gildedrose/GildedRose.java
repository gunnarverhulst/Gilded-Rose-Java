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
            ItemType itemType = ItemType.convertToItemType(item.name);
            if (itemType != ItemType.SULFURAS)
                item.sellIn--;

            int addedQuality = 0;
            if(itemType == ItemType.AGED_BRIE )
                addedQuality = isSellInLessThenMin(item.sellIn) ? 2 : 1;
            else if (itemType == ItemType.BACKSTAGE_PASS)

                if(isSellInLessThenMin(item.sellIn))
                    item.quality = 0;
                else if (item.sellIn < 6)
                    addedQuality = 3;
                else if (item.sellIn < 11)
                    addedQuality = 2;
                else
                    addedQuality = 1;

             else if (itemType == ItemType.CONJURED_ITEM)
                addedQuality = isSellInLessThenMin(item.sellIn) ? -4 : -2;
            else if (itemType != ItemType.SULFURAS)
                addedQuality = isSellInLessThenMin(item.sellIn) ? -2 : -1;

            if(itemType != ItemType.SULFURAS)
                item.quality = Math.min(Math.max(item.quality + addedQuality, MIN_QUALITY_VALUE), MAX_QUALITY_VALUE);
        }
    }

    private static boolean isSellInLessThenMin(int itemSellInvValue) {
        return itemSellInvValue < MIN_SELLIN_VALUE;
    }

}
