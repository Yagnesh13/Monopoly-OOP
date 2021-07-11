
public class UsualSquare extends Square {
	private int buyPrice;
	private int rent;
	private boolean occupied;
	private int occupiedPlayerId;
        private String col;
	
	public UsualSquare(String name, int buyPrice, int rent,String col) {
		super(name, "Usual");
		this.buyPrice = buyPrice;
		this.rent = rent;
                this.col = col;
		occupied = false;
		occupiedPlayerId = -1;
	}
	
	boolean getOccupiedStatus() {
		return occupied;
	}
	
        String getColor(){
            return col;
        }
        
	void setOccupiedStatus(boolean occupiedStatus) {
		occupied = occupiedStatus;
	}
	
	int getRent() {
		return rent;
	}
	
	int getBuyPrice() {
		return buyPrice;
	}
	
        void incRent(int amt){
            this.rent+=amt;
        }
        
	int getOccupant() {
		return occupiedPlayerId;
	}
	
	void setOccupant(int occupant) {
		occupiedPlayerId = occupant;
	}
	
	String getSQuareDetails() {
		return "Buying Price: " + buyPrice + " Rent: " + rent;
	}
}
