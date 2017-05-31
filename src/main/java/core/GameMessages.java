package core;

public enum GameMessages {
    PLAYER_WIN_GAME("You are winner !!!"),
    PLAYER_LOSE_GAME("You are loser !!!"),
    PLAYER_MOVE_ERROR("Now is not your turn of move."),
    PLAYER_TURN_MOVE("Now is your turn of move."),
    PLAYER_MOVE_SUCCESS_HIT("You have success hit."),
    PLAYER_MOVE_MISS_HIT("You have miss hit."),
    PLAYER_MOVE_ERROR_SAME_FIELD("You shot by this field in last time.");

    private String message;

    GameMessages(String message) {
        this.message = message;
    }

    public String toString() { return message; }
}