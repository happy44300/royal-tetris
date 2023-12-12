package game.tetris.block;

import game.tetris.datastructure.*;

public abstract class ServerBlock extends AbstractBlock {

    TetrisColor COLOR;

    public ServerBlock(int x, int y, TetrisGrid tetrisGrid) {
        super(x, y, tetrisGrid);
    }

    @Override
    public Runnable translate(Point point) {
        return () -> {
            if(!canTranslate(point)) return;

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
        };
    }

    public void lockBlock() {
        this.block();
        for (Point p : this.points){
            ((ServerCell) this.tetrisGrid.getCell(p)).block();
        }
    }

    public boolean isDirectlyAboveLockedCell() {
        for (Point p : this.points){
            Point pointToCheck = new Point(p.getX()+1, p.getY());
            if(((ServerTetrisGrid) this.tetrisGrid).isCellBlocked(pointToCheck)){
                return true;
            }
        }
        return false;
    }

    public boolean canTranslate(Point point) {
        if(((ServerTetrisGrid) this.tetrisGrid).isNotInLimits(point)){
            return false;
        }

        if(point.getY() == this.points[0].getY()-1){
            return this.canTranslateLeft();
        }

        if(point.getY() == this.points[0].getY()+1){
            return this.canTranslateRight();
        }

        return false;
    }

    public boolean canTranslateLeft(){

        for(Point p : this.points){
            Point pointToCheck = new Point(p.getX(), p.getY()-1);

            // We check if there is not any point out of the grid
            if(((ServerTetrisGrid) this.tetrisGrid).isNotInLimits(pointToCheck)){
                return false;
            }

            // We check if there is already a blocked block which prevents movement
            if(((ServerTetrisGrid) this.tetrisGrid).isCellBlocked(pointToCheck)){
                return false;
            }
        }

        return true;
    }
    public boolean canTranslateRight(){
        for(Point p : this.points){
            Point pointToCheck = new Point(p.getX(), p.getY()+1);

            // We check if there is not any point out of the grid
            if(((ServerTetrisGrid) this.tetrisGrid).isNotInLimits(pointToCheck)){
                return false;
            }

            // We check if there is already a blocked block which prevents movement
            if(((ServerTetrisGrid) this.tetrisGrid).isCellBlocked(pointToCheck)){
                return false;
            }
        }

        return true;
    }

    // If a point isn't in the grid, return false.
    public boolean ArePointsWithinBounds(Point[] pointsToCheck) {

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
