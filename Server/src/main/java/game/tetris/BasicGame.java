package game.tetris;

import game.tetris.block.OBlock;
import game.tetris.block.ServerBlock;
import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.ServerTetrisGrid;
import game.tetris.datastructure.TetrisGrid;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class BasicGame implements Game{

    private final Map<String, RemotePlayer> ipToPlayer;
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

    private List<Client> getClients(){
        List<Client> clients = new ArrayList<>();

        for(RemotePlayer rp: this.ipToPlayer.values()){
            clients.add(rp.getClient());
        }

        return clients;
    }

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
    public void submitGameAction(Runnable gameAction) throws RemoteException {
        synchronized (this.grid){
            this.grid.updateGrid(gameAction);

            try {
                handleGameActionConsequences();
            } catch (Exception e) {
                throw new RemoteException("Couldn't handle the consequences of game action!");
            }
        }
    }

    public void submitBlockDescent() {
        synchronized (this.grid){
            for (RemotePlayer p : this.ipToPlayer.values()) {
                p.getCurrentBlock().goDown();
            }

            try {
                handleGameActionConsequences();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleGameActionConsequences() throws Exception {
        try{
            RemotePlayer currentPlayer = this.ipToPlayer.get(RemoteServer.getClientHost());
            ServerBlock currentBlock = currentPlayer.getCurrentBlock();

            if(currentBlock.isDirectlyAboveLockedCell()){
                currentBlock.lockBlock();
                currentPlayer.setNewBlock(new OBlock(0,0, this.grid));
                this.grid.removeCompletedLines();
            }

        } catch (ServerNotActiveException e){
            throw new RemoteException("Couldn't find client ip!");
        }
    }

    private void play(){
        BasicGame game = this;
        Thread descentThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    game.submitBlockDescent();
                }
            }
        });

        descentThread.start();
    }

}
