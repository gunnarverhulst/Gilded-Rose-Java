package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose app;

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.getItemName(0));
    }

    @Test
    void elixirOfMongooseOneRun() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 7) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.getItemName(0));
        assertEquals(4, app.getItemSellIn(0));
        assertEquals(6, app.getItemQuality(0));
    }

    @Test
    void handOfSulfurasOneRun(){

        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.getItemName(0));
        assertEquals(0, app.getItemSellIn(0));
        assertEquals(80, app.getItemQuality(0));
        assertEquals("Sulfuras, Hand of Ragnaros", app.getItemName(1));
        assertEquals(-1, app.getItemSellIn(1));
        assertEquals(80, app.getItemQuality(1));
    }

    @Test
    void agedBrieOneRun() {
        Item[] items = new Item[] { new Item("Aged Brie", 3, 0) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.getItemName(0));
        assertEquals(2, app.getItemSellIn(0));
        assertEquals(1, app.getItemQuality(0));
    }

    @Test
    void concertOneRunWithinQualityBound(){

        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 30),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30) };
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(0));
        assertEquals(14, app.getItemSellIn(0));
        assertEquals(21, app.getItemQuality(0));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(1));
        assertEquals(9, app.getItemSellIn(1));
        assertEquals(32, app.getItemQuality(1));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(2));
        assertEquals(4, app.getItemSellIn(2));
        assertEquals(33, app.getItemQuality(2));
    }

    @Test
    void regularItemAtSellInBoundary(){
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 0, 7) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.getItemName(0));
        assertEquals(-1, app.getItemSellIn(0));
        assertEquals(5, app.getItemQuality(0));
    }
    @Test
    void regularItemBeyondSellInBoundary(){
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", -1, 7) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.getItemName(0));
        assertEquals(-2, app.getItemSellIn(0));
        assertEquals(5, app.getItemQuality(0));
    }

    @Test
    void concertOneRunAtSellInBoundary(){

        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10)};
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(0));
        assertEquals(-1, app.getItemSellIn(0));
        assertEquals(0, app.getItemQuality(0));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(1));
        assertEquals(0, app.getItemSellIn(1));
        assertEquals(13, app.getItemQuality(1));
    }

    @Test
    void agedBrieOneRunAtSellInBoundary() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 5) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.getItemName(0));
        assertEquals(-1, app.getItemSellIn(0));
        assertEquals(7, app.getItemQuality(0));
    }

    @Test
    void agedBrieOneRunAtQualityBoundary() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 49),new Item("Aged Brie", 5, 50) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.getItemName(0));
        assertEquals(4, app.getItemSellIn(0));
        assertEquals(50, app.getItemQuality(0));
        assertEquals("Aged Brie", app.getItemName(1));
        assertEquals(4, app.getItemSellIn(1));
        assertEquals(50, app.getItemQuality(1));
    }

    @Test
    void concertOneRunAtQualityInBoundary(){

        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 48),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 47),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)};
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(0));
        assertEquals(14, app.getItemSellIn(0));
        assertEquals(50, app.getItemQuality(0));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(1));
        assertEquals(8, app.getItemSellIn(1));
        assertEquals(50, app.getItemQuality(1));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(2));
        assertEquals(4, app.getItemSellIn(2));
        assertEquals(50, app.getItemQuality(2));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(3));
        assertEquals(14, app.getItemSellIn(3));
        assertEquals(50, app.getItemQuality(3));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(4));
        assertEquals(8, app.getItemSellIn(4));
        assertEquals(50, app.getItemQuality(4));
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.getItemName(5));
        assertEquals(4, app.getItemSellIn(5));
        assertEquals(50, app.getItemQuality(5));
        
    }

    @Test
    void conjuredManaCakeOneRun(){
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 6) };
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Conjured Mana Cake", app.getItemName(0));
        assertEquals(2, app.getItemSellIn(0));
        assertEquals(4, app.getItemQuality(0));
    }

    @Test
    void dexterityVestOneRun(){
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 20)};
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("+5 Dexterity Vest", app.getItemName(0));
        assertEquals(9, app.getItemSellIn(0));
        assertEquals(19, app.getItemQuality(0));
    }

    @Test
    void conjuredManaGemOneRun(){
        Item[] items = new Item[] { new Item("Conjured Mana Gem", 3, 6) };
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Conjured Mana Gem", app.getItemName(0));
        assertEquals(2, app.getItemSellIn(0));
        assertEquals(4, app.getItemQuality(0));
    }
}
