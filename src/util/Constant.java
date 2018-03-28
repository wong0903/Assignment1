package util;

public class Constant {
	
	//Number of rows and columns in the maze
	public final static int ROWS = 6;
	public final static int COLS = 6;
	
	//Rewards for different colors of grids
	public final static double BROWN_REWARD = -1.00;
	public final static double WHITE_REWARD = -0.04;
	public final static double GREEN_REWARD = 1.00;
	
	//Transition outcome
	public final static double PROB_INTENT = 0.8;
	public final static double PROB_CW = 0.1;
	public final static double PROB_CCW = 0.1;
	
	//Discount factor
	public final static double DISCOUNT = 0.99;
	
	//Coordinates for special grids
	public final static String BrownGrid = "1,1 1,5 2,2 3,3 4,4";
	
	public final static String GreenGrid = "0,0 0,2 0,5 1,3 1,4 1,5";					
	
	public final static String Wall = "0,1 1,4 4,1 4,2 4,3";				 

	public final static String startPoint = "3,2";
	
	//Maximum Reward
	public final static int Rmax = 1;
	
	public final static double c = 0.1;
	
	//Epsilon e = c * Rmax
	public final static double maxError = 0.1
			;
	// Constant k (i.e. number of times simplified Bellman update is executed
	// to produce the next utility estimate)
	public final static int K = 10;
}
