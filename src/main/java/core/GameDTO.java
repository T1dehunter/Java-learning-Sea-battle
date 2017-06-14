package core;

import java.util.ArrayList;

public class GameDTO {
    private ArrayList<PlayerDTO> playersData;

    GameDTO (String s, ArrayList<PlayerDTO> playersData) {
        this.playersData = playersData;
    }

    public ArrayList<PlayerDTO> getPlayersData() {
        return playersData;
    }
}
