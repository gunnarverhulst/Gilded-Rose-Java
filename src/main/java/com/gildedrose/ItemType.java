package com.gildedrose;


public enum ItemType {
    AGED_BRIE,
    BACKSTAGE_PASS,
    SULFURAS,
    CONJURED_ITEM,
    REGULAR_ITEM;

    private static final int MIN_SELLIN_VALUE = 0;

    public static ItemType convertToItemType(String itemString){
        String preppedString = itemString.startsWith("Conjured") ? "Conjured" : itemString;

        return switch(preppedString){
            case "Aged Brie" -> AGED_BRIE;
            case "Sulfuras, Hand of Ragnaros" -> SULFURAS;
            case "Backstage passes to a TAFKAL80ETC concert" -> BACKSTAGE_PASS;
            case "Conjured" -> CONJURED_ITEM;
            default -> REGULAR_ITEM;
        };
    }

    public static int calculateQualityChangeForToday(Item item, ItemType itemType) {
        return switch (itemType) {
            case AGED_BRIE -> calculateQualityChangeAgedBrie(item);
            case BACKSTAGE_PASS -> calculateQualityChangeTodayForBackstagePass(item);
            case CONJURED_ITEM -> calculateConjuredItemQualityChangeForToday(item);
            default -> calculateSulfurasQualityChangeForToday(item);
        };
    }

    private static int calculateConjuredItemQualityChangeForToday(Item item) {
        return isSellInLessThanZero(item.sellIn) ? -4 : -2;
    }

    private static int calculateSulfurasQualityChangeForToday(Item item) {
        return isSellInLessThanZero(item.sellIn) ? -2 : -1;
    }

    private static int calculateQualityChangeAgedBrie(Item item) {
        return isSellInLessThanZero(item.sellIn) ? 2 : 1;
    }

    private static boolean isSellInLessThanZero(int itemSellInValue) {
        return itemSellInValue < MIN_SELLIN_VALUE;
    }

    private static int calculateQualityChangeTodayForBackstagePass(Item item) {
        if (isSellInLessThanZero(item.sellIn))
            return -item.quality;
        else if (item.sellIn < 6)
            return 3;
        else if (item.sellIn < 11)
            return 2;
        else
            return 1;
    }

}
