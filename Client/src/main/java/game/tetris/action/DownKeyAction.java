package game.tetris.action;

import game.tetris.TetrisApplication;
import org.jetbrains.annotations.NotNull;

public class DownKeyAction extends ClientAction {
    public DownKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }
    @Override
    protected void onActionBegin() {
        try {
            System.out.println("DOWN!");
            if(!tetrisApplication.isGameStarted()){return;}
            tetrisApplication.getClientToServer().submitBlockUpdate(tetrisApplication.getPlayerID(),new Rotate(false));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
