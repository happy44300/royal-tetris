package game.tetris.datastructure;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.TetrisGrid;
import game.tetris.tetrominos.TetrominosTexture;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class Cell extends Entity {
	@NotNull
	Texture texture;

	public Cell() {
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
