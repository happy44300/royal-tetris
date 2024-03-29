package game.tetris.grid;
import java.io.Serializable;
import java.util.Objects;

public class Point implements Serializable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setPosition(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public Point translate(int xTranslation, int yTranslation){
        this.x += xTranslation;
        this.y += yTranslation;
        return this;
    }

    // You can add other methods as needed

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
