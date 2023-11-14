package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public abstract class ClientBlock extends AbstractBlock {
    Point[] points = new Point[4];
    TetrisColor COLOR;
    Rotation rotation;
    public ClientBlock(int x, int y, TetrisGrid grid){
        super(x, y, grid);
        rotation = Rotation.LEFT;
    }

    public void paint() throws RemoteException {
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
    private boolean canTranslateUnsafe(Point toPoint, TetrisGrid grid) throws RemoteException {
        boolean isSelfColliding = false;
        for (Point point : points) {
            var neededPoint = grid.getCell(point.add(toPoint));
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
    public void rotate() throws RemoteException{
        switch (this.rotation) {
            case RIGHT -> this.rotate(Rotation.DOWN);
            case UP -> this.rotate(Rotation.RIGHT);
            case DOWN -> this.rotate(Rotation.LEFT);
            default -> this.rotate(Rotation.UP);
        }
    }
    @Override
    public void rotate(Rotation dir) throws RemoteException {
        if(!canRotate(dir))
            return;
        this.tetrisGrid.updateGridSynchronously(
                safeGrid-> {
                    try {
                        for (Point point : points) {
                            safeGrid.getCell(point).setColor(TetrisColor.NOTHING);
                        }
                        rotationCoordinate(dir);
                        for (Point point : points) {
                            safeGrid.getCell(point).setColor(COLOR);
                        }

                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        this.rotation = dir;
    }

    public void rotationCoordinate(Rotation dir){}
}
