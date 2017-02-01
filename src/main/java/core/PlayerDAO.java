package core;

import java.util.ArrayList;

public class PlayerDAO {
    private String message;
    private ArrayList<Cell> cells;

    public PlayerDAO() {

    }

    public PlayerDAO setMessage(String message) {
        this.message = message;

        return this;
    }

    public PlayerDAO setCells(ArrayList<Cell> ships) {
        this.cells = ships;

        return this;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public String getMessage() {
        return message;
    }
}
