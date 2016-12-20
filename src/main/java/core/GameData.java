package core;

import java.util.ArrayList;

public class GameData {
    public String data;
    private ArrayList<PlayerData> playersData;

    GameData(String s, ArrayList<PlayerData> playersData) {
        this.playersData = playersData;
        this.data = s;
    }

    public ArrayList<PlayerData> getPlayersData() {
        return playersData;
    }

}
