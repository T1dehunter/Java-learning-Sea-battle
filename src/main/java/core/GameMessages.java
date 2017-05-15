package core;

public enum GameMessages {
    PLAYER_MOVE_ERROR("Now is not your turn of move"),
    TEST("Text 2");

    private String message;

    GameMessages(String message) {
        this.message = message;
    }

    public String getText() { return message; }
}