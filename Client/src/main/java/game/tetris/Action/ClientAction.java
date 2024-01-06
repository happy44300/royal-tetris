package game.tetris.Action;

import com.almasb.fxgl.input.UserAction;
import game.tetris.TetrisApplication;
import org.jetbrains.annotations.NotNull;

public class ClientAction extends UserAction {
    TetrisApplication tetrisApplication;
    public ClientAction(@NotNull String name, TetrisApplication ta) {
        super(name);
        tetrisApplication = ta;
    }



}
