package cz.cvut.fel.golosgeo.archive;

import cz.cvut.fel.golosgeo.shop.Item;
import cz.cvut.fel.golosgeo.shop.Order;
import cz.cvut.fel.golosgeo.shop.StandardItem;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PurchasesArchiveTest {
    static private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    static private final PrintStream originalOut = System.out;

    @Test
    public void printItemPurchaseStatisticsTest() {
        System.setOut(new PrintStream(outContent));

        ItemPurchaseArchiveEntry ipaeMock = mock(ItemPurchaseArchiveEntry.class);
        when(ipaeMock.toString()).thenReturn("toString of mock was called");

        HashMap<Integer, ItemPurchaseArchiveEntry> hm = new HashMap<Integer, ItemPurchaseArchiveEntry>();
        hm.put(10, ipaeMock);

        PurchasesArchive arch = new PurchasesArchive(hm, new ArrayList<>());
        arch.printItemPurchaseStatistics();

        // verify() doesn't work with toString() method
        // so I use assertEquals()
        assertEquals(outContent.toString(), "ITEM PURCHASE STATISTICS:\r\ntoString of mock was called\r\n");

        System.setOut(originalOut);
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldTest(){
        ItemPurchaseArchiveEntry ipaeMock = mock(ItemPurchaseArchiveEntry.class);
        when(ipaeMock.getCountHowManyTimesHasBeenSold()).thenReturn(25);

        HashMap<Integer, ItemPurchaseArchiveEntry> hm = new HashMap<Integer, ItemPurchaseArchiveEntry>();
        hm.put(10, ipaeMock);

        PurchasesArchive arch = new PurchasesArchive(hm, new ArrayList<>());

        StandardItem itemMock = mock(StandardItem.class);
        when(itemMock.getID()).thenReturn(10);

        arch.getHowManyTimesHasBeenItemSold(itemMock);

        verify(itemMock, times(2)).getID();
        verify(ipaeMock, times(1)).getCountHowManyTimesHasBeenSold();
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldNoItemTest(){
        ItemPurchaseArchiveEntry ipaeMock = mock(ItemPurchaseArchiveEntry.class);
        when(ipaeMock.getCountHowManyTimesHasBeenSold()).thenReturn(25);

        HashMap<Integer, ItemPurchaseArchiveEntry> hm = new HashMap<Integer, ItemPurchaseArchiveEntry>();
        hm.put(10, ipaeMock);

        PurchasesArchive arch = new PurchasesArchive(hm, new ArrayList<>());

        StandardItem itemMock = mock(StandardItem.class);
        when(itemMock.getID()).thenReturn(20);

        arch.getHowManyTimesHasBeenItemSold(itemMock);

        verify(itemMock, times(1)).getID();
        verify(ipaeMock, never()).getCountHowManyTimesHasBeenSold();
    }

    @Test
    public void putOrderToPurchasesArchiveTest() {
        ItemPurchaseArchiveEntry ipaeMock = mock(ItemPurchaseArchiveEntry.class);
        doNothing().when(ipaeMock).increaseCountHowManyTimesHasBeenSold(anyInt());

        HashMap<Integer, ItemPurchaseArchiveEntry> hm = new HashMap<Integer, ItemPurchaseArchiveEntry>();
        hm.put(10, ipaeMock);

        StandardItem itemMock = mock(StandardItem.class);
        when(itemMock.getID()).thenReturn(10);

        ArrayList<Item> items = new ArrayList<>();
        items.add(itemMock);

        PurchasesArchive arch = new PurchasesArchive(hm, new ArrayList<>());
        Order ordMock = mock(Order.class);
        when(ordMock.getItems()).thenReturn(items);

        arch.putOrderToPurchasesArchive(ordMock);

        verify(ipaeMock, times(1)).increaseCountHowManyTimesHasBeenSold(anyInt());
        verify(itemMock, times(2)).getID();
    }

    @Test
    public void putOrderToPurchasesArchiveItemPurchasesArchiveDoesNotContainItemTest(){
        ItemPurchaseArchiveEntry ipaeMock = mock(ItemPurchaseArchiveEntry.class);
        doNothing().when(ipaeMock).increaseCountHowManyTimesHasBeenSold(anyInt());

        HashMap<Integer, ItemPurchaseArchiveEntry> hm = new HashMap<Integer, ItemPurchaseArchiveEntry>();
        hm.put(10, ipaeMock);

        StandardItem itemMock = mock(StandardItem.class);
        when(itemMock.getID()).thenReturn(20);

        ArrayList<Item> items = new ArrayList<>();
        items.add(itemMock);

        PurchasesArchive arch = new PurchasesArchive(hm, new ArrayList<>());
        Order ordMock = mock(Order.class);
        when(ordMock.getItems()).thenReturn(items);

        arch.putOrderToPurchasesArchive(ordMock);

        verify(ipaeMock, times(0)).increaseCountHowManyTimesHasBeenSold(anyInt());
        verify(itemMock, times(2)).getID();
    }
}
