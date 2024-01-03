package game.tetris;

public class Cell {
    Color color;

    public Cell() {
        this.color = Color.NOTHING;
    }

    public Cell(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
