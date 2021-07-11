import java.util.*;

public class Board {
	private Square[] board = new Square[36];
	private Player[] players;
	
	private int totalPlayers;
	
	public Board(int totalPlayers, String[] playerNames) {
		this.totalPlayers = totalPlayers;
		
		setPlayers(playerNames);
		fillBoard();
	}
	
	void setPlayers(String[] playerNames) {
		players = new Player[totalPlayers];
		int startMoney = 30000;
		
		for(int i=0; i<totalPlayers; i++) {
			players[i] = new Player(i, playerNames[i], startMoney);
			players[i].setCurrentPosition(0);
		}
	}
	
	void fillBoard() {
		board[0] = new SpecialSquare("Start");
		board[1] = new UsualSquare("Old Kent Road", 8500, 1200,"Maroon");
		board[2] = new UsualSquare("Whitechapel Road", 3200, 500,"Maroon");
		board[3] = new UsualSquare("The Angel Islington", 9500, 1000,"Blue");
		board[4] = new UsualSquare("Euston Road", 4000, 400,"Blue");
		board[5] = new SpecialSquare("Income Tax");
		board[6] = new UsualSquare("Pentonville Road", 1500, 200,"Blue");
		board[7] = new SpecialSquare("Chance");
		board[8] = new UsualSquare("Pall Mall", 1500, 200,"Pink");
		board[9] = new SpecialSquare("Jail");
		board[10] = new UsualSquare("Whitehall", 6000, 750,"Pink");
		board[11] = new UsualSquare("Northumberland Avenue", 2500, 200,"Pink");
		board[12] = new UsualSquare("Bow Street C.O.", 2500, 200,"Orange");
		board[13] = new UsualSquare("Marlborough Street", 3500, 600,"Orange");
		board[14] = new UsualSquare("Vine Street", 2200, 200,"Orange");
		board[15] = new UsualSquare("The Strand", 3300, 300,"Red");
		board[16] = new SpecialSquare("Community Chest");
		board[17] = new UsualSquare("Fleet Street", 5000, 550,"Red");
		board[18] = new SpecialSquare("Sports Club");
		board[19] = new UsualSquare("Trafalgar Square", 2500, 200,"Red");
		board[20] = new SpecialSquare("Chance");
		board[21] = new UsualSquare("Leicester Square", 4000, 400,"Yellow");
		board[22] = new UsualSquare("Coventry Street", 2000, 150,"Yellow");
		board[23] = new UsualSquare("Piccadilly", 2500, 200,"Yellow");
		board[24] = new UsualSquare("Regent Street", 10500, 1200,"Green");
		board[25] = new UsualSquare("Oxford Street", 6500, 800,"Green");
		board[26] = new UsualSquare("Bond Street", 3500, 300,"Green");
		board[27] = new SpecialSquare("Water World Resort");
		board[28] = new UsualSquare("Park Lane", 7000, 900,"Navy");
		board[29] = new SpecialSquare("Community Chest");
		board[30] = new UsualSquare("Mayfair", 4000, 400,"Navy");
		board[31] = new SpecialSquare("Wealth Tax");
		board[32] = new UsualSquare("Kings Cross Station", 2500, 200,"White");
		board[33] = new UsualSquare("Marylebone Station", 3000, 300,"White");
		board[34] = new UsualSquare("Fenchurch Street Station", 5500, 500,"White");
		board[35] = new UsualSquare("Liverpool Street Station", 4000, 400,"White");
	}
	
	boolean changePlayerPosition(int playerId, int dieValue) {
		int playerPosition = players[playerId].getCurrentPosition();
		
		int previousPosition =  playerPosition;
		
		playerPosition = (previousPosition+dieValue)%36;
		players[playerId].setCurrentPosition(playerPosition);
		
		System.out.println("\nPlayer " + (playerId+1) + " is now on position " + players[playerId].getCurrentPosition());
		
		if(players[playerId].getCurrentPosition() < previousPosition) {
			if(players[playerId].getCurrentPosition() == 0) {
				players[playerId].updateMoney("give", 2000);
				
				System.out.println("Player " + (playerId+1) + " is on Start and is awarded 2000\n");
			}
			else {
				players[playerId].updateMoney("give", 1500);
				
				System.out.println("Player " + (playerId+1) + " has passed Start and is awarded 1500\n");
			}
		}
		
		playAction(players[playerId].getCurrentPosition(), playerId, dieValue);
		return checkStatus(playerId);
	}
	
	void playAction(int position, int playerId, int dieValue) {
		if(board[position].getSquareType().compareToIgnoreCase("usual") == 0)
			usualSquareAction(position, playerId);
		else 
			specialSquareAction(position, playerId, dieValue);
	}
	
