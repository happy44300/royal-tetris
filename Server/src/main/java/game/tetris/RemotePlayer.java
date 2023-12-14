package game.tetris;

import game.tetris.block.OBlock;
import game.tetris.block.ServerBlock;
import game.tetris.datastructure.AbstractBlock;

public class RemotePlayer{

    private String name;
    private String ip;
    private Client client;

    ServerBlock currentBlock;

    public RemotePlayer(String name, String ip, Client client){
        this.name = name;
        this.ip = ip;
        this.client = client;
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
}
