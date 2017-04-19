package core;

import gui.GuiBuilder;
import core.Player;

import gui.PlayerAction;
import junit.framework.TestCase;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestCore extends TestCase {
    private Core core;
    private GuiBuilder builder;

    private Player player1;
    private Player player2;

    private int playerGameFieldWidth = 10;
    private int playerGameFieldHeight = 10;

    public TestCore() {
//        this.players.add(new Player("Vasya"));
//        this.players.add(new Player("Petya"));
//
//        this.core = new Core(builder, players);
    }

    @Override
    protected void setUp() throws Exception {
        this.player1 = new Player("Player1");
        this.player2 = new Player("Player2");

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(this.player1);
        players.add(this.player2);


        this.builder = new GuiBuilder(playerGameFieldWidth, playerGameFieldHeight);
        this.core = new Core(this.builder, players, playerGameFieldWidth, playerGameFieldHeight);
    }

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

    public void testHandlePlayerActionWhenSamePlayerMakesTwoMoves() {
        GuiBuilder builder = mock(GuiBuilder.class);
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

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

        assertEquals("Now is not your turn of move", player1.getMessage());
    }

//    public void testOnStartGameUsersShouldHaveCorrectScore() {
//        this.core.preparePlayers();
//
//        Assert.assertTrue("Player 1 has score", this.player1.getScore() > 0);
//        Assert.assertTrue( "Player 2 has score", this.player2.getScore() > 0);
//        Assert.assertEquals("Before start game players has equal scores", this.player1.getScore(), this.player2.getScore());
//    }
//
//    public void testOnStartGameUsersShouldHaveShips() {
//        this.core.preparePlayers();
//
//        Assert.assertNotNull("Player 1 has ships", this.player1.getShips());
//        Assert.assertNotNull( "Player 2 has ships", this.player2.getShips());
//    }
}
