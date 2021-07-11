import java.util.*;

public class PlayGame {
	private Board monopoly;
	private Die gameDie;
	private int numberOfPlayers;
	
	public PlayGame() {
		setGame();
	}
	
	public static void main(String[] args) {
		new PlayGame();
	}
	
	void setGame() {
		Scanner sc = new Scanner(System.in);
		String[] playerNames;
		
		System.out.println("Enter number of players");
		numberOfPlayers = sc.nextInt();
		System.out.println();
		
		playerNames = new String[numberOfPlayers];
		
		for(int i=0; i<numberOfPlayers; i++) {
			System.out.println("Enter player " + (i+1) + "'s name");
			playerNames[i] = sc.next();
		}
		
		monopoly = new Board(numberOfPlayers, playerNames);
		gameDie = new Die();
		
		playGame();
	}
	
	void playGame() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n******** GAME STARTED ********");
		
		String endGame = "begin";
		int currentPlayer = 0;
		int dieValue = 0;
		boolean playerStatus;
		
		System.out.println();
		
		while(endGame.compareToIgnoreCase("exit") != 0) {
			if(monopoly.checkJail(currentPlayer) == true) {
				monopoly.freeFromJail(currentPlayer);
				currentPlayer = (currentPlayer+1)%numberOfPlayers;
				continue;
			}
			
			System.out.println("Player " + (currentPlayer+1) + ": Type 'roll' to roll the die or 'exit' to end game");

			endGame = scan.next();
			
			if(endGame.compareToIgnoreCase("exit") == 0)
				break;
			
			dieValue = gameDie.getDieValue();
			
			playerStatus = monopoly.changePlayerPosition(currentPlayer, dieValue);
			monopoly.getPlayersStatus();
			
			if(playerStatus == false) {
				System.out.println("PLayer " + (currentPlayer+1) + " is out of money");
				System.out.println("PLayer " + (currentPlayer+1) + " loses");
				
				break;
			}
		
			currentPlayer = (currentPlayer+1)%numberOfPlayers;
		}
		
		System.out.println("\n******** GAME ENDED ********");
	}
}
