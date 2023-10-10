package game.tetris;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alain Defrance
 */
public interface AddInterface extends Remote {
    public Integer add(Integer nb1, Integer nb2) throws RemoteException;
}