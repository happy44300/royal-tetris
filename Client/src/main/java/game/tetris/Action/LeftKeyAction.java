package game.tetris.Action;

import game.tetris.TetrisApplication;
import game.tetris.action.Rotate;
import game.tetris.action.Translate;
import org.jetbrains.annotations.NotNull;

public class LeftKeyAction extends ClientAction {
    public LeftKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }

    @Override
    protected void onActionBegin() {
        try {
            System.out.println("LEFT!");
            if(!tetrisApplication.isGameStarted()){return;}
            tetrisApplication.getClientToServer().submitBlockUpdate(tetrisApplication.getPlayerID(),new Translate(-1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
