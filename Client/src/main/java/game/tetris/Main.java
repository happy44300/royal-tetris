package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

import game.tetris.datastructure.TetrisGridClient;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.Nullable;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Main extends GameApplication {


	private static final int ROWS = 15;
	private static final int COLLUMNS = 10;

	@Nullable private static String ip = null;
	@Nullable
	private static String username = null;

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
	public void initGame() {

        TetrisGridClient tetrisGridClient = new TetrisGridClient(ROWS, COLLUMNS);
		var background = FXGL.entityBuilder().
				at(0,-1,0)
				.view(new Rectangle(getAppWidth(),getAppHeight(), Color.BLACK))
				.buildAndAttach();

		for (Entity[] row : tetrisGridClient.getGridEntities()) {
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

		//Connecting with server, and setting up local endpoint for server to send updates
		try {
			Registry remoteRegistry = LocateRegistry.getRegistry(ip, 10000);
			Lobby lobby = (Lobby) remoteRegistry.lookup("Lobby");
			String usedIP = lobby.join(username);
			lobby.start();


			System.setProperty("java.rmi.server.hostname",usedIP);
			Registry localRegistry = LocateRegistry.createRegistry(10000);

			Client client = (Client) UnicastRemoteObject.exportObject(new BasicClient(), 10000);
			localRegistry.rebind("Client", client);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}


}
