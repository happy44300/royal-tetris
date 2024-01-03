package game.tetris;

import com.almasb.fxgl.input.UserAction;
import org.jetbrains.annotations.NotNull;

public abstract class TetrisAction extends UserAction {
    TetrisApplication tetrisApplication;

    public TetrisAction(@NotNull String name, TetrisApplication ta) {
        super(name);
        this.tetrisApplication = ta;
    }
}
