package game.tetris;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

import game.tetris.datastructure.ClientTetrisGrid;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.Nullable;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Main{


	public static void main(String[] args) {

		final int ROWS = 15;
		String clientID = null;
		final int COLLUMNS = 10;
		String ip = null;
		String username = null;

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

		new TetrisApplication(ip, username, clientID, args);

		//Connecting with server, and setting up local endpoint for server to send updates

	}




}
