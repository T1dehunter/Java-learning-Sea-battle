package core;

import java.util.ArrayList;
import java.util.Map;

public class PlayerDAO {
    private String message;
    private ArrayList ships;

    public PlayerDAO() {

    }

    public PlayerDAO setMessage(String message) {
        this.message = message;

        return this;
    }

    public PlayerDAO setShips(ArrayList ships) {
        this.ships = ships;

        return this;
    }

    public ArrayList getShips() {
        return ships;
    }

    public String getMessage() {
        return message;
    }
}
