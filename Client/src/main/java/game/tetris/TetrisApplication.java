package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.texture.Texture;
import game.tetris.action.DownKeyAction;
import game.tetris.action.LeftKeyAction;
import game.tetris.action.RightKeyAction;
import game.tetris.action.UpKeyAction;
import game.tetris.block.Block;
import game.tetris.grid.Cell;
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
    private final String ip = "127.0.0.1";
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
        getInput().addAction(new DownKeyAction("Move Down", this), KeyCode.DOWN);
    }



    private static void render(Texture text, int x, int y) {
        text.setFitHeight(TetrisApplication.CELL_SIZE);
        text.setFitWidth(TetrisApplication.CELL_SIZE);
        text.preserveRatioProperty().setValue(true);
        FXGL.entityBuilder().at(x * (TetrisApplication.CELL_SIZE+1d),(y * TetrisApplication.CELL_SIZE +1d))
                .view(text)
                .buildAndAttach();
    }

    /**
     * Expensive method
     */
    void drawGridInRenderThread(){
        System.out.println("Drawing grid");

        GameWorld world = getGameWorld();

        world.removeEntities(new ArrayList<>(world.getEntities()));

        Cell[][] cells = grid.getCells();

        Cell[] row;
        for(int y=0; y< cells.length; y++){
            row = cells[y];
            for(int x = 0; x < row.length; x++){
                var text = TetrominosTexture.tetrisColorToTexture(cells[y][x].getColor()).copy();
                render(text, x, y);
            }
        }
    }

    @Override
    public void updateGrid(Grid updatedGrid) throws RemoteException {
        System.out.println("Received grid update");
        this.grid = updatedGrid;
        Platform.runLater(this::drawGridInRenderThread);
        this.isGameStarted = true;
    }

    void drawBlockInRenderThread(Point[] pointsToRemove, Block updatedBlock){
        System.out.println("Drawing block");

        int x, y;

        for(Point p : pointsToRemove){
            x = p.getX();
            y = p.getY();

            var text = TetrominosTexture.tetrisColorToTexture(TetrisColor.NOTHING).copy();
            render(text, x, y);
        }

        Point[] pointsToRender = updatedBlock.getPoints();

        for(Point p : pointsToRender){
            x = p.getX();
            y = p.getY();
            var text = TetrominosTexture.tetrisColorToTexture(updatedBlock.getColor()).copy();
            render(text, x, y);
        }
    }

    @Override
    public void updateBlock(String playerID, Block updatedBlock) throws RemoteException {

        System.out.println("Received block update");
        Point[] pointsToRemove;
        if (playerToBlock.containsKey(playerID)) {
            pointsToRemove = playerToBlock.get(playerID).getPoints();
        } else {
            pointsToRemove = new Point[0];
        }

        playerToBlock.put(playerID, updatedBlock);

        Platform.runLater(() -> drawBlockInRenderThread(pointsToRemove, updatedBlock));

        this.isGameStarted = true;
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

    public boolean isGameStarted() {
        return isGameStarted;
    }
}
