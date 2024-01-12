package game.tetris;

import game.tetris.action.DownKeyAction;
import game.tetris.action.LeftKeyAction;
import game.tetris.action.RightKeyAction;
import game.tetris.action.UpKeyAction;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class Client2 extends TetrisApplication{

    public Client2(){
        this.setClientPort(10002);
    }
    public static void main(String[] args) {
        launch(Client2.class, args);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new LeftKeyAction("MLEFT", this), KeyCode.Q);
        getInput().addAction(new RightKeyAction("MRIGHT", this), KeyCode.D);
        getInput().addAction(new UpKeyAction("MUP", this), KeyCode.Z);
        getInput().addAction(new DownKeyAction("MDOWN", this), KeyCode.S);
    }
}
