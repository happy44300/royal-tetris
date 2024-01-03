package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import org.jetbrains.annotations.NotNull;

public class UpKeyAction extends TetrisAction{
    public UpKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }

    @Override
    protected void onActionBegin() {
        try {
            Game game = tetrisApplication.getGame();
            AbstractBlock block = game.getBlock();

            game.submitGameAction(block.rotate(block.getRotation()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
