package cz.cvut.fel.golosgeo.shop;

import cz.cvut.fel.golosgeo.storage.NoItemInStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EShopControllerTryToPurchaseItemThatIsNotInStorageTest {
    @Test
    public void tryToPurchaseItemThatIsNotInStorageTest() {
        EShopController.startEShop();
        int[] itemCount = {0, 2};
        Item[] storageItems = {
                new StandardItem(1, "Dancing Panda", 5000, "GADGETS", 5),
                new DiscountedItem(6, "Soft toy", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };
        EShopController.insertItems(storageItems,itemCount);

        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);

        Exception e = assertThrows(NoItemInStorage.class,
                () -> EShopController.purchaseShoppingCart(newCart, "Gosha", "My Address")
        );
        assertTrue(e.getMessage().contains("No item in storage"));
    }
}
