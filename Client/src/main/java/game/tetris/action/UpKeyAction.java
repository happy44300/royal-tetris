package game.tetris.action;

import game.tetris.TetrisApplication;
import org.jetbrains.annotations.NotNull;

public class UpKeyAction extends ClientAction {
    public UpKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }

    @Override
    protected void onActionBegin() {
        try {
            if(!tetrisApplication.isGameStarted()){return;}
            tetrisApplication.getClientToServer().submitBlockUpdate(tetrisApplication.getPlayerID(),new Rotate(true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
