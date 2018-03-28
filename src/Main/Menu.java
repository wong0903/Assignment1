package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.*;
import util.*;

public class Menu {
	public static Maze generator;
	public static List<Maze> memory; 
	public static void main(String[] args) {
		//Generate the maze
		generator = new Maze();
		memory = new ArrayList<Maze>();
		
		//Display the maze
		generator.drawMaze();
		
		System.out.println("1. Policy Iteration");
		System.out.println("2. Value Iteration");
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Choose a method:");
		int i = reader.nextInt();
		reader.close();
		
		if(i == 1) {
			memory = policyIteration(generator.getMaze());
			FileIO.writeUtility(memory, "policyIteration_part2.csv");
			Display.displayOptimalPolicy(generator.getMaze());
			Display.displayStateUtilities(generator.getMaze());
		}else if (i == 2) {
			memory = valueIteration(generator.getMaze());
			FileIO.writeUtility(memory, "valueIteration_part2.csv");
			Display.displayOptimalPolicy(generator.getMaze());
			Display.displayStateUtilities(generator.getMaze());
		}
	}
	

	public static List<Maze> policyIteration(Grid[][] maze) {
		int iter = 0;
		boolean converge;
		Action bestAction;
		
		do {
			//update the number of iterations
			iter++;
			
			//update the converge
			converge = true;
			
			//save all the previous maze 
			memory.add(new Maze(generator));
			
			//update the policy for each state
			policyEvaluation(maze);
			for(int row = 0; row < Constant.ROWS; row++) {
				for (int col = 0; col < Constant.COLS; col++) {
					if(maze[row][col].isWall()) {
						continue;
					}
					//Find the best action using the updated utility
					bestAction = Utility.findBestAction(maze, row, col);
					
					//Check if it has already converge
					if(bestAction != maze[row][col].getAction()) {
						converge = false;
						maze[row][col].setAction(bestAction);
					}
				}
			}
		}while(!converge);
		System.out.println("Number of Iterations: " + iter);
		System.out.println("");
		memory.add(new Maze(generator));
		return memory;
	}
	
	public static void policyEvaluation(Grid[][] maze) {
		Maze dupMaze = new Maze(generator);
		Grid[][] dupGrid = dupMaze.getMaze();
		double utility = 0.0;
		for(int i = 0; i < Constant.K; i++) {
			for(int row = 0; row < Constant.ROWS; row++) {
				for (int col = 0; col < Constant.COLS; col++) {
					if(maze[row][col].isWall()) {
						continue;
					}
					//Estimate the utility for each state
					utility = Utility.calcActionUtility(dupGrid, row, col, dupGrid[row][col].getAction());
					maze[row][col].setUtility(utility);
				}
			}
			dupMaze = new Maze(generator);
			dupGrid = dupMaze.getMaze();
		}
	}
		
	public static List<Maze> valueIteration(Grid[][] maze) {
		double delta;
		int iter = 0;
		double nextUtility, utility = 0.0;
		Maze dupMaze = new Maze(generator);
		Grid[][] dupGrid = dupMaze.getMaze();
		do {
			//maximum change in the utility of any state in an iteration
			delta = 0.0;
			iter++;
			memory.add(new Maze(generator));
			
			//Calculate new utility for each state
			for(int row = 0; row < Constant.ROWS; row++) {
				for(int col = 0; col < Constant.COLS; col++) {
					//ignore if it is a wall
					if(maze[row][col].isWall()) {
						continue;
					}
					utility = maze[row][col].getUtility();
					nextUtility = Utility.calcBestActionUtility(dupGrid, row, col);
					maze[row][col].setUtility(nextUtility);
					
					//Check if it has converge
					if(Math.abs(nextUtility - utility) > delta ) {
						delta = Math.abs(nextUtility - utility) ;
					}
				}
			}
			dupMaze = new Maze(generator);
			dupGrid = dupMaze.getMaze();
			//Terminating condition: Max Norm less than convergence criteria
		}while(delta >= Constant.maxError * (1 - Constant.DISCOUNT)/Constant.DISCOUNT);
		
		for(int row = 0; row < Constant.ROWS; row++) {
			for(int col = 0; col < Constant.COLS; col++) {
				if(maze[row][col].isWall()) {
					continue;
				}
				maze[row][col].setAction(Utility.findBestAction(maze, row, col));
			}
		}
		System.out.println("Number of Iterations: " + iter);
		System.out.println("");
		memory.add(new Maze(generator));
		return memory;
	}
}


