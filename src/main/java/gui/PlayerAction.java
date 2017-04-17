package gui;

import core.Point;

public class PlayerAction {
    private String playerName;
    private String action;
    private Point point;

    public PlayerAction(String userName) {
        this.playerName = userName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAction() {
        return action;
    }

    public PlayerAction setAction(String action) {
        this.action = action;

        return this;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}