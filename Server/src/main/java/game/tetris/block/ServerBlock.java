package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public abstract class ServerBlock extends AbstractBlock {

    Point[] points = new Point[4];
    TetrisColor COLOR;

    public ServerBlock(int x, int y, TetrisGrid tetrisGrid) {
        super(x, y, tetrisGrid);
    }


    @Override
    public void translate(Point point) throws RemoteException {
        if(point.getX() == this.points[0].getX()-1){
            for(Point p : this.points){
                p.move(-1,0);
            }
        }
        if(point.getX() == this.points[0].getX()+1){
            for(Point p : this.points){
                p.move(1,0);
            }
        }
    }


    @Override
    public boolean canTranslate(Point point) throws RemoteException {
        return this.tetrisGrid.updateGridSynchronously(grid -> {
            return canTranslateSafe(point,grid);
        });
    }

    private Boolean canTranslateSafe(Point point, TetrisGrid grid) {
        if(!grid.isInLimits(point)){
            return false;
        }

        if(point.getX() == this.points[0].getX()-1){
            return this.canTranslateLeft(point, grid);
        }

        if(point.getX() == this.points[0].getX()+1){
            return this.canTranslateRight(point, grid);
        }

        return false;
    }

    public abstract boolean canTranslateLeft(Point point, TetrisGrid grid);
    public abstract boolean canTranslateRight(Point point, TetrisGrid grid);
}
