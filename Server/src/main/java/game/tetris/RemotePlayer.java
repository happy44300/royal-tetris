package game.tetris;

import game.tetris.datastructure.AbstractBlock;

public class RemotePlayer implements Player{
    //TODO: Implement class
    private String name;
    private String ip;

    AbstractBlock currentBlock;

    public RemotePlayer(String name, String ip){
        this.name = name;
        this.ip = ip;
    }

    public void setCurrentBlock(AbstractBlock abstractBlock){
        this.currentBlock = abstractBlock;
    }

    public AbstractBlock getCurrentBlock(){
        return this.currentBlock;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getIP() {
        return this.ip;
    }
}
