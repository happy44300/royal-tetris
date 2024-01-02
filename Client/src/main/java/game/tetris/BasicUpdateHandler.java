package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.TetrisGrid;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
    public void provideStartingBlocks(Map<String, AbstractBlock> startingBlocks) throws RemoteException {
        this.tetrisApplication.setPlayerBlocks(startingBlocks);
    }

    @Override
    public void blockUpdate(AbstractBlock block, String id) throws RemoteException {
        this.tetrisApplication.getPlayerBlocks().replace(id, block);
    }

    @Override
    public void lockBlockUpdate(AbstractBlock lockedBlock, AbstractBlock newBlock, String id) throws RemoteException {
        this.tetrisApplication.getPlayerBlocks().get(id).block();
        this.tetrisApplication.getPlayerBlocks().replace(id, newBlock);
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
        System.out.println("0");
        Registry remoteRegistry = LocateRegistry.getRegistry(ip, 10002);
        System.out.println("1");
        Game game = null;
        try {
            game = (Game) remoteRegistry.lookup("Game");
        } catch (NotBoundException e) {
            throw new RemoteException();
        }
        System.out.println("2");
        tetrisApplication.setGame(game);
        System.out.println("3");
    }

    @Override
    public void setID(String ID) throws RemoteException {
        this.id = ID;
    }


}
