package game.tetris.tetrominos;

import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.TetrisGridClient;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public enum TetrominosTexture {


	IMAGE_BACKGROUND(new ColoredTexture(TetrisGridClient.CELL_SIZE, TetrisGridClient.CELL_SIZE, Color.BLACK));

	@NotNull private final Texture texture;

	TetrominosTexture(@NotNull Texture texture) {
			this.texture = texture;
	}

	public Texture getTexture(){
		return texture;
	}
}
