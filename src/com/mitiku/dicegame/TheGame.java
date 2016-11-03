package com.mitiku.dicegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class TheGame extends Activity
{
	Play 		Myplay			= new Play ();
	String		playName		= "";
	int			Length			= 0;
	private Button		PlayBtn, ExitBtn,startAsNewUserBtn,repeatBtn;
	String		displayString 	= "";
	TextView	displaySemiResult;
	MediaPlayer btnMusic;
	
	
	int s;
 	int sum1 = 0;
    int sum2 = 0;
	
	final int INVISIBLE		= 4;
	final int VISIBLE 		= 0;
	final int GONE			= 8;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
		intitialize();
	}
	
	private void intitialize()
	{
		
		        
		Bundle bundle = getIntent().getExtras();
		if(bundle != null)
		{
			playName = bundle.getString("PlayerName");
			Length   = Integer.parseInt((bundle.getString("length")));
		}
		displaySemiResult = (TextView)findViewById(R.id.textView1);
		displaySemiResult.setText("NAME: "+playName + "\nNo. of DICE: "+ Integer.toString(Length));
		PlayBtn = (Button)findViewById(R.id.playBtn);
		ExitBtn	= (Button)findViewById(R.id.exitBtn);
		startAsNewUserBtn = (Button)findViewById(R.id.startAsnewuser);
		repeatBtn		  = (Button)findViewById(R.id.Repeat);
		btnMusic = MediaPlayer.create(this, R.raw.btnclick);

		startAsNewUserBtn.setVisibility(GONE);
		repeatBtn.setVisibility(GONE);
		        	
        PlayBtn.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{
				int s;
			 	int sum1 = 0;
		        int sum2 = 0;
			   
	        for(int i = 0;i<Length; i++)
	        {
	        	s = Myplay.returnRandomValue();
	        	displayString = displayString + "you got"+s + "\n";
	        	sum1 = sum1+s;
	        }
	        
	     // sum of the dice for the opoenet machine
	        for( int j = 0; j < Length; j++ )
			{
				s = Myplay.returnRandomValue();
				sum2 = sum2 + s;
			}
	        
	        if(sum1 > sum2)
	        {
	        	displayString = "Congradualation you won";
	        }
	        else if(sum2 > sum1)
	        		displayString = "Oh sorry you lost";
	        else if(sum2==sum1)
	        	displayString = "Equal";
	        	
	        showDialog(0);
			}
		});
		
		repeatBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				
				btnMusic.start();
				finish();
						
			}
		});
		
		startAsNewUserBtn.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				btnMusic.start();
				startActivity(new Intent("com.mitiku.dicegame.INFOSCREEN"));
			}
		});
		ExitBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				btnMusic.start();
				finish();
				
			}
		});
	}
	
	protected Dialog onCreateDialog(int id)
    {
    	switch (id) 
    	{
		case 0:
			return new AlertDialog.Builder(this)
				.setIcon(R.raw.dice6)
				.setTitle(("\t\t\t"+playName+" Here is ur result"))
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
						
						showDialog(1);
						
					}
				})
				.setMessage(displayString)
				.create();
		case 1:
			return new AlertDialog.Builder(this)
				.setIcon(R.raw.dice6)
				.setTitle("Info")
				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
						((TextView)findViewById(R.id.textView1)).setText("What You want to do?");
						PlayBtn.setVisibility(GONE);
						repeatBtn.setVisibility(VISIBLE);
						startAsNewUserBtn.setVisibility(VISIBLE);
						
					}
				})
				.setMessage("Thank you for the playing the game!")
				.create();
			
		}
    	return null;
    }


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		btnMusic.pause();

		
		super.onPause();
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		btnMusic.start();
		super.onRestart();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		btnMusic.stop();

		super.onDestroy();
	}
	

}
