package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    private static final int MAX_QUALITY_VALUE = 50;
    private static final int MIN_QUALITY_VALUE = 0;
    private static final int MIN_SELLIN_VALUE = 0;
    private final List<Item> itemList;

    public GildedRose(Item[] items) {
        itemList = new ArrayList<>();
        itemList.addAll(List.of(items));
    }

    public void updateQuality() {
        itemList.forEach(this::handleItem);
    }

    public int getItemSellIn(int itemIndex) {
        return itemList.get(itemIndex).sellIn;
    }
    public int getItemQuality(int itemIndex) {
        return itemList.get(itemIndex).quality;
    }
    public String getItemName(int itemIndex) {
        return itemList.get(itemIndex).name;
    }

    private void handleItem(Item item) {

        ItemType itemType = ItemType.convertToItemType(item.name);
        normalizeQuality(item, itemType);
        if (itemType != ItemType.SULFURAS)
            item.sellIn--;

        int qualityChangeForToday = calculateQualityChangeForToday(item, itemType);

        if(itemType != ItemType.SULFURAS)
            item.quality = applyQualityChangeForToday(item, qualityChangeForToday);
    }

    private static void normalizeQuality(Item item, ItemType itemType) {
        if(itemType != ItemType.SULFURAS){
            if(item.quality > MAX_QUALITY_VALUE){
                item.quality = MAX_QUALITY_VALUE;
            }

            if(item.quality < MIN_QUALITY_VALUE){
                item.quality = MIN_QUALITY_VALUE;
            }
        }
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

    private static int applyQualityChangeForToday(Item item, int qualityChangeForToday) {
        return Math.min(Math.max(item.quality + qualityChangeForToday, MIN_QUALITY_VALUE), MAX_QUALITY_VALUE);
    }
}
