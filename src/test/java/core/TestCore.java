package core;

import gui.GuiBuilder;
import core.Player;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestCore extends TestCase {
    private Core core;

    private Player player1;
    private Player player2;



//    public TestCore() {
//        this.players.add(new Player("Vasya"));
//        this.players.add(new Player("Petya"));
//
//        this.core = new Core(builder, players);
//    }

    @Override
    protected void setUp() throws Exception {
        this.player1 = new Player("Vasya");
        this.player2 = new Player("Petya");

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(this.player1);
        players.add(this.player2);

        this.core = new Core(new GuiBuilder(), players);
    }

    public void testOnStartGameUsersShouldHaveCorrectScore() {
        this.core.preparePlayers();

        Assert.assertTrue("Player 1 has score", this.player1.getScore() > 0);
        Assert.assertTrue( "Player 2 has score", this.player2.getScore() > 0);
        Assert.assertEquals("Before start game players has equal scores", this.player1.getScore(), this.player2.getScore());
    }

    public void testOnStartGameUsersShouldHaveShips() {
        this.core.preparePlayers();

        Assert.assertNotNull("Player 1 has ships", this.player1.getShips());
        Assert.assertNotNull( "Player 2 has ships", this.player2.getShips());
    }
}
