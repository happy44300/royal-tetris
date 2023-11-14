package game.tetris;
import game.tetris.datastructure.Cell;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Alain Defrance
 */
public class Client {
    public static void main(String[] argv) {
        try {
            Registry registry = LocateRegistry.getRegistry("172.16.134.156", 10000);
            // AddInterface stub = (AddInterface) registry.lookup("Add");
            // Cell stub = (Cell) registry.lookup("Cell");
            //System.out.println(stub.getPos().getX()); // Affiche 3
            Lobby stub = (Lobby) registry.lookup("Lobby");
            stub.join("Georges Abitbol");
            stub.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
