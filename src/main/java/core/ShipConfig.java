package core;


public enum ShipConfig {
    BATTLESHIP (1, 4, "blue"),
    CRUISER    (2, 3, "green"),
    DESTROYER  (3, 2, "yellow"),
    BOAT       (4, 1, "red");

    private final int count;
    private final int size;
    private final String color;

    ShipConfig(int count, int size, String color) {
        this.count = count;
        this.size = size;
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }
}