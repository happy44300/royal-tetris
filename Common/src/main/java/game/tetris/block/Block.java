package game.tetris.block;

import game.tetris.BlockType;
import game.tetris.Orientation;
import game.tetris.Point;
import game.tetris.action.PlayerAction;
import game.tetris.action.Rotate;
import game.tetris.action.Translate;

import java.io.Serializable;

public abstract class Block implements Serializable {
    Point origin;
    Point[] points;
    Orientation orientation = Orientation.LEFT;
    BlockType type;

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
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

    public abstract Point[] getPoints();

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
            case LEFT -> {
                return rotate.isClockwise() ? Orientation.UP : Orientation.DOWN;
            }
            default -> {
                return Orientation.UP;
            }
        }
    }

    public abstract Point[] computeRotation(Rotate rotate);

    public Point[] doRotate(Rotate rotate) {
        this.orientation = computeNewOrientation(rotate);
        this.points = computeRotation(rotate);
        return this.points;
    }

    public Point[] computeTranslation(Translate translate) {
        Point[] computedPoints = new Point[4];
        int translateValue = translate.getTranslateValue();

        for(int i = 0; i < 4; i++){
            Point currentPoint = this.points[i];
            computedPoints[i] = new Point(currentPoint.getX(), currentPoint.getY() + translateValue);
        }

        return computedPoints;
    }

    public Point[] doTranslation(Translate translate) {
        this.origin.translate(0, translate.getTranslateValue());
        this.points = computeTranslation(translate);
        return points;
    }
}
