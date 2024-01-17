package com.gildedrose;

class GildedRose {
    private static final int MAX_QUALITY_VALUE = 50;
    private static final int MIN_QUALITY_VALUE = 0;
    private static final int MIN_SELLIN_VALUE = 0;
    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemType itemType = ItemType.convertToItemType(item.name);
            if (itemType != ItemType.SULFURAS)
                item.sellIn--;

            int qualityChangeForToday = calculateQualityChangeForToday(item, itemType);

            if(itemType != ItemType.SULFURAS)
                item.quality = applyQualityChangeForToday(item, qualityChangeForToday);
        }
    }

    public Item getItem(int itemIndex) {
        return items[itemIndex];
    }

    private static int applyQualityChangeForToday(Item item, int qualityChangeForToday) {
        return Math.min(Math.max(item.quality + qualityChangeForToday, MIN_QUALITY_VALUE), MAX_QUALITY_VALUE);
    }

    private int calculateQualityChangeForToday(Item item, ItemType itemType) {
        int qualityChangeForToday = 0;
        if(itemType == ItemType.AGED_BRIE )
            qualityChangeForToday = isSellInLessThanZero(item.sellIn) ? 2 : 1;
        else if (itemType == ItemType.BACKSTAGE_PASS)
            qualityChangeForToday = calculateQualityChangeTodayForBackstagePass(item);
        else if (itemType == ItemType.CONJURED_ITEM)
            qualityChangeForToday = isSellInLessThanZero(item.sellIn) ? -4 : -2;
        else if (itemType != ItemType.SULFURAS)
            qualityChangeForToday = isSellInLessThanZero(item.sellIn) ? -2 : -1;
        return qualityChangeForToday;
    }

    private static boolean isSellInLessThanZero(int itemSellInvValue) {
        return itemSellInvValue < MIN_SELLIN_VALUE;
    }

    private int calculateQualityChangeTodayForBackstagePass(Item item) {
        if (isSellInLessThanZero(item.sellIn))
            return -item.quality;
        else if (item.sellIn < 6)
            return 3;
        else if (item.sellIn < 11)
            return 2;

        return 1;
    }


}
