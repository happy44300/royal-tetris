package game.tetris.tetrominos;

import com.almasb.fxgl.texture.ColoredTexture;
import com.almasb.fxgl.texture.Texture;
import game.tetris.datastructure.TetrisGridClient;
import game.tetris.datastructure.TetrisColor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Objects;

public enum TetrominosTexture {


	IMAGE_BACKGROUND(new ColoredTexture(TetrisGridClient.CELL_SIZE, TetrisGridClient.CELL_SIZE, Color.WHITE)),
	SQUAREBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/O-block.jpg"))))),
	TEEBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/T-block.jpg"))))),
	LBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/L-block.jpg"))))),
	JBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/J-block.jpg"))))),
	SBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/S-block.jpg"))))),
	ZBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/Z-block.jpg"))))),
	RODBLOCK(new Texture(new Image(Objects.requireNonNull(TetrominosTexture.class.getResourceAsStream("/sprite/I-block.jpg")))));


	@NotNull private final Texture texture;

	private static final EnumMap<TetrisColor, TetrominosTexture> map = new EnumMap<>(TetrisColor.class);

	static {
		map.put(TetrisColor.NOTHING, IMAGE_BACKGROUND);
		map.put(TetrisColor.YELLOW, SQUAREBLOCK);
		map.put(TetrisColor.PURPLE, TEEBLOCK);
		map.put(TetrisColor.ORANGE, LBLOCK);
		map.put(TetrisColor.BLUE, JBLOCK);
		map.put(TetrisColor.GREEN, SBLOCK);
		map.put(TetrisColor.RED, ZBLOCK);
		map.put(TetrisColor.TURQUOISE, RODBLOCK);
	}

	TetrominosTexture(@NotNull Texture texture) {
			this.texture = texture;
			this.texture.setFitHeight(TetrisGridClient.CELL_SIZE);
			this.texture.setFitWidth(TetrisGridClient.CELL_SIZE);
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
