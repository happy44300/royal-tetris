package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import game.tetris.datastructure.AbstractBlock;

import java.util.List;
import java.util.Map;

public class BasicUpdateHandler implements UpdateHandler {

    String id;

    TetrisApplication tetrisApplication;

    public BasicUpdateHandler(String id, TetrisApplication tetrisApplication) {
        this.id = id;
        this.tetrisApplication = tetrisApplication;
    }

    @Override
    public void blockDescentUpdate() {
        for(AbstractBlock block: this.tetrisApplication.getPlayerBlocks().values()){
            block.goDown();
        }
    }

    @Override
    public void provideStartingBlocks(Map<String, AbstractBlock> startingBlocks) {
        this.tetrisApplication.setPlayerBlocks(startingBlocks);
    }

    @Override
    public void blockUpdate(AbstractBlock block, String id) {
        //TODO: implement
    }

    @Override
    public void lockBlockUpdate(AbstractBlock lockedBlock, AbstractBlock newBlock) {
        //TODO: implement
    }

    @Override
    public void handleLineRemoval(List<Integer> linesToRemove) {
        //TODO: implement
    }

    @Override
    public void setGame(Game game) {
        tetrisApplication.setGame(game);
    }

}
