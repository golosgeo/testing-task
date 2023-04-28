package cz.cvut.fel.golosgeo.storage;

public class NoItemInStorage extends Exception{
    public NoItemInStorage() {
        super("No item in storage");
    }
}
