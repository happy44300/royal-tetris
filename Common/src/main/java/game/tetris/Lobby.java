package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.rmi.Remote;

public interface Lobby extends Remote {
    void join(String playerName);
    void start();
    TetrisGrid getGrid();
}
