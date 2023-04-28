package cz.cvut.fel.golosgeo.shop;

import cz.cvut.fel.golosgeo.storage.NoItemInStorage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EShopControllerPurchaseEmptyCartTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void purchaseEmptyCart() throws NoItemInStorage {
        System.setOut(new PrintStream(outContent));
        EShopController.startEShop();
        ShoppingCart newCart = new ShoppingCart();

        EShopController.purchaseShoppingCart(newCart, "Honza", "Honza's address");
        assertEquals(outContent.toString(), "Error: shopping cart is empty\r\n");
        System.setOut(originalOut);
    }
}
