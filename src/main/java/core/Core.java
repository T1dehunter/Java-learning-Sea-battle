package core;

import gui.GuiBuilder;
import gui.PlayerAction;

import java.util.*;

public class Core {
    private GuiBuilder builder;
    private ArrayList<Player> players;
    private Player previousPlayerWhoMadeMove;
    private boolean gameInProcess;

    private final String  STATUS_HIT = "hit";
    private final String  STATUS_MISS = "miss";
    private final String  ACTION_TYPE_CELL_SELECTED = "cell selected";

    public Core(GuiBuilder builder, ArrayList<Player> players) {
        this.builder = builder;
        this.players = players;

        this.gameInProcess = true;
    }

    public void run() {
        ArrayList<PlayerDTO> playersData = new ArrayList<PlayerDTO>();

        for (Player player: players) {
            ArrayList<Cell> playerCells = new ArrayList<Cell>();

            player.placeShips();

            ArrayList<Ship> ships = player.getShips();

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

    public void handlePlayerAction(PlayerAction playerAction) {
        if (!gameInProcess) {
            return;
        }

        Player currentPlayer = getCurrentPlayer(playerAction.getPlayerName());
        Player opponentPlayer = getOpponentPlayer(playerAction.getPlayerName());

        if (currentPlayer == null || opponentPlayer == null) {
            return;
        }

        System.out.print("Current player -> " + currentPlayer.getName() + " \n");

        if (playerAction.getAction().equals(ACTION_TYPE_CELL_SELECTED)) {
            GameDTO gameData;
            Point playerMove = playerAction.getPoint();

            if (!isCorrectOrderOfMoveForPlayer(currentPlayer.getName())) {
                gameData = handleIncorrectOrderOfMove(currentPlayer, opponentPlayer);
            } else if (currentPlayer.isMoveWasMade(playerMove)) {
                gameData = handleMoveToSameCell(currentPlayer, opponentPlayer);
            } else if (opponentPlayer.isHit(playerMove)) {
                gameData = handleMoveHit(currentPlayer, opponentPlayer, playerMove);
            } else {
                gameData = handleMoveMiss(currentPlayer, opponentPlayer, playerMove);
            }

            previousPlayerWhoMadeMove = currentPlayer;

            builder.update(gameData);
        }
    }

    private boolean isCorrectOrderOfMoveForPlayer(String playerName) {
        Player currentPlayer = getCurrentPlayer(playerName);

        return isPreviousAndCurrentPlayersAreDifferent(currentPlayer) || isCurrentPlayerHasHitOnPreviousMove(currentPlayer);
    }

    private Player getCurrentPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }

        return null;
    }

    private Player getOpponentPlayer(String playerName) {
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
                currentPlayer.getLastMove().getStatus().equals(STATUS_HIT);
    }

    private ArrayList<Cell> convertPlayerMovesToCells(ArrayList<Player.Move> moves) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (Player.Move m: moves) {
            String cellColor = m.getStatus().equals(STATUS_HIT) ? "green" : "red";
            cells.add(new Cell(m.getRow(), m.getCell(), cellColor));
        }

        return cells;
    }

    private GameDTO buildGameData(String currentPlayerName, String opponentPlayerName) {
        PlayerDTO currentPlayerDTO = new PlayerDTO(currentPlayerName);
        PlayerDTO opponentPlayerDTO = new PlayerDTO(opponentPlayerName);

        ArrayList<PlayerDTO> playersData = new ArrayList<>();

        playersData.add(currentPlayerDTO);
        playersData.add(opponentPlayerDTO);

        return new GameDTO("test", playersData);
    }

    private GameDTO handleIncorrectOrderOfMove(Player currentPlayer, Player opponentPlayer) {
        GameDTO gameDTO = buildGameData(currentPlayer.getName(), opponentPlayer.getName());

        PlayerDTO currentPlayerDTO = gameDTO.getPlayersData().get(0);

        currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_ERROR);

        return gameDTO;
    }

    private GameDTO handleMoveToSameCell(Player currentPlayer, Player opponentPlayer) {
        GameDTO gameDTO = buildGameData(currentPlayer.getName(), opponentPlayer.getName());

        PlayerDTO currentPlayerDTO = gameDTO.getPlayersData().get(0);
        PlayerDTO opponentPlayerDTO = gameDTO.getPlayersData().get(1);

        currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_ERROR_SAME_FIELD);
        opponentPlayerDTO.setMessage(GameMessages.PLAYER_TURN_MOVE);

        return gameDTO;
    }

    private GameDTO handleMoveHit(Player currentPlayer, Player opponentPlayer, Point hit) {
        currentPlayer.addMove(hit, STATUS_HIT);
        opponentPlayer.addHit(hit);

        GameDTO gameDTO = buildGameData(currentPlayer.getName(), opponentPlayer.getName());
        PlayerDTO currentPlayerDTO = gameDTO.getPlayersData().get(0);
        PlayerDTO opponentPlayerDTO = gameDTO.getPlayersData().get(1);

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
            if (opponentPlayer.isShipWasDestroyed()) {
                currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_SHIP_DESTROYED);
            }
        }

        return gameDTO;
    }

    private GameDTO handleMoveMiss(Player currentPlayer, Player opponentPlayer, Point miss) {
        GameDTO gameDTO = buildGameData(currentPlayer.getName(), opponentPlayer.getName());
        PlayerDTO currentPlayerDTO = gameDTO.getPlayersData().get(0);
        PlayerDTO opponentPlayerDTO = gameDTO.getPlayersData().get(1);

        currentPlayer.addMove(miss, STATUS_MISS);
        // set cells for opponent game field in current player screen
        currentPlayerDTO.setOpponentCells(convertPlayerMovesToCells(currentPlayer.getMoves()));

        // set cells for own game field in opponent player screen
        opponentPlayerDTO.setOwnCells(convertPlayerMovesToCells(currentPlayer.getMoves()));

        currentPlayerDTO.setMessage(GameMessages.PLAYER_MOVE_MISS_HIT);
        opponentPlayerDTO.setMessage(GameMessages.PLAYER_TURN_MOVE);

        return gameDTO;
    }
}
