package core;

import core.ShipBuilder;
import gui.GuiBuilder;
import core.Player;
import core.GameMessages;

import gui.PlayerAction;
import junit.framework.TestCase;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestCore extends TestCase {
    private GuiBuilder builder;

    private Player player1;
    private Player player2;

    private ArrayList<Ship> shipsOfPlayer1;
    private ArrayList<Ship> shipsOfPlayer2;

    private ArrayList<Player> players = new ArrayList<>();

       /*
    @Override

    protected void setUp() throws Exception {
        this.player1 = new Player("Player1");
        this.shipsOfPlayer1 = new ArrayList<>();
        this.player1.setShips(this.shipsOfPlayer1);

        this.player2 = new Player("Player2");
        this.shipsOfPlayer2 = new ArrayList<>();
        this.player2.setShips(this.shipsOfPlayer2);

        players.add(this.player1);
        players.add(this.player2);

        this.builder = new GuiBuilder(10, 10);
//        this.core = new Core(this.builder, players, playerGameFieldWidth, playerGameFieldHeight);
    }
    */

//    public void testHandlePlayerAction() {
//        GuiBuilder builder = mock(GuiBuilder.class);
//        ArrayList<Player> players = new ArrayList<>();
//        players.add(this.player1);
//        players.add(this.player2);
//
//        Core core = new Core(builder, players, 10, 10);
//
//        PlayerAction action = new PlayerAction("Player1");
//        action.setAction("cell selected").setPoint(new Point(0, 0));
//
//        core.handlePlayerAction(action);
//
//        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
//        verify(builder).update(argument.capture());
//
//        GameDTO gameDTOArg = argument.getValue();
//        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);
////        PlayerDTO player2 = gameDTOArg.getPlayersData().get(1);
//        assertEquals(0, player1.getOwnCells().get(0).getRow());
//        assertEquals(0, player1.getOwnCells().get(0).getCell());
//        assertEquals("red", player1.getOwnCells().get(0).getColor());
//    }


    public void testSomeMethod() {
        assertEquals(1, 1);
    }

    /*

    public void testHandlePlayerActionWhenPlayerHasSuccessHit() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> cords = new ArrayList<>();
        cords.add(new Point(0, 0));
        cords.add(new Point(0, 1));
        cords.add(new Point(0, 2));
        Ship ship = new Ship(3, "test");
        ship.setCoordinates(cords);
        ships.add(ship);

        this.player2.setShips(ships);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction action1 = new PlayerAction("Player1");
        Point selectedPoint = new Point(0, 0);
        action1.setAction("cell selected").setPoint(selectedPoint);

        core.handlePlayerAction(action1);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(1)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);
        PlayerDTO player2 = gameDTOArg.getPlayersData().get(1);
        ArrayList<Cell> player1OpponentCells = player1.getOpponentCells();
        ArrayList<Cell> player2OwnCells = player2.getOwnCells();

        assertEquals("Player who made success hit has correct message", GameMessages.PLAYER_MOVE_SUCCESS_HIT.toString(), player1.getMessage());
        assertEquals("Score of opponent was correct decreased", (Integer) 2, this.player2.getScore());

        assertEquals("Color of cell in player1 opponent field is correct", player1OpponentCells.get(0).getColor(), "green");
        assertEquals("Column of cell in player1 opponent field is correct", player1OpponentCells.get(0).getCell(), selectedPoint.getCell());
        assertEquals("Row of cell in player1 opponent field is correct", player1OpponentCells.get(0).getRow(), selectedPoint.getRow());

        assertEquals("Color of cell in player2 own field is correct", player2OwnCells.get(0).getColor(), "green");
        assertEquals("Column of cell in player2 own field is correct", player2OwnCells.get(0).getCell(), selectedPoint.getCell());
        assertEquals("Row of cell in player2 own field is correct", player2OwnCells.get(0).getRow(), selectedPoint.getRow());
    }

    public void testHandlePlayerActionWhenPlayerHasMissHit() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> cords = new ArrayList<>();
        cords.add(new Point(0, 0));
        cords.add(new Point(0, 1));
        cords.add(new Point(0, 2));
        Ship ship = new Ship(3, "test");
        ship.setCoordinates(cords);
        ships.add(ship);

        this.player2.setShips(ships);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction action1 = new PlayerAction("Player1");
        Point selectedPoint = new Point(9, 9);
        action1.setAction("cell selected").setPoint(selectedPoint);

        core.handlePlayerAction(action1);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(1)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);
        PlayerDTO player2 = gameDTOArg.getPlayersData().get(1);
        ArrayList<Cell> player1OpponentCells = player1.getOpponentCells();
        ArrayList<Cell> player2OwnCells = player2.getOwnCells();

        assertEquals("Player who made miss hit has correct message", GameMessages.PLAYER_MOVE_MISS_HIT.toString(), player1.getMessage());
        assertEquals("Score of opponent was not decreased", (Integer) 3, this.player2.getScore());

        assertEquals("Color of cell in player1 opponent field is correct", player1OpponentCells.get(0).getColor(), "red");
        assertEquals("Column of cell in player1 opponent field is correct", player1OpponentCells.get(0).getCell(), selectedPoint.getCell());
        assertEquals("Row of cell in player1 opponent field is correct", player1OpponentCells.get(0).getRow(), selectedPoint.getRow());

        assertEquals("Color of cell in player2 own field is correct", "red", player2OwnCells.get(0).getColor());
        assertEquals("Column of cell in player2 own field is correct", selectedPoint.getCell(), player2OwnCells.get(0).getCell());
        assertEquals("Row of cell in player2 own field is correct", selectedPoint.getRow(), player2OwnCells.get(0).getRow());
    }

    public void testHandlePlayerActionWhenSamePlayerMakesSecondMoveAfterSuccessHit() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> cords = new ArrayList<>();
        cords.add(new Point(0, 0));
        cords.add(new Point(0, 1));
        cords.add(new Point(0, 2));
        Ship ship = new Ship(3, "test");
        ship.setCoordinates(cords);
        ships.add(ship);

        this.player2.setShips(ships);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction action1 = new PlayerAction("Player1");
        action1.setAction("cell selected").setPoint(new Point(0, 0));

        PlayerAction action2 = new PlayerAction("Player1");
        action2.setAction("cell selected").setPoint(new Point(0, 1));

        core.handlePlayerAction(action1);
        core.handlePlayerAction(action2);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(2)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);
        ArrayList<Cell> player1OpponentCells = player1.getOpponentCells();


        assertEquals(GameMessages.PLAYER_MOVE_SUCCESS_HIT.toString(), player1.getMessage());
        assertEquals("Score of opponent was decreased twice", (Integer) 1, this.player2.getScore());
        assertEquals("Count cells is correct", 2, player1OpponentCells.size());
        assertEquals("Cell from first move in opponent field on player screen has correct color", "green", player1OpponentCells.get(0).getColor());
        assertEquals("Cell from second move in opponent field on player screen has correct color", "green", player1OpponentCells.get(1).getColor());
    }

    public void testHandlePlayerActionWhenSamePlayerMakesSecondMoveOutsideOfQue() {
        GuiBuilder builder = mock(GuiBuilder.class);

        ArrayList<Point> player1ShipCords = new ArrayList<>();
        player1ShipCords.add(new Point(0, 0));
        player1ShipCords.add(new Point(0, 1));
        player1ShipCords.add(new Point(0, 2));
        Ship player1Ship = new Ship(3, "test");
        player1Ship.setCoordinates(player1ShipCords);
        shipsOfPlayer1.add(player1Ship);

        ArrayList<Point> player2ShipCords = new ArrayList<>();
        player2ShipCords.add(new Point(0, 0));
        player2ShipCords.add(new Point(0, 1));
        player2ShipCords.add(new Point(0, 2));
        Ship player2Ship = new Ship(3, "test");
        player2Ship.setCoordinates(player2ShipCords);
        shipsOfPlayer2.add(player2Ship);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction action1 = new PlayerAction("Player1");
        action1.setAction("cell selected").setPoint(new Point(9, 0));

        PlayerAction action2 = new PlayerAction("Player1");
        action2.setAction("cell selected").setPoint(new Point(9, 1));

        core.handlePlayerAction(action1);
        core.handlePlayerAction(action2);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(2)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);

        assertEquals(GameMessages.PLAYER_MOVE_ERROR.toString(), player1.getMessage());
        assertEquals("Score of opponent was not decreased",(Integer) 3, this.player2.getScore());
    }

    public void testHandlePlayerActionWhenPlayerMakesMoveToSameCell() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> cords = new ArrayList<>();
        cords.add(new Point(0, 0));
        cords.add(new Point(0, 1));
        cords.add(new Point(0, 2));
        Ship ship = new Ship(3, "test");
        ship.setCoordinates(cords);
        ships.add(ship);

        this.player2.setShips(ships);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction firstAction = new PlayerAction("Player1");
        firstAction.setAction("cell selected").setPoint(new Point(0, 0));

        PlayerAction secondAction = new PlayerAction("Player1");
        secondAction.setAction("cell selected").setPoint(new Point(0, 1));

        PlayerAction thirdActionBySameField = new PlayerAction("Player1");
        thirdActionBySameField.setAction("cell selected").setPoint(new Point(0, 0));

        core.handlePlayerAction(firstAction);
        core.handlePlayerAction(secondAction);
        core.handlePlayerAction(thirdActionBySameField);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(3)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);

        assertEquals(GameMessages.PLAYER_MOVE_ERROR_SAME_FIELD.toString(), player1.getMessage());
        assertEquals("Score of opponent was decreased only twice",(Integer) 1, this.player2.getScore());
    }

    public void testHandlePlayerActionWhenPlayerMakesMoveWinningGame() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> cords = new ArrayList<>();
        cords.add(new Point(0, 0));
        Ship ship = new Ship(3, "test");
        ship.setCoordinates(cords);
        ships.add(ship);

        this.player2.setShips(ships);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction gameWinAction = new PlayerAction("Player1");
        gameWinAction.setAction("cell selected").setPoint(new Point(0, 0));

        core.handlePlayerAction(gameWinAction);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(1)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);
        PlayerDTO player2 = gameDTOArg.getPlayersData().get(1);

        assertEquals(GameMessages.PLAYER_WIN_GAME.toString(), player1.getMessage());
        assertEquals(GameMessages.PLAYER_LOSE_GAME.toString(), player2.getMessage());
    }


    public void testHandlePlayerActionWhenPlayerWhoWinMakesMoveAfterGameEnd() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> cords = new ArrayList<>();
        cords.add(new Point(0, 0));
        Ship ship = new Ship(3, "test");
        ship.setCoordinates(cords);
        ships.add(ship);

        this.player2.setShips(ships);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction gameWinAction = new PlayerAction("Player1");
        gameWinAction.setAction("cell selected").setPoint(new Point(0, 0));

        PlayerAction actionAfterGameWin = new PlayerAction("Player1");
        actionAfterGameWin.setAction("cell selected").setPoint(new Point(0, 0));

        core.handlePlayerAction(gameWinAction);
        core.handlePlayerAction(actionAfterGameWin);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(1)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);
        PlayerDTO player2 = gameDTOArg.getPlayersData().get(1);

        assertEquals(GameMessages.PLAYER_WIN_GAME.toString(), player1.getMessage());
        assertEquals(GameMessages.PLAYER_LOSE_GAME.toString(), player2.getMessage());
    }

    public void testHandlePlayerActionWhenPlayerWhoLoseMakesMoveAfterGameEnd() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> cords = new ArrayList<>();
        cords.add(new Point(0, 0));
        Ship ship = new Ship(3, "test");
        ship.setCoordinates(cords);
        ships.add(ship);

        this.player2.setShips(ships);

        Core core = new Core(builder, players, 10, 10);

        PlayerAction gameWinAction = new PlayerAction("Player1");
        gameWinAction.setAction("cell selected").setPoint(new Point(0, 0));

        PlayerAction actionAfterGameEnd = new PlayerAction("Player2");
        actionAfterGameEnd.setAction("cell selected").setPoint(new Point(0, 0));

        core.handlePlayerAction(gameWinAction);
        core.handlePlayerAction(actionAfterGameEnd);

        ArgumentCaptor<GameDTO> argument = ArgumentCaptor.forClass(GameDTO.class);
        verify(builder, times(1)).update(argument.capture());

        GameDTO gameDTOArg = argument.getValue();
        PlayerDTO player1 = gameDTOArg.getPlayersData().get(0);
        PlayerDTO player2 = gameDTOArg.getPlayersData().get(1);

        assertEquals(GameMessages.PLAYER_WIN_GAME.toString(), player1.getMessage());
        assertEquals(GameMessages.PLAYER_LOSE_GAME.toString(), player2.getMessage());
    }


    */
}
