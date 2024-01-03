package game.tetris;

import java.io.Serializable;

interface Grid extends Serializable {
    Cell getCell(Point point);

    Cell[][] getCells();

    int getNumberOfRows();

    int getNumberOfColumns();
}
