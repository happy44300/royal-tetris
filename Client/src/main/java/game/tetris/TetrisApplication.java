package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import game.tetris.block.*;
import game.tetris.datastructure.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class TetrisApplication extends GameApplication {
    private final int ROWS = 20;
    private final int COLUMNS = 40;
    private String ip = "127.0.0.1";
    private String username = "Original And Unique ID !!!!!!";
    private String clientID = null;
    private Map<String, ClientBlock> playerBlocks = null;
    private Game game = null;
    private ClientTetrisGrid tetrisGrid = null;
    private UpdateHandler updateHandler = null;

    public static void main(String[] args) {

        launch(args);
        //Connecting with server, and setting up local endpoint for server to send updates
    }

    public ClientTetrisGrid getTetrisGrid() {
        return tetrisGrid;
    }

    public void setTetrisGrid(ClientTetrisGrid tetrisGrid) {
        this.tetrisGrid = tetrisGrid;
    }

    public Game getGame(){
        return this.game;
    }

    public void setGame(String ip, int port) throws RemoteException {
        Registry remoteRegistry = LocateRegistry.getRegistry(ip, port);
        Game game = null;
        try {
            game = (Game) remoteRegistry.lookup("Game");
        } catch (NotBoundException e) {
            throw new RemoteException();
        }

        this.game = game;
        System.out.println("GAME IS GAME: ");
        System.out.println(this.game);
    }

    public void addPlayerAndHisBlock(String playerKey, ClientBlock playerBlock) {
        this.playerBlocks.put(playerKey, playerBlock);
    }

    public void modifyPlayerBlock(String playerKey, ClientBlock newBlock) {
        this.playerBlocks.replace(playerKey, newBlock);
    }

    public Map<String, ClientBlock> getPlayerBlocks() {
        return playerBlocks;
    }

    public void setPlayerBlocks(Map<String, ClientBlock> playerBlocks) {
        this.playerBlocks = playerBlocks;
    }

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

        this.tetrisGrid = new ClientTetrisGrid(ROWS, COLUMNS);
        var background = FXGL.entityBuilder().
                at(0,-1,0)
                .view(new Rectangle(getAppWidth(),getAppHeight(), Color.BLACK))
                .buildAndAttach();

        for (Entity[] row : this.tetrisGrid.getGrid()) {
            for (Entity cell : row) {
                getGameWorld().addEntity(cell);
            }
        }

        connect();
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
            Lobby lobby = (Lobby) remoteRegistry.lookup("Lobby");

            Registry localRegistry = LocateRegistry.createRegistry(10001);

            UpdateHandler updateHandler = (UpdateHandler) UnicastRemoteObject.
                    exportObject(new BasicUpdateHandler(this), 10001);

            localRegistry.rebind("UpdateHandler", updateHandler);

            //logs is a list of two elements : an IP and an ID
            List<String> logs = lobby.join(username);

            System.setProperty("java.rmi.server.hostname", logs.get(0));

            updateHandler.setID(logs.get(1));

            this.updateHandler = updateHandler;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClientBlock createBlock(BlockBluePrint blockBluePrint){
        int x = blockBluePrint.getCoordinates().getX();
        int y = blockBluePrint.getCoordinates().getY();
        TetrisGrid grid = this.getTetrisGrid();

        switch (blockBluePrint.getType()){
            case IBLOCK -> {
                return new IBlock(x,y,grid);
            }
            case LBLOCK -> {
                return new LBlock(x,y,grid);
            }
            case LRBLOCK -> {
                return new LRBlock(x,y,grid);
            }
            case OBLOCK -> {
                return new OBlock(x,y,grid);
            }
            case SBLOCK -> {
                return new SBlock(x,y,grid);
            }
            case SRBLOCK -> {
                return new SRBlock(x,y,grid);
            }
            case TBLOCK -> {
                return new TBlock(x,y,grid);
            }
        }

        //Should never happen
        return null;
    }
}
