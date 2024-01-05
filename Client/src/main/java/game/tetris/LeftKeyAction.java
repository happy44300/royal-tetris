package game.tetris;

import game.tetris.action.Rotate;
import org.jetbrains.annotations.NotNull;

public class LeftKeyAction extends ClientAction{
    public LeftKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }

    @Override
    protected void onActionBegin() {
        try {
            if(!tetrisApplication.isGameStarted){return;}
            tetrisApplication.getClientToServer().submitBlockUpdate(tetrisApplication.getPlayerID(),new Rotate(false));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
