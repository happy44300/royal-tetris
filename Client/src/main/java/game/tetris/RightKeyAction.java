package game.tetris;

import org.jetbrains.annotations.NotNull;

public class RightKeyAction extends ClientAction{
    public RightKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }
    @Override
    protected void onActionBegin() {
        try {
            //TODO
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
