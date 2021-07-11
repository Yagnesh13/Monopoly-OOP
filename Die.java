import java.util.Random;

public class Die {
	public int getDieValue() {
		Random roll = new Random();
		
		int firstDice = roll.nextInt(6) + 1;
		int secondDice = roll.nextInt(6) + 1;
		
		System.out.println("\nDie rolled has values "+firstDice+" and "+secondDice);
		return firstDice + secondDice;
	}
}
