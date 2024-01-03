package game.tetris.datastructure;

import java.io.Serializable;

public class BlockBluePrint implements Serializable {
    BlockType type;
    Rotation rotation;
    Point coordinates;

    public BlockBluePrint(BlockType type, Rotation rotation, Point coordinates) {
        this.type = type;
        this.rotation = rotation;
        this.coordinates = coordinates;
    }

    public BlockType getType() {
        return type;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Point getCoordinates() {
        return coordinates;
    }
}
