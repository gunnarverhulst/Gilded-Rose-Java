package com.gildedrose;

public enum ItemType {
    AGED_BRIE,
    BACKSTAGE_PASS,
    SULFURAS,
    CONJURED_ITEM,
    REGULAR_ITEM;

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

}
