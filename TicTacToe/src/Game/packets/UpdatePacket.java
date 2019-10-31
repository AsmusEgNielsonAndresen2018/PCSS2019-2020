package Game.packets;

import java.io.Serializable;

public class UpdatePacket implements Serializable{

	
	private static final long serialVersionUID = -1145849281660127644L;

	private int[][] fields;
	
	private int currentPlayer;

	public UpdatePacket(int[][] fields, int currentPlayer) {
		super();
		this.fields = fields;
		this.currentPlayer = currentPlayer;
	}

	public int[][] getFields() {
		return fields;
	}

	public void setFields(int[][] fields) {
		this.fields = fields;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
}
