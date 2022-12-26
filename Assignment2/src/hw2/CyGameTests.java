package hw2;

public class CyGameTests {
	public static void main(String args[]) { 
		CyGame game = new CyGame(3, 200);
		System.out.println(game); 
		   
		// Player 1 to JUMP_FORWARD square 
		game.roll(1); 
		game.roll(4); 
		System.out.println(game); 
		   
		
}
}