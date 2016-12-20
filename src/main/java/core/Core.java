package core;

import gui.GuiBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gui.PlayerAction;
import gui.Point;

public class Core {
    private GuiBuilder builder;
    private ArrayList<Player> players;

    final int GAME_FIELD_WIDTH = 10;
    final int GAME_FIELD_HEIGHT = 10;

    public Core(GuiBuilder builder, ArrayList<Player> players) {
        this.builder = builder;
        this.players = players;

        builder.setHandler(this);

        builder.setGameFieldSize(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);

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
        ArrayList<PlayerData> playersData = new ArrayList<PlayerData>();

        for (Player player: players) {
            PlayerData playerData = new PlayerData();

            playerData.setMessage("Prepare to start game!");
            playerData.setShips(player.getShips());

            playersData.add(playerData);
        }

        Map gameFieldSize = new HashMap();
        gameFieldSize.put("width", GAME_FIELD_WIDTH);
        gameFieldSize.put("width", GAME_FIELD_HEIGHT);

        GameData gameData = new GameData("DATA FROM CORE!", playersData);

        builder.build(gameData, this.players.get(0).getName(), this.players.get(1).getName());
    }

    public void handleAction(PlayerAction userAction) {
        System.out.print("\nCore gets action from " + userAction.getUserName());
        System.out.print("\nCore gets action " + userAction.getAction());


        if (userAction.getAction().equals("select point")) {
            Point point = userAction.getPoint();

            System.out.print("\nCore gets action: user select cell by cords " + point.getRow() + " : " + point.getCell());
        }
    }
}
