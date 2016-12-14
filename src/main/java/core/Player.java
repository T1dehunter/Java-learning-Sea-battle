package core;

import java.util.Map;

public class Player {
    private String name;
    private Integer score;
    private Map ships;

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

    public void setShips(Map ships) {
        this.ships = ships;
    }

    public Map getShips() {
        return this.ships;
    }
}
