package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;

public class BasicLobby implements Lobby {

    List<Player> playerList = new ArrayList<>();
    Game game;

    @Override
    public void join(String playerName) throws RemoteException {

        System.out.println("IP OF CLIENT : ");
        try {
            String playerIP = RemoteServer.getClientHost();
            System.out.println(playerIP);
            playerList.add(new RemotePlayer(playerName, playerIP));
        } catch (ServerNotActiveException e) {
            throw new RuntimeException(e);
        }

        //TODO: Finish constructor
    }

    @Override
    public void start() throws RemoteException{
        System.out.println("starting game with :");
        for(Player p: this.playerList){
            System.out.println(p.getName());
        }
        this.game = new Game();
    }

    @Override
    public TetrisGrid getGrid() throws RemoteException{
        //TODO: Implement game's tetris grid getter
        return this.game.getGrid();
    }
}
