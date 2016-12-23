package core;

import gui.GuiBuilder;

import java.util.*;

import gui.PlayerAction;

public class Core {
    private GuiBuilder builder;
    private ArrayList<Player> players;

    //??
    final int GAME_FIELD_WIDTH = 10;
    final int GAME_FIELD_HEIGHT = 10;

    public Core(GuiBuilder builder, ArrayList<Player> players) {
        this.builder = builder;
        this.players = players;

        builder.setHandler(this);

        builder.setGameFieldSize(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);

        this.players.get(0)
                .setScore(100)
                .setShips(this.buildPlayerShips());

        this.players.get(1)
                .setScore(100)
                .setShips(this.buildPlayerShips());
    }

    public void run() {
        ArrayList<PlayerDAO> playersData = new ArrayList<PlayerDAO>();

        for (Player player: players) {
            PlayerDAO playerData = new PlayerDAO();

            playerData.setMessage("Prepare to start game!");
            playerData.setShips(player.getShips());

            playersData.add(playerData);
        }

        Map gameFieldSize = new HashMap();

        //??
        gameFieldSize.put("width", GAME_FIELD_WIDTH);
        gameFieldSize.put("height", GAME_FIELD_HEIGHT);

        GameDAO gameData = new GameDAO("DATA FROM CORE!", playersData);

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

    private ArrayList<Ship> buildPlayerShips() {
        // TEST
        ArrayList <Ship> ships = new ArrayList<Ship>();
        ArrayList <Point> coordinates = new ArrayList<Point>();

        coordinates.add(new Point(9, 7));
        coordinates.add(new Point(9, 8));
        coordinates.add(new Point(9, 9));

        Ship ship = new Ship(coordinates);
        ships.add(ship);

        return ships;

//        for (ShipConfig s : ShipConfig.values()) {
//            System.out.print(s.getCount());
//        }
    }










}
