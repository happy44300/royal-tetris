package game.tetris.datastructure;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.TetrisGridClient;
import game.tetris.datastructure.Cell;
import game.tetris.datastructure.Point;
import game.tetris.tetrominos.TetrominosTexture;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.rmi.RemoteException;

public class ClientCell extends Entity implements Cell {
	@NotNull
	Texture texture;

	TetrisColor color = TetrisColor.NOTHING;

	Point gridPos = new Point(0,0);

	public void setPos(Point point){
		this.gridPos = point;
		this.setPosition(new Vec2(point.getX() * (TetrisGridClient.CELL_SIZE+1),(point.getY() * TetrisGridClient.CELL_SIZE)+1));
	}

	@Override
	public Point getPos() {
		return gridPos;
	}

	@Override
	public void setColor(TetrisColor color) throws RemoteException {
		this.color = color;
		this.texture = TetrominosTexture.tetrisColorToTexture(color);
		this.getViewComponent().removeChild(texture);
		this.getViewComponent().addChild(texture);
	}

	@Override
	public TetrisColor getColor() throws RemoteException {
		return this.color;
	}

	public ClientCell() {
		this.setOpacity(1);
		this.texture = new ColoredTexture(TetrisGridClient.CELL_SIZE, TetrisGridClient.CELL_SIZE, Color.AQUA);
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
