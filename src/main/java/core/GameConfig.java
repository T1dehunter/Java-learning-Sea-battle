package core;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "gameconfig")

public class GameConfig {

    int fieldSize;
    int age;
    int id;


    @XmlElement(name="battleship")
//    @XmlList
//    @XmlValue
    private List<BattleShip> ships;

    @XmlElement
    public void setFieldSize(int size) {
        this.fieldSize = size;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setShips(List<BattleShip> s) {
        this.ships = s;
    }

    public List<BattleShip> getShips() {
        return ships;
    }
}


//import java.lang.reflect.Array;
//import java.util.AbstractList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//public class GameConfig {
//    private enum ShipConfig {
//        BATTLESHIP (1, 4, "blue"),
//        CRUISER    (2, 3, "green"),
//        DESTROYER  (3, 2, "yellow"),
//        BOAT       (4, 1, "red");
//
//        private final int count;
//        private final int size;
//        private final String color;
//
//        ShipConfig(int count, int size, String color) {
//            this.count = count;
//            this.size = size;
//            this.color = color;
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public int getSize() {
//            return size;
//        }
//
//        public String getColor() {
//            return color;
//        }
//    }
//
//    public static final int GAME_FIELD_WIDTH = 10;
//    public static final int GAME_FIELD_HEIGHT = 10;
//
//    public static final ShipConfig[] ships = ShipConfig.values();


