package gui;

public class PlayerAction {
    private String userName;
    private String action;
    private Point point;

    PlayerAction(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}