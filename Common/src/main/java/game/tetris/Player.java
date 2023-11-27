package game.tetris;

import game.tetris.datastructure.AbstractBlock;

public interface Player {
    public String getName();
    public String getIP();

    AbstractBlock getCurrentBlock();
}
