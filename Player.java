
import java.util.*;


public class Player {
	private String name;
	private int id;
	private int currentPosition;
	private int money;
	private int numberOfOccupiedSquares;
	private boolean skipChance;
        
        ArrayList<String> places = new ArrayList<>();
        //ArrayList<String> color;// = new ArrayList<>();
        HashMap<String,Integer> color;
	
	public Player(int id, String name, int money) {
		this.name = name;
		this.id = id;
		this.money = money;
		numberOfOccupiedSquares = 0;
		skipChance = false;
                this.places=new ArrayList<>();
                this.color=new HashMap<>();
	}
	
	String getPlayerName() {
		return name;
	}
	
	int getPlayerId() {
		return id;
	}
	
	int getCurrentPosition() {
		return currentPosition;
	}
	
	void setCurrentPosition(int position) {
		currentPosition = position;
	}

        String getPlaces(){
            return places.toString();
        }
        
        String getColor(){
            return color.toString();
        }
        
	int getMoney() {
		return money;
	}
	
	void updateMoney(String operation, int amount) {
		if(operation.compareToIgnoreCase("take") == 0)
			money -= amount;
		if(operation.compareToIgnoreCase("give") == 0)
			money += amount;
	}
	
	int getNumberOfOccupiedSquares() {
		return numberOfOccupiedSquares;
	}
	
	void occupySqaure() {
		numberOfOccupiedSquares += 1;
	}
	
	void setSkipChance(boolean skip) {
		skipChance = skip;
	}
	
	boolean getSkipStatus() {
		return skipChance;
	}
}
