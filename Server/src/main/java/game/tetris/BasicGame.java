package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.ServerTetrisGrid;
import game.tetris.datastructure.TetrisGrid;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BasicGame implements Game{

    private Map<String, Player> ipToPlayer;
    private final TetrisGrid grid;

    public BasicGame(List<Player> playerList){
        this.grid = new ServerTetrisGrid(20,40);

        this.ipToPlayer = new HashMap<>();
        for(int i = 0; i < playerList.size(); i++){
            this.ipToPlayer.put(playerList.get(i).getIP(), playerList.get(i));
        }

        this.play();
    }

    public TetrisGrid getGrid(){
        return this.grid;
    };

    public AbstractBlock getBlock() throws RemoteException{
        try{
            return this.ipToPlayer.get(RemoteServer.getClientHost()).getCurrentBlock();
        } catch (Exception e){
            throw new RemoteException("Couldn't fetch current block for that player!");
        }
    }

    @Override
    public void submitGameAction(Consumer<TetrisGrid> gameAction) throws RemoteException {
        this.grid.updateGridSynchronously(gameAction);
    }


    private void play(){
        while(true){
            //game loop, here we should add the forced block descent
            return;
        }
    }

}
