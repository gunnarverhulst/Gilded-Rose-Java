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
