
public class CS225_Lab4_Client
{
	public static void main(String[] args)
	{
		
		System.out.println("Sean Billideau");
		
		System.out.println("");
	
		CS225_Lab4 game = new CS225_Lab4(); // Create new game
		
		// Keep playing the game until it is over
		while (!game.gameIsOver())
		{
			game.playNextTurn();
		}
		System.out.println("\n\n\nThank you for playing!");
	}
}
