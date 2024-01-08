package game.tetris.action;

import game.tetris.TetrisApplication;
import org.jetbrains.annotations.NotNull;

public class LeftKeyAction extends ClientAction {
    public LeftKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }

    @Override
    protected void onActionBegin() {
        try {
            if(!tetrisApplication.isGameStarted()){return;}
            tetrisApplication.getClientToServer().submitBlockUpdate(tetrisApplication.getPlayerID(),new Translate(-1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
