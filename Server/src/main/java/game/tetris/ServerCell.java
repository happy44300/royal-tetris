package game.tetris;

import game.tetris.datastructure.Cell;
import game.tetris.datastructure.Point;

import java.rmi.RemoteException;

public class ServerCell implements Cell {
    @Override
    public void setPos(Point point) throws RemoteException {

    }

    @Override
    public Point getPos() throws RemoteException {
        return new Point(5, 6);
    }
}
