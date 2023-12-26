package game.tetris;

import game.tetris.block.ServerBlock;

public class RemotePlayer{

    private String name;
    private String ip;
    private UpdateHandler updateHandler;
    private String gameID;

    ServerBlock currentBlock;

    public RemotePlayer(String name, String ip, UpdateHandler updateHandler, String gameID){
        this.name = name;
        this.ip = ip;
        this.updateHandler = updateHandler;
        this.gameID = gameID;
    }

    public void setCurrentBlock(ServerBlock serverBlock){
        this.currentBlock = serverBlock;
    }

    public ServerBlock getCurrentBlock(){
        return this.currentBlock;
    }

    public String getName() {
        return this.name;
    }

    public String getIP() {
        return this.ip;
    }

    public UpdateHandler getUpdateHandler() {
        return updateHandler;
    }

    public void setUpdateHandler(UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

    public void setNewBlock(ServerBlock serverBlock) {
        this.currentBlock = serverBlock;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}
