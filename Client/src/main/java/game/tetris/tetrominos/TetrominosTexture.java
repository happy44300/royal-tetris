package game.tetris.tetrominos;

import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.TetrisGridClient;
import game.tetris.datastructure.TetrisColor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Objects;

public enum TetrominosTexture {


	IMAGE_BACKGROUND(new ColoredTexture(TetrisGridClient.CELL_SIZE, TetrisGridClient.CELL_SIZE, Color.WHITE)),
	SQUAREBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/O-block.jpg")))));


	@NotNull private final Texture texture;

	private static final EnumMap<TetrisColor, TetrominosTexture> map = new EnumMap<>(TetrisColor.class);

	static {
		map.put(TetrisColor.NOTHING, IMAGE_BACKGROUND);
		map.put(TetrisColor.YELLOW, SQUAREBLOCK);
	}

	TetrominosTexture(@NotNull Texture texture) {
			this.texture = texture;
	}

	@NotNull
	public Texture getTexture(){
		return texture;
	}

	public static Texture tetrisColorToTexture(TetrisColor color){
		return tetrisColorToTetrominosColor(color).getTexture();
	}

	public static TetrominosTexture tetrisColorToTetrominosColor(TetrisColor color){
		return map.get(color);
	}

}
