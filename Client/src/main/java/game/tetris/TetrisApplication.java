package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.input.KeyCode;
import game.tetris.TetrisGame;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class TetrisApplication extends GameApplication {

    Game clientToServer;

    String playerID;

    public String getPlayerID() {
        return playerID;
    }

    public Game getClientToServer() {
        return clientToServer;
    }

    ConnectionManager connectionManager;
    private String ip = "127.0.0.1";
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

    }
    @Override
    protected void initInput() {
        getInput().addAction(new LeftKeyAction("Move Left", this), KeyCode.LEFT);
        getInput().addAction(new RightKeyAction("Move Right", this), KeyCode.RIGHT);
        getInput().addAction(new UpKeyAction("Move UP", this), KeyCode.UP);
    }

    private void connect() {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(ip, 10000);
            TetrisGame game = (TetrisGame) remoteRegistry.lookup("TetrisGame");

            Registry localRegistry = LocateRegistry.createRegistry(10001);

            connectionManager = (ConnectionManager) UnicastRemoteObject.
                    exportObject(new ConnectionManagerClient(), 10001);

            localRegistry.rebind("ConnectionManager", connectionManager);

            /*//logs is a list of two elements : an IP and an ID
            List<String> logs = game.connect();

            System.setProperty("java.rmi.server.hostname", logs.get(0));

            updateHandler.setID(logs.get(1));

            this.updateHandler = updateHandler;*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
