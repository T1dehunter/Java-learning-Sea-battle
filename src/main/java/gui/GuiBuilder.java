package gui;

public class GuiBuilder implements Handler {
    private GameScreen gameScreen1;
    private GameScreen gameScreen2;

    public GuiBuilder() {
        gameScreen1 = new GameScreen(new BattleField());
        gameScreen1.setHandler(this);
//        gameScreen2 = new GameScreen(new BattleField());
    }

    public void build() {
        gameScreen1.build();
//        gameScreen2.build();
    }

    public void handle(PlayerAction action) {

    }
}
