package core;

import gui.GuiBuilder;
import gui.PlayerAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Core {
    private GuiBuilder builder;
    private ArrayList<Player> players;

    private int gameFieldWidth;
    private int gameFieldHeight;

    public Core(GuiBuilder builder, ArrayList<Player> players) {
        this.builder = builder;
        this.players = players;

        Properties settings = this.getGameSettings();

        this.gameFieldWidth = Integer.parseInt(settings.getProperty("width"));
        this.gameFieldHeight = Integer.parseInt(settings.getProperty("height"));
    }

    public void run() {
        this.preparePlayers();

        // ??
        builder.setHandler(this);
        builder.setGameFieldSize(gameFieldWidth, gameFieldHeight);

        ArrayList<PlayerDAO> playersData = new ArrayList<PlayerDAO>();

        for (Player player: players) {
            PlayerDAO playerData = new PlayerDAO();

            playerData.setMessage("Prepare to start game!");

            ArrayList<Cell> playerCells = new ArrayList<Cell>();

            for (Ship s : player.getShips()) {
                for (Point p : s.getCoordinates()) {
                    playerCells.add(new Cell(p.getRow(), p.getCell(), s.getColor()));
                }
            }

            playerData.setCells(playerCells);

            playersData.add(playerData);
        }

        GameDAO gameData = new GameDAO("DATA FROM CORE!", playersData);

        builder.build(gameData, this.players.get(0).getName(), this.players.get(1).getName());
    }

    private Properties getGameSettings() {
        Properties settings = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("config/config.properties");

            settings.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return settings;
    }

    public void preparePlayers() {
        for (Player player: players) {
            player.setShips(this.buildPlayerShips());
            System.out.print(player.getShips().size());
        }
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
        CoordsBuilder coordsBuilder = new CoordsBuilder(gameFieldWidth, gameFieldHeight);
        ShipBuilder shipBuilder = getShipBuilder();
        ArrayList<Ship> ships = shipBuilder.buildListShips();

        for (Ship s: ships) {
            ArrayList<Point> randomCords = coordsBuilder.buildRandomCoords(s.getLength());
            s.setCoordinates(randomCords);
        }

        return ships;
    }

    private ShipBuilder getShipBuilder() {
        ShipBuilder builder;
        try {
            JAXBContext jc = JAXBContext.newInstance(ShipBuilder.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream xml = getClass().getClassLoader().getResourceAsStream("config/ships.settings");
            builder = (ShipBuilder) unmarshaller.unmarshal(xml);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "ShipBuilder.xsd");
        } catch (Exception e) {
            builder = null;
            e.printStackTrace();
        }

        return builder;
    }
}
