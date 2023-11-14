package game.tetris;

import game.tetris.datastructure.Cell;
import game.tetris.datastructure.ServerCell;
import game.tetris.datastructure.ServerTetrisGrid;
import game.tetris.datastructure.TetrisGrid;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] argv) {
        try {
            // 10000 est le port sur lequel sera publié le service. Nous devons le préciser à la fois sur le registry et à la fois à la création du stub.
            // AddInterface skeleton = (AddInterface) UnicastRemoteObject.exportObject(new AddImpl(), 10000); // Génère un stub vers notre service
            // Registry registry = LocateRegistry.createRegistry(10000);
            // registry.rebind("Add", skeleton); // publie notre instance sous le nom "Add"

            TetrisGrid grid = (TetrisGrid) UnicastRemoteObject.exportObject(new ServerTetrisGrid(20, 40), 10000);

            Registry registry = LocateRegistry.createRegistry(10000);
            registry.rebind("Grid", grid);

            Lobby lobby = (Lobby) UnicastRemoteObject.exportObject(new BasicLobby(), 10000);
            registry.rebind("Lobby", lobby);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
