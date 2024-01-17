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
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void elixirOfMongooseOneRun() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 7) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void handOfSulfurasOneRun(){

        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[1].name);
        assertEquals(-1, app.items[1].sellIn);
        assertEquals(80, app.items[1].quality);
    }

    @Test
    void agedBrieOneRun() {
        Item[] items = new Item[] { new Item("Aged Brie", 3, 0) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void concertOneRunWithinQualityBound(){

        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 30),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30) };
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(14, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[1].name);
        assertEquals(9, app.items[1].sellIn);
        assertEquals(32, app.items[1].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[2].name);
        assertEquals(4, app.items[2].sellIn);
        assertEquals(33, app.items[2].quality);
    }

    @Test
    void regularItemAtSellInBoundary(){
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 0, 7) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);
    }
    @Test
    void regularItemBeyondSellInBoundary(){
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", -1, 7) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);
    }

    @Test
    void concertOneRunAtSellInBoundary(){

        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10)};
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[1].name);
        assertEquals(0, app.items[1].sellIn);
        assertEquals(13, app.items[1].quality);
    }

    @Test
    void agedBrieOneRunAtSellInBoundary() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 5) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(7, app.items[0].quality);
    }

    @Test
    void agedBrieOneRunAtQualityBoundary() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 49),new Item("Aged Brie", 5, 50) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
        assertEquals("Aged Brie", app.items[1].name);
        assertEquals(4, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);
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

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(14, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[1].name);
        assertEquals(8, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[2].name);
        assertEquals(4, app.items[2].sellIn);
        assertEquals(50, app.items[2].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(14, app.items[3].sellIn);
        assertEquals(50, app.items[3].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[4].name);
        assertEquals(8, app.items[4].sellIn);
        assertEquals(50, app.items[4].quality);
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[5].name);
        assertEquals(4, app.items[5].sellIn);
        assertEquals(50, app.items[5].quality);
        
    }

    @Test
    void conjuredManaCakeOnRun(){
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 6) };
        app = new GildedRose(items);
        app.updateQuality();

        assertEquals("Conjured Mana Cake", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }
}
