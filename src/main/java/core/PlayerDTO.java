package core;

import java.util.ArrayList;

public class PlayerDTO {
    private String name;
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<Cell> ownCells;
    private ArrayList<Cell> opponentCells;

    public PlayerDTO(String name) {
        this.name = name;
    }

    public PlayerDTO setMessage(String message) {
        this.messages.add(message);

        return this;
    }

    public PlayerDTO setMessage(GameMessages message) {
        this.messages.add(message.toString());

        return this;
    }

    public PlayerDTO setOwnCells(ArrayList<Cell> cells) {
        this.ownCells = cells;

        return this;
    }

    public PlayerDTO setOpponentCells(ArrayList<Cell> cells) {
        this.opponentCells = cells;

        return this;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Cell> getOwnCells() {
        return ownCells;
    }

    public ArrayList<Cell> getOpponentCells() {
        return opponentCells;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }
}
