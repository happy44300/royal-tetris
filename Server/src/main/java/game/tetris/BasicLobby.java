package game.tetris;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;

public class BasicLobby implements Lobby {

    List<RemotePlayer> playerList = new ArrayList<>();
    BasicGame game;

    @Override
    public String join(String playerName) throws RemoteException {
        String playerIP;
        synchronized (this){
            System.out.println("IP OF CLIENT : ");

            try {
                playerIP = RemoteServer.getClientHost();
                System.out.println(playerIP);


                Registry remoteRegistry = LocateRegistry.getRegistry(playerIP, 10000);
                Client playerClient = (Client) remoteRegistry.lookup("Client");

                playerList.add(new RemotePlayer(playerName, playerIP, playerClient));
            } catch (ServerNotActiveException | NotBoundException e) {
                throw new RuntimeException(e);
            }
        }

        if(this.playerList.size() == 4){
            this.start();
        }

        if(this.playerList.size() > 4){
            throw new RemoteException("Too many players!");
        }

        return playerIP;
    }

    @Override
    public void start() throws RemoteException{
        System.out.println("starting game with :");
        for(RemotePlayer p: this.playerList){
            System.out.println(p.getName());
        }
        this.game = new BasicGame(playerList);
    }

    @Override
    public Game getGame() throws RemoteException {
        return this.game;
    }
}
