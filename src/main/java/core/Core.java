package core;

import gui.GuiBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Core {
    private GuiBuilder builder;
    private ArrayList<Player> players;

    public Core(GuiBuilder builder, ArrayList<Player> players) {
        this.builder = builder;
        this.players = players;

//        this.players.get(2)

        Map player1Ships = new HashMap();
        player1Ships.put("cruiser", 1);

        Map player2Ships = new HashMap();
        player2Ships.put("cruiser", 1);

        this.players.get(0)
                .setScore(100)
                .setShips(player1Ships);

        this.players.get(1)
                .setScore(100)
                .setShips(player2Ships);
    }

    public void run() {
//        this.builder.run();
    }
}
