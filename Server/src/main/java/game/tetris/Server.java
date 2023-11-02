package game.tetris;

import game.tetris.datastructure.Cell;
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

            TetrisGrid grid = (TetrisGrid) UnicastRemoteObject.exportObject(new ServerTetrisGrid(), 10000);
            Cell cell = (Cell) UnicastRemoteObject.exportObject(new ServerCell(), 10000);

            Registry registry = LocateRegistry.createRegistry(10000);
            registry.rebind("Grid", grid);
            registry.rebind("Cell", cell);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
