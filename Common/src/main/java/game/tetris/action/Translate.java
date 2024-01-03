package game.tetris.action;

public class Translate extends PlayerAction {
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
