package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Constant;

public class Utility {
	
	//Calculate the utility for each action and return the action with max utility
	public static Action findBestAction(Grid[][] nextGrid, int row, int col) {
		Action bestAction = Action.UP;
		List<Double> listBestAction = new ArrayList<Double>();
		listBestAction.add(calcUpUtility(nextGrid, row, col));
		listBestAction.add(calcDownUtility(nextGrid, row, col));
		listBestAction.add(calcRightUtility(nextGrid, row, col));
		listBestAction.add(calcLeftUtility(nextGrid, row, col));
		int indexOfBestAction = listBestAction.indexOf(Collections.max(listBestAction));
		switch(indexOfBestAction) {
			case 0: bestAction = Action.UP;
					break;
			case 1: bestAction = Action.DOWN;
					break;
			case 2: bestAction = Action.RIGHT;
					break;
			case 3: bestAction = Action.LEFT;
					break;
		}
		return bestAction;
	}
	
	//Calculate and find best utility
	public static double calcBestActionUtility(Grid[][] nextGrid, int row, int col) {
		List<Double> listBestUtility = new ArrayList<Double>();
		listBestUtility.add(calcUpUtility(nextGrid, row, col));
		listBestUtility.add(calcDownUtility(nextGrid, row, col));
		listBestUtility.add(calcRightUtility(nextGrid, row, col));
		listBestUtility.add(calcLeftUtility(nextGrid, row, col));
		return nextGrid[row][col].getReward() + Constant.DISCOUNT * Collections.max(listBestUtility);
	}
	
	//Calculate the utility for a given action
	public static double calcActionUtility(Grid[][] nextGrid, int row, int col, Action action) {
		double utility = 0.0;
		switch(action) {
				case UP: 
					utility = calcUpUtility(nextGrid, row, col);
				break;
				case DOWN: 
					utility = calcDownUtility(nextGrid, row, col);
				break;
				case RIGHT: 
					utility = calcRightUtility(nextGrid, row, col);
				break;
				case LEFT: 
					utility = calcLeftUtility(nextGrid, row, col);
				break;
		}
		return nextGrid[row][col].getReward() + Constant.DISCOUNT * utility;
	}
	
	//Calculate the utility when the action is up
	public static double calcUpUtility(Grid[][] nextGrid, int row, int col) {
		double utility = 0.0;
		utility = Constant.PROB_INTENT * getUpUtility(nextGrid, row, col)
				+ Constant.PROB_CW * getRightUtility(nextGrid, row, col)
				+ Constant.PROB_CCW * getLeftUtility(nextGrid, row, col);
		
		return utility;
	}
	
	//Calculate the utility when the action is down
	public static double calcDownUtility(Grid[][] nextGrid, int row, int col) {
		double utility = 0.0;
		utility = Constant.PROB_INTENT * getDownUtility(nextGrid, row, col)
				+ Constant.PROB_CW * getLeftUtility(nextGrid, row, col)
				+ Constant.PROB_CCW * getRightUtility(nextGrid, row, col);
		
		return utility;
	}
	
	//Calculate the utility when the action is right
	public static double calcRightUtility(Grid[][] nextGrid, int row, int col) {
		double utility = 0.0;
		utility = Constant.PROB_INTENT * getRightUtility(nextGrid, row, col)
				+ Constant.PROB_CW * getDownUtility(nextGrid, row, col)
				+ Constant.PROB_CCW * getUpUtility(nextGrid, row, col);
		
		return utility;
	}
	
	//Calculate the utility when the action is left
	public static double calcLeftUtility(Grid[][] nextGrid, int row, int col) {
		double utility = 0.0;
		utility = Constant.PROB_INTENT * getLeftUtility(nextGrid, row, col)
				+ Constant.PROB_CW * getUpUtility(nextGrid, row, col)
				+ Constant.PROB_CCW * getDownUtility(nextGrid, row, col);
		
		return utility;
	}
	
	/*
	 * Find the utility above the current state
	 * agent will stay at the same place
	 * if it is out of bound and not a wall
	 * */
	public static double getUpUtility(Grid[][] nextGrid, int row, int col) {
		return (row - 1 >= 0 && !nextGrid[row-1][col].isWall())? 
				nextGrid[row-1][col].getUtility() : nextGrid[row][col].getUtility();
	}
	
	/*
	 * Find the utility below the current state
	 * agent will stay at the same place
	 * if it is out of bound and not a wall
	 * */
	public static double getDownUtility(Grid[][] nextGrid, int row, int col) {
		return (row + 1 < Constant.ROWS && !nextGrid[row+1][col].isWall())? 
				nextGrid[row+1][col].getUtility() : nextGrid[row][col].getUtility();
	}
	
	/*
	 * Find the utility on the right of the current state
	 * agent will stay at the same place
	 * if it is out of bound and not a wall
	 * */
	public static double getRightUtility(Grid[][] nextGrid, int row, int col) {
		return (col + 1 < Constant.COLS && !nextGrid[row][col+1].isWall())? 
				nextGrid[row][col+1].getUtility() : nextGrid[row][col].getUtility();
	}
	
	/*
	 * Find the utility on the left of the current state
	 * agent will stay at the same place
	 * if it is out of bound and not a wall
	 * */
	public static double getLeftUtility(Grid[][] nextGrid, int row, int col) {
		return (col - 1 >= 0 && !nextGrid[row][col-1].isWall())? 
				nextGrid[row][col-1].getUtility() : nextGrid[row][col].getUtility();
	}


}
