package Game.packets;

import java.io.Serializable;

public class GameEndPacket implements Serializable{
	
	
	private static final long serialVersionUID = 5161356661848312399L;
	private int winner;

	public GameEndPacket(int winner) {
		this.winner = winner;
	}

	public int getWinner() {
		return winner;
	}

}
