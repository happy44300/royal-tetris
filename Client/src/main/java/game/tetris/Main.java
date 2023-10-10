package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

import javafx.scene.input.KeyCode;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class Main extends GameApplication {


	private static final int ROWS = 15;
	private static final int COLLUMNS = 10;

	@Nullable private static String ip = null;
	@Nullable private static String username = null;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(800);
		settings.setHeight(600);
		settings.setTicksPerSecond(1);
	}

	@Override
	protected void initGameVars(Map<String, Object> vars) {
		super.initGameVars(vars);
	}

	@Override
	protected void initGame() {


        TetrisGrid tetrisGrid = new TetrisGrid(ROWS, COLLUMNS);
		for (Entity[] row : tetrisGrid.getGridEntities()) {
			for (Entity cell : row) {
				getGameWorld().addEntity(cell);
			}
		}
	}

	@Override
	protected void initInput() {
		getInput().addAction(new UserAction("Move Left") {
			@Override
			protected void onActionBegin() {
				// Handle left movement
			}
		}, KeyCode.LEFT);

		//TODO: Add actions for other Tetris movements (right, rotate, etc.)
	}

	@Override
	protected void onUpdate(double tpf) {
		//TODO: Update game logic here
	}

	private void connect(){
		//TODO: implement connection
	}

	public static void main(String[] args) {

		for (int i = 0; i < args.length; i++) {
			if ("-ip".equals(args[i]) && i < args.length - 1) {
				// Check if the next argument is a valid IP address
				ip = args[i + 1];
			} else if ("-username".equals(args[i]) && i < args.length - 1) {
				username = args[i + 1];
			}
		}

		if (ip != null) {
			System.out.println("IP: " + ip);
		} else {
			System.out.println("IP parameter not provided, hosting local game");
		}

		if (username != null) {
			System.out.println("Username: " + username);
		} else {
			System.out.println("Username parameter not provided, using default username");
			username = "George Abitbol";
		}

		launch(args);
	}


}
