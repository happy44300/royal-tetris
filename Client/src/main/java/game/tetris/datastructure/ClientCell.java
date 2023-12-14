package game.tetris.datastructure;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import game.tetris.tetrominos.TetrominosTexture;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

public class ClientCell extends Entity implements Cell {
	@NotNull
	Texture texture;

	TetrisColor color = TetrisColor.NOTHING;

	Point gridPos;

	public void setPos(Point point){
		this.gridPos = point;
		this.setPosition(new Vec2(point.getX() * (TetrisGridClient.CELL_SIZE+1),(point.getY() * TetrisGridClient.CELL_SIZE)+1));
	}

	@Override
	public Point getPos() {
		throw new NotImplementedError();
	}

	@Override
	public void setColor(TetrisColor color) {
		this.color = color;
		setTexture(TetrominosTexture.tetrisColorToTetrominosColor(this.color));
	}

	@Override
	public TetrisColor getColor() {
		return this.color;
	}

	public ClientCell() {
		this.setOpacity(1);
		this.texture = new Texture(TetrominosTexture.IMAGE_BACKGROUND.getTexture().getImage());
		this.getViewComponent().addChild(texture);
		this.texture.setOpacity(1);

	}

	public void setTexture(TetrominosTexture tetrominosTexture){
		this.texture.set(tetrominosTexture.getTexture());
	}

	public void makeBackground(){
		setTexture(TetrominosTexture.IMAGE_BACKGROUND);
	}
}
