package cz.cvut.fel.golosgeo.shop;

import cz.cvut.fel.golosgeo.storage.NoItemInStorage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EShopControllerPurchaseItemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void purchaseItemTest() throws IOException {
        System.setOut(new PrintStream(outContent));

        EShopController.startEShop();
        int[] itemCount = {10, 3};
        Item[] storageItems = {
                new StandardItem(1, "Dancing Panda", 5000, "GADGETS", 5),
                new DiscountedItem(6, "Soft toy", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };
        EShopController.insertItems(storageItems,itemCount);

        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);
        newCart.addItem(storageItems[1]);
        try {
            EShopController.purchaseShoppingCart(newCart, "Honza", "Honza's address");
        }catch(NoItemInStorage e){
            fail("No item in storage ???");
        }

        EShopController.printListOfStoredItems();
        EShopController.printItemPurchaseStatistics();

        EShopController.makeStorageEmptyOnlyForTests();
        String content = Files.readString(Path.of("src/test/resources/purchaseItem.txt"));
        assertEquals(outContent.toString(), content);
        System.setOut(originalOut);
    }
}
