package cz.cvut.fel.golosgeo.storage;

import cz.cvut.fel.golosgeo.shop.StandardItem;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemStockTest {
    @Test
    public void itemStockConstructurTest(){
        StandardItem item = new StandardItem(654,"qwerty",1234567,"CaTeGoRy",800);
        ItemStock itemStock = new ItemStock(item);
        assertAll("itemStock",
                () -> assertEquals(itemStock.getCount(), 0),
                () -> assertEquals(itemStock.getItem().getID(), 654),
                () -> assertEquals(itemStock.getItem().getPrice(), 1234567),
                () -> assertEquals(itemStock.getItem().getCategory(), "CaTeGoRy"),
                () -> assertEquals(itemStock.getItem().getName(), "qwerty"));
    }

    @ParameterizedTest
    @Order(1)
    @CsvFileSource(resources = "/itemStockIncreaseCount.csv", numLinesToSkip = 1)
    public void itemStockIncreaseTest(int number1, int number2, int result) {
        ItemStock itemStock = new ItemStock(null);
        itemStock.IncreaseItemCount(number1);
        itemStock.IncreaseItemCount(number2);
        assertEquals(itemStock.getCount(), result);
    }

    @ParameterizedTest
    @Order(2)
    @CsvFileSource(resources = "/itemStockDecreaseCount.csv", numLinesToSkip = 1)
    public void itemStockDecreaseTest(int number1, int number2, int result) {
        ItemStock itemStock = new ItemStock(null);
        itemStock.IncreaseItemCount(number1);
        itemStock.decreaseItemCount(number2);
        assertEquals(itemStock.getCount(), result);
    }
}
