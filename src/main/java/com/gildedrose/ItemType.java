package com.gildedrose;

public enum ItemType {
    AGED_BRIE,
    BACKSTAGE_PASS,
    SULFURAS,
    REGULAR_ITEM;

    public static ItemType convertToItemType(String itemString){

        return switch(itemString){
            case "Aged Brie" -> AGED_BRIE;
            case "Sulfuras, Hand of Ragnaros" -> SULFURAS;
            case "Backstage passes to a TAFKAL80ETC concert" -> BACKSTAGE_PASS;
            default -> REGULAR_ITEM;
        };
    }

}
