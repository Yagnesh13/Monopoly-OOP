
public class SpecialSquare extends Square {
	
	public SpecialSquare(String name) {
		super(name, "Special");
	}

	int[] chanceCommunityChest(String type, int dieValue) {
		int[] response = new int[2];
		
		if(type.compareToIgnoreCase("community chest") == 0) {
			switch(dieValue) {
			case 2:
				System.out.println("Lost 2000");
				response[0] = 2000;
				response[1] = -1;
				return response;
			case 3:
				System.out.println("Collect 2500");
				response[0] = 2500;
				response[1] = -1;
				return response;
			case 4:
				System.out.println("Lost 1000");
				response[0] = 1000;
				response[1] = -1;
				return response;
			case 5:
				System.out.println("Collect 1500");
				response[0] = 1500;
				response[1] = -1;
				return response;
			case 6:
				System.out.println("Lost 3500");
				response[0] = 3500;
				response[1] = -1;
				return response;
			case 7:
				System.out.println("Win 3000");
				response[0] = 3000;
				response[1] = -1;
				return response;
			case 8:
				System.out.println("Go to Jail");
				response[0] = 500;
				response[1] = 9;
				return response;
			case 9:
				System.out.println("Win 2000");
				response[0] = 2000;
				response[1] = -1;
				return response;
			case 10:
				System.out.println("Lost 1000");
				response[0] = 1000;
				response[1] = -1;
				return response;
			case 11:
				System.out.println("Collect 1500 and go to Delhi");
				response[0] = 1500;
				response[1] = 10;
				return response;
			case 12:
				System.out.println("LOst 4000");
				response[0] = 4000;
				response[1] = -1;
				return response;
			}
		}
		else {
			switch(dieValue) {
			case 2:
				System.out.println("Collect 1500 and go to Whitehall");
				response[0] = 1500;
				response[1] = 1;
				return response;
			case 3:
				System.out.println("Lost 1000");
				response[0] = 1000;
				response[1] = -1;
				return response;
			case 4:
				System.out.println("Collect 3000");
				response[0] = 3000;
				response[1] = -1;
				return response;
			case 5:
				System.out.println("Lost 2000");
				response[0] = 2000;
				response[1] = -1;
				return response;
			case 6:
				System.out.println("Win 2000 in lottery");
				response[0] = 2000;
				response[1] = -1;
				return response;
			case 7:
				System.out.println("Lost 1500");
				response[0] = 1500;
				response[1] = -1;
				return response;
			case 8:
				System.out.println("Collect 1000 and go to Whitehall");
				response[0] = 1000;
				response[1] = 26;
				return response;
			case 9:
				System.out.println("Go to Jail");
				response[0] = 500;
				response[1] = 9;
				return response;
			case 10:
				System.out.println("Collect 2500");
				response[0] = 2500;
				response[1] = -1;
				return response;
			case 11:
				System.out.println("Lose 1500");
				response[0] = 1500;
				response[1] = -1;
				return response;
			case 12:
				System.out.println("Collect 3000 and go to Start");
				response[0] = 5000;
				response[1] = 0;
				return response;
			}
		}
		return response;
	}
}
