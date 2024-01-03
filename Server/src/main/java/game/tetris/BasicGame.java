package game.tetris;

import game.tetris.block.IBlock;
import game.tetris.block.OBlock;
import game.tetris.block.ServerBlock;
import game.tetris.datastructure.*;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BasicGame implements Game{

    private final Map<String, RemotePlayer> ipToPlayer;
    private final TetrisGrid grid;

    public BasicGame(List<RemotePlayer> playerList){
        this.grid = new ServerTetrisGrid(20,40);

        this.ipToPlayer = new HashMap<>();
        for (RemotePlayer player : playerList) {
            this.ipToPlayer.put(player.getIP(), player);
        }
    }

    private List<UpdateHandler> getClients(){
        List<UpdateHandler> updateHandlers = new ArrayList<>();

        for(RemotePlayer rp: this.ipToPlayer.values()){
            updateHandlers.add(rp.getUpdateHandler());
        }

        return updateHandlers;
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
                handlePlayerActionConsequences();
                RemotePlayer player = this.ipToPlayer.get(RemoteServer.getClientHost());
                AbstractBlock block = player.getCurrentBlock();
                String id = player.getGameID();

                for(UpdateHandler c: getClients()){
                    c.blockUpdate(toBluePrint(block), id);
                }
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
                for(UpdateHandler c: getClients()){
                    c.blockDescentUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleGameActionConsequences() throws RemoteException, ServerNotActiveException {

        Collection<RemotePlayer> players = this.ipToPlayer.values();

        for(RemotePlayer p : players){
            ServerBlock currentBlock = p.getCurrentBlock();

            if(currentBlock.isDirectlyAboveLockedCell()){
                currentBlock.lockBlock();
                AbstractBlock newBlock = new OBlock(0,0, this.grid);
                p.setNewBlock((ServerBlock) newBlock);
                List<Integer> removedLines = this.grid.removeCompletedLines();

                for(UpdateHandler c: getClients()){
                    c.lockBlockUpdate(toBluePrint(newBlock), this.ipToPlayer.get(p.getIP()).getGameID());

                    if(!removedLines.isEmpty()){
                        c.handleLineRemoval(removedLines);
                    }
                }
            }
        }
    }

    private void handlePlayerActionConsequences() throws RemoteException {
        try{
            RemotePlayer currentPlayer = this.ipToPlayer.get(RemoteServer.getClientHost());
            ServerBlock currentBlock = currentPlayer.getCurrentBlock();

            if(currentBlock.isDirectlyAboveLockedCell()){
                currentBlock.lockBlock();
                AbstractBlock newBlock = new IBlock(0,0, this.grid);
                currentPlayer.setNewBlock((ServerBlock) newBlock);
                List<Integer> removedLines = this.grid.removeCompletedLines();

                for(UpdateHandler c: getClients()){
                    c.lockBlockUpdate(toBluePrint(newBlock), this.ipToPlayer.get(RemoteServer.getClientHost()).getGameID());

                    if(!removedLines.isEmpty()){
                        c.handleLineRemoval(removedLines);
                    }
                }
            }

        } catch (ServerNotActiveException e){
            throw new RemoteException("Couldn't find client ip!");
        }
    }

    @Override
    public void play() throws RemoteException {
        BasicGame game = this;

        Map<String, BlockBluePrint> startingBlocks = new HashMap<>();

        int gridOffset = 0;

        for(RemotePlayer p: this.ipToPlayer.values()){
            ServerBlock newServerBlock = new OBlock(0,0 + gridOffset, this.grid);
            p.setCurrentBlock(newServerBlock);
            startingBlocks.put(p.getGameID(), toBluePrint(newServerBlock));
            gridOffset += 3;
        }

        for(UpdateHandler uh: this.getClients()){
            uh.provideStartingBlocks(startingBlocks);
        }

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

    private BlockBluePrint toBluePrint(AbstractBlock block){
        return new BlockBluePrint(block.getBlockType(), block.getRotation(), block.getPosition());
    }

}
