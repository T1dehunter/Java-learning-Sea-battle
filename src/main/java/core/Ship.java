package core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import java.util.ArrayList;

public class Ship {
    private int length;
    private int hp;

    // ??
    private String color;

    ArrayList<Point> coordinates;


    public Ship(int length, String color) {
        this.length = length;
        this.hp = length;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void decreaseHp() {
        hp = hp - 1;
    }

    public int getHp() {
        return hp;
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Point> coords) {
        coordinates = coords;
    }
//    public Ship(ArrayList<Point> coordinates) {
//        this.coordinates = coordinates;
////        this.hp = coordinates.size();
//    }

    public String test() {
        return "\n length: " + length + "| color: " + color;
    }


}
