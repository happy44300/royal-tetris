package game.tetris;

public class RemotePlayer implements Player {
    //TODO: Implement class
    private String name;
    private String ip;

    public RemotePlayer(String name, String ip){
        this.name = name;
        this.ip = ip;
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
