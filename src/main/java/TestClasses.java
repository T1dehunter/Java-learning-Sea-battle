//import java.util.Objects;

public class TestClasses {

    public void run() {
        Core c = new Core();

        Builder b = new Builder("Vasya", "Petya", c);

        c.setBuilder(b);

        c.run();
    }
}

class Core {
    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void run() {

        GameData gameData = new GameData("DATA FROM CORE!");

        builder.build(gameData);
    }

    public void handleAction(Action userAction) {
        if (userAction == null) {
            throw new NullPointerException("userAction is null!");
        }


        System.out.print("\nCore gets action from " + userAction.getUserName());
        System.out.print("\nCore gets action " + userAction.getAction());


        if (userAction.getAction().equals("select point")) {
            Point point = userAction.getPoint();

            System.out.print("\nCore gets action: user select cell by cords " + point.getRow() + " : " + point.getCell());
        }
    }
}

class Builder {
    public Screen s1;
    public Screen s2;
    private Core core;

    Builder(String userName1, String userName2, Core core) {
        s1 = new Screen(userName1);
        s2 = new Screen(userName2);

        this.core = core;

        s1.addListener(this);
        s2.addListener(this);
    }

    public void build(GameData gameData) {
        String data = gameData.data;
        s1.build(data);
    }

    public void handleUserAction(Action action) {
        core.handleAction(action);
    }
}

class Screen {
    private String userName;
    private Builder handler;
    private Field field;

    Screen(String userName) {
        this.userName = userName;
        field = new Field();
        field.addListener(this);
    }

    void addListener(Builder handler) {
        this.handler = handler;
    }

    public void build(String data) {
        field.display(data);
        field.testEvent();
    }

    public void handleFieldCellClick(Point point) {
        Action action = new Action(userName);
        action.setPoint(point);
        action.setAction("select point");
        handler.handleUserAction(action);
    }
}

class Field {
    private Screen handler;

    void addListener(Screen handler) {
        this.handler = handler;
    }

    public void display(String s) {
        System.out.print("Field displas somting -> " + s);
    }

    public void testEvent() {
        System.out.print("\nUser made some event");
        handler.handleFieldCellClick(new Point(9, 9));
    }
}

class Action {
    private String userName;
    private String action;
    private Point point;

    Action(String userName) {
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

class GameData {
    public String data;

    GameData(String s) {
        this.data = s;
    }
}

class Point {
    private int row;
    private int cell;

    public Point(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }
}
