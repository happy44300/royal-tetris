package game.tetris.block;

import game.tetris.BlockType;
import game.tetris.Orientation;
import game.tetris.Point;

import java.io.Serializable;

public abstract class Block implements Serializable {
    Point origin;
    Orientation orientation = Orientation.UP;
    BlockType type;

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public abstract Point[] getPoints();
}
