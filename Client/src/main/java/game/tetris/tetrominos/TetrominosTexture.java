package game.tetris.tetrominos;

import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.TetrisGridClient;
import game.tetris.datastructure.TetrisColor;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;

public enum TetrominosTexture {


	IMAGE_BACKGROUND(new ColoredTexture(TetrisGridClient.CELL_SIZE, TetrisGridClient.CELL_SIZE, Color.WHITE));


	@NotNull private final Texture texture;

	private static final EnumMap<TetrisColor, TetrominosTexture> map = new EnumMap<>(TetrisColor.class);

	static {
		map.put(TetrisColor.NOTHING, IMAGE_BACKGROUND);
	}

	TetrominosTexture(@NotNull Texture texture) {
			this.texture = texture;
	}

	public Texture getTexture(){
		return texture;
	}

	public  Texture tetrisColorToTexture(TetrisColor color){
		return tetrisColorToTetrominosColor(color).getTexture();
	}

	public TetrominosTexture tetrisColorToTetrominosColor(TetrisColor color){
		return map.get(color);
	}

}
