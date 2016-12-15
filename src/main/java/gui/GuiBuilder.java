package gui;

import core.Core;
import core.GameData;

public class GuiBuilder {
    public GameScreen s1;
    public GameScreen s2;
    private Core core;

    public GuiBuilder() {

    }

    public void build(GameData gameData, String userName1, String userName2) {
        s1 = new GameScreen(userName1);
        s2 = new GameScreen(userName2);

        s1.addListener(this);
        s2.addListener(this);

        String data = gameData.data;
        s1.build(data);
    }

    public void handlePlayerAction(PlayerAction action) {
        core.handleAction(action);
    }

    public void setCore(Core core) {
        this.core = core;
    }
}
