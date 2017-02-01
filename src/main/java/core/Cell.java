package core;

public class Cell extends Point {
    private String color;

    public Cell(int row, int cell, String color) {
        super(row, cell);

        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
