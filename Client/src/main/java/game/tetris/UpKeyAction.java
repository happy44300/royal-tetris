package game.tetris;

import org.jetbrains.annotations.NotNull;

public class UpKeyAction extends ClientAction{
    public UpKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }

    @Override
    protected void onActionBegin() {
        try {
            if(!tetrisApplication.isGameStarted){return;}
            //TODO
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
