package de.littlerolf.kippomat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human implements Player {

	BufferedReader br;
	
	@Override
	public int makeTurn(int sum, int prevNum) {
		System.out.println("   Current sum: " + sum);
		System.out.println("   Previous number: " + prevNum);
		System.out.print("   Enter num:");
		int i = 0; //default value
		try {
			i = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public void initialize(int winSum,int startSum, boolean isFirst) {
		System.out.println("   You are " + ((isFirst)? "" : "not") + "first");
		System.out.println("   The win number is " + winSum + ", starting by " + startSum);
		br = new BufferedReader(new InputStreamReader(System.in));

	}

}
