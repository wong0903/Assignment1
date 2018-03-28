package classes;

public class Grid {
	private int row;
	private int col;
	private double reward;
	private double utility;
	private boolean wall;
	private Action action;
	
	public Grid(int row, int col, double reward, boolean wall){
		this.setRow(row);
		this.setCol(col);
		this.setReward(reward);
		this.setWall(wall);
		this.setUtility(0.0);
		this.action = Action.UP;
	}
	
	//Copy constructor
	public Grid(Grid grid) {
        this.row = grid.getRow();
        this.col = grid.getCol();
        this.reward = grid.getReward();
        this.wall = grid.isWall();
        this.utility = grid.getUtility();
        this.action = grid.getAction();
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public boolean isWall() {
		return wall;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}


}
