package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.texture.ColoredTexture;
import game.tetris.block.Block;
import game.tetris.grid.Grid;
import game.tetris.grid.Point;
import game.tetris.grid.TetrisColor;
import game.tetris.tetrominos.TetrominosTexture;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class TetrisApplication extends GameApplication implements ConnectionManager {

    Game clientToServer;

    String playerID;

    boolean isGameStarted = false;

    Grid grid;

    Map<String,Block> playerToBlock = new HashMap<>();

    public static final int CELL_SIZE = 20;

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
        settings.setWidth(800);
        settings.setHeight(800);
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
        //Set the background color to be black
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);

        connect();
    }
    @Override
    protected void initInput() {



        getInput().addAction(new LeftKeyAction("Move Left", this), KeyCode.LEFT);
        getInput().addAction(new RightKeyAction("Move Right", this), KeyCode.RIGHT);
        getInput().addAction(new UpKeyAction("Move UP", this), KeyCode.UP);
    }


    /**
     * Expensive method
     */

    void drawGridInRenderThread(){
        System.out.println("Drawing grid");

        GameWorld world = getGameWorld();

        world.removeEntities(new ArrayList<>(world.getEntities()));


        for(int x=0; x< grid.getCells().length; x++){
            for(int y = 0; y < grid.getCells()[x].length; y++){
                System.out.println("Drawing cell at " + x * (TetrisApplication.CELL_SIZE+1d) + " " +(y * TetrisApplication.CELL_SIZE+1d));

                var text = TetrominosTexture.RODBLOCK.getTexture().copy();
                text.setFitHeight(TetrisApplication.CELL_SIZE);
                text.setFitWidth(TetrisApplication.CELL_SIZE);
                text.preserveRatioProperty().setValue(true);
                 FXGL.entityBuilder().at(x * (TetrisApplication.CELL_SIZE+1d),(y * TetrisApplication.CELL_SIZE +1d))
                        .view(text)
                        .buildAndAttach();
            }
        }
    }

    @Override
    public void updateGrid(Grid updatedGrid) throws RemoteException {
        System.out.println("Received grid update");
        this.isGameStarted = true;
        this.grid = updatedGrid;
        Platform.runLater(this::drawGridInRenderThread);
    }

    @Override
    public void updateBlock(String playerID, Block updatedBlock) throws RemoteException {

        System.out.println("Updating block");

        this.isGameStarted = true;


        if (playerToBlock.containsKey(playerID)) {
            for (Point point : playerToBlock.get(playerID).getPoints()) {
                grid.getCell(point).setColor(TetrisColor.NOTHING);
            }


            for (Point point : updatedBlock.getPoints()) {
                grid.getCell(point).setColor(TetrisColor.BLUE);
            }
            playerToBlock.put(playerID, updatedBlock);
        }
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
