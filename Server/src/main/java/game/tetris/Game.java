package game.tetris;

import game.tetris.datastructure.ServerTetrisGrid;
import game.tetris.datastructure.TetrisGrid;

public class Game {
    public TetrisGrid getGrid(){
        return new ServerTetrisGrid(20, 40);
    };
}
