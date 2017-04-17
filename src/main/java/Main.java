import core.Core;
import core.GameSettings;
import core.Player;
//import gui.TestGuiElements;
//import gui.BattleField;
import gui.GuiBuilder;

import java.util.ArrayList;
import java.util.Properties;


public class Main {
    public static final String test = "TEST";

    public static void main(String[] args) {
        TestClasses test = new TestClasses();
        test.run();

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));

        GameSettings settings = new GameSettings("config/config.properties");
        Properties props = settings.getSettings();
        int playerFieldWidth = Integer.parseInt(props.getProperty("width"));
        int playerFieldHeight = Integer.parseInt(props.getProperty("height"));

        GuiBuilder builder = new GuiBuilder(playerFieldWidth, playerFieldHeight);
        Core core = new Core(builder, players, playerFieldWidth, playerFieldHeight);
        builder.setCore(core);

        core.run();
    }
}
