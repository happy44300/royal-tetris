package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public abstract class ClientBlock extends AbstractBlock {
    TetrisColor COLOR;
    Rotation rotation;
    public ClientBlock(int x, int y, TetrisGrid grid){
        super(x, y, grid);
        rotation = Rotation.LEFT;
    }

    public Runnable paint() throws RemoteException {
        return () -> {
            if (!canTranslate(new Point(0,0))) {
                throw new RuntimeException("Cannot draw cell on occupied cell");
            }
            for (Point point : points) {
                this.tetrisGrid.getCell(point).setColor(COLOR);
            }
        };

    }

    @Override
    public Runnable translate(Point toPoint) throws RemoteException {
        return () -> {
            for (Point point : points) {
                this.tetrisGrid.getCell(point).setColor(TetrisColor.NOTHING);
            }
            for (Point point : points) {
                var gridPoint = this.tetrisGrid.getCell(point.add(toPoint));
                gridPoint.setColor(COLOR);
            }
        };
    }

    @Override
    public boolean canTranslate(Point toPoint) {
        boolean isSelfColliding = false;
        for (Point point : points) {
            var neededPoint = this.tetrisGrid.getCell(point.add(toPoint));
            if(neededPoint.getColor() != TetrisColor.NOTHING){
                isSelfColliding = false;
                for (Point p: this.points){
                    if (neededPoint.equals(p)){
                        isSelfColliding = true;
                    }
                }
                return isSelfColliding;
            }
        }
        return true;
    }

    public void rotateClockwise() throws RemoteException{
        switch (this.rotation) {
            case RIGHT -> this.rotate(Rotation.DOWN);
            case UP -> this.rotate(Rotation.RIGHT);
            case DOWN -> this.rotate(Rotation.LEFT);
            default -> this.rotate(Rotation.UP);
        }
    }

    @Override
    public Runnable rotate(Rotation dir) throws RemoteException {
        return () -> {
            if(!canRotate(dir)) return;
            for (Point point : points) {
                this.tetrisGrid.getCell(point).setColor(TetrisColor.NOTHING);
            }
            rotationCoordinate(dir);
            for (Point point : points) {
                this.tetrisGrid.getCell(point).setColor(COLOR);
            }

            this.rotation = dir;
        };
    }

    public void rotationCoordinate(Rotation dir){}
}
