package Game;

import java.io.IOException;
import java.net.Socket;

import Game.packets.ClientPlayPacket;
import Game.packets.GameEndPacket;
import Game.packets.UpdatePacket;

public class ClientGame extends Game {

	private Socket socket;
	private Connection connection;
	
	public ClientGame() {
		super (Game.PLAYER_2);

		try {
			socket = new Socket("localhost", Game.PORT);
			connection = new Connection(this, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void inputReceived(int x, int y) {
		if(myTurn()) {
			connection.sendPacket(new ClientPlayPacket(x,y));
		}
	}
	
		@Override
	public void packetReceived(Object object) {
		if(object instanceof UpdatePacket) {
			UpdatePacket packet = (UpdatePacket) object;
			fields = packet.getFields();
			currentPlayer = packet.getCurrentPlayer();
		} else if (object instanceof GameEndPacket) {
			GameEndPacket packet = (GameEndPacket) object;
			showWinner(packet.getWinner());
		}
		
		gameWindow.repaint();
		
	}

	@Override
	public void close() {
		try {
			connection.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
