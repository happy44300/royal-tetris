package game.tetris.action;

import java.io.Serializable;

public abstract class PlayerAction implements Serializable {
    PlayerActionType playerActionType;

    public PlayerActionType getPlayerActionType() {
        return this.playerActionType;
    }
}
