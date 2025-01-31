package Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Game.packets.ClientPlayPacket;
import Game.packets.GameEndPacket;
import Game.packets.UpdatePacket;

public class ServerGame extends Game {

private ServerSocket serverSocket;	
private Socket socket;
private Connection connection;

public ServerGame() {
	super (Game.PLAYER_1);

	try {
		serverSocket = new ServerSocket(Game.PORT);
		socket = serverSocket.accept();
		connection = new Connection(this, socket);
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
	@Override
	public void inputReceived(int x, int y) {
		if(myTurn()) {
			updateField(x,y);
		}
		
		
		
	}
	
	private void updateField(int x, int y) {
		
		if (fields[x][y] == Game.FREE) {
			
			fields[x][y] = currentPlayer;
			if(currentPlayer == Game.PLAYER_1) {
				currentPlayer= Game.PLAYER_2;
			}	else if (currentPlayer == Game.PLAYER_2) {
				currentPlayer = Game.PLAYER_1;
		
			
		}
		connection.sendPacket(new UpdatePacket(fields, currentPlayer));
		gameWindow.repaint();
		int winner = checkWin();
		
		if(winner != Game.FREE) {
			endGame(winner);
		} else {
			int emptyCount = 0;
			for (int a = 0; a < 3; a++) {
				for (int b = 0; b < 3; b++) {
					if(fields[a][b] == Game.FREE) {
						emptyCount++;
					}
				}
			}
			if(emptyCount == 9)
			{
				 endGame(winner);
			}
		}}}
	
	private void endGame(int winner) {
		showWinner(winner);
		connection.sendPacket(new GameEndPacket(winner));
	}
	
	private int checkWin() {
		
		for (int player = Game.PLAYER_1; player <= Game.PLAYER_2; player++) {
			for (int y = 0; y < 3; y++) {
				int playerCount = 0;
				
				for (int x = 0; x < 3; x++) {
					if (fields[x][y] == player) {
						playerCount++;
					}
				}
				if (playerCount == 3) {
					return player;
				}
			}
			
			for (int x = 0; x < 3; x++) {
				int playerCount = 0;
				
				for (int y = 0; y < 3; y++) {
					if (fields[x][y] == player) {
						playerCount++;
					}
				}
				if (playerCount == 3) {
					return player;
				}
			}
			int playerCount = 0;
			for (int coordinate = 0; coordinate < 3; coordinate++) {
				if(fields[coordinate][coordinate] == player) {
					playerCount++;
				}
			}
			
			if (playerCount == 3) {
				return player;
			}
			
			 playerCount = 0;
			for (int coordinate = 0; coordinate < 3; coordinate++) {
				if(fields[2 - coordinate][coordinate] == player) {
					playerCount++;
				}
			}
			
			if (playerCount == 3) {
				return player;
			}
			}
		
		return Game.FREE;
			
		}
		
	

	@Override
	public void close() {
		try {
			connection.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void packetReceived(Object object) {
		
		if(object instanceof ClientPlayPacket) {
			ClientPlayPacket packet = (ClientPlayPacket) object;
			updateField(packet.getX(), packet.getY());
			
		}
		
		
	}
	
	

	
	
}
