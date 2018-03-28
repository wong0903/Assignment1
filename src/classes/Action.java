package classes;

public enum Action {
	UP("^"),
	RIGHT(">"), 
	DOWN("v"),
	LEFT("<");
	
	private String action;
	
	private Action(String action) {
		this.setAction(action);
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
}
