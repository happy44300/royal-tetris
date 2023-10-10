package game.tetris;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Alain Defrance
 */
public class Client {
    public static void main(String[] argv) {
        try {
            Registry registry = LocateRegistry.getRegistry(10000);
            AddInterface stub = (AddInterface) registry.lookup("Add");
            System.out.println(stub.add(1, 2)); // Affiche 3
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
