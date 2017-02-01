package gui;

import core.*;

import java.util.ArrayList;

public class GuiBuilder {
    public GameScreen s1;
    public GameScreen s2;
    private Core handler;

    // ??
    private Integer gameFieldWidth;
    private Integer getGameFieldHeight;

    public GuiBuilder() {

    }

    public void build(GameDAO gameData, String userName1, String userName2) {
        for (PlayerDAO playerData: gameData.getPlayersData()) {
            ArrayList <Cell> playerCells = playerData.getCells();
            s1 = new GameScreen("Test User", playerCells);
            s1.addListener(this);
            s1.build(playerData.getMessage());
        }
    }

    public void handlePlayerAction(PlayerAction action) {
        handler.handleAction(action);
    }

    public void setHandler(Core core) {
        this.handler = core;
    }

    //??
    public void setGameFieldSize(Integer width, Integer height) {
        this.gameFieldWidth = width;
        this.getGameFieldHeight = height;
    }

    public void update(GameDAO gameData) {

    }
}
