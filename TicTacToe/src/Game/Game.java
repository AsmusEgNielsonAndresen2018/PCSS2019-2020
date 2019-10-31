package Game;

import javax.swing.JOptionPane;

import Game.GUI.GameWindow;
import Game.GUI.Window;

public abstract class Game {
	
	public static final int PORT = 6666;
	
	public static final int WIDTH = 600, HEIGHT = 600;
	public static final int FIELD_WIDTH = WIDTH / 3, FIELD_HEIGHT = HEIGHT / 3;
	
	public static final int FREE = 0, PLAYER_1 = 1, PLAYER_2 = 2;
	protected int [][] fields;
	
	private Window window;
	protected GameWindow gameWindow;
	
	protected int currentPlayer;

	protected int thisPlayer;
	
	public Game(int thisPlayer) {
		this.thisPlayer = thisPlayer;
		window = new Window(this, "TicTacToe Motherfuckers! " + this.thisPlayer, WIDTH, HEIGHT);
		gameWindow = new GameWindow(this);
		fields = new int[3][3];
		//fields[0][0] = PLAYER_3;
		//fields[0][2] = PLAYER_1;
		window.add(gameWindow);
		window.setVisible(true);
		currentPlayer = Game.PLAYER_1;
	}
	
	protected void showWinner (int winner) {
		
		if (winner == Game.FREE) {
		JOptionPane.showMessageDialog(null, "Nobody won motherfuckers!" );
		
	}
		else {
			JOptionPane.showMessageDialog(null, "Player " + winner + " has won the game motherfucker!" );
		}}
	
	protected boolean myTurn() {
		if (thisPlayer == currentPlayer) {
			return true;
		}
		return false;
	}
	public abstract void inputReceived(int x, int y);
	public abstract void close();
	public abstract void packetReceived(Object object);
	
	public int[][] getFields(){
		
		return fields;
		
	}
}
