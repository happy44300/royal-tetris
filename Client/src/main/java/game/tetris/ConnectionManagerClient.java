package game.tetris;

import game.tetris.block.Block;
import game.tetris.grid.Grid;

import java.rmi.RemoteException;

public class ConnectionManagerClient implements ConnectionManager{

    public ConnectionManagerClient(){

    }
    //ça sert de passe plat pour le connect coté serveur
    public void connect(){

    }

    @Override
    public void updateGrid(Grid updatedGrid) throws RemoteException {

    }

    @Override
    public void updateBlock(String playerID, Block updatedBlock) {

    }
}
