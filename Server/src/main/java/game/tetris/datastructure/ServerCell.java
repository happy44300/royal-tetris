package game.tetris.datastructure;

import java.rmi.RemoteException;

import static game.tetris.datastructure.TetrisColor.NOTHING;

public class ServerCell implements Cell{
	private Point pos;
	private TetrisColor color;
	private boolean isBlocked;

	public ServerCell(int x, int y){
		this.pos = new Point(x, y);
		this.color = NOTHING;
		this.isBlocked = false;
	}

	public ServerCell(Point pos){
		this.pos = pos;
		this.color = NOTHING;
		this.isBlocked = false;
	}

	@Override
	public Point getPos() throws RemoteException {
		return this.pos;
	}

	@Override
	public void setColor(TetrisColor color) throws RemoteException {
		this.color = color;
	}

	@Override
	public TetrisColor getColor() throws RemoteException {
		return this.color;
	}

	public void block(){
		this.isBlocked = true;
	}

	// Idk if this method will be useful
	public void unblock(){
		this.isBlocked = false;
	}

	public boolean getIsBlocked(){
		return this.isBlocked;
	}

}
