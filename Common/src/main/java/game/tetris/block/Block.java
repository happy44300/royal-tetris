package game.tetris.block;

import game.tetris.grid.Point;
import game.tetris.action.Rotate;
import game.tetris.action.Translate;
import game.tetris.grid.TetrisColor;

import java.io.Serializable;

public abstract class Block implements Serializable {
    Point[] points = new Point[4];
    TetrisColor color;
    Orientation orientation = Orientation.LEFT;
    BlockType type;

    public Point getOrigin() {
        return points[0];
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return this.points;
    }

    protected Orientation computeNewOrientation(Rotate rotate) {
        switch (this.orientation){
            case UP -> {
                return rotate.isClockwise() ? Orientation.RIGHT : Orientation.LEFT;
            }
            case RIGHT -> {
                return rotate.isClockwise() ? Orientation.DOWN : Orientation.UP;
            }
            case DOWN -> {
                return rotate.isClockwise() ? Orientation.LEFT : Orientation.RIGHT;
            }
            default -> {
                return rotate.isClockwise() ? Orientation.UP : Orientation.DOWN;
            }
        }
    }

    public abstract Point[] computeRotation(Rotate rotate);

    public Point[] doRotate(Rotate rotate) {
        this.points = computeRotation(rotate);
        this.orientation = computeNewOrientation(rotate);
        return this.points;
    }

    public Point[] computeTranslation(Translate translate) {
        Point[] computedPoints = new Point[4];
        int translateValue = translate.getTranslateValue();

        for(int i = 0; i < 4; i++){
            Point currentPoint = this.points[i];
            computedPoints[i] = new Point(currentPoint.getX() + translateValue, currentPoint.getY());
        }

        return computedPoints;
    }

    public Point[] doTranslate(Translate translate) {
        this.points = computeTranslation(translate);
        return this.points;
    }

    public Point[] doGoDown() {
        for(Point p : this.points){
            p.translate(0, 1);
        }

        return this.points;
    }

    public TetrisColor getColor() {
        return color;
    }
}
