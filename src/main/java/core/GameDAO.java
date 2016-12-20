package core;

import java.util.ArrayList;

public class GameDAO {
    public String data;
    private ArrayList<PlayerDAO> playersData;

    GameDAO(String s, ArrayList<PlayerDAO> playersData) {
        this.playersData = playersData;
        this.data = s;
    }

    public ArrayList<PlayerDAO> getPlayersData() {
        return playersData;
    }

}
