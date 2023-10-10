package game.tetris.datastructure;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.TetrisGrid;
import game.tetris.datasrtucture.Cell;
import game.tetris.datasrtucture.Point;
import game.tetris.tetrominos.TetrominosTexture;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class ClientCell extends Entity implements Cell {
	@NotNull
	Texture texture;

	Point gridPos = new Point(0,0);

	@Override
	public void setPos(Point point){
		this.gridPos = point;
		this.setPosition(new Vec2(point.getX() * (TetrisGrid.CELL_SIZE+1),(point.getY() * TetrisGrid.CELL_SIZE)+1));
	}

	@Override
	public Point getPos() {
		return gridPos;
	}

	public ClientCell() {
		this.setOpacity(1);
		this.texture = new ColoredTexture(TetrisGrid.CELL_SIZE,TetrisGrid.CELL_SIZE, Color.AQUA);
		this.getViewComponent().addChild(texture);
		this.texture.setOpacity(1);
		makeBackground();
	}

	public void setTexture(TetrominosTexture tetrominosTexture){
		this.texture.set(tetrominosTexture.getTexture());
	}

	public void makeBackground(){
		setTexture(TetrominosTexture.IMAGE_BACKGROUND);
	}
}
