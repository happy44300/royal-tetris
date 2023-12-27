package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.ClientTetrisGrid;
import game.tetris.datastructure.Point;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class TetrisApplication extends GameApplication {
    private final int ROWS = 15;
    private final int COLLUMNS = 10;
    private String ip = null;
    private String username = null;
    private String clientID = null;
    private Map<String, AbstractBlock> playerBlocks = null;
    private Game game = null;
    private ClientTetrisGrid tetrisGrid = null;

    public TetrisApplication(String ip, String username, String clientID, String[] args) {
        this.ip = ip;
        this.username = username;
        this.clientID = clientID;

        launch(args);

        connect();
    }

    public ClientTetrisGrid getTetrisGrid() {
        return tetrisGrid;
    }

    public void setTetrisGrid(ClientTetrisGrid tetrisGrid) {
        this.tetrisGrid = tetrisGrid;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public void addPlayerAndHisBlock(String playerKey, AbstractBlock playerBlock) {
        this.playerBlocks.put(playerKey, playerBlock);
    }

    public void modifyPlayerBlock(String playerKey, AbstractBlock newBlock) {
        this.playerBlocks.replace(playerKey, newBlock);
    }

    public Map<String, AbstractBlock> getPlayerBlocks() {
        return playerBlocks;
    }

    public void setPlayerBlocks(Map<String, AbstractBlock> playerBlocks) {
        this.playerBlocks = playerBlocks;
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTicksPerSecond(1);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
    }

    @Override
    public void initGame() {

        this.tetrisGrid = new ClientTetrisGrid(ROWS, COLLUMNS);
        var background = FXGL.entityBuilder().
                at(0,-1,0)
                .view(new Rectangle(getAppWidth(),getAppHeight(), Color.BLACK))
                .buildAndAttach();

        for (Entity[] row : this.tetrisGrid.getGrid()) {
            for (Entity cell : row) {
                getGameWorld().addEntity(cell);
            }
        }

    }

    @Override
    protected void initInput() {
        Game game = this.game;
        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                try {
                    AbstractBlock block = game.getBlock();
                    Point position = block.getPosition();

                    game.submitGameAction(block.translate(new Point(position.getX(), position.getY()-1)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, KeyCode.LEFT);
        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                try {
                    AbstractBlock block = game.getBlock();
                    Point position = block.getPosition();

                    game.submitGameAction(block.translate(new Point(position.getX(), position.getY()+1)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, KeyCode.RIGHT);
        getInput().addAction(new UserAction("Move UP") {
            @Override
            protected void onActionBegin() {
                try {
                    AbstractBlock block = game.getBlock();
                    Point position = block.getPosition();

                    game.submitGameAction(block.rotate(block.getRotation()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, KeyCode.UP);

        //TODO: Add actions for other Tetris movements (right, rotate, etc.)
    }

    private void connect() {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(ip, 10000);
            Lobby lobby = (Lobby) remoteRegistry.lookup("Lobby");

            this.game = lobby.getGame();

            //logs in a list of two elements : an IP and an ID
            List<String> logs = lobby.join(username);

            System.setProperty("java.rmi.server.hostname", logs.get(0));

            Registry localRegistry = LocateRegistry.createRegistry(10000);

            UpdateHandler updateHandler = (UpdateHandler) UnicastRemoteObject.exportObject(new BasicUpdateHandler(logs.get(1), this), 10000);
            localRegistry.rebind("Client", updateHandler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
