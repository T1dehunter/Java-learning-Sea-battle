package core;

import java.util.Map;

public class PlayerData {
    private String message;
    private Map ships;

    public PlayerData() {

    }

    public PlayerData setMessage(String message) {
        this.message = message;

        return this;
    }

    public PlayerData setShips(Map ships) {
        this.ships = ships;

        return this;
    }

    public String getMessage() {
        return message;
    }
}
