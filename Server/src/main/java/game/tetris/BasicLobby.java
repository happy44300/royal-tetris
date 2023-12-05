package game.tetris;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;

public class BasicLobby implements Lobby {

    List<Player> playerList = new ArrayList<>();
    BasicGame game;

    @Override
    public void join(String playerName) throws RemoteException {

        synchronized (this){
            System.out.println("IP OF CLIENT : ");
            try {
                String playerIP = RemoteServer.getClientHost();
                System.out.println(playerIP);
                playerList.add(new RemotePlayer(playerName, playerIP));
            } catch (ServerNotActiveException e) {
                throw new RuntimeException(e);
            }
        }

        if(this.playerList.size() == 4){
            this.start();
        }

        if(this.playerList.size() > 4){
            throw new RemoteException("Too many players!");
        }


        //TODO: Finish constructor
    }

    @Override
    public void start() throws RemoteException{
        System.out.println("starting game with :");
        for(Player p: this.playerList){
            System.out.println(p.getName());
        }
        this.game = new BasicGame(playerList);
    }

    @Override
    public Game getGame() throws RemoteException {
        return this.game;
    }
}
