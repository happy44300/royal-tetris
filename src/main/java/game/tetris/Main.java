package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.logging.Logger;

public class Main extends GameApplication {

	public static final Logger LOG = Logger.get("Tetris-Royale");


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void initSettings(GameSettings settings) {
			LOG.info("Hello world!");
		}
}
