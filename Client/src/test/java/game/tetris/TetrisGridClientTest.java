package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import game.tetris.block.*;
import game.tetris.datastructure.Point;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.rmi.RemoteException;
import java.util.Map;

import static com.almasb.fxgl.app.GameApplication.launch;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class TetrisGridClientTest extends GameApplication{
    TetrisGridClient tetrisGridClient;
    ClientBlock b1;
    @Test
    void updateGridSynchronously() {
    }

    @Test
    void updateGridUnsafe() {
    }

    @Test
    void readGrid() {
    }

    @Test
    void getGridEntities() {
    }

    public static void main(String[] args) {
        launch(new String[]{""});
    }

    @Test
    void generateBlock(){

    }

    private static final int ROWS = 15;
    private static final int COLLUMNS = 10;

    @Nullable
    private static String ip = null;
    @Nullable
    private static String username = null;

    @Override
    public void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTicksPerSecond(1);
    }

    @Override
    public void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
    }

    @Override
    public void initGame() {

        tetrisGridClient = new TetrisGridClient(ROWS, COLLUMNS);
        var background = FXGL.entityBuilder().
                at(0, -1, 0)
                .view(new Rectangle(getAppWidth(), getAppHeight(), Color.BLACK))
                .buildAndAttach();

        for (Entity[] row : tetrisGridClient.getGridEntities()) {
            for (Entity cell : row) {
                getGameWorld().addEntity(cell);
            }
        }
        b1 = new IBlock(0,4,tetrisGridClient);



    }

    @Override
    public void initInput() {
        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                try {
                    b1.translate(new Point(0,-1));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }, KeyCode.LEFT);
        getInput().addAction(new UserAction("Move right") {
            @Override
            protected void onActionBegin() {
                try {
                    b1.translate(new Point(0,1));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }, KeyCode.RIGHT);
        getInput().addAction(new UserAction("Move DOWN") {
            @Override
            protected void onActionBegin() {
                try {
                    b1.translate(new Point(1,0));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }, KeyCode.DOWN);
        getInput().addAction(new UserAction("ROTATED") {
            @Override
            protected void onActionBegin() {
                try {
                    b1.rotate();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }, KeyCode.UP);
        //TODO: Add actions for other Tetris movements (right, rotate, etc.)
    }

    @Override
    public void onUpdate(double tpf) {
        //TODO: Update game logic here
    }

    private void connect() {
        //TODO: implement connection
    }

}