package core;

import java.util.ArrayList;

public class PlayerDTO {
    private String message;
    private ArrayList<Cell> cells;

    public PlayerDTO() {

    }

    public PlayerDTO setMessage(String message) {
        this.message = message;

        return this;
    }

    public PlayerDTO setCells(ArrayList<Cell> ships) {
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
