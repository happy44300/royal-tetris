package game.tetris.tetrominos;

import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.TetrisGrid;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public enum TetrominosTexture {


	IMAGE_BACKGROUND(new ColoredTexture(TetrisGrid.CELL_SIZE, TetrisGrid.CELL_SIZE, Color.BLACK));

	@NotNull private final Texture texture;

	TetrominosTexture(@NotNull Texture texture) {
			this.texture = texture;
	}

	public Texture getTexture(){
		return texture;
	}
}
