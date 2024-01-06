package game.tetris;

import game.tetris.action.PlayerAction;
import game.tetris.action.Rotate;
import game.tetris.action.Translate;
import game.tetris.block.*;
import game.tetris.grid.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TetrisGame implements Game{
    private final int NUMBER_OF_PLAYERS_MAX = 1;
    private final int NUMBER_OF_ROWS = 20;
    private final int NUMBER_OF_COLUMNS = 10;
    private final static String SERVER_HOST_IP = "127.0.0.1";
    private final static int SERVER_HOST_PORT = 10000;


    private final Grid grid;

    private final Map<String, Block> playerToBlock;
    private final Map<String, ConnectionManager> playerToConnectionManager;

    public static void main(String[] argv) {
        try {
            System.setProperty("java.rmi.server.hostname",SERVER_HOST_IP);
            Registry registry = LocateRegistry.createRegistry(10000);

            Game tetrisGame = (Game) UnicastRemoteObject.exportObject(new TetrisGame(), SERVER_HOST_PORT);
            registry.rebind("TetrisGame", tetrisGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TetrisGame(){
        this.grid = new BasicGrid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        this.playerToBlock = new HashMap<>();
        this.playerToConnectionManager = new HashMap<>();
    }

    @Override
    public void submitBlockUpdate(String playerID, PlayerAction action) throws RemoteException {
        synchronized (this.grid){
            Block blockToUpdate = this.playerToBlock.get(playerID);
            if(this.handlePlayerAction(blockToUpdate, action)){
                Block updatedBlock = this.playerToBlock.get(playerID);
                if(this.handleConsequences(playerID, updatedBlock)){
                    for(ConnectionManager cm: this.playerToConnectionManager.values()){
                        cm.updateBlock(playerID, updatedBlock);
                        cm.updateGrid(this.grid);
                    }
                }else {
                    for(ConnectionManager cm: this.playerToConnectionManager.values()){
                        cm.updateBlock(playerID, updatedBlock);
                    }
                }
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
            Point[] preRotationPositions = block.getPoints();

            //Saves the block's color
            TetrisColor blockColor = this.grid.getCell(preRotationPositions[0]).getColor();

            //Actually executes the rotation
            Point[] postRotationPositions = block.doRotate(rotation);

            //Removes color from old cells
            for(Point pointToRemove: preRotationPositions){
                this.grid.getCell(pointToRemove).setColor(TetrisColor.NOTHING);
            }

            //Paints new cells with old color
            for(Point pointToDraw: postRotationPositions){
                this.grid.getCell(pointToDraw).setColor(blockColor);
            }
            return true;
        }

        return false;
    }

    private boolean isRotationValid(Block block, Rotate rotation) {
        Point[] preRotationPositions = block.getPoints();
        Point[] postRotationPositions = block.computeRotation(rotation);

        outerloop:
        for(Point pointToCheck: postRotationPositions){
            for(Point other: preRotationPositions){
                if(other.equals(pointToCheck)){
                    continue outerloop;
                }
            }

            if(pointToCheck.getY() < 0 || pointToCheck.getY() >= NUMBER_OF_ROWS) return false;
            if(pointToCheck.getX() < 0 || pointToCheck.getX() >= NUMBER_OF_COLUMNS) return false;

            if (this.grid.getCell(pointToCheck).getColor() != TetrisColor.NOTHING){
                return false;
            }
        }

        return true;
    }

    private boolean handleTranslate(Block block, Translate translation) {
        if(this.isTranslationValid(block, translation)){
            Point[] preTranslationPositions = block.getPoints();

            //Saves the block's color
            TetrisColor blockColor = this.grid.getCell(preTranslationPositions[0]).getColor();

            //Actually executes the translation
            Point[] postTranslationPositions = block.doTranslate(translation);

            //Removes color from old cells
            for(Point pointToRemove: preTranslationPositions){
                this.grid.getCell(pointToRemove).setColor(TetrisColor.NOTHING);
            }

            //Paints new cells with old color
            for(Point pointToDraw: postTranslationPositions){
                this.grid.getCell(pointToDraw).setColor(blockColor);
            }

            return true;
        }

        return false;
    }

    private boolean isTranslationValid(Block block, Translate translation) {

        Point[] preTranslationPositions = block.getPoints();
        Point[] postTranslationPositions = block.computeTranslation(translation);

        outerloop:
        for(Point pointToCheck: postTranslationPositions){
            for(Point other: preTranslationPositions){
                if(other.equals(pointToCheck)){
                    continue outerloop;
                }
            }

            if(pointToCheck.getX() < 0 || pointToCheck.getX() >= NUMBER_OF_COLUMNS) return false;
            if (this.grid.getCell(pointToCheck).getColor() != TetrisColor.NOTHING) return false;
        }

        return true;
    }

    //True if any consequence happened, false otherwise
    private boolean handleConsequences(String playerID, Block updatedBlock){
        if(this.isBlockDirectlyAboveLockedCell(updatedBlock)){
            this.removeCompletedLines();

            Block newBlock = getRandomBlock(this.NUMBER_OF_COLUMNS/2, 3);

            //Loss condition
            for(Point p: newBlock.getPoints()){
                if(this.grid.getCell(new Point(p.getX(), p.getY())).getColor() != TetrisColor.NOTHING) this.lose();
            }

            this.playerToBlock.put(playerID, newBlock);

            return true;
        }

        return false;
    }

    private void lose() {
        synchronized (this.grid){

            Grid endgameGrid = new BasicGrid(4,4, TetrisColor.GREEN);
            for(ConnectionManager cm: this.playerToConnectionManager.values()){
                try {
                    cm.updateGrid(endgameGrid);
                } catch (RemoteException e) {
                    System.out.println("COULDN'T SEND GAME OVER SCREEN TO PLAYER");
                    throw new RuntimeException(e);
                }
            }

            while(true){

            }
        }
    }

    private void start() {
        int gridOffset = 5;

        for(String playerID: this.playerToConnectionManager.keySet()){

            Block randomBlock = getRandomBlock(gridOffset, 3);
            this.playerToBlock.put(playerID, randomBlock);

            for(Point p: randomBlock.getPoints()){
                this.grid.getCell(p).setColor(randomBlock.getColor());
            }
            gridOffset += 3;
        }

        for(ConnectionManager cm: this.playerToConnectionManager.values()){
            for(String playerID: this.playerToBlock.keySet()){
                try {
                    cm.updateBlock(playerID, this.playerToBlock.get(playerID));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            try {
                cm.updateGrid(this.grid);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        Thread descentThread = new Thread(() -> {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                submitBlockDescent();
            }
        });

        descentThread.start();
    }

    private Block getRandomBlock(int gridOffset, int y) {
        int numBlock = getRandomNumberInRange(1,7);

        return switch (numBlock) {
            case 1 -> new IBlock(gridOffset, y);
            case 2 -> new LBlock(gridOffset, y);
            case 3 -> new JBlock(gridOffset, y);
            case 4 -> new OBlock(gridOffset, y);
            case 5 -> new SBlock(gridOffset, y);
            case 6 -> new ZBlock(gridOffset, y);
            default -> new TBlock(gridOffset, y); // 7 is the "default" case
        };
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("max must be greater than min");

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    private void submitBlockDescent(){
        synchronized (this.grid){
            for (Block block: this.playerToBlock.values()) {

                Point[] pointsToRemove = block.getPoints();

                for(Point pointToRemove: pointsToRemove){
                    this.grid.getCell(pointToRemove).setColor(TetrisColor.NOTHING);
                }

                Point[] pointsToDraw = block.doGoDown();

                for(Point pointToDraw: pointsToDraw){
                    this.grid.getCell(pointToDraw).setColor(block.getColor());
                }
            }

            try {
                if(this.handleBlockDescentConsequences()){
                    for(ConnectionManager cm: this.playerToConnectionManager.values()){
                        for(String playerID: this.playerToConnectionManager.keySet()){
                            cm.updateBlock(playerID, this.playerToBlock.get(playerID));
                        }
                        cm.updateGrid(this.grid);
                    }
                }else {
                    for(ConnectionManager cm: this.playerToConnectionManager.values()){
                        for(String playerID: this.playerToConnectionManager.keySet()){
                            cm.updateBlock(playerID, this.playerToBlock.get(playerID));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Returns if lowering every block had any game consequences
    private boolean handleBlockDescentConsequences() {
        for(String playerID: this.playerToBlock.keySet()){
            if(handleConsequences(playerID, this.playerToBlock.get(playerID))){
                return true;
            }
        }

        return false;
    }

    private boolean isBlockDirectlyAboveLockedCell(Block updatedBlock) {
        Point[] blockPoints = updatedBlock.getPoints();
        int currentX;
        int currentY;

        outerloop:
        for(Point p: blockPoints){
            currentX = p.getX();
            currentY = p.getY();

            if(currentY == NUMBER_OF_ROWS - 1) return true;

            Point pointBelow = new Point(currentX, currentY + 1);

            for(Point other: blockPoints){
                if(other.equals(pointBelow)){
                    continue outerloop;
                }
            }

            if(this.grid.getCell(pointBelow).getColor() != TetrisColor.NOTHING){
                return true;
            }

        }

        return false;
    }

    public void removeCompletedLines() {
        System.out.println("REMOVING LINES!!!");

        int numLine = 0;
        //List<Integer> numLinesDeleted = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_ROWS; i++){

            Cell[] row = this.grid.getCells()[i];
            if(this.isRowLocked(row)){
                this.removeLine(numLine);
                //numLinesDeleted.add(numLine);
            }
            numLine += 1;
        }

        //return numLinesDeleted;
    }

    public void removeLine(int numLine) {
        this.shiftRowDownward(numLine);
        this.stackEmptyLine();
    }

    private void shiftRowDownward(int numLine) {
        for(int i = numLine; i > 0; i--){
            this.grid.getCells()[i] = this.grid.getCells()[i-1];
        }
    }

    private void stackEmptyLine() {
        Cell[] newLine = new Cell[NUMBER_OF_COLUMNS];
        for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
            newLine[j] = new Cell(TetrisColor.NOTHING);
        }
        this.grid.getCells()[0] = newLine;
    }

    private boolean isRowLocked(Cell[] row) {
        for (Cell cell : row){
            if(cell.getColor() == TetrisColor.NOTHING) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String connect() throws RemoteException {
        String playerIP;
        String playerID;

        synchronized (this.playerToConnectionManager){
            System.out.println("IP OF CLIENT : ");

            try {
                playerIP = RemoteServer.getClientHost();
                System.out.println(playerIP);

                Registry remoteRegistry = LocateRegistry.getRegistry(playerIP, 10001);
                ConnectionManager playerConnectionManager = (ConnectionManager) remoteRegistry.lookup("ConnectionManager");

                playerID = "player" + playerToConnectionManager.size();

                this.playerToConnectionManager.put(playerID, playerConnectionManager);
            } catch (NotBoundException | ServerNotActiveException e) {
                throw new RemoteException();
            }
        }

        if(this.playerToConnectionManager.size() == NUMBER_OF_PLAYERS_MAX){
            this.start();
        }

        if(this.playerToConnectionManager.size() > NUMBER_OF_PLAYERS_MAX){
            throw new RemoteException("Too many players!");
        }


        return playerID;
    }
}
