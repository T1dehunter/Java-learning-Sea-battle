package core;

import java.util.ArrayList;

public class Ship {
    private String color;

    private ArrayList<Point> coordinates;

    public Ship(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getLength() {
        return coordinates.size();
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Point> cords) {
        coordinates = cords;
    }

    public void addHit(Point hit) {
        int indexOfHit = -1;

        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).getRow() == hit.getRow() && coordinates.get(i).getCell() == hit.getCell()) {
                indexOfHit = i;
                break;
            }
        }
        if (indexOfHit > -1) {
            coordinates.remove(indexOfHit);
        }
    }
}
