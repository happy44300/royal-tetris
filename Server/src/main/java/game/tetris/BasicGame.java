package game.tetris;

import game.tetris.block.ServerBlock;
import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.ServerTetrisGrid;
import game.tetris.datastructure.TetrisGrid;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BasicGame implements Game{

    private Map<String, RemotePlayer> ipToPlayer;
    private final TetrisGrid grid;

    public BasicGame(List<RemotePlayer> playerList){
        this.grid = new ServerTetrisGrid(20,40);

        this.ipToPlayer = new HashMap<>();
        for (RemotePlayer player : playerList) {
            this.ipToPlayer.put(player.getIP(), player);
        }

        this.play();
    }

    public TetrisGrid getGrid(){
        return this.grid;
    };

    @Override
    public AbstractBlock getBlock() throws RemoteException{
        return this.getServerBlock();
    }

    private ServerBlock getServerBlock() throws RemoteException {
        try{
            ServerBlock currentBlock = ((ServerBlock) this.ipToPlayer
                    .get(RemoteServer.getClientHost())
                    .getCurrentBlock());
            return currentBlock;
        } catch (Exception e){
            throw new RemoteException("Couldn't fetch current block for that player!");
        }
    }

    @Override
    public void submitGameAction(Consumer<TetrisGrid> gameAction) throws RemoteException {
        synchronized (this.grid){
            this.grid.updateGrid(gameAction);

            try{
                RemotePlayer currentPlayer = this.ipToPlayer.get(RemoteServer.getClientHost());
                ServerBlock currentBlock = ((ServerBlock) currentPlayer.getCurrentBlock());

                if(currentBlock.isDirectlyAboveLockedCell()){
                    currentBlock.lockBlock();
                    currentPlayer.setNewBlock();
                    this.grid.removeCompletedLines();
                }

            } catch (ServerNotActiveException e){
                throw new RemoteException("Couldn't find client ip!");
            }
        }
    }

    public void sendUpdateClient(RemotePlayer p) throws RemoteException {
        //TODO
    }

    public void submitBlockDescent(){
        synchronized (this.grid){
            for (RemotePlayer p : this.ipToPlayer.values()) {
                p.getCurrentBlock().goDown();
            }
        }
    }


    private void play(){
        while(true){
            //game loop, here we should add the forced block descent
            return;
        }
    }

}
