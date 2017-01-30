/* CS 225 - Intro to Computer Science
 * File Name: CS225_Lab4.java
 * Java Programming
 * Lab 4 - Due X/XX/XXXX
 * Instructor: Dr. Dan Grissom
 * 
 * Name 1: Sean Billideau
 * Name 2: 
 * Description: This file contains the source code for Lab 4.
 */

///////////////////////////////////////////////////////////////////////////////
// INSTRUCTIONS: Update the header above EACH LAB with the correct due date,
// first/last names (remove the "Name 2" line if working alone) and description
// for this specific lab. You should also update the first/last names and problem
// number (if relevant) below in the "System.out.println" statement at the beginning
// of the "main()" method. Failure to do so will result in lost points. DO NOT change
// the name of the class or the autograder will give you 0 points.
//
// PHILOSOPHY: Lab is a chance to "get your feet wet" in a safe environment as it
// will be the first time you'll be trying to program new concepts. Thus, as you'll
// see below, the collaboration rules for LABS are very generous since the main idea
// is for you to learn programming with a lot of resources. To foster this 
// environment, you'll have access to a programming partner of your choice, your peers,
// experienced lab technicians and your instructor.
//
// COLLABORATION: Students may officially work with ONE (1) partner. Both names should
// be listed in the header and in the initial "System.out.println()" statement. When
// you submit your lab, only one partner should submit it (again, make sure both names
// are on all files). Students MAY seek advice and look at other students' code DURING
// lab for tips (including students who are not your direct partner), but may NOT
// copy/paste code from students other than your official partner.
//
// FINISHING & GRADING: Lab assignments must be performed in the Computer Science
// Laboratory each week. You are required to attend every lab session. Labs usually
// consist of several book problems (sometimes there are none) and 1-2 code problems.
// The book problems and code should both be turned in when fully completed. 
//
// When you finish a code problem during lab, you will demonstrate your working program
// to the instructor or to a lab-tech/TA on duty and you and the instructor will both
// sign off on that problem. At that point, you will receive full credit for that problem.
// If you did not finish one or more code problems by the end of lab, you may sign off with
// an instructor/lab-tech/TA during the last 15 minutes of lab; in this event, you will not
// be deducted points for not finishing , but will be graded based on your final
// submission. Book problems will always be graded via the submission and will not be checked
// off during lab.
//
// If you do not finish your lab assignment during the lab period, you and your partner may
// only seek help with your code from lab technicians (available whenever the lab is open)
// and the instructor to aid with any confusion; please refer to posted lab times at the
// entrance of the Main Computer Lab to see when lab techs and computers are available.
// You may also complete the labs on your own machine if youd like (this is preferable).
///////////////////////////////////////////////////////////////////////////////
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
		// TODO 6: Instantiate offerHistory using the new keyword
		
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
		
		// TODO 2: Write code which returns the banker's offer as a double,
		// according to the description in this method's comment above.
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
			
			// TODO 3: Set the gameIsOver variable to true
			// Inform the user that the game is over and how much money they accepted from the banker.
			// Print the offer history (there is a method to call for this).
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
			// TODO 5: Set the gameIsOver variable to true
			// Let the user know what prize they won behind the final door!
			// Print the offer history (there is a method to call for this).
		//else {printOfferHistory(0.0);
		
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
		// TODO 8: print out the banker offer history (will need to loop through
		// your offerHistory member variable) and find the max offer made.
		// Print one offer per line, like:
		// Offer 1: $10.00
		// Offer 2: $5.00
		// .....
		;		
		// TODO 9: If the max offer was greater than the accepted prize, then print out
		// the $$$ left out on the table (see the example in this method's header above).
		// Otherwise, congratulate the user that they won more than the banker's max
		// offer and display the banker's max offer.
	}
	
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////DO NOT EDIT ANY PORTIONS OF METHODS BELOW///////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
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
