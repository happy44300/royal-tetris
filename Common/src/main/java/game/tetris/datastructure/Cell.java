package game.tetris.datastructure;

import java.rmi.Remote;

public interface Cell extends Remote {

    void setPos(Point point);

    Point getPos();

}
