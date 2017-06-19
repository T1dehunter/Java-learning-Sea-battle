package gui;

import core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GuiBuilder implements PlayerActionHandler {
    private Core core;

    private int playerGameFieldSize;

    private Map<String, GameScreen> playersScreens = new HashMap<>();

    public GuiBuilder(int playerGameFieldSize) {
        this.playerGameFieldSize = playerGameFieldSize;
    }

    public void build(GameDTO gameData) {
        for (PlayerDTO playerData: gameData.getPlayersData()) {
            GameScreen screen;

            PlayerAction action = new PlayerAction(playerData.getName());

            screen = new GameScreen(playerGameFieldSize, playerData,this, action);
            screen.build(playerData.getMessages());

            playersScreens.put(playerData.getName(), screen);
        }
    }

    // ??
    public void handle(PlayerAction action) {
        core.handlePlayerAction(action);
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public void update(GameDTO gameData) {
        for (PlayerDTO playerData: gameData.getPlayersData()) {
            GameScreen screen = playersScreens.get(playerData.getName());

            screen.update(playerData);
        }
    }
}
