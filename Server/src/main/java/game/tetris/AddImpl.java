package game.tetris;

import java.rmi.RemoteException;

/**
 * @author Alain Defrance
 */
public class AddImpl implements AddInterface {
    public Integer add(Integer nb1, Integer nb2) throws RemoteException {
        return nb1 + nb2;
    }
}
