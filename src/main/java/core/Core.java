package core;

import gui.GuiBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

//import javax.xml.bind.*;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import gui.PlayerAction;



public class Core {
    private GuiBuilder builder;
    private ArrayList<Player> players;

    private ArrayList<HashMap>shipsSettings = new ArrayList<HashMap>();

    //??
    final int GAME_FIELD_WIDTH = 10;
    final int GAME_FIELD_HEIGHT = 10;

    // mb should later move init this obj to level up outside from Core


    public Core(GuiBuilder builder, ArrayList<Player> players) {
        this.builder = builder;
        this.players = players;
    }

    public void run() {
        Properties settings = this.getGameSettings();

        setShipsSettings(settings);

        int gameWidth = Integer.parseInt(settings.getProperty("fieldwidth"));
        int gameHeight = Integer.parseInt(settings.getProperty("fieldheight"));

        this.preparePlayers();

        builder.setHandler(this);
        builder.setGameFieldSize(gameWidth, gameHeight);

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

        Map gameFieldSize = new HashMap();

        //??
        gameFieldSize.put("width", GAME_FIELD_WIDTH);
        gameFieldSize.put("height", GAME_FIELD_HEIGHT);

        GameDAO gameData = new GameDAO("DATA FROM CORE!", playersData);

        builder.build(gameData, this.players.get(0).getName(), this.players.get(1).getName());
    }

    private Properties getGameSettings() {
        Properties settings = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
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

    private void setShipsSettings(Properties props) {
        HashMap<String, String> settings = new HashMap<String, String>();
        settings.put("battleship", props.getProperty("battleship"));
        settings.put("cruiser", props.getProperty("cruiser"));
        settings.put("destroyer", props.getProperty("destroyer"));
        shipsSettings.add(settings);

        System.out.print("\n TESTTTTTTTT SETTINGS");
//        System.out.print(shipsSettings.get(0));
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
        CoordsBuilder coordsBuilder = new CoordsBuilder(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        ShipBuilder shipBuilder = getShipBuilder();
        ArrayList<Ship> ships = shipBuilder.buildListShips();


        ArrayList<Integer> arrayOfShipsHP = ships.stream()
                .map(s -> s.getHp())
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ArrayList<Point>> randomCoords = coordsBuilder.buildRandomCoordsForElems(arrayOfShipsHP);

        System.out.print("SHOW CORDS FOR SHIPS\n");
        System.out.print(randomCoords);


        for (int i = 0; i < randomCoords.size(); i++) {
            Ship s = ships.get(i);
            ArrayList<Point> cords = randomCoords.get(i);
            s.setCoordinates(cords);
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
//            marshaller.marshal(sc, System.out);

//            System.out.println("\n Get ship -> " + sc.getShipByType().test());
//            System.out.println("\n Get ship -> " + sc);
        } catch (Exception e) {
            builder = null;
            e.printStackTrace();
        }

        return builder;

    }










}
