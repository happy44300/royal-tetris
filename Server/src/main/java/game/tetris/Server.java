package game.tetris;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] argv) {
        try {
            System.setProperty("java.rmi.server.hostname","192.168.1.2");
            Registry registry = LocateRegistry.createRegistry(10000);

            Lobby lobby = (Lobby) UnicastRemoteObject.exportObject(new BasicLobby(), 10000);
            registry.rebind("Lobby", lobby);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
