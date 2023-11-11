package game.tetris.block;

import game.tetris.datastructure.*;
import game.tetris.tetrominos.TetrominosTexture;

import java.rmi.RemoteException;
import java.security.InvalidParameterException;

public class OBlock extends AbstractBlock {


    Point[] points = new Point[4];
    private static final TetrisColor COLOR = TetrisColor.YELLOW;

    public OBlock(int x, int y, TetrisGrid grid) {
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x+1,y);
        points[2] = new Point(x,y+1);
        points[3] = new Point(x+1,y+1);
        try {
            paint();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    private void paint() throws RemoteException {
        this.tetrisGrid.updateGridSynchronously(
                safeGrid-> {
                    try {
                        if (!canTranslateUnsafe(new Point(0,0), safeGrid)) {
                            throw new RuntimeException("Cannot draw cell on occupied cell");
                        }
                        for (Point point : points) {
                            safeGrid.getCell(point).setColor(COLOR);
                        }

                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public void translate(Point toPoint) throws RemoteException {
        this.tetrisGrid.updateGridSynchronously(
                safeGrid-> {
                    try {
                        /*if (!canTranslateUnsafe(toPoint, safeGrid)) {
                            return;
                        }*/
                        for (Point point : points) {
                            safeGrid.getCell(point).setColor(TetrisColor.NOTHING);
                        }
                        for (Point point : points) {
                            var gridPoint = safeGrid.getCell(point.add(toPoint));
                            gridPoint.setColor(COLOR);
                        }

                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }


    @Override
    public void rotate(Rotation dir) {
        //null op
    }

    private boolean canTranslateUnsafe(Point toPoint, TetrisGrid grid) throws RemoteException {
        for (Point point : points) {
            var neededPoint = grid.getCell(point.add(toPoint));
            if(neededPoint.getColor() != TetrisColor.NOTHING){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canTranslate(Point point) throws RemoteException {
        return tetrisGrid.updateGridSynchronously(grid -> {
            try {
                return canTranslateUnsafe(point,grid);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean canRotate(Rotation dir) {
        return true;
    }
}
