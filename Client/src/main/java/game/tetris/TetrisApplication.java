package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.input.KeyCode;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class TetrisApplication extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1600);
        settings.setHeight(1500);
        settings.setTicksPerSecond(1);
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
    }

    @Override
    public void initGame() {
        //TODO
        /*this.tetrisGrid = new ClientTetrisGrid(ROWS, COLUMNS);
        var background = FXGL.entityBuilder().
                at(0,-1,0)
                .view(new Rectangle(getAppWidth(),getAppHeight(), Color.BLACK))
                .buildAndAttach();

        for (Entity[] row : this.tetrisGrid.getGrid()) {
            for (Entity cell : row) {
                getGameWorld().addEntity(cell);
            }
        }

        connect();*/
    }
    @Override
    protected void initInput() {
        getInput().addAction(new LeftKeyAction("Move Left", this), KeyCode.LEFT);
        getInput().addAction(new RightKeyAction("Move Right", this), KeyCode.RIGHT);
        getInput().addAction(new UpKeyAction("Move UP", this), KeyCode.UP);
    }
}
