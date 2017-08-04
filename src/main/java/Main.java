import core.*;
import gui.GuiBuilder;

import java.util.ArrayList;
import java.util.Properties;


public class Main {
    public static void main(String[] args) {
//        TestClasses test = new TestClasses();
//        test.run();

        GameSettings settings = new GameSettings("config/config.properties");
        Properties props = settings.getSettings();

        PlayerCreator creator = new PlayerCreator(props);

        ArrayList<Player> players = new ArrayList<>();

        players.add(creator.create("Player1"));
        players.add(creator.create("Player2"));

        int playerFieldWidth = Integer.parseInt(props.getProperty("width"));
        int playerFieldHeight = Integer.parseInt(props.getProperty("height"));

        GuiBuilder builder = new GuiBuilder(playerFieldWidth, playerFieldHeight);
        Core core = new Core(builder, players);
        builder.setCore(core);

        core.run();
    }
}
