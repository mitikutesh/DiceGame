package com.mitiku.dicegame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Info extends Activity
{
	Button		infoOk,contBtn;
	TextView	urName;
	EditText	diceLeng;
	MediaPlayer btnMusic;
	
	String 		name = null;
	String 		leng = null;
		
	final int request_Code	= 1;
	final int INVISIBLE		= 4;
	final int VISIBLE 		= 0;
	final int GONE			= 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		inti();
		
		infoOk.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnMusic.start();
				startActivityForResult(new Intent("com.mitiku.dicegame.USERSCREEN"), request_Code);
				
			}
		});
		
		contBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				btnMusic.start();
				Intent i = new Intent("com.mitiku.dicegame.LOADERSCREEN");
				Bundle extras = new Bundle();
				extras.putString("Name",name);
				extras.putString("Length",diceLeng.getText().toString());
				i.putExtras(extras);
				startActivityForResult(i, request_Code);
			}
		});
	}
	
	private void inti()
	{
		infoOk		= (Button)findViewById(R.id.infoOk);
		urName		= (TextView)findViewById(R.id.yourName);
		contBtn		= (Button)findViewById(R.id.infoCont);
		diceLeng	=(EditText)findViewById(R.id.infoLengofDice);
		btnMusic	=MediaPlayer.create(this, R.raw.btnclick);
		
		
		contBtn.setVisibility(GONE);
		diceLeng.setVisibility(GONE);
		String s = "This is a game a dice game " +
				"which is played between a you and a machine" +
				" first you will select how many dice you want to through" +
				"Each dice has a face 1 to 6 and then add up all the dice that you get" +
				"if you have more that a machine you will be a winner" +
				"else either you will lose or and become equal!";
		
		urName.setText(s);
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intr) 
	{
		if(request_Code == requestCode)
		{
			if(resultCode == RESULT_OK)
			{
				name = intr.getData().toString();
				String str = "Hello " +name + " Welcome to this Dice game" +
						"\nPlease put how many dice you want through? ";
										
				urName.setText(str);
				infoOk.setVisibility(GONE);
				contBtn.setVisibility(VISIBLE);
				diceLeng.setVisibility(VISIBLE);
				
			}
		}
		
	}

	
		

}
