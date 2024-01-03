package game.tetris.action;

public class Rotate extends PlayerAction {
    boolean isClockwise;

    public Rotate(){
        this.isClockwise = true;
        this.playerActionType = PlayerActionType.ROTATE;
    }

    public Rotate(boolean isClockwise) {
        this.isClockwise = isClockwise;
        this.playerActionType = PlayerActionType.ROTATE;
    }

    public boolean isClockwise() {
        return isClockwise;
    }

    public void setClockwise(boolean clockwise) {
        isClockwise = clockwise;
    }
}
