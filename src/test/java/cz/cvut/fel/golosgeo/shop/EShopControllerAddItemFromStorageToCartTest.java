package cz.cvut.fel.golosgeo.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EShopControllerAddItemFromStorageToCartTest {
    @Test
    public void addItemFromStorageToCart() {
        EShopController.startEShop();
        int[] itemCount = {10,2};
        Item[] storageItems = {
                new StandardItem(1, "Dancing Panda", 5000, "GADGETS", 5),
                new DiscountedItem(6, "Soft toy", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };
        EShopController.insertItems(storageItems,itemCount);

        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);
        newCart.addItem(storageItems[1]);

        assertFalse(newCart.getCartItems().isEmpty());
        assertEquals(newCart.getItemsCount(), 2);
    }

}
