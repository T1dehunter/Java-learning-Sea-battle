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

    // temp code for test some idea
    public class Move extends Point {
        private String status;

        public Move(int row, int cell, String status) {
            super(row, cell);

            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    public Move getLastMove() {
        return new Move(0, 0, "miss");
    }
    // temp code for test some idea
}
