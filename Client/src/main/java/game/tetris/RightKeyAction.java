package game.tetris;

import game.tetris.action.Rotate;
import org.jetbrains.annotations.NotNull;

public class RightKeyAction extends ClientAction{
    public RightKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }
    @Override
    protected void onActionBegin() {
        try {
            if(!tetrisApplication.isGameStarted){return;}
            tetrisApplication.getClientToServer().submitBlockUpdate(tetrisApplication.getPlayerID(),new Rotate(true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
