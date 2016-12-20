package core;


import java.util.ArrayList;

public class Ship {
    private ArrayList<Point> coordinates;
    private Integer hp = 0;
    public Ship(ArrayList<Point> coordinates) {
        this.coordinates = coordinates;
        this.hp = coordinates.size();
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }
}
