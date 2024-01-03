package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import org.jetbrains.annotations.NotNull;

public class RightKeyAction extends TetrisAction{
    public RightKeyAction(@NotNull String name, TetrisApplication tetrisApplication) {
        super(name, tetrisApplication);
    }
    @Override
    protected void onActionBegin() {
        try {
            Game game = tetrisApplication.getGame();
            AbstractBlock block = game.getBlock();
            Point position = block.getPosition();

            game.submitGameAction(block.translate(new Point(position.getX(), position.getY()+1)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
