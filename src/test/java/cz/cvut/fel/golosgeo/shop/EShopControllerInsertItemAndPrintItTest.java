package cz.cvut.fel.golosgeo.shop;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EShopControllerInsertItemAndPrintItTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void insertItemsAndPrintIt() throws IOException {
        System.setOut(new PrintStream(outContent));

        EShopController.startEShop();

        int[] itemCount = {10,2};
        Item[] storageItems = {
                new StandardItem(1, "Dancing Panda", 5000, "GADGETS", 5),
                new DiscountedItem(6, "Soft toy", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

        EShopController.insertItems(storageItems,itemCount);

        EShopController.printListOfStoredItems();

        String content = Files.readString(Path.of("src/test/resources/insertItemAndPrintIt"));

        EShopController.makeStorageEmptyOnlyForTests();
        assertEquals(outContent.toString(), content);

        System.setOut(originalOut);
    }
}
