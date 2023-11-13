package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.util.List;

public class BasicLobby implements Lobby {

    List<Player> playerList;
    Game game;

    @Override
    public void join(String playerName) {
        playerList.add(new BasicPlayer(playerName));
        //TODO: Finish constructor
    }

    @Override
    public void start() {
        //TODO: Implement starting a game
    }

    @Override
    public TetrisGrid getGrid() {
        //TODO: Implement game's tetris grid getter
        return null;
    }
}
