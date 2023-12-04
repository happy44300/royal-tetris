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
    public void translate(Point point) throws Exception {
        if(!canTranslate(point)) throw new Exception("can't translate this way!");

        if(point.getY() == this.points[0].getY()-1){
            for(Point p : this.points){
                p.move(-1,0);
            }
        }
        if(point.getY() == this.points[0].getY()+1){
            for(Point p : this.points){
                p.move(1,0);
            }
        }

        if(this.isDirectlyAboveLockedCell()){
            this.lockBlock();
            this.tetrisGrid.removeCompletedLines();
        }
    }

    void lockBlock() {
        //TODO: implement
        //Must lock block, and update give the player a new tetromino to play with
    }

    boolean isDirectlyAboveLockedCell() {
        //TODO: implement
        return  false;
    }

    @Override
    public boolean canTranslate(Point point) throws RemoteException {
        return this.tetrisGrid.updateGridSynchronously(grid -> {
            return canTranslateSafe(point, (ServerTetrisGrid) grid);
        });
    }

    private Boolean canTranslateSafe(Point point, ServerTetrisGrid grid) {
        if(grid.isNotInLimits(point)){
            return false;
        }

        if(point.getY() == this.points[0].getY()-1){
            return this.canTranslateLeft(grid);
        }

        if(point.getY() == this.points[0].getY()+1){
            return this.canTranslateRight(grid);
        }

        return false;
    }

    public boolean canTranslateLeft(ServerTetrisGrid grid){

        for(Point p : this.points){
            Point pointToCheck = new Point(p.getX(), p.getY()-1);

            // We check if there is not any point out of the grid
            if(grid.isNotInLimits(pointToCheck)){
                return false;
            }

            // We check if there is already a blocked block which prevents movement
            if(grid.isCellBlocked(pointToCheck)){
                return false;
            }
        }

        return true;
    }
    public boolean canTranslateRight(ServerTetrisGrid grid){
        for(Point p : this.points){
            Point pointToCheck = new Point(p.getX(), p.getY()+1);

            // We check if there is not any point out of the grid
            if(grid.isNotInLimits(pointToCheck)){
                return false;
            }

            // We check if there is already a blocked block which prevents movement
            if(grid.isCellBlocked(pointToCheck)){
                return false;
            }
        }

        return true;
    }

    // If a point isn't in the grid, return false.
    public boolean ArePointsInLimits(Point[] pointsToCheck) {

        for(Point p : pointsToCheck){
            if(((ServerTetrisGrid) this.tetrisGrid).isNotInLimits(p)){
                return false;
            }
        }
        return true;
    }

    // If a point indicates a blocked cell in the grid, return false.
    public boolean ArePointsNotOnBlockedCells(Point[] pointsToCheck) {

        for(Point p : pointsToCheck){
            if(((ServerTetrisGrid) this.tetrisGrid).isCellBlocked(p)){
                return false;
            }
        }
        return true;
    }
}
