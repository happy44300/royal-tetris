package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import game.tetris.block.Block;
import game.tetris.grid.Cell;
import game.tetris.grid.Grid;
import javafx.scene.input.KeyCode;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class TetrisApplication extends GameApplication implements ConnectionManager {

    Game clientToServer;

    String playerID;

    boolean isGameStarted = false;

    Grid grid;

    Map<String,Block> playerToBlock = new HashMap<>();

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initGame() {
        connect();
    }
    @Override
    protected void initInput() {



        getInput().addAction(new LeftKeyAction("Move Left", this), KeyCode.LEFT);
        getInput().addAction(new RightKeyAction("Move Right", this), KeyCode.RIGHT);
        getInput().addAction(new UpKeyAction("Move UP", this), KeyCode.UP);
    }

    void drawGrid(){
        for(Cell[] rows : grid.getCells()){
            for(Cell cell : rows){

            }
        }
    }

    @Override
    public void updateGrid(Grid updatedGrid) throws RemoteException {
        this.isGameStarted = true;
        this.grid = updatedGrid;
        drawGrid();
    }

    @Override
    public void updateBlock(String playerID, Block updatedBlock) throws RemoteException {
        this.isGameStarted = true;
        this.playerToBlock.put(playerID,updatedBlock);
        drawGrid();
    }

    private void connect() {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(ip, 10000);
            Game game = (Game) remoteRegistry.lookup("TetrisGame");
            this.clientToServer = game;

            Registry localRegistry = LocateRegistry.createRegistry(10001);

            connectionManager = (ConnectionManager) UnicastRemoteObject.exportObject(this, 10001);

            localRegistry.rebind("ConnectionManager", connectionManager);

            this.playerID = game.connect();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
