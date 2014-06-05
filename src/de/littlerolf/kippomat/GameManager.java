package de.littlerolf.kippomat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameManager {

	private int winNum;
	
	private Player p1, p2;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Geben Sie die Gewinnnummer ein:");
		int i = 10; //default value
		try {
			i = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GameManager gm = new GameManager(i);
		gm.runGame();
	}
	
	public GameManager(int winNum) {
		this.winNum = winNum;
		
		p1 = new Bot("Alf");
		p2 = new Bot("Peter");
	}
	
	public void runGame() {
		int currentSum = 0;
		
		int startNum = 1;
		int lastNum = startNum;
		
		boolean beginner = (Math.random() > 0.5);
		p1.initialize(winNum, currentSum, beginner);
		p2.initialize(winNum, currentSum, !beginner);
		
		while(currentSum < winNum) {
			System.out.println("[GM] Player " + ((beginner)? "1" : "2") + " playing");
			int num = ((beginner)? p1 : p2).makeTurn(currentSum, lastNum);
			System.out.println("[GM] Player chose number " + num);
			lastNum = num;
			currentSum += num;
			System.out.println("[GM] Sum now is " + currentSum);
			beginner = !beginner;
		}
		System.out.println("[GM] Player " + ((beginner)? "1": "2") + " won the game");
		
	}
}
