package game.tetris.block;

import game.tetris.datastructure.*;

import java.util.function.Consumer;

public class OBlock extends ServerBlock {
    public OBlock(int x, int y, TetrisGrid grid) {
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x+1,y);
        points[2] = new Point(x,y+1);
        points[3] = new Point(x+1,y+1);
        COLOR = TetrisColor.YELLOW;
        this.type = BlockType.OBLOCK;
    }

    @Override
    public Runnable rotate(Rotation dir) throws Exception{
        return () -> {};
    }

    @Override
    public boolean canRotate(Rotation dir) {
        return true;
    }
}
