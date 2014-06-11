package de.littlerolf.kippomat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameManager {

	private int winSum;
	
	private Player p1, p2;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Geben Sie die Gewinnsumme ein:");
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
		this.winSum = winNum;
		
		p1 = new MinMaxBot("Alf");
		p2 = new Human();
	}
	
	public void runGame() {
		int currentSum = 0;
		
		int startNum = 1;
		int lastNum = startNum;
		
		boolean beginner = (Math.random() > 0.5);
		p1.initialize(winSum, startNum, beginner);
		p2.initialize(winSum, startNum, !beginner);
		
		while(currentSum < winSum) {
			System.out.println("[GM] Player " + ((beginner)? "1" : "2") + " playing");
			int num = 0;
			do {
				num = ((beginner)? p1 : p2).makeTurn(currentSum, lastNum);
			} while(num < 1 || num > 6 || num == lastNum || num == 7-lastNum) ;
			System.out.println("[GM] Player chose number " + num);
			
			lastNum = num;
			currentSum += num;
			System.out.println("[GM] Sum now is " + currentSum);
			beginner = !beginner;
		}
		if(currentSum == winSum) 
			System.out.println("[GM] Player " + ((beginner)? "1": "2") + " won the game");
		else
			System.out.println("[GM] Tie");
		
	}
}
