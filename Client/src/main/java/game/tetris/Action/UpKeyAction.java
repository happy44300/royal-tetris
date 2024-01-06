package game.tetris.Action;

import game.tetris.TetrisApplication;
import game.tetris.action.Rotate;
import org.jetbrains.annotations.NotNull;

public class UpKeyAction extends ClientAction {
    public UpKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }

    @Override
    protected void onActionBegin() {
        try {
            System.out.println("UP!");
            if(!tetrisApplication.isGameStarted()){return;}
            tetrisApplication.getClientToServer().submitBlockUpdate(tetrisApplication.getPlayerID(),new Rotate(true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
