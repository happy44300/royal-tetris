package game.tetris;

import game.tetris.block.ClientBlock;
import game.tetris.datastructure.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicUpdateHandler implements UpdateHandler {

    String id;

    TetrisApplication tetrisApplication;

    public BasicUpdateHandler(String id, TetrisApplication tetrisApplication) {
        this.id = id;
        this.tetrisApplication = tetrisApplication;
    }

    public BasicUpdateHandler(TetrisApplication tetrisApplication) {
        this.tetrisApplication = tetrisApplication;
    }

    @Override
    public void blockDescentUpdate() throws RemoteException {
        for(AbstractBlock block: this.tetrisApplication.getPlayerBlocks().values()){
            block.goDown();
        }
    }

    @Override
    public void provideStartingBlocks(Map<String, BlockBluePrint> startingBlocksBlueprints) throws RemoteException {
        Map<String, ClientBlock> startingBlocks = new HashMap<>();

        for(String key: startingBlocksBlueprints.keySet()){
            startingBlocks.put(key, this.tetrisApplication.createBlock(startingBlocksBlueprints.get(key)));
        }

        this.tetrisApplication.setPlayerBlocks(startingBlocks);
    }

    @Override
    public void blockUpdate(BlockBluePrint updatedBlock, String id) throws RemoteException {
        this.tetrisApplication.getPlayerBlocks().replace(id, this.tetrisApplication.createBlock(updatedBlock));
    }

    @Override
    public void lockBlockUpdate(BlockBluePrint newBlock, String id) throws RemoteException {
        this.tetrisApplication.getPlayerBlocks().get(id).block();
        this.tetrisApplication.getPlayerBlocks().replace(id, this.tetrisApplication.createBlock(newBlock));
    }

    @Override
    public void handleLineRemoval(List<Integer> linesToRemove) throws RemoteException {
        TetrisGrid grid = this.tetrisApplication.getTetrisGrid();

        for(Integer line : linesToRemove){
            grid.removeLine(line);
        }
    }

    @Override
    public void setGamePort(int port, String ip) throws RemoteException {

        tetrisApplication.setGame(ip, port);
    }

    @Override
    public void setID(String ID) throws RemoteException {
        this.id = ID;
    }
}
