package core;

import java.util.ArrayList;
import java.util.Map;

public class Player {
    private String name;
    private Integer score;
    private ArrayList<Ship> ships;

    public Player(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    Integer getScore() {
        return this.score;
    }

    public Player setScore(Integer score) {
        this.score = score;

        return this;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }
}
