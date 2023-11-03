package game.tetris.datastructure;

import java.rmi.RemoteException;

public class ServerCell implements Cell{
	private Point pos;

	public ServerCell(int x, int y){

	}

	public ServerCell(Point pos){

	}

	@Override
	public Point getPos() throws RemoteException {
		return new Point(5, 6);
	}
}
