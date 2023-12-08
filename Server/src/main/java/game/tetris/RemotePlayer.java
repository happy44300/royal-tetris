package game.tetris;

import game.tetris.block.ServerBlock;

public class RemotePlayer{

    private String name;
    private String ip;

    ServerBlock currentBlock;

    public RemotePlayer(String name, String ip){
        this.name = name;
        this.ip = ip;
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

    public void setNewBlock() {
    }
}