	void usualSquareAction(int position, int playerId) {
		Scanner sc = new Scanner(System.in);
		
		String response;
		UsualSquare usualSquare = (UsualSquare) board[position];
		
		System.out.println("Landed on " + usualSquare.getSquareName() + " " + usualSquare.getSQuareDetails());
		
		if(usualSquare.getOccupiedStatus() == false) {
			if(players[playerId].getMoney() > usualSquare.getBuyPrice()) {
				System.out.println("Do you want to buy (yes/no) " + usualSquare.getSquareName());
				
				response = sc.next();
				
				if(response.compareToIgnoreCase("yes") == 0) {
					players[playerId].updateMoney("take", usualSquare.getBuyPrice());
					players[playerId].occupySqaure();
					players[playerId].places.add(usualSquare.getSquareName());
                                        String ch = usualSquare.getColor();
                                        players[playerId].color.put(ch,players[playerId].color.getOrDefault(ch,0)+1);
					usualSquare.setOccupiedStatus(true);
					usualSquare.setOccupant(playerId);
				}
				else {
					System.out.println("Not bought");
				}
			}
			else {
				System.out.println("Cant buy " + usualSquare.getSquareName());
			}
		}
                
		else {
			if(usualSquare.getOccupant() != playerId) {
				players[playerId].updateMoney("take", usualSquare.getRent());
				players[usualSquare.getOccupant()].updateMoney("give", usualSquare.getRent());
				
				System.out.println("Player " + (playerId+1) + " has paid " + usualSquare.getRent() + " to player " + (usualSquare.getOccupant()+1));
			}
                        else if(usualSquare.getOccupant() == playerId) {
                            System.out.println("Do you want ot build a house (yes/no)");
                            response = sc.next();
                            if(response=="yes"){
                                usualSquare.incRent(150);
                            }
                            System.out.println("Rent of " + usualSquare.getSquareName() + " increased to "+usualSquare.getRent());
			}
			else {
				System.out.println("Player " + (playerId+1) + " already has this card");
			}
		}
	}
	
	void specialSquareAction(int position, int playerId, int dieValue) {
		if(position == 9) {
			System.out.println("Player " + (playerId+1) + " is now in Jail. Player has to skip a turn and also pay 500 to the bank");
			
			players[playerId].updateMoney("take", 500);
			players[playerId].setSkipChance(true);
		}
		else if(position == 18) {
			System.out.println("Player " + (playerId+1) + " is now at the Sports Club. Player has to pay 100 to every other player");
			
			for(int i=0; i<totalPlayers; i++) {
				if(i == playerId)
					continue;
				
				players[playerId].updateMoney("take", 100);
				players[i].updateMoney("give", 100);
			}
		}
		else if(position == 27) {
			System.out.println("Player " + (playerId+1) + " is now at the Water World Resort. Ever other player will pay 100 to player " + (playerId+1));
			
			for(int i=0; i<totalPlayers; i++) {
				if(i == playerId)
					continue;
				
				players[i].updateMoney("take", 100);
				players[playerId].updateMoney("give", 100);
			}
		}
		else if(position == 5) {
			System.out.println("Player " + (playerId+1) + " has arrived on Income Tax");
			
			players[playerId].updateMoney("take", players[playerId].getNumberOfOccupiedSquares()*50);
		}
		else if(position == 31) {
			System.out.println("Player " + (playerId+1) + " has arrived on Wealth Tax");
			
			players[playerId].updateMoney("take", players[playerId].getMoney()/100);
		}
		else {
			SpecialSquare specialSquare = (SpecialSquare) board[position];
			
			System.out.println("\n" + specialSquare.getSquareName());
			
			String operation = "";
			
			if(dieValue%2 == 0)
				operation = (specialSquare.getSquareName().compareToIgnoreCase("community chest") == 0)?"take":"give";
			else 
				operation = (specialSquare.getSquareName().compareToIgnoreCase("chance") == 0)?"take":"give";
			
			int[] squareResponse = specialSquare.chanceCommunityChest(specialSquare.getSquareName(), dieValue);
			
			if(squareResponse[1] == 0) {
				players[playerId].updateMoney("give", squareResponse[0]);
				players[playerId].setCurrentPosition(0);
			}
			else if(squareResponse[1] < 0)
				players[playerId].updateMoney(operation, squareResponse[0]);
			else {
				players[playerId].updateMoney(operation, squareResponse[0]);
				players[playerId].setCurrentPosition(squareResponse[1]);
				
				if(players[playerId].getCurrentPosition() == 9) {
					System.out.println("You are in jail therefore you have to lost 500 and have to skip a chance");
					players[playerId].setSkipChance(true);
				}
				else
					usualSquareAction(squareResponse[1], playerId);
			}
		}
	}
	
	void getPlayersStatus() {
		System.out.println("\nPLayers' status");
		
		for(int i=0; i<totalPlayers; i++) {
			System.out.println("PLayer " + (i+1) + " is on position: " + players[i].getCurrentPosition() + " and has money " + players[i].getMoney()+ " has: " + players[i].getPlaces());
			//System.out.println("PLayer " + (i+1) + " has: " + players[i].getColor());
                        
		}
		
		System.out.println();
	}
	
	boolean checkJail(int playerId) {
		return players[playerId].getSkipStatus();
	}
	
	void freeFromJail(int playerId) {
		players[playerId].setSkipChance(false);
	}
	
	boolean checkStatus(int playerId) {
		if(players[playerId].getMoney() < 0)
			return false;
		else
			return true;
	}
}
