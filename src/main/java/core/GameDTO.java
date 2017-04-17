package core;

import java.util.ArrayList;

public class GameDTO {
    private String data;
    private ArrayList<PlayerDTO> playersData;

    GameDTO (String s, ArrayList<PlayerDTO> playersData) {
        this.playersData = playersData;
        this.data = s;
    }

    public ArrayList<PlayerDTO> getPlayersData() {
        return playersData;
    }

    public String superTest() {
        return "ggg";
    }
}
