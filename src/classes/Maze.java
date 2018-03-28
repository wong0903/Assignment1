package classes;

import util.Constant;
 
public class Maze {
	private Grid[][] maze;
	
	public Maze() {
		this.maze = new Grid[Constant.ROWS][Constant.COLS];
		this.generateMaze();	
	}
	
	public Maze(Maze maze) {
		this.maze = new Grid[Constant.ROWS][Constant.COLS];
		for(int x=0; x < Constant.ROWS; x++) {
			for(int y=0; y < Constant.COLS; y++) {
				this.maze[x][y] = new Grid(maze.getMaze()[x][y]);
			}
		}
	}

	public void generateMaze() {
		Grid grid;
		for(int x=0; x < Constant.ROWS; x++) {
			for(int y=0; y < Constant.COLS; y++) {
				grid = new Grid(x,y,Constant.WHITE_REWARD,false);
				this.maze[x][y] = grid;
				}
			}	
		
		String brownCoords[] = Constant.BrownGrid.split("\\s+");
		for(String s : brownCoords)
		{
			String coordXY[] = s.split(",");
			int row = Integer.parseInt(coordXY[0]);
			int col = Integer.parseInt(coordXY[1]);
			grid = new Grid(row,col,Constant.BROWN_REWARD,false);
			this.maze[row][col] = grid;
		}
		
		String greenCoords[] = Constant.GreenGrid.split("\\s+");
		for(String s : greenCoords)
		{
			String coordXY[] = s.split(",");
			int row = Integer.parseInt(coordXY[0]);
			int col = Integer.parseInt(coordXY[1]);
			grid = new Grid(row,col,Constant.GREEN_REWARD,false);	
			this.maze[row][col] = grid;
		}
		
		String wallCoords[] = Constant.Wall.split("\\s+");
		for(String s : wallCoords)
		{
			String coordXY[] = s.split(",");
			int row = Integer.parseInt(coordXY[0]);
			int col = Integer.parseInt(coordXY[1]);
			grid = new Grid(row,col,0.0,true);	
			this.maze[row][col] = grid;
		}
	}
	
	public void drawMaze() {
		System.out.println("Maze:");
		for(int x=0; x < Constant.ROWS; x++) {
			for(int y=0; y < Constant.COLS; y++) {
				if(this.maze[x][y].isWall() == true) {
					System.out.print(String.format("%8s", "[Wall]"));
				}
				else {
					System.out.print(String.format("%8s", "["+this.maze[x][y].getReward()+"]"));
				}
			}
			System.out.println("");
		}
	}
	
	public Grid[][] getMaze(){
		return maze;
	}
}
