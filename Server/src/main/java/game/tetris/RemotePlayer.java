package game.tetris;

import game.tetris.block.OBlock;
import game.tetris.block.ServerBlock;
import game.tetris.datastructure.AbstractBlock;

public class RemotePlayer{

    private String name;
    private String ip;
    private Client client;
    private String gameID;

    ServerBlock currentBlock;

    public RemotePlayer(String name, String ip, Client client, String gameID){
        this.name = name;
        this.ip = ip;
        this.client = client;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setNewBlock(ServerBlock serverBlock) {
        this.currentBlock = serverBlock;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }
}
