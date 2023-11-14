package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class BasicLobby implements Lobby {

    List<Player> playerList = new ArrayList<>();
    Game game;

    @Override
    public void join(String playerName) throws RemoteException {
        playerList.add(new BasicPlayer(playerName));
        //TODO: Finish constructor
    }

    @Override
    public void start() throws RemoteException{
        this.game = new Game();
    }

    @Override
    public TetrisGrid getGrid() throws RemoteException{
        //TODO: Implement game's tetris grid getter
        return this.game.getGrid();
    }
}
