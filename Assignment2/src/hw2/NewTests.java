package hw2;

public class NewTests {
	public static void main(String args[]) { 
		CyGame game = new CyGame(16, 200);
		game.roll(5);
		System.out.println(game);
		game.roll(15);
		System.out.println(game);

}
}