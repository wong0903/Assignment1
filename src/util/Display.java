package util;

import classes.Grid;

public class Display {
	//Display the optimal policy on the screen
	public static void displayOptimalPolicy(Grid[][] maze) {
		StringBuilder sb = new StringBuilder();
		sb.append("Optimal Policy:\n");
		for (int row = 0; row < Constant.ROWS; row++) {
            for (int col = 0; col < Constant.COLS; col++) {
                if (maze[row][col].isWall()) {
                    sb.append(String.format("%3s", "W"));
                } else {
                    sb.append(String.format("%3s", maze[row][col].getAction().getAction()));
                }
            }

            sb.append("\n");
        }
        System.out.println(sb.toString());
	}
	
	//Display the utilities for all the states on the screen
	public static void displayStateUtilities(Grid[][] maze) {
		StringBuilder sb = new StringBuilder();
		sb.append("Utilities of all states:\n");
		for (int row = 0; row < Constant.ROWS; row++) {
            for (int col = 0; col < Constant.COLS; col++) {
            	sb.append("|");
            	sb.append(String.format("%8.4f", maze[row][col].getUtility()));
            	}
            sb.append("|");
            sb.append("\n");
        	}
        sb.append("\n");
        System.out.println(sb.toString());
        }
}


