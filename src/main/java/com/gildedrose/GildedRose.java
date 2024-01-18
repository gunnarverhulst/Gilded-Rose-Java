package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    private static final int MAX_QUALITY_VALUE = 50;
    private static final int MIN_QUALITY_VALUE = 0;
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

        if (itemType != ItemType.SULFURAS){
            normalizeQuality(item);
            item.sellIn--;

            int qualityChangeForToday = ItemType.calculateQualityChangeForToday(item, itemType);

            item.quality = applyQualityChangeForToday(item, qualityChangeForToday);
        }
    }

    private static void normalizeQuality(Item item) {
        if(item.quality > MAX_QUALITY_VALUE){
            item.quality = MAX_QUALITY_VALUE;
        }

        if(item.quality < MIN_QUALITY_VALUE){
            item.quality = MIN_QUALITY_VALUE;
        }
    }

    private static int applyQualityChangeForToday(Item item, int qualityChangeForToday) {
        return Math.min(Math.max(item.quality + qualityChangeForToday, MIN_QUALITY_VALUE), MAX_QUALITY_VALUE);
    }
}
