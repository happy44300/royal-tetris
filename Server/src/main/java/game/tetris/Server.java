package game.tetris;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {


    private static final int ROWS = 15;
    private static final int COLLUMNS = 10;
    private static String ip = null;
    private static String username = null;
    private static String clientID = null;


    public static void main(String[] argv) {
        try {
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            Registry registry = LocateRegistry.createRegistry(10000);

            Lobby lobby = (Lobby) UnicastRemoteObject.exportObject(new BasicLobby(), 10000);
            registry.rebind("Lobby", lobby);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
