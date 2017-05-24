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

    private boolean gameInProcess;

    private int playerFieldWith;
    private int playerFieldHeight;

    public Core(GuiBuilder builder, ArrayList<Player> players, int playerFieldWith, int playerFieldHeight) {
        this.builder = builder;
        this.players = players;

        // for test
        this.currentPlayer = players.get(0);

        this.playerFieldWith = playerFieldWith;
        this.playerFieldHeight = playerFieldHeight;

        this.gameInProcess = true;
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
        if (!gameInProcess) {
            return;
        }

        Player currentPlayer = getPlayerByName(userAction.getPlayerName());
        Player opponentPlayer = getPlayerOpponent(userAction.getPlayerName());

        if (currentPlayer == null || opponentPlayer == null) {
            return;
        }

        PlayerDTO currentPlayerDTO = new PlayerDTO(userAction.getPlayerName());
        PlayerDTO opponentPlayerDTO = new PlayerDTO(opponentPlayer.getName());

        ArrayList<PlayerDTO> playersData = new ArrayList<>();

        playersData.add(currentPlayerDTO);
        playersData.add(opponentPlayerDTO);

        GameDTO gameData = new GameDTO("test", playersData);

        if (userAction.getAction().equals("cell selected")) {
            if (!isCorrectOrderOfMoveForPlayer(userAction.getPlayerName())) {
                currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_ERROR);
                builder.update(gameData);

                return;
            }

            previousPlayerWhoMadeMove = currentPlayer;

            Point selectedPointOfCurrentPlayer = userAction.getPoint();

            if (currentPlayer.isMoveWasMade(selectedPointOfCurrentPlayer)) {
                currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_ERROR_SAME_FIELD);
                builder.update(gameData);

                return;
            }

            if (opponentPlayer.isHit(selectedPointOfCurrentPlayer)) {
                currentPlayer.addMove(selectedPointOfCurrentPlayer, "hit");
                opponentPlayer.addHit(selectedPointOfCurrentPlayer);

                // set cells for opponent game field in current player screen
                currentPlayerDTO.setOpponentCells(convertPlayerMovesToCells(currentPlayer.getMoves()));

                // set cells for own game field in opponent player screen
                opponentPlayerDTO.setOwnCells(convertPlayerMovesToCells(currentPlayer.getMoves()));

                if (opponentPlayer.isLose()) {
                    currentPlayerDTO.setMessage(GameMessages.PLAYER_WIN_GAME);
                    opponentPlayerDTO.setMessage(GameMessages.PLAYER_LOSE_GAME);

                    gameInProcess = false;
                } else {
                    currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_SUCCESS_HIT);
                }
            } else {
                currentPlayer.addMove(selectedPointOfCurrentPlayer, "miss");
                // set cells for opponent game field in current player screen
                currentPlayerDTO.setOpponentCells(convertPlayerMovesToCells(currentPlayer.getMoves()));

                // set cells for own game field in opponent player screen
                opponentPlayerDTO.setOwnCells(convertPlayerMovesToCells(currentPlayer.getMoves()));

                currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_MISS_HIT);
            }

            builder.update(gameData);
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
                currentPlayer.getLastMove().getStatus().equals("hit");
    }

    private ArrayList<Cell> convertPlayerMovesToCells(ArrayList<Player.Move> moves) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (Player.Move m: moves) {
            String cellColor = m.getStatus().equals("hit") ? "green" : "red";
            cells.add(new Cell(m.getRow(), m.getCell(), cellColor));
        }

        return cells;
    }
}
