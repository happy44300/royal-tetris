package game.tetris;

import java.io.Serializable;

public class Block implements Serializable {
    Point origin;
    Orientation orientation;
    BlockType type;
}
