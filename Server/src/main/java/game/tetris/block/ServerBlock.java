package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public abstract class ServerBlock extends AbstractBlock {

    Point[] points = new Point[4];
    TetrisColor COLOR;

    public ServerBlock(int x, int y, ServerTetrisGrid tetrisGrid) {
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
            return canTranslateSafe(point, (ServerTetrisGrid) grid);
        });
    }

    private Boolean canTranslateSafe(Point point, ServerTetrisGrid grid) {
        if(!grid.isInLimits(point)){
            return false;
        }

        if(point.getX() == this.points[0].getX()-1){
            return this.canTranslateLeft(grid);
        }

        if(point.getX() == this.points[0].getX()+1){
            return this.canTranslateRight(grid);
        }

        return false;
    }

    public boolean canTranslateLeft(ServerTetrisGrid grid){

        for(Point p : this.points){
            Point pointToVerif = new Point(p.getX()-1, p.getY());

            // We check if there is not any point out of the grid
            if(!grid.isInLimits(pointToVerif)){
                return false;
            }

            // We check if there is already a blocked block which prevents movement
            if(grid.isCellBlocked(pointToVerif)){
                return false;
            }
        }

        return true;
    }
    public boolean canTranslateRight(ServerTetrisGrid grid){
        for(Point p : this.points){
            Point pointToVerif = new Point(p.getX()+1, p.getY());

            // We check if there is not any point out of the grid
            if(!grid.isInLimits(pointToVerif)){
                return false;
            }

            // We check if there is already a blocked block which prevents movement
            if(grid.isCellBlocked(pointToVerif)){
                return false;
            }
        }

        return true;
    }
}
