package core;

import gui.GuiBuilder;
import gui.PlayerAction;

import java.io.InputStream;
import java.util.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Core {
    private GuiBuilder builder;

    private ArrayList<Player> players;
    private Player currentPlayer;
    private Player previousPlayerWhoMadeMove;

    private int playerFieldWith;
    private int playerFieldHeight;

    public Core(GuiBuilder builder, ArrayList<Player> players, int playerFieldWith, int playerFieldHeight) {
        this.builder = builder;
        this.players = players;

        // for test
        this.currentPlayer = players.get(0);

        this.playerFieldWith = playerFieldWith;
        this.playerFieldHeight = playerFieldHeight;
    }

    public void run() {
        ArrayList<PlayerDTO> playersData = new ArrayList<PlayerDTO>();

        for (Player player: players) {
            ArrayList<Cell> playerCells = new ArrayList<Cell>();
            ArrayList<Ship> ships = this.buildPlayerShips();

            player.setShips(ships);

            for (Ship s : ships) {
                for (Point p : s.getCoordinates()) {
                    playerCells.add(new Cell(p.getRow(), p.getCell(), s.getColor()));
                }
            }

            PlayerDTO playerData = new PlayerDTO(player.getName());

            playerData.setMessage("Prepare to start game!");

            playerData.setOwnCells(playerCells);

            playersData.add(playerData);
        }

        GameDTO gameData = new GameDTO("DATA FROM CORE!", playersData);

        builder.build(gameData);
    }

    public void handlePlayerAction(PlayerAction userAction) {
        Player currentPlayer = getPlayerByName(userAction.getPlayerName());
        ArrayList<PlayerDTO> playersData = new ArrayList<>();

        if (userAction.getAction().equals("cell selected")) {
            if (!isCorrectOrderOfMoveForPlayer(userAction.getPlayerName())) {
                PlayerDTO playerDto = new PlayerDTO(userAction.getPlayerName());
                playerDto.setMessage(GameMessages.PLAYER_MOVE_ERROR.getText());
                playersData.add(playerDto);
                GameDTO gameData = new GameDTO("test", playersData);
                builder.update(gameData);

                return;


            }

            previousPlayerWhoMadeMove = currentPlayer;

            // temp comment! case when need implement checking hit in player move
            Point selectedPointOfCurrentPlayer = userAction.getPoint();

            Player opponent = getPlayerOpponent(userAction.getPlayerName());

            if (opponent.isHit(selectedPointOfCurrentPlayer)) {
                PlayerDTO playerDto = new PlayerDTO(userAction.getPlayerName());

                playerDto.setMessage("you have success hit!!!");

                playersData.add(playerDto);

                GameDTO gameData = new GameDTO("DATA FROM CORE!", playersData);

                builder.update(gameData);
            }



//            Point cordinatesPlayerMove = userAction.getPoint();
//            ArrayList<Cell> playerCells = new ArrayList<>();
//            playerCells.add(new Cell(cordinatesPlayerMove.getRow(), cordinatesPlayerMove.getCell(), "red"));
//            player.setOwnCells(playerCells);
//            playersData.add(player);
//            GameDTO gameData = new GameDTO("DATA FROM CORE!", playersData);
//            builder.update(gameData);
        }
    }


    // ??
    private ArrayList<Ship> buildPlayerShips() {
        CoordsBuilder coordsBuilder = new CoordsBuilder(playerFieldWith, playerFieldHeight);
        ShipBuilder shipBuilder = getShipBuilder();
        ArrayList<Ship> ships = shipBuilder.buildListShips();

        for (Ship s: ships) {
            ArrayList<Point> randomCords = coordsBuilder.buildRandomCoords(s.getLength());
            s.setCoordinates(randomCords);
        }

        return ships;
    }

    // ??
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

    private boolean isCorrectOrderOfMoveForPlayer(String playerName) {
        Player currentPlayer = getPlayerByName(playerName);

        return isPreviousAndCurrentPlayersAreDifferent(currentPlayer) || isCurrentPlayerHasHitOnPreviousMove(currentPlayer);
    }

    private Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }

        return null;
    }

    private Player getPlayerOpponent(String playerName) {
        for (Player player : players) {
            if (!player.getName().equals(playerName)) {
                return player;
            }
        }

        return null;
    }

    private boolean isPreviousAndCurrentPlayersAreDifferent(Player currentPlayer) {
        return previousPlayerWhoMadeMove == null || !currentPlayer.getName().equals(previousPlayerWhoMadeMove.getName());
    }

    private boolean isCurrentPlayerHasHitOnPreviousMove(Player currentPlayer) {
        return currentPlayer.getName().equals(previousPlayerWhoMadeMove.getName()) &&
                previousPlayerWhoMadeMove.getLastMove().getStatus().equals("hit");
    }
}
