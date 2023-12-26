package game.tetris;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicLobby implements Lobby {

    List<RemotePlayer> playerList = new ArrayList<>();
    Game game;

    @Override
    public List<String> join(String playerName) throws RemoteException {
        String playerIP;
        String playerID;
        synchronized (this){
            System.out.println("IP OF CLIENT : ");

            try {
                playerIP = RemoteServer.getClientHost();
                System.out.println(playerIP);


                Registry remoteRegistry = LocateRegistry.getRegistry(playerIP, 10000);
                UpdateHandler playerUpdateHandler = (UpdateHandler) remoteRegistry.lookup("Client");

                playerID = String.valueOf(this.playerList.size()+1);

                playerList.add(new RemotePlayer(playerName, playerIP, playerUpdateHandler, playerID));
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

        return Arrays.asList(playerIP, playerID);
    }

    @Override
    public void start() throws RemoteException{
        this.game = new BasicGame(playerList);

        System.out.println("starting game with :");
        for(RemotePlayer p: this.playerList){
            System.out.println(p.getName());
            p.getUpdateHandler().setGame(this.game);
        }

        this.game.play();
    }

    @Override
    public Game getGame() throws RemoteException {
        return this.game;
    }
}
