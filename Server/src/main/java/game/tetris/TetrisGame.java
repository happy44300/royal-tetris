package game.tetris;

import game.tetris.action.PlayerAction;
import game.tetris.action.Rotate;
import game.tetris.action.Translate;
import game.tetris.block.Block;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class TetrisGame implements Game{
    private final int NUMBER_OF_PLAYERS_MAX = 4;
    private final int NUMBER_OF_ROWS = 20;
    private final int NUMBER_OF_COLUMNS = 40;
    private final static String SERVER_HOST_IP = "127.0.0.1";
    private final static int SERVER_HOST_PORT = 10000;


    Grid grid;

    Map<String, Block> playerToBlock;

    public static void main(String[] argv) {
        try {
            System.setProperty("java.rmi.server.hostname",SERVER_HOST_IP);
            Registry registry = LocateRegistry.createRegistry(10000);

            TetrisGame tetrisGame = (TetrisGame) UnicastRemoteObject.exportObject(new TetrisGame(), SERVER_HOST_PORT);
            registry.rebind("TetrisGame", tetrisGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TetrisGame(){
        this.grid = new BasicGrid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        this.playerToBlock = new HashMap<>();
    }

    @Override
    public void submitBlockUpdate(String playerID, PlayerAction action) throws RemoteException {
        synchronized (this.grid){
            Block blockToUpdate = this.playerToBlock.get(playerID);
            if(this.handlePlayerAction(blockToUpdate, action)){
                Block updatedBlock = this.playerToBlock.get(playerID);
                this.handleConsequences(updatedBlock);
            }
        }
    }

    //Returns True if the action was successful, false otherwise
    private boolean handlePlayerAction(Block block, PlayerAction action){
        switch (action.getPlayerActionType()){
            case ROTATE -> {
                return this.handleRotate(block, (Rotate) action);
            }
            case TRANSLATE -> {
                return this.handleTranslate(block, (Translate) action);
            }
            default -> {
                return false;
            }
        }
    }

    private boolean handleRotate(Block block, Rotate rotation){
        if(this.isRotationValid(block, rotation)){
            block.doRotate(rotation);
            return true;
        }

        return false;
    }

    private boolean isRotationValid(Block block, Rotate rotation) {
        Point[] postRotationPositions = block.computeRotation(rotation);

        for(Point p: postRotationPositions){
            if (this.grid.getCell(p).tetrisColor != TetrisColor.NOTHING){
                return false;
            }
        }

        return true;
    }

    private boolean handleTranslate(Block block, Translate translation) {
        if(this.isTranslationValid(block, translation)){
            block.doTranslation(translation);
            return true;
        }

        return false;
    }

    private boolean isTranslationValid(Block block, Translate translation) {
        Point[] postTranslationPositions = block.computeTranslation(translation);

        for(Point p: postTranslationPositions){
            if (this.grid.getCell(p).tetrisColor != TetrisColor.NOTHING){
                return false;
            }
        }

        return true;
    }

    private void handleConsequences(Block updateBlock){

    }

   ; @Override
    public String connect() {
        //TODO: implement

        String id = "player" + String.valueOf(playerToBlock.size());
        return null;
    }



}
