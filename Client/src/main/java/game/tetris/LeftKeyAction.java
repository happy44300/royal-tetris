package game.tetris;

import org.jetbrains.annotations.NotNull;

public class LeftKeyAction extends ClientAction{
    public LeftKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
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
