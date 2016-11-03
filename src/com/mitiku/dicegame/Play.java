package com.mitiku.dicegame;

import java.util.Random;

public class Play 
{
	Random generator = new Random();
	public int thro;
	final  int length = 6;

	// method that'll throw the dice and get a random integer.
	public int returnRandomValue()
	{
		// the range of the dice's numbers will be from 1 to length.
		return thro = 1 + generator.nextInt( length );
	}

}
