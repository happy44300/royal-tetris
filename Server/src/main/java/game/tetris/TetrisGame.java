package game.tetris;

import game.tetris.action.PlayerAction;

import java.rmi.RemoteException;

public class TetrisGame implements Game{


    @Override
    public void submitBlockUpdate(String playerID, PlayerAction action) throws RemoteException {

    }

    @Override
    public String connect() {
        return null;
    }
}
