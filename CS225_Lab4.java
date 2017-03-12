
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class CS225_Lab4
{
	private final Scanner scan = new Scanner(System.in);
	private final DecimalFormat df = new DecimalFormat("$#,##0.00");
	private final double [] cashPrizes = {.01, .50, 1, 5, 10, 50, 100, 250, 500, 1000, 5000, 10000, 100000, 500000, 1000000};
	private ArrayList<Double> remainingPrizesBoard;
	private boolean gameIsOver;
	private ArrayList<Double> offerHistory = new ArrayList<Double>();
	
	/////////////////////////////////////////////////////////////////////
	// The constructor initializes remainingPrizesBoard to a new board
	// containing a random ordering of all the prizes.
	/////////////////////////////////////////////////////////////////////
	CS225_Lab4()
	{
		System.out.println("Let's play DEAL OR NO DEAL!");
		
		gameIsOver = false; // New game, so game is not over!
		initializeRandomPrizeBoard();
		
		
	}

	/////////////////////////////////////////////////////////////////////
	// Prints the locations available to choose from
	// (0 through numRemainingPrizes-1)
	/////////////////////////////////////////////////////////////////////
	private void printRemainingPrizeBoard()
	{
		// Copies remaining prizes ArrayList into prizes ArrayList (a temp ArrayList)
		ArrayList<Double> prizes = new ArrayList();
		for (double prize : remainingPrizesBoard)
			prizes.add(prize);
		
		Collections.sort(prizes);
		for(int i = 0; i < prizes.size(); i++);
		{
		System.out.println((prizes));
		System.out.println();
		}
		// TODO 1: Print the prizes in the prize ArrayList. All values should be on
		// a single line (put a few spaces after each prize) and add a new line
		// at the end (simple for loop)
	}
	
	/////////////////////////////////////////////////////////////////////
	// Generates the banks offer. The banker algorithm computes the average
	// value of the remaining prizes and then offers 85% of the average. 
	/////////////////////////////////////////////////////////////////////
	private double getBankerOffer()
	{
		double sum=0;
		for(int i = 0; i < remainingPrizesBoard.size(); i++)		
		{
			sum+=remainingPrizesBoard.get(i);
					
		}
		sum /= remainingPrizesBoard.size();
		sum *= 0.85;
		return sum;
		
		
	}

	/////////////////////////////////////////////////////////////////////
	// Takes in the selected door number and processes the result
	/////////////////////////////////////////////////////////////////////
	private void chooseDoor(int door)
	{
		offerHistory.add(getBankerOffer());
			
		// TODO 7: Add the current bank offer (remember, we have a method
		// for this) to the our offerHistory 
		
		if (door == -1)
		{
			gameIsOver = true;
					System.out.println("The game is over");
					System.out.println("You accepted " + df.format(offerHistory.get(offerHistory.size()-1) ) );
					for(int i = 0; i<offerHistory.size(); i++)
						System.out.println("offer " + (i+1) + ": "+ df.format(offerHistory.get(i)));
					
			// This block is executed when the player accepts the banker's offer. Thus the game is over.
			
			
		}
		else
		{
			// This block is executed when the player selects one of the remaining doors.
			remainingPrizesBoard.get(door);
			System.out.println("You chose door " + door + " with prize " + remainingPrizesBoard.get(door));
			remainingPrizesBoard.remove(door);
			// TODO 4: Obtain the prize behind the proper door and remove the prize from the board
			// Print out which door the user selected and what prize was behind it (this prize is now gone)
		}
		
		// If only one prize remaining, game is over!!!
		if (remainingPrizesBoard.size() == 1){
			// This block is executed when there is only one more prize remaining...so it is what they win!
			gameIsOver = true;
			System.out.println("You won the game");
			System.out.println("Behind the final door is the prize " + df.format(remainingPrizesBoard.get(0)));
			
			printOfferHistory((remainingPrizesBoard.get(0)));
		}
			
	}
		
	/////////////////////////////////////////////////////////////////////
	// This method is called when the game is over, and thus takes as a
	// parameter the prize that was accepted (either from the banker's offer
	// or the final door).
	//
	// Prints out the offers made by the banker in chronological order.
	// Then, prints out the money left on the table (for example, if the
	// largest offer from the banker was $250,000, and you ended up winning
	// $1,000, whether from a later banker offer or from the last door,
	// then you "left $249,000 on the table).
	/////////////////////////////////////////////////////////////////////
	private void printOfferHistory(double acceptedPrize)
	{
		//double moneyLeft=0;
		// Print out the history of offers from the banker
		for(int i = 0; i<offerHistory.size();i++)
			System.out.println("offer " + (i+1) +" " + df.format( offerHistory.get(i)));
		Double max = 0.0;
		for(int i = 0; i<offerHistory.size(); i++)
		{
			if	( offerHistory.get(i) > max){

				max=offerHistory.get(i);
			}
		}
		System.out.println("The max offer was " + df.format(max));
		if
		( max > getBankerOffer()){
			//for	(int i = 0; i<offerHistory.size(); i ++)
			//if(max > offerHistory.get(i))
			// moneyLeft = offerHistory.get(i);
				System.out.println("You left " + df.format(max - acceptedPrize) + " on the table");
			
		}
		else
		{
			System.out.println("You won more than the bankers offer which was " + df.format(max));
			
		}
		
	}
	

	
	
	/////////////////////////////////////////////////////////////////////
	// Processes all the code needed to execute a single turn of the game
	/////////////////////////////////////////////////////////////////////
	public void playNextTurn()
	{
		System.out.println("-----------------------------------------------------------------------");
		
		// Print out remaining prizes
		System.out.println("There are " + remainingPrizesBoard.size() + " prizes remaining, listed in ascending order: ");
		printRemainingPrizeBoard();
		
		// Display all prize doors
		System.out.println("\nThe prizes have been dispersed randomly behind the following doors:");
		for (int i = 0; i < remainingPrizesBoard.size(); i++)
			System.out.print(i + "     ");
		System.out.println();
		
		// Print out banker's offer and ask user what to do
		System.out.println("The banker would like to make you an offer of...................." + df.format(getBankerOffer()));
		System.out.print("What would you like to do? Enter '-1' to accept the banker's offer, " +
							"or select one of the doors above (0-" + (remainingPrizesBoard.size()-1) + "): ");
		

		// Get selection from user and choose door
		int selectedDoorNum = scan.nextInt();
		if (selectedDoorNum >= -1 && selectedDoorNum < remainingPrizesBoard.size()) // Make sure valid selection
			chooseDoor(selectedDoorNum);
		else
			System.out.println(selectedDoorNum + " is not a valid selection.");
		System.out.println();
		System.out.println();
	}
	
	/////////////////////////////////////////////////////////////////////
	// Basically, a getter method for the gameIsOver method. The client
	// will continually call playNextTurn() until gameIsOver() evaluates
	// to true.
	/////////////////////////////////////////////////////////////////////
	public boolean gameIsOver()
	{
		return gameIsOver;
	}
	
	/////////////////////////////////////////////////////////////////////
	// Copies the constant prizes (from an array) into a temporary array
	// and uses that array to populate the initial board into the member
	// variable 'remainingPrizesBoard'.
	/////////////////////////////////////////////////////////////////////
	private void initializeRandomPrizeBoard()
	{
		// Start with a fresh board with nothing in it
		remainingPrizesBoard = new ArrayList<Double>();
		
		// Copies cashPrizes array into prizes ArrayList (a temp ArrayList)
		ArrayList<Double> prizes = new ArrayList();
		for (double prize : cashPrizes)
			prizes.add(prize);
		
		// Randomizes prizes into remainingPrizesBoard
		while (!prizes.isEmpty())
		{
			Random r = new Random();
			int i = r.nextInt(prizes.size()); // Pick a random remaining prize
			remainingPrizesBoard.add(prizes.get(i)); // Copy into our "board"
			prizes.remove(i);
		}
		
		// Debug print which will show the random board contents
		//for (double d : remainingPrizesBoard)
		//	System.out.print(df.format(d) + "  --  ");
		//System.out.println();
	}
}
