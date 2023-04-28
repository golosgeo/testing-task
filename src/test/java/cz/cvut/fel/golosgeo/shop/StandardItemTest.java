package cz.cvut.fel.golosgeo.shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class StandardItemTest {
    @Test
    public void ConstructorAllZeroOrEmptyTest() {
        StandardItem stdItem = new StandardItem(0, "", 0, "", 0);
        assertAll("stdItem",
                () -> assertEquals(stdItem.getID(), 0),
                () -> assertEquals(stdItem.getLoyaltyPoints(), 0),
                () -> assertEquals(stdItem.getPrice(), 0),
                () -> assertEquals(stdItem.getName(), ""),
                () -> assertEquals(stdItem.getCategory(), ""));
    }

    @Test
    public void ConstructorSimpleTest() {
        StandardItem stdItem = new StandardItem(10024, "ItemName", 1511, "GoodItems", 10);
        assertAll("stdItem",
                () -> assertEquals(stdItem.getID(), 10024),
                () -> assertEquals(stdItem.getLoyaltyPoints(), 10),
                () -> assertEquals(stdItem.getPrice(), 1511),
                () -> assertEquals(stdItem.getName(), "ItemName"),
                () -> assertEquals(stdItem.getCategory(), "GoodItems"));
    }

    @Test
    public void copyZeroOrEmptyTest(){
        StandardItem stdItem = new StandardItem(0, "", 0, "", 0);
        StandardItem stdItemCopy = stdItem.copy();
        assertAll("stdItemCopy",
                () -> assertEquals(stdItemCopy.getID(), 0),
                () -> assertEquals(stdItemCopy.getLoyaltyPoints(), 0),
                () -> assertEquals(stdItemCopy.getPrice(), 0),
                () -> assertEquals(stdItemCopy.getName(), ""),
                () -> assertEquals(stdItemCopy.getCategory(), ""));
    }

    @Test
    public void copySimpleTest(){
        StandardItem stdItem = new StandardItem(10024, "ItemName", 1511, "GoodItems", 10);
        StandardItem stdItemCopy = stdItem.copy();
        assertAll("stdItemCopy",
                () -> assertEquals(stdItemCopy.getID(), 10024),
                () -> assertEquals(stdItemCopy.getLoyaltyPoints(), 10),
                () -> assertEquals(stdItemCopy.getPrice(), 1511),
                () -> assertEquals(stdItemCopy.getName(), "ItemName"),
                () -> assertEquals(stdItemCopy.getCategory(), "GoodItems"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/standardItem.csv", numLinesToSkip = 1)
    public void equalsParameterizedTest (int id, String name, float price, String category, int loyaltyPoints){
        StandardItem stdItem = new StandardItem(id, name, price, category, loyaltyPoints);
        assertTrue(stdItem.equals(new StandardItem(id, name, price, category, loyaltyPoints)));
    }

    @Test
    public void equalsNullTest(){
        StandardItem stdItem = new StandardItem(10024, "ItemName", 1511, "GoodItems", 10);
        assertFalse(stdItem.equals(null));
    }
}
