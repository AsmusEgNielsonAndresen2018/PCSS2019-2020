package Game;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		int choice = Integer.parseInt(JOptionPane.showInputDialog("1 for host, 2 for 2nd player"));
		
		if (choice == 1) {
			new ServerGame();
		} else if (choice == 2) {
			new ClientGame();
			
		}
		 
		

	}

}
