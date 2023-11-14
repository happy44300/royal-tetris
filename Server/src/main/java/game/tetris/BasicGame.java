package game.tetris;

import game.tetris.datastructure.ServerTetrisGrid;
import game.tetris.datastructure.TetrisGrid;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BasicGame implements Game{

    final private List<Player> playerList;
    private final TetrisGrid grid;

    public BasicGame(List<Player> playerList){
        this.playerList = playerList;
        this.grid = new ServerTetrisGrid(20,40);
    }

    public TetrisGrid getGrid(){
        return this.grid;
    };

    @Override
    public void submitGameAction(Consumer<TetrisGrid> gameAction) {
        this.grid.updateGridSynchronously(gameAction);
    }


}
