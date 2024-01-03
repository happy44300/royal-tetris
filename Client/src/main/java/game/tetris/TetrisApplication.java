package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class TetrisApplication extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1600);
        settings.setHeight(1500);
        settings.setTicksPerSecond(1);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new LeftKeyAction("Move Left", this), KeyCode.LEFT);
        getInput().addAction(new RightKeyAction("Move Right", this), KeyCode.RIGHT);
        getInput().addAction(new UpKeyAction("Move UP", this), KeyCode.UP);
    }
}
