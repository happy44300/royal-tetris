package game.tetris.action;

import java.io.Serializable;

public class Translate extends PlayerAction implements Serializable {
    int translateValue;

    public Translate(int translateValue) {
        this.translateValue = translateValue;
        this.playerActionType = PlayerActionType.TRANSLATE;
    }

    public int getTranslateValue() {
        return translateValue;
    }

    public void setTranslateValue(int translateValue) {
        this.translateValue = translateValue;
    }
}
