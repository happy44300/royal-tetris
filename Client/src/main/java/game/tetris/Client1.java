package game.tetris;

import game.tetris.action.DownKeyAction;
import game.tetris.action.LeftKeyAction;
import game.tetris.action.RightKeyAction;
import game.tetris.action.UpKeyAction;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class Client1 extends TetrisApplication{

    public Client1(){
        this.setClientPort(10001);
    }
    public static void main(String[] args) {
        launch(Client1.class, args);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new LeftKeyAction("LEFT", this), KeyCode.LEFT);
        getInput().addAction(new RightKeyAction("RIGHT", this), KeyCode.RIGHT);
        getInput().addAction(new UpKeyAction("UP", this), KeyCode.UP);
        getInput().addAction(new DownKeyAction("DOWN", this), KeyCode.DOWN);
    }
}
