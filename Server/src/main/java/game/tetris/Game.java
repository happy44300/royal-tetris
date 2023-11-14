package game.tetris;

import game.tetris.datastructure.ServerTetrisGrid;
import game.tetris.datastructure.TetrisGrid;

public class Game {

    private TetrisGrid grid;

    public Game(){
        this.grid = new ServerTetrisGrid(20,40);
    }

    public TetrisGrid getGrid(){
        return this.grid;
    };
}
