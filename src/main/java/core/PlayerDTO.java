package core;

import java.util.ArrayList;

public class PlayerDTO {
    private String name;
    private String message;
    private ArrayList<Cell> ownCells;
    private ArrayList<Cell> enemyCells;

    public PlayerDTO(String name) {
        this.name = name;
    }

    public PlayerDTO setMessage(String message) {
        this.message = message;

        return this;
    }

    public PlayerDTO setOwnCells(ArrayList<Cell> cells) {
        this.ownCells = cells;

        return this;
    }

    public PlayerDTO setEnemyCells(ArrayList<Cell> cells) {
        this.enemyCells = cells;

        return this;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Cell> getOwnCells() {
        return ownCells;
    }

    public String getMessage() {
        return message;
    }
}
