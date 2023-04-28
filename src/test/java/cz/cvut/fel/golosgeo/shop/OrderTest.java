package cz.cvut.fel.golosgeo.shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    static Stream<ShoppingCart> prepareShoppingCart(){
        ArrayList<Item> items1 = new ArrayList<>();
        ArrayList<Item> items2 = new ArrayList<>();
        items1.add(new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5));
        items1.add(new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10));
        items1.add(new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"));
        items2.add(new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"));
        return Stream.of(new ShoppingCart(items1), new ShoppingCart(items2));
    }

    @ParameterizedTest
    @MethodSource("prepareShoppingCart")
    public void orderConstructorWithStateTest(ShoppingCart cart){
        Order ord = new Order(cart, "Honza", "Somewhere in prague", 3);
        assertAll(
                () -> assertEquals(ord.getState(), 3),
                () -> assertEquals(ord.getCustomerAddress(), "Somewhere in prague"),
                () -> assertEquals(ord.getCustomerName(), "Honza"),
                () -> assertNotNull(ord.getItems())
        );
    }

    @ParameterizedTest
    @MethodSource("prepareShoppingCart")
    public void orderConstructorWithoutStateTest(ShoppingCart cart){
        Order ord = new Order(cart, "Honza", "Somewhere in prague");
        assertAll(
                () -> assertEquals(ord.getState(), 0),
                () -> assertEquals(ord.getCustomerAddress(), "Somewhere in prague"),
                () -> assertEquals(ord.getCustomerName(), "Honza"),
                () -> assertNotNull(ord.getItems())
        );
    }

    @Test
    public void orderConstructorNullItemsInCartTest() {
        ShoppingCart cart = new ShoppingCart(null);
        Order ord = new Order(cart, "Honza", "Somewhere in prague");
        assertNull(ord.getItems());
    }
}
