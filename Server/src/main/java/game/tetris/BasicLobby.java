package game.tetris;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
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

                Registry remoteRegistry = LocateRegistry.getRegistry(playerIP, 10001);
                UpdateHandler playerUpdateHandler = (UpdateHandler) remoteRegistry.lookup("UpdateHandler");

                playerID = String.valueOf(this.playerList.size()+1);

                playerList.add(new RemotePlayer(playerName, playerIP, playerUpdateHandler, playerID));
            } catch (ServerNotActiveException | NotBoundException e) {
                throw new RuntimeException(e);
            }
        }

        if(this.playerList.size() == 1){
            this.start();
        }

        if(this.playerList.size() > 4){
            throw new RemoteException("Too many players!");
        }

        return Arrays.asList(playerIP, playerID);
    }

    @Override
    public void start() throws RemoteException{
        Registry registry = LocateRegistry.createRegistry(10002);

        Game game = (Game) UnicastRemoteObject.exportObject(new BasicGame(playerList), 10002);
        registry.rebind("Game", game);

        this.game = game;

        System.out.println("starting game with :");

        for(RemotePlayer p: this.playerList){
            System.out.println(p.getName());
            p.getUpdateHandler().setGamePort(10002, "127.0.0.1");
        }

        this.game.play();
    }
}
