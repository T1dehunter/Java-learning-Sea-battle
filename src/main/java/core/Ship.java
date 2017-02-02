package core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import java.util.ArrayList;

public class Ship {
    private int length;

    // ??
    private String color;

    ArrayList<Point> coordinates;

    public Ship(int length, String color) {
        this.length = length;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void decreaseLength() {
        length = length - 1;
    }

    public int getLength() {
        return length;
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Point> coords) {
        coordinates = coords;
    }
}
