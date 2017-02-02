package core;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Ship> ships;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        int score = 0;

        for (Ship s: ships) {
            score += s.getLength();
        }

        return score;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }
}
