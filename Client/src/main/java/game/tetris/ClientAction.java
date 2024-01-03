package game.tetris;

import com.almasb.fxgl.input.UserAction;
import org.jetbrains.annotations.NotNull;

public class ClientAction extends UserAction {
    TetrisApplication tetrisApplication;
    public ClientAction(@NotNull String name, TetrisApplication ta) {
        super(name);
        tetrisApplication = ta;
    }


}
