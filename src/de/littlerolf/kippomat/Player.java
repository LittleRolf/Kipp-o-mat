package de.littlerolf.kippomat;

public interface Player {
	
	public int makeTurn(int sum, int prevNum);
	public void initialize(int winSum,int startSum, boolean isFirst);
	
	
}
